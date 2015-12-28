package startappzke.calsheet;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Amortization extends Activity {
    private EditText mLoanAmount, mInterestRate, mLoanPeriod;
    private TextView mMontlyPaymentResult, mTotalPaymentsResult;
    private AlertDialog show;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amortization);
        mLoanAmount = (EditText)findViewById(R.id.loan_amount);
        mInterestRate = (EditText)findViewById(R.id.interest_rate);
        mLoanPeriod = (EditText)findViewById(R.id.loan_period);
        mMontlyPaymentResult = (TextView)findViewById(R.id.monthly_payment_result); 
        mTotalPaymentsResult = (TextView)findViewById(R.id.total_payments_result);
    }
    
    public void showLoanPayments(View clickedButton) {
    	if ((mLoanAmount.getText().length() == 0)
				|| (mLoanAmount.getText().toString() == "")
				|| (mInterestRate.getText().length() ==0)
				|| (mInterestRate.getText().toString() == "")
				|| (mLoanPeriod.getText().length() ==0)
				|| (mLoanPeriod.getText().toString() == "")) {

			show = new AlertDialog.Builder(Amortization.this).setTitle("StartappzKE!! Error")
					.setMessage("Nothing to Calculate")
					.setPositiveButton("OK", null).show();
			
    	} else { 

        
        double loanAmount = Integer.parseInt(mLoanAmount.getText().toString());
        double interestRate = (Integer.parseInt(mInterestRate.getText().toString()));
        double loanPeriod = Integer.parseInt(mLoanPeriod.getText().toString());
        double r = interestRate/1200;
        double r1 =  Math.pow(r+1,loanPeriod);
        
        double monthlyPayment = (double) ((r+(r/(r1-1))) * loanAmount);
        double totalPayment = monthlyPayment * loanPeriod;
        
        mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment)+"/=");
        mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment)+"/=");
           }
    }
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(this, MainActivity.class));
			Amortization.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
		}

}

