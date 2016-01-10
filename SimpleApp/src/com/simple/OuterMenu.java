package com.simple;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class OuterMenu extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        
   
    }
    //method to handle button clicks
    public void onButtonClicker(View v){
    	
    	switch(v.getId()){//get button id that was clicked
    	//make decision- case 1
    	case R.id.btnbiblestories://create an intent
    	Intent signup = new Intent(OuterMenu.this,Hym1.class);
    	startActivity(signup);	
        break;
    	
    	//case 2
    	case R.id.btnnewtest://create an intent
        	Intent signin = new Intent(OuterMenu.this,MainMenu1.class);
        	startActivity(signin);	
            break;
    
    	case R.id.btnhelp://create an intent with tabs called campus
        	Intent ca = new Intent(OuterMenu.this,Help.class);
        	startActivity(ca);	
            break;
 	
	    case R.id.btnabout://create an intent with tabs called campus
    	Intent proverbs = new Intent(OuterMenu.this,About.class);
    	startActivity(proverbs);	
        break;
	   
    	}
	}
    	//do for the other buttons
    	
    
	


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


