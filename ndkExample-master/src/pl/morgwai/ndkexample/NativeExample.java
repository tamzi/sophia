package pl.morgwai.ndkexample;

import android.util.Log;

public class NativeExample {

	public static final String LOG_TAG = NativeExample.class.getSimpleName();
	private static boolean libLoaded = false;

	public NativeExample() {
		synchronized (NativeExample.class) {
			if (!libLoaded) {
				System.loadLibrary("example");
				libLoaded = true;
				Log.i(LOG_TAG, "lib loaded");
			} else {
				Log.v(LOG_TAG, "not loading lib: already loaded before");
			}
		}
	}

	public native long getExampleValue();
}
