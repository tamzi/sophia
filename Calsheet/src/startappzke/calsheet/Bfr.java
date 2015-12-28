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

public class Bfr extends Activity{
	private EditText input1;
	private EditText input2;
	private EditText solution;
	private EditText solution1;
	private EditText solution2;
	private Bfr mContext;
	public static final int mSuccessThreshold = 1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.bfr);
		
		input1 = (EditText) findViewById(R.id.input1);
		input2 = (EditText) findViewById(R.id.input2);
		solution = (EditText) findViewById(R.id.solution);
		solution1 = (EditText) findViewById(R.id.solution1);
		solution2 = (EditText) findViewById(R.id.solution2);

		// We create an OnClick Event in each button.

		Button Calculate = (Button) findViewById(R.id.button1);
		
		
		
		Calculate.setOnClickListener(new OnClickListener() {

			private AlertDialog show;
   
			public void onClick(View arg0) {

				if ((input1.getText().length() == 0)
						|| (input1.getText().toString() == "")
						|| (input2.getText().length() ==0)
						|| (input2.getText().toString() == ""))
					    {

					show = new AlertDialog.Builder(mContext).setTitle("StartappzKE!! Error")
							.setMessage("Nothing to Calculate")
							.setPositiveButton("OK", null).show();


				} else {

					double result = new Double(input1.getText().toString())
							+new Double(input2.getText().toString());
					
					double results = new Double(input1.getText().toString())
					                 /new Double(input2.getText().toString());
					
					double result2 = new Double(results)
		                          *100;
					
					
					double results1 = new Double(input1.getText().toString())
                                     /new Double(result) ;
					
					
					double result1 = new Double(results1)
                                     *100 ;
					
				
					
					
					solution.setText("Selling Price = "+Double.toString(result));
					solution1.setText("Mark-up = "+Double.toString(result2)+"%");
					solution2.setText("Margin = "+Double.toString(result1)+"%");
					
					
				}

			}

		});

			
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this, MainActivity.class));
			Bfr.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
		}

}

