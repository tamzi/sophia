package com.simple;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Hym1 extends Activity{
	String [] splitted;
	int z;
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
			getHym1 = tf.readTxt("hym1.txt");
			
			splitted = getHym1.split("#");
			
			
			// random = (int)Math.ceil(Math.random()*3);
			 System.loadLibrary("first");

		        z = Compute(0, 3);
		        Toast.makeText(
		        		getApplicationContext(),
		        		"hello how is \n Android!!"+z,
		        		Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ScrollView sv = new ScrollView(this);
	     sv.addView(hym1);
		hym1.setText(splitted[z]);
		setContentView(sv);
		
		
}
	
	public native int Compute(int  x, int  y);
}