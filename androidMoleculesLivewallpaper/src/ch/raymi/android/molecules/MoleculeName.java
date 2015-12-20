package ch.raymi.android.molecules;

import org.jmol.api.JmolViewer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public final class MoleculeName extends WallpaperElement {
	private final String mName;
	private final Paint mPaint;
	private final float mTextHeight;
	private final float mTextWidth;

	public MoleculeName(String name, long duration) {
		super("MoleculeName", duration);
		this.mName = name;
		this.mPaint = new Paint();
		this.mTextHeight = 36;
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(mTextHeight);
		mPaint.setAntiAlias(true);
		this.mTextWidth = mPaint.measureText(mName);
	}

	@Override
	public void draw(Canvas c, JmolViewer v) {
		c.drawText(mName, (c.getWidth() - this.mTextWidth) / 2, (c.getHeight() - this.mTextHeight) / 2, mPaint);
	}

}
