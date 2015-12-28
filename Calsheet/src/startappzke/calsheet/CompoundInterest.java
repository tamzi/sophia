package startappzke.calsheet;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CompoundInterest extends Activity{
	private EditText input1;
	private EditText input2;
	private EditText input3;
	private EditText input4;
	private EditText output1;
	private EditText solution;
	private EditText solution1;
	private CompoundInterest mContext;
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

					
					
					double result1 = new Double(input2.getText().toString())
					                 /100;
					
					double t = new Double(input3.getText().toString())
	                 /12;
					
					double result2 =  Math.pow((result1+1),t);
					
					double result = new Double(input1.getText().toString())
					* new Double(result2);
					
					double r = new Double(result)
							- new Double(input1.getText().toString());
					
					
					
					solution.setText("Total interest "+ Double.toString(r));
					solution1.setText("Total Amount "+Double.toString(result));
					
					
				}

			}

		});

			
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this, MainActivity.class));
			CompoundInterest.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
		}

}
