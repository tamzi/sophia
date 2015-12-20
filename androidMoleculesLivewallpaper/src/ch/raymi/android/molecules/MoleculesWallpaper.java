package ch.raymi.android.molecules;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import javax.vecmath.Vector3f;

import org.jmol.adapter.smarter.SmarterJmolAdapter;
import org.jmol.api.JmolViewer;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MoleculesWallpaper extends WallpaperService {

	private final Handler mHandler = new Handler();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public Engine onCreateEngine() {
		//android.os.Debug.waitForDebugger();
		return new MoleculeEngine();

	}

	class MoleculeEngine extends Engine implements SharedPreferences.OnSharedPreferenceChangeListener {
		private class ActiveMolecule {
			private ActiveMolecule(Molecule molecule) {
				mMolecule = molecule;
				mStartTime = System.currentTimeMillis();
			}
			private final Molecule mMolecule;
			private final long mStartTime;
		}
		private final long mMinMoleculeActiveTime = 5 * 60 * 1000;
		private final Paint mPaint = new Paint();
		private float mTouchX = -1;
		private float mTouchY = -1;
		private Paint mOverlayPaint = null;
		private final JmolViewer mViewer;
		private boolean mRandom = true;
		private ActiveMolecule mActiveMolecule = null;
		private String mInitScript = "spin y";
		private final Random mRandomizer = new Random();
		private boolean mShowNameOnTouch = true;
		private boolean mAutoRotated = false;


		private final LinkedHashSet<WallpaperElement> mElements = new LinkedHashSet<WallpaperElement>();

		private final Runnable mDrawCube = new Runnable() {
			@Override
			public void run() {
				drawFrame();
			}
		};
		private boolean mVisible;

		MoleculeEngine() {
			mViewer = JmolViewer.allocateViewer(new Object(), new SmarterJmolAdapter(), null, null, null, "-NOTmultitouch-tab platform=" + AndroidPlatform.class.getName(), null);
			SharedPreferences prefs = MoleculesWallpaper.this.getSharedPreferences(MoleculesWallpaperPreferences.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
			onSharedPreferenceChanged(prefs, null);
			prefs.registerOnSharedPreferenceChangeListener(this);
		}

		private void loadMolecule(Molecule molecule) {
			try {
				mViewer.haltScriptExecution();
				Reader reader = new InputStreamReader(getResources().getAssets().open(molecule.getFilename()));
				mViewer.openReader(molecule.getName(), molecule.getName(), reader);
				mViewer.script(mInitScript);
				this.mActiveMolecule = new ActiveMolecule(molecule);
				synchronized(this) {
					mAutoRotated = false;
				}
			} catch (IOException ioex) {

			}
		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);

			// By default we don't get touch events, so enable them.
			setTouchEventsEnabled(true);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			mHandler.removeCallbacks(mDrawCube);
			SharedPreferences prefs = MoleculesWallpaper.this.getSharedPreferences(MoleculesWallpaperPreferences.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
			prefs.unregisterOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			mVisible = visible;
			if (visible) {
				drawFrame();
			} else {
				if (mRandom && mMinMoleculeActiveTime < (System.currentTimeMillis() - mActiveMolecule.mStartTime)) {
					loadMolecule(getRandomMolecule());
				}
				mHandler.removeCallbacks(mDrawCube);
			}
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);
			drawFrame();
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			mVisible = false;
			mHandler.removeCallbacks(mDrawCube);
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xStep, float yStep, int xPixels, int yPixels) {
			drawFrame();
		}

		/*
		 * Store the position of the touch event so we can use it for drawing later
		 */
		@Override
		public void onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				mTouchX = event.getX();
				mTouchY = event.getY();
			} else {
				mTouchX = -1;
				mTouchY = -1;
			}
			if (mShowNameOnTouch) {
				mElements.add(new MoleculeName(mActiveMolecule.mMolecule.getName(), 5000));
			}
			super.onTouchEvent(event);
		}
		void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			Canvas c = null;
			try {
				c = holder.lockCanvas();
				if (c != null && mViewer != null) {
					Vector3f boundBoxCornerVector = mViewer.getBoundBoxCornerVector();
					synchronized(this) {
						if (c.getWidth() < c.getHeight()) {
							if (!mAutoRotated && (boundBoxCornerVector.x > boundBoxCornerVector.y)) {
								mViewer.script("reset; rotate z 90; spin on;");
								mAutoRotated = true;
							}
						} else {
							if (mAutoRotated) {
								mViewer.script("reset; spin on;");
								mAutoRotated = false;
							}
						}
					}

					mViewer.renderScreenImage(c, null, c.getWidth(), c.getHeight());
					for (Iterator<WallpaperElement> it = mElements.iterator(); it.hasNext(); ) {
						WallpaperElement el = it.next();
						boolean remove = el.show(c, mViewer);
						if (remove) {
							it.remove();
						}
					}
					if (mOverlayPaint != null) {
						c.drawRect(0, 0, c.getWidth(), c.getHeight(), mOverlayPaint);
					}
				}
			} finally {
				if (c != null) holder.unlockCanvasAndPost(c);
			}

			// Reschedule the next redraw
			mHandler.removeCallbacks(mDrawCube);
			if (mVisible) {
				mHandler.postDelayed(mDrawCube, 20);
			}
		}

		void drawTouchPoint(Canvas c) {
			if (mTouchX >=0 && mTouchY >= 0) {
				c.drawCircle(mTouchX, mTouchY, 80, mPaint);
			}
		}

		private Molecule getRandomMolecule() {
			List<Molecule> molecules = Molecule.Molecules.getMolecules();
			int index = mRandomizer.nextInt(molecules.size());
			return molecules.get(index);
		}

		@Override
		public void onSharedPreferenceChanged(SharedPreferences prefs,
				String key) {
			String color = MoleculesWallpaperPreferences.getOverlayColor(prefs);
			if (color != null && !color.equals("")) {
				mOverlayPaint = new Paint();
				mOverlayPaint.setColor(Color.parseColor(color));
			} else {
				mOverlayPaint = null;
			}
			mRandom = MoleculesWallpaperPreferences.isRandomMolecule(prefs);
			Molecule molecule = null;
			if (mRandom) {
				molecule = getRandomMolecule();
			} else {
				String moleculeName = MoleculesWallpaperPreferences.getMoleculeName(prefs);
				molecule = Molecule.Molecules.getMolecule(moleculeName);
				if (molecule == null) {
					molecule = getRandomMolecule();
				}
			}
			String style = MoleculesWallpaperPreferences.getStyle(prefs);
			mInitScript = "";
			if (style.equals("spacefilling")) {
				mInitScript =  "spacefill 100%;";
			} else if (style.equals("ballnstick")) {
				mInitScript =  "wireframe 0.15; spacefill 20%;";
			} else if (style.equals("stickmodel")) {
				mInitScript = "wireframe 0.3; spacefill 0;";
			} else if (style.equals("wireframe")) {
				mInitScript = "wireframe 0.01; spacefill 0;";
			}
			mInitScript += "set spinFps 20;";
			boolean spinx = MoleculesWallpaperPreferences.isSpinX(prefs);
			mInitScript += "set spin x " + (spinx ? "4" : "0") + ";";
			boolean spiny = MoleculesWallpaperPreferences.isSpinY(prefs);
			mInitScript += "set spin y " + (spiny ? "4" : "0") + ";";
			boolean spinz = MoleculesWallpaperPreferences.isSpinZ(prefs);
			mInitScript += "set spin z " + (spinz ? "4" : "0") + ";";
			if (spinx || spiny || spinz) {
				mInitScript += "spin on;";
			}
			mShowNameOnTouch = MoleculesWallpaperPreferences.isShowNameOnTouch(prefs);
			loadMolecule(molecule);
		}

	}
}