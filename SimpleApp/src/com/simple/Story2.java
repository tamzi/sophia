package com.simple;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class Story2 extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		TextView hym1 = new TextView(this);
		
		hym1.setTextColor(Color.GRAY);
		hym1.setScrollContainer(true);
		hym1.setTextSize(15);
	
		TextFilesReader tf = new TextFilesReader(this);
		String getHym1 = null;
		try {
			getHym1 = tf.readTxt("story2.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ScrollView sv = new ScrollView(this);
	     sv.addView(hym1);
		hym1.setText(getHym1);
		setContentView(sv);
		
		
}
}