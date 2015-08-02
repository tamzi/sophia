package idig.za.net.contactstutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by clive on 25-Nov-14.
 * www.101apps.co.za
 */
public class AppMenuActivity extends MyMenuActivityClass implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        Button buttonAddContacts = (Button) findViewById(R.id.buttonAddContacts);
        buttonAddContacts.setOnClickListener(this);
        Button buttonDeleteContacts = (Button) findViewById(R.id.buttonDeleteContacts);
        buttonDeleteContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppMenuActivity.this, ListContactsActivity.class);
                intent.putExtra("showButton", true);
                startActivity(intent);
            }
        });
    }

    public void viewContacts(View view) {
        Intent intent = new Intent(this, ListContactsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View buttonView) {
        int buttonId = buttonView.getId();
        if (buttonId == R.id.buttonAddContacts) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        }
    }
}
