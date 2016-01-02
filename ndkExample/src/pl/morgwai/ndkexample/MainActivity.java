package pl.morgwai.ndkexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void getValue(View view) {
		NativeExample nativeExample = new NativeExample();
		long nativeValue = nativeExample.getExampleValue();
		TextView mainText = (TextView) findViewById(R.id.mainText);
		mainText.setText("value: " + nativeValue);
	}
}
