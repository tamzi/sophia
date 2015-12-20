package ch.raymi.android.molecules;

import javax.vecmath.Point3f;

import org.jmol.api.ApiPlatform;
import org.jmol.api.JmolPopupInterface;
import org.jmol.api.JmolViewer;
import org.jmol.g3d.Font3D;
import org.jmol.viewer.ActionManager;
import org.jmol.viewer.Viewer;

import android.graphics.Canvas;

public class AndroidPlatform implements ApiPlatform {

	@Override
	public Object allocateRgbImage(int windowWidth, int windowHeight,
			int[] pBuffer, int windowSize,
			boolean backgroundTransparent) {
		//return Image.allocateRgbImage(windowWidth, windowHeight, pBuffer, windowSize, backgroundTransparent);
		return pBuffer;
	}

	@Override
	public Object createImage(Object data) {
		return null;
		//return Image.createImage(data);
	}

	@Override
	public void disposeGraphics(Object graphicForText) {
		//Image.disposeGraphics(graphicForText);
	}

	@Override
	public void drawImage(Object graphic, Object imgInts, int x, int y, int width, int height) {
		Canvas canvas = ((Canvas) graphic);
		canvas.drawBitmap((int[]) imgInts, 0, width, x, y, width, height, true, null);
		//Image.drawImage(graphic, img, x, y, width, height);
	}

	@Override
	public int[] grabPixels(Object imageobj, int width, int height) {
		//return Image.grabPixels(imageobj, width, height);
		return null;
	}

	@Override
	public int[] drawImageToBuffer(Object gOffscreen, Object imageOffscreen,
			Object imageobj, int width, int height, int bgcolor) {
		//return Image.drawImageToBuffer(gOffscreen, imageOffscreen, imageobj, width, height, bgcolor);
		return null;
	}

	@Override
	public int[] getTextPixels(String text, Font3D font3d, Object gObj,
			Object image, int width, int height, int ascent) {
		//return Image.getTextPixels(text, font3d, gObj, image, width, height, ascent);
		return null;
	}

	@Override
	public void flushImage(Object imagePixelBuffer) {
		//Image.flush(imagePixelBuffer);
	}

	@Override
	public Object getGraphics(Object image) {
		return Image.getGraphics(image);
	}

	@Override
	public int getImageHeight(Object image) {
		return Image.getHeight(image);
	}

	@Override
	public int getImageWidth(Object image) {
		return Image.getWidth(image);
	}

	@Override
	public Object getJpgImage(Viewer viewer, int quality, String comment) {
		//return Image.getJpgImage(this, viewer, quality, comment);
		return null;
	}

	@Override
	public Object getStaticGraphics(Object image, boolean backgroundTransparent) {
		return getGraphics(image);
		//return Image.getStaticGraphics(image, backgroundTransparent);
	}

	@Override
	public Object newBufferedImage(Object image, int w, int h) {
		return Image.newBufferedImage(image, w, h);
	}

	@Override
	public Object newBufferedRgbImage(int w, int h) {
		return Image.newBufferedImage(w, h);
	}

	@Override
	public boolean waitForDisplay(Object display, Object image) throws InterruptedException {
		//Image.waitForDisplay(display, image);
		return true;
	}


	@Override
	public void clearMouse() {
		// TODO Auto-generated method stub

	}

	@Override
	public void convertPointFromScreen(Object arg0, Point3f arg1) {
		// TODO Auto-generated method stub

	}


	@Override
	public void disposeMouse() {
		// TODO Auto-generated method stub

	}


	@Override
	public int fontStringWidth(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFontAscent(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFontDescent(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getFontMetrics(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getFullScreenDimensions(Object arg0, int[] arg1) {
		// TODO Auto-generated method stub

	}



	@Override
	public Object getJsObjectInfo(Object arg0, String arg1, Object[] arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JmolPopupInterface getMenuPopup(Viewer arg0, String arg1, char arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getMouseManager(Viewer arg0, ActionManager arg1) {
		// TODO Auto-generated method stub

	}



	@Override
	public boolean handleOldJvm10Event(int arg0, int arg1, int arg2, int arg3,
			long arg4) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFocus(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Object newFont(String arg0, boolean arg1, boolean arg2, float arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String prompt(String arg0, String arg1, String[] arg2, boolean arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renderScreenImage(JmolViewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void repaint(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestFocusInWindow(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCursor(int arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTransparentCursor(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setViewer(JmolViewer arg0, Object arg1) {
		// TODO Auto-generated method stub

	}


}
