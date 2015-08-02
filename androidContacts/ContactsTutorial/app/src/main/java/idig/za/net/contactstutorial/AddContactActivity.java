package idig.za.net.contactstutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by clive on 25-Nov-14.
 * www.101apps.co.za
 */
public class AddContactActivity extends MyMenuActivityClass implements TextView.OnEditorActionListener {
//    private static final String TAG = "contacts";
    private String sex = "female";
    private String name;
    private String email;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        editTextName.setOnEditorActionListener(this);
        editTextEmail.setOnEditorActionListener(this);
        editTextPhone.setOnEditorActionListener(this);
    }

    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.radioButtonMale:
//                Log.i(TAG, "Male radio button checked");
                sex = "male";
                break;
            case R.id.radioButtonFemale:
//                Log.i(TAG, "Female radio button checked");
                sex = "female";
                break;
        }
    }

    public void saveContact(View view) {
        Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();
//        Log.i(TAG, "Saved contact: " + name + "|" + email + "|" + phone + "|" + sex);
        Intent intent = new Intent(this, AppMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        boolean handled = false;
        if (actionId== EditorInfo.IME_ACTION_NEXT) {
            if (v.getId()==R.id.editTextName) {
                name = v.getText().toString();
            } else if (v.getId()==R.id.editTextEmail) {
                email = v.getText().toString();
            }
        } else if (actionId==EditorInfo.IME_ACTION_DONE) {
//            Log.i(TAG, "action done");
            phone = v.getText().toString();
            handled = true;

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        return handled;
    }
}
