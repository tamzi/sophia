package com.simple;


import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Help extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//set linear layout    * please note that this activity does not use an xml
	     LinearLayout mainLineLayout = new LinearLayout(this);
         mainLineLayout.setOrientation(LinearLayout.VERTICAL);
         //layout orientation and width and height
         mainLineLayout.setLayoutParams(new LinearLayout.LayoutParams(
                         LinearLayout.LayoutParams.FILL_PARENT,
                         LinearLayout.LayoutParams.FILL_PARENT, 1));
         mainLineLayout.setBackgroundColor(Color.GRAY);
         //create a TextView
		TextView hym1 = new TextView(this);
		//set text color
		hym1.setTextColor(Color.BLACK);
		
	//set text size
		hym1.setTextSize(15);
		//access the 'TextFilesReader' class to help us read a specified text file
		TextFilesReader tf = new TextFilesReader(this);
		String getHym1 = null;
		try {
			//read the textfile using the 'TextFilesReader' class
			getHym1 = tf.readTxt("help.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create a scroll view
		ScrollView sv = new ScrollView(this);
		//add textview to scroll view
		sv.addView(hym1);
		//set text to text view - 'getHym1' is a string
		hym1.setText(getHym1);
		mainLineLayout.addView(sv);//add scroll view to layout
		setContentView(mainLineLayout);//set the layout - will be shown in the activity
		
		
}
}




