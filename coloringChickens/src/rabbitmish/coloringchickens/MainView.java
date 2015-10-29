/* -*- mode:java; coding:utf-8; -*- Time-stamp: <MainView.java - root> */

package rabbitmish.coloringchickens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback
{
    private MainThread _thread;

    public MainView(Context context)
    {
        super(context);
        getHolder().addCallback(this);

        _thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        _thread.setRunning(true);
        _thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;

        while (retry)
        {
            try
            {
                _thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                // ignore
            }
        }
    }

    public void render(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
    }

    public void update()
    {
    }
}
