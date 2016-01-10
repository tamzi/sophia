package com.simple;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash1 extends Activity {
	private int displayTime = 4000;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Thread splashThread = new Thread(){
			int wait = 0;
			
			@Override
			public void run(){
				try{
					super.run();
					
					while(wait<displayTime){
						sleep(100);
						wait += 100;
					}
				}catch(Exception e){}
				finally{
					//Intent intent = new Intent(splashActivity.this,toutMain.class);
					Intent intent = new Intent(Splash1.this,OuterMenu.class);
					startActivity(intent);
					finish();
			
				}
				
			}
		};
		splashThread.start();
    }
}
