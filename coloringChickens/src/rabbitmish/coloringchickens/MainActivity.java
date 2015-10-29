/* -*- mode:java; coding:utf-8; -*- Time-stamp: <MainActivity.java - root> */

package rabbitmish.coloringchickens;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity
{
    private final OnEraseListener _listener = new OnEraseListener() {
            public void onErased()
            {
                Log.d("------------------------------", "ERASED");
            }
        };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EraseView view = (EraseView)findViewById(R.id.eraseView);

        if (view != null)
        {
            view.setOnEraseListener(_listener);
        }
    }

    @Override
    public void onDestroy()
    {
        final ImageView view = (ImageView)findViewById(R.id.eraseView);

        if (view != null)
        {
            view.setImageDrawable(null);
        }
        super.onDestroy();
    }
}
