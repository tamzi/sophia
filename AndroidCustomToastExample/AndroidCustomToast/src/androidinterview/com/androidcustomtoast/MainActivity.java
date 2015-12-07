package androidinterview.com.androidcustomtoast;

import org.apache.http.util.LangUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// My custom toast
	public void btntoast(View view) {
		Context context=getApplicationContext();
		LayoutInflater inflater=getLayoutInflater();
		
		View customToastroot =inflater.inflate(R.layout.mytoast, null);
		
		Toast customtoast=new Toast(context);
		
		customtoast.setView(customToastroot);
		customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
		customtoast.setDuration(Toast.LENGTH_LONG);
		customtoast.show();
	}
	// Red toast
		public void btnRed(View view) {
			Context context=getApplicationContext();
			LayoutInflater inflater=getLayoutInflater();
			
			View customToastroot =inflater.inflate(R.layout.red_toast, null);
			
			Toast customtoast=new Toast(context);
			
			customtoast.setView(customToastroot);
			customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
			customtoast.setDuration(Toast.LENGTH_LONG);
			customtoast.show();
		}
		// Yellow toast
		public void btnYellow(View view) {
			Context context=getApplicationContext();
			LayoutInflater inflater=getLayoutInflater();
			
			View customToastroot =inflater.inflate(R.layout.yellow_toast, null);
			
			Toast customtoast=new Toast(context);
			
			customtoast.setView(customToastroot);
			customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
			customtoast.setDuration(Toast.LENGTH_LONG);
			customtoast.show();
		}
		// Blue toast
		public void btnBlue(View view) {
			Context context=getApplicationContext();
			LayoutInflater inflater=getLayoutInflater();
			
			View customToastroot =inflater.inflate(R.layout.blue_toast, null);
			
			Toast customtoast=new Toast(context);
			
			customtoast.setView(customToastroot);
			customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
			customtoast.setDuration(Toast.LENGTH_LONG);
			customtoast.show();
		}
}
