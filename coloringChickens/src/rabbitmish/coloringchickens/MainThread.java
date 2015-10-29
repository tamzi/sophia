/* -*- mode:java; coding:utf-8; -*- Time-stamp: <MainThread.java - root> */

package rabbitmish.coloringchickens;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{
    private final static int FRAME_DURATION = 1000 / 25 /* frames per second */;
    private final static int MAX_SKIP_COUNT = 5;

    private boolean _running;
    private SurfaceHolder _surface_holder;
    private MainView _view;

    private void draw()
    {
        Canvas canvas = null;

        try
        {
            canvas = _surface_holder.lockCanvas();

            synchronized (_surface_holder)
            {
                _view.render(canvas);
            }
        }
        finally
        {
            if (canvas != null)
            {
                _surface_holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public MainThread(SurfaceHolder h, MainView v)
    {
        super();

        _surface_holder = h;
        _view = v;
    }

    @Override
    public void run()
    {
        // ending time of the last rendered frame
        long frame_time = System.currentTimeMillis();

        // total number of frames to skip
        long skip_total = 0;

        // number of remaining frames to skip
        long skip_count = 0;

        while (_running)
        {
            _view.update();

            if (skip_count > 0)
            {
                skip_count--;
            }
            else
            {
                draw();
                frame_time += (skip_total + 1) * FRAME_DURATION;

                final long delta = frame_time - System.currentTimeMillis();

                if (delta < 0)
                {
                    skip_total = Math.min((FRAME_DURATION - delta - 1) / FRAME_DURATION, MAX_SKIP_COUNT);
                    skip_count = skip_total;
                }
                else
                {
                    try
                    {
                        Thread.sleep(delta);
                    }
                    catch (InterruptedException e)
                    {
                        // ignore
                    }
                    skip_total = 0;
                    skip_count = 0;
                }
            }
        }
    }

    public void setRunning(boolean running)
    {
        _running = running;
    }
}
