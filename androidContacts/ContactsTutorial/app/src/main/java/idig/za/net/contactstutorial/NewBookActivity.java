package idig.za.net.contactstutorial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by clive on 25-Nov-14.
 * www.101apps.co.za
 */
public class NewBookActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        Button buttonEbook = (Button) findViewById(R.id.buttonEbook);
        buttonEbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webAddress = Uri.parse("http://www.101apps.co.za" +
                        "/index.php/landingpage/ebook-learning-android-using-android-studio.html");
                Intent intentBrowser = new Intent(Intent.ACTION_VIEW, webAddress);
                if (intentBrowser.resolveActivity(getPackageManager()) != null) {
                    startActivity(intentBrowser);
                }else {
                    Toast.makeText(NewBookActivity.this, "No Browser found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
