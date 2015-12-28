package startappzke.calsheet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Finance extends Activity {
	
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private ImageView image6;
	private ImageView image7;
	private ImageView image8;
	private ImageView image9;
	
	
    public void onCreate(Bundle savedInstanceState) {
    	
    	final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);
        image4 = (ImageView) findViewById(R.id.imageView4);
        image5 = (ImageView) findViewById(R.id.imageView5);
        image6 = (ImageView) findViewById(R.id.imageView6);
        image7 = (ImageView) findViewById(R.id.imageView7);
        image8 = (ImageView) findViewById(R.id.imageView8);
        image9 = (ImageView) findViewById(R.id.imageView9);
        
        
        image1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, SimpleInterest.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        image2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, CompoundInterest.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});

        image3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, Depreciation.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        
        image4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, CashFlow.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        image5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, LeverageRatio.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        image6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, Cagr.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        image7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, Rost.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        
        image8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, Amortization.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
        image9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, Bfr.class);
				startActivity(intent);
				
				Finance.this.finish();
	    
			}

		});
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
