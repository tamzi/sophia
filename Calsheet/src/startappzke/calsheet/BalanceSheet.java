package startappzke.calsheet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BalanceSheet extends Activity {
	
    static EditText input1;
	static EditText input2;
	static EditText input3;
	
	private Button b1;
	
    public void onCreate(Bundle savedInstanceState) {
    	final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bs1);
        
        input1 = (EditText) findViewById(R.id.editText1);
        input2 = (EditText) findViewById(R.id.editText2);
        input3 = (EditText) findViewById(R.id.editText3);
        
        b1 = (Button) findViewById(R.id.button1);
        
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, BalanceSheet1.class);
				startActivity(intent);
				
	    
			}

		});
        
    }
}