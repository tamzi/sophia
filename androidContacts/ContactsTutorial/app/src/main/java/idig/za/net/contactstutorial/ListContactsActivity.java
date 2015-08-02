package idig.za.net.contactstutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by clive on 25-Nov-14.
 * www.101apps.co.za
 */
public class ListContactsActivity extends ListActivity {
    private boolean deleteContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleteContact = getIntent().getBooleanExtra("showButton", false);
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.name_array));
        setListAdapter(myArrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DisplayContactsActivity.class);
        if (deleteContact) {
            intent.putExtra("showButton", true);
        }
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
