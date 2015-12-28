package startappzke.calsheet;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, Finance.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Finance")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_finance_config))
			.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, BalanceSheet.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("BalanceSheet")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_balancesheet_config))
			.setContent(intentApple);
		
		// Windows tab
		Intent intentWindows = new Intent().setClass(this, Reciepts.class);
		TabSpec tabSpecWindows = tabHost
			.newTabSpec("Reciepts")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_reciept_config))
			.setContent(intentWindows);
		
		
	
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecWindows);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}
	
}
