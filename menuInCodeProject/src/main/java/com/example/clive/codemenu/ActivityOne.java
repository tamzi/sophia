package com.example.clive.codemenu;

import android.os.Bundle;
import android.view.Menu;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class ActivityOne extends MenuClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.removeItem(1);
        return true;
    }
}
