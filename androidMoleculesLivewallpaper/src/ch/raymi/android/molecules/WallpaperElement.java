package ch.raymi.android.molecules;

import org.jmol.api.JmolViewer;

import android.graphics.Canvas;

public abstract class WallpaperElement {
	private final long mStarted;
	private final long mDuration;
	private final String mId;

	public WallpaperElement(String id, long duration) {
		mStarted = System.currentTimeMillis();
		mDuration = duration;
		mId = id;
	}

	protected long shownSince() {
		long t = System.currentTimeMillis() - mStarted;
		return t > 0 ? t : 0;
	}

	public boolean show(Canvas c, JmolViewer v) {
		draw(c, v);
		return shownSince() > mDuration;
	}

	protected abstract void draw(Canvas c, JmolViewer v);

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof WallpaperElement)) {
			return false;
		}
		return ((WallpaperElement)o).mId.equals(this.mId);
	}

	@Override
	public int hashCode() {
		return this.mId.hashCode();
	}
}
