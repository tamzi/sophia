package idig.za.net.contactstutorial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by clive on 24-Nov-14.
 * www.101apps.co.za
 */
public class MainActivity extends ActionBarActivity {
//    private static final String TAG = "contacts";
    private AlertDialog myAlertDialog;
    private TextView textView;
    private ImageView imageView;

    private String MY_PREFERENCE_FILE = "MySharedPreferenceFile";
    private SharedPreferences mySharedPreferences;
    private SharedPreferences.Editor mySharedPreferencesEditor;
    private boolean isDialogShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String message = "in onCreate";
//        Log.i(TAG, message);
        textView = (TextView) findViewById(R.id.textViewMainActivity);
        imageView = (ImageView) findViewById(R.id.imageView);
        mySharedPreferences = getSharedPreferences(MY_PREFERENCE_FILE, MODE_PRIVATE);
        boolean showImage = mySharedPreferences.getBoolean("showImage", true);
        if (!showImage) {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            Bitmap rawBitmapImage = getRawImage();
            imageView.setImageBitmap(rawBitmapImage);
            imageView.setVisibility(View.VISIBLE);
        }
        myAlertDialog = buildAlertDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String message = "in onPause";
//        Log.i(TAG, message);
        if (myAlertDialog.isShowing()) {
            myAlertDialog.dismiss();
            isDialogShowing = true;
        } else {
            isDialogShowing = false;
        }
        mySharedPreferencesEditor = mySharedPreferences.edit();
        mySharedPreferencesEditor.putBoolean("isDialogShowing", isDialogShowing);
        mySharedPreferencesEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String message = "in onResume";
//        Log.i(TAG, message);
        mySharedPreferences = getSharedPreferences(MY_PREFERENCE_FILE, MODE_PRIVATE);
        boolean isDialogShowing = mySharedPreferences.getBoolean("isDialogShowing", false);
        if (isDialogShowing) {
            myAlertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAddContact:
                Toast.makeText(this, "Add Contact", Toast.LENGTH_SHORT).show();
                Intent intentAddContact = new Intent(this, AddContactActivity.class);
                startActivity(intentAddContact);
                return true;
            case R.id.menuDeleteContact:
                Toast.makeText(this, "Delete Contact", Toast.LENGTH_SHORT).show();
                Intent intentDeleteContact = new Intent(this, ListContactsActivity.class);
                intentDeleteContact.putExtra("showButton", true);
                startActivity(intentDeleteContact);
                return true;
            case R.id.menuViewContact:
                Toast.makeText(this, "View Contact", Toast.LENGTH_SHORT).show();
                Intent intentViewContact = new Intent(this, ListContactsActivity.class);
                startActivity(intentViewContact);
                return true;
            case R.id.menuDialog:
//                Log.i(TAG, "Dialog menu item pressed");
                myAlertDialog.show();
                return true;
            case R.id.menuMenu:
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
                Intent intentGoMenuActivity = new Intent(this, AppMenuActivity.class);
                startActivity(intentGoMenuActivity);
                return true;
            case R.id.menuBrowser:
                Uri webAddress = Uri.parse("http://www.101apps.co.za");
                Intent intentBrowser = new Intent(Intent.ACTION_VIEW, webAddress);
                if (intentBrowser.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentBrowser);
                } else {
                    Toast.makeText(this, "No Browser found", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.menuEbook:
                Intent intent = new Intent(this, NewBookActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog buildAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.dialogMessage))
                .setTitle(getResources().getString(R.string.dialogTitle));

        builder.setPositiveButton(getResources()
                .getString(R.string.dialogPositiveLabel)
                , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.i(TAG, "Show image");
                updateImageView(true);
            }
        });

        builder.setNeutralButton(getResources()
                .getString(R.string.dialogNeutralLabel)
                , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.i(TAG, "Do nothing");
            }
        });

        builder.setNegativeButton(getResources()
                .getString(R.string.dialogNegativeLabel)
                , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.i(TAG, "Show menu");
                updateImageView(false);
            }
        });
        return builder.create();
    }

    private void updateImageView(boolean isVisible) {
        if (isVisible) {
            textView.setVisibility(View.GONE);
            Bitmap rawBitmapImage = getRawImage();
            imageView.setImageBitmap(rawBitmapImage);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
        mySharedPreferencesEditor = mySharedPreferences.edit();
        mySharedPreferencesEditor.putBoolean("showImage", isVisible);
        mySharedPreferencesEditor.apply();
    }

    private Bitmap getRawImage() {
        InputStream inputStream = getResources().openRawResource(R.raw.clock);
        return BitmapFactory.decodeStream(inputStream);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String message = "in onStart";
//        Log.i(TAG, message);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String message = "in onRestart";
//        Log.i(TAG, message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String message = "in onStop";
//        Log.i(TAG, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String message = "in onDestroy";
//        Log.i(TAG, message);
    }
}
