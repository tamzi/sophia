package com.example.clive.codemenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class MenuClass extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 2, R.string.startActivityOne);
        menu.add(0, 2, 3, R.string.startActivityTwo);
        menu.add(0, 3, 1, R.string.go_home);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intentGoOne = new Intent(this, ActivityOne.class);
                startActivity(intentGoOne);
                return true;
            case 2:
                Intent intentGoTwo = new Intent(this, ActivityTwo.class);
                startActivity(intentGoTwo);
                return true;
            case 3:
                Intent intentGoHome = new Intent(this, MainActivity.class);
                startActivity(intentGoHome);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
