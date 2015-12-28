package startappzke.calsheet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BalanceSheet1 extends Activity {
	
	
	private TextView text1;
	private TextView text2;
	private TextView text3;
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bs2);
        
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        
        
        text1.setText(startappzke.calsheet.BalanceSheet.input1.getText().toString());
        text2.setText("Location: " + startappzke.calsheet.BalanceSheet.input3.getText().toString());
        text3.setText("Date: " + startappzke.calsheet.BalanceSheet.input2.getText().toString());
        
        
    }
}
