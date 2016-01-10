package com.simple;


import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainMenu1 extends TabActivity implements OnTabChangeListener{

	TabHost tabHost;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		/* TabHost will have Tabs */
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.setOnTabChangedListener(this);
        
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can  setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        // Initialize a TabSpec for each tab 
        TabSpec tab1 = tabHost.newTabSpec("tid21");
        TabSpec tab2 = tabHost.newTabSpec("tid21");
    
        /* TabSpec setIndicator() is used to set name for the tab. */
        //get drawables for each tab  as well
        tab1.setIndicator("Story1", getResources().getDrawable(android.R.drawable.ic_input_add));
        tab2.setIndicator("Story2", getResources().getDrawable(android.R.drawable.ic_menu_share));
    
        /* TabSpec setContent() is used to set content for a particular tab. */
        tab1.setContent(new Intent(this,Story1.class));
        tab2.setContent(new Intent(this,Story2.class));
       
        /* Add tabSpec to the TabHost to display. */
        
        
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
      
        
        
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#C58917"));
        }
        
        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));
	}
	
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#C58917"));
        } 
				
		tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#ffffff"));
		
	}

}

