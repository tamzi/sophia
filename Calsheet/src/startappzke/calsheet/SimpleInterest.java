package startappzke.calsheet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SimpleInterest extends Activity{
	private EditText input1;
	private EditText input2;
	private EditText input3;
	private EditText input4;
	private EditText output1;
	private EditText solution;
	private EditText solution1;
	private SimpleInterest mContext;
	public static final int mSuccessThreshold = 1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.sl);
		
		input1 = (EditText) findViewById(R.id.input1);
		input2 = (EditText) findViewById(R.id.input2);
		input3 = (EditText) findViewById(R.id.input3);
		solution = (EditText) findViewById(R.id.solution);
		solution1 = (EditText) findViewById(R.id.solution1);

		// We create an OnClick Event in each button.

		Button Calculate = (Button) findViewById(R.id.button1);
		
		
		
		Calculate.setOnClickListener(new OnClickListener() {

			private AlertDialog show;
   
			public void onClick(View arg0) {

				if ((input1.getText().length() == 0)
						|| (input1.getText().toString() == "")
						|| (input2.getText().length() ==0)
						|| (input2.getText().toString() == "")
						|| (input3.getText().length() ==0)
						|| (input3.getText().toString() == ""))
					    {

					show = new AlertDialog.Builder(mContext).setTitle("StartappzKE!! Error")
							.setMessage("Nothing to Calculate")
							.setPositiveButton("OK", null).show();


				} else {

					double result = new Double(input1.getText().toString())
							*new Double(input2.getText().toString())
					        * new Double(input3.getText().toString());
					
					double result1 = new Double(result/1200)
					                +new Double(input1.getText().toString());
					
					
					solution.setText("Total interest "+Double.toString(result/1200));
					solution1.setText("Total Amount "+Double.toString(result1));
					
					
				}

			}

		});

			
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this, MainActivity.class));
			SimpleInterest.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
		}

}