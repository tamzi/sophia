package com.peterleow.androidstorage;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    public void getSharedPreferences(View view) {
        Intent intent = new Intent(getApplicationContext(), SharedPreferencesActivity.class);
        startActivity(intent);
    }
    */
    /*
    public void getInternalStorage(View view) {
        Intent intent = new Intent(getApplicationContext(), InternalStorageActivity.class);
        startActivity(intent);
    }
        */
    /*
    public void getExternalStorage(View view) {
        Intent intent = new Intent(getApplicationContext(), ExternalStorageActivity.class);
        startActivity(intent);
    }
        */
    /*
    public void getSqlite(View view) {
        Intent intent = new Intent(getApplicationContext(), SQLiteActivity.class);
        startActivity(intent);
    }
    */
    /*
    public void getWebservice(View view) {
        Intent intent = new Intent(getApplicationContext(), WebserviceActivity.class);
        startActivity(intent);
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
