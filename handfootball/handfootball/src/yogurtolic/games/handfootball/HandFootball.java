package yogurtolic.games.handfootball;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class HandFootball extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
        		WindowManager.LayoutParams.FLAG_FULLSCREEN , 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN
        		);
        
        setContentView(new GameView(this));
    }
    
    
    
    class GameView extends SurfaceView implements SurfaceHolder.Callback {
    	
    	private GameGroundThread gameThread;
    	
    	private int _FieldWidth , _FieldHeight;
    	private int x = 0 , y = 0;
    	private GBall _ball = null;
    	
    	private GRacket _home = null;
    	private GRacketAI _away = null;
    	
    	private ArrayList<GBase> _ltObject;
    	
    	private boolean m_bCatched = false;
    	
    	private float _fElapsedTime = 0.0f;
    	    	
    	private final int GOALIN_AWAY = 3;
    	private final int GOALIN_HOME = 2;
    	private final int WALLBOUND = 1;
    	private final int NOBOUND = 0;
    	
    	private final float RESTART_DELAY = 5.0f;
    	
    	private int _iGameState = 0;		// 0 : playing , 1 : Goal After Delay
		private float _fGoalStateTime = 0.0f;
		
		private float _fWallWidth = 0.0f;
		
		private Vector2d _GoalInBallPosition;
		
		private int _iHomeScore = 0;
		private int _iAwayScore = 0;
		
    	
    	public GameView(Context context) {
    		super(context);
    		getHolder().addCallback(this);
    		
    		_ltObject = new ArrayList<GBase>();
    		_ltObject.clear();
    		
    		gameThread = new GameGroundThread(getHolder() , this);
    		
    		
    		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    		
    		_FieldWidth = display.getWidth();
    		_FieldHeight = display.getHeight();
    		
    		_ball = new GBall(
    				new Vector2d(((float)_FieldWidth)/2.0f, (float)_FieldHeight/2.0f) , 
    				new Vector2d(0.0f , 0.0f)
    				);
    		
    		_ball.init(null);
    		
    		_home = new GRacket(
    				new Vector2d((float)_FieldWidth/2, ((float)_FieldHeight)*0.75f) , 
    				new Vector2d(0.0f , 0.0f)
    				
			);   		
    		_away = new GRacketAI(
    				new Vector2d((float)_FieldWidth/2, ((float)_FieldHeight)*0.25f) , 
    				new Vector2d(1.0f , 0.0f)
			); 
    		
    		_away.setRange(new Vector2d(_FieldWidth * 0.25f , 0.0f), new Vector2d(_FieldWidth * 0.75f , 20.0f));
    
    		_ltObject.add(_ball);
    		_ltObject.add(_home);
    		_ltObject.add(_away);
    		
    		_fWallWidth = (float)_FieldHeight/20.0f;
    		
    		_GoalInBallPosition = new Vector2d();
    		
    		_iHomeScore = 0;
    		_iAwayScore = 0;
    		
    		setFocusable(true);
    	}
    	
    	
    	
    	public boolean isCollisionBall(GBase racket , GBase ball) {
    		      		
    		Vector2d vDistance = new Vector2d(ball._pos);
    		vDistance = vDistance.minus(racket._pos);
    		
    		boolean bCollision = (vDistance.size() < (ball.radius + racket.radius));
    		
    		if(bCollision) {
    			
    			//float fDistance = ball.radius + racket.radius - vDistance.size(); 
    			        		
        		Vector2d normal = new Vector2d(vDistance); 		
        		normal.normalize();
        		
        		Vector2d balldir = new Vector2d(ball._dir);
        		
        		if( ball._velocity > 0.0f) {
        			ball._dir = reflection(normal , balldir);
            		ball._dir.normalize();

            		ball._velocity = Math.max(racket._velocity*10.0f, ball._velocity);            		
        		}
        		else {
        			ball._dir.copy(normal);
        			ball._velocity = racket._velocity*10.0f;        			    			    			
        		}
        		
        		ball.move(normal.multiply(racket.radius));
    		}
    		
    		return bCollision;    		
    	}
    	
    	public int isCollisionWall(GBase ball) {
    		Vector2d vBallPos = new Vector2d(ball._pos);
    		Vector2d vBallDir = new Vector2d(ball._dir);
    		
    		int bCollision = NOBOUND;
    		
    		
    		
    		// goal check
    		if( ( (float)_FieldWidth * 0.25f < vBallPos._x && vBallPos._x < (float)_FieldWidth*0.75f )
    				&& ( vBallPos._y < _fWallWidth+ball.radius || vBallPos._y > _FieldHeight-_fWallWidth-ball.radius)   				
    			) 
    		{
    			if(vBallPos._y < _fWallWidth+ball.radius) {
    				bCollision = GOALIN_HOME;
    			}
    			else {
    				bCollision = GOALIN_AWAY;
    			}    			    			    			
    		}
    		else	// wall bound check 
    		{
    			
    			if( _fWallWidth+ball.radius > vBallPos._x ) {
        			ball._dir = reflection(new Vector2d(1.0f , 0.0f) , vBallDir);
        			ball._pos._x = _fWallWidth+ball.radius;
        			
        			bCollision = WALLBOUND;
        		}
        		else if( _FieldWidth - _fWallWidth - ball.radius < vBallPos._x ) {
        			ball._dir = reflection(new Vector2d(-1.0f , 0.0f) , vBallDir);
        			ball._pos._x = _FieldWidth - _fWallWidth - ball.radius;
        			
        			bCollision = WALLBOUND;
        		}
        		
        		if( _fWallWidth + ball.radius > vBallPos._y ) {
        			ball._dir = reflection(new Vector2d(0.0f , 1.0f) , vBallDir);
        			ball._pos._y = _fWallWidth + ball.radius;
        			
        			bCollision = WALLBOUND;
        		}
        		else if( _FieldHeight - _fWallWidth - ball.radius < vBallPos._y ) {
        			ball._dir = reflection(new Vector2d(0.0f , -1.0f) , vBallDir);
        			ball._pos._y = _FieldHeight - _fWallWidth - ball.radius;
        			
        			bCollision = WALLBOUND;
        		}    			
    		}   		   		
    		
    		return bCollision;
    	}
    	
    	public Vector2d reflection(Vector2d normal , Vector2d balldir) {
    		Vector2d vResult = new Vector2d();
    		
    		vResult.copy(
    				normal.multiply((balldir.multiply(-1.0f).dot(normal)*2.0f)).add(balldir)
    				);    				
    		
    		return vResult;
    	}
    	
    	public void cleanTable()
    	{
    		readyTable();
    		
    		_ball.setPos(new Vector2d(_GoalInBallPosition)); 
			_ball.setDir(new Vector2d(0.0f , 0.0f));    		
    	}
    	public void readyTable()
    	{
    		m_bCatched = false;
    		
    		_home.resetState();
    		_home.setPos(new Vector2d((float)_FieldWidth/2, ((float)_FieldHeight)*0.75f));
    		_home.setDir(new Vector2d(0.0f , 0.0f));
    		_home.setGripRelease();
    		
    		//_away.resetState();
    		_away.setPos(new Vector2d((float)_FieldWidth/2, ((float)_FieldHeight)*0.25f));
    		_away.setDir(new Vector2d(1.0f , 0.0f));
    		
    		_ball.resetState();
    		_ball.setPos(new Vector2d(((float)_FieldWidth)/2.0f, (float)_FieldHeight/2.0f)); 
			_ball.setDir(new Vector2d(0.0f , 0.0f));
    	}
    	
    	public void update(float fElapsedTime)
    	{    	
    		_fElapsedTime = fElapsedTime;
    		
    		if( _iGameState == 0 ) {
    			
    			Iterator<GBase> iter = _ltObject.iterator();
        		
        		while(iter.hasNext()) {
        			
        			iter.next().update(fElapsedTime);    			
        		} 
        		
        		isCollisionBall(_home, _ball);
        		isCollisionBall(_away, _ball);
        		
        		int iCollisionState = isCollisionWall(_ball); 
        		    		
        		if(iCollisionState == GOALIN_AWAY || iCollisionState == GOALIN_HOME) {
        			_fGoalStateTime = 0.0f;    	
        			_iGameState = 1; 
        			
        			_GoalInBallPosition.copy(_ball._pos);
        			
        			if(iCollisionState == GOALIN_HOME) {
        				_iHomeScore++;
        			}
        			else if(iCollisionState == GOALIN_AWAY) {
        				_iAwayScore++;
        			}
        			
        			
        			cleanTable();        			     			
        		}    			
    		}
    		else if( _iGameState == 1 ) {
    			_fGoalStateTime += _fElapsedTime;  
    			
    			if(_fGoalStateTime > 5.0f) {
    				_iGameState = 0;
    				_fGoalStateTime = 0.0f;				
    				
    				
    				readyTable();
    			}
    		}
    		

    	}
    	
    	public void render(Canvas canvas) {
    		
    		canvas.drawColor(Color.parseColor("#dedede"));
    		
    		// game table draw
    		Paint paint = new Paint();
    		paint.setColor(Color.DKGRAY);    		
    		canvas.drawLine((int)_fWallWidth , _fWallWidth , (int)_fWallWidth , _FieldHeight-(int)_fWallWidth, paint);
    		canvas.drawLine((int)_fWallWidth , _fWallWidth , _FieldWidth - (int)_fWallWidth, (int)_fWallWidth, paint);
    		canvas.drawLine(_FieldWidth - (int)_fWallWidth , _fWallWidth , _FieldWidth - (int)_fWallWidth, _FieldHeight-(int)_fWallWidth, paint);
    		canvas.drawLine((int)_fWallWidth , _FieldHeight-(int)_fWallWidth , _FieldWidth - (int)_fWallWidth, _FieldHeight-(int)_fWallWidth, paint);
    		
    		
    		// goal post draw
    		paint.setColor(Color.WHITE);
    		canvas.drawRect(_FieldWidth * 0.25f, 0, (float)_FieldWidth*0.75f, _fWallWidth, paint);
    		canvas.drawRect(_FieldWidth * 0.25f, _FieldHeight-_fWallWidth, (float)_FieldWidth*0.75f, _FieldHeight, paint);
    		    		
    		Iterator<GBase> iter = _ltObject.iterator();
    		
    		while(iter.hasNext()) {
    			iter.next().render(canvas);
    		}    		
    		
    		paint.setColor(Color.MAGENTA);
    		float fTextSize = paint.getTextSize();
    		
    		paint.setTextSize(30.0f);
    		canvas.drawText(String.format("%d",_iHomeScore) , 0 , _FieldHeight , paint);
    		canvas.drawText(String.format("%d",_iAwayScore) , _FieldWidth - _fWallWidth , _fWallWidth , paint);
    		    		
    		paint.setTextSize(fTextSize);
    		
    		if(_iGameState == 1) {
    			
    			paint.setColor(Color.YELLOW);
    			paint.setTextSize(30.0f);
    			paint.setAlpha((int)((RESTART_DELAY-_fGoalStateTime)/RESTART_DELAY * 255.0f));
    			
    			canvas.drawText(String.format("Goal!!"), _FieldWidth/2, _FieldHeight/2, paint);
    		}
    		
//    		String szTemp;
//    		szTemp = String.format("ElapsedTime : %.2f", _fElapsedTime);    		
//    		
//    		paint.setColor(Color.BLACK);
//    		canvas.drawText(szTemp, 10, 20, paint);
//    		
//    		szTemp = String.format("FPS : %.2f" , (1.0f/_fElapsedTime));
//    		canvas.drawText(szTemp, 10, 40, paint);
//    		
//    		szTemp = String.format("Ball Pos : %.2f , %.2f" , _ball.getPos()._x , _ball.getPos()._y);
//    		canvas.drawText(szTemp, 10, 60, paint);
//    		
//    		szTemp = String.format("Ball velocity : %.2f" , _ball._velocity);
//    		canvas.drawText(szTemp, 10, 80, paint);
    		
    	}
    	
    	
    	@Override
    	public void onDraw(Canvas canvas) {
    		render(canvas);
    	}
    	
    	//@Override
    	public void surfaceChanged(SurfaceHolder holder , int format , int width , int height) {
    		
    	}
    	
    	//@Override
    	public void surfaceCreated(SurfaceHolder holder) {
    		gameThread.setRunning(true);
    		gameThread.start();
    	}
    	
    	//@Override
    	public void surfaceDestroyed(SurfaceHolder holder) {
    		boolean retry = true;
    		gameThread.setRunning(false);
    		
    		while(retry) {
    			try {
    				gameThread.join();
    				retry = false;
    				
    			} catch (InterruptedException e) {
    				
    			}    			 
    		}
    	}
    	
    
    	@Override
    	public boolean onTouchEvent(MotionEvent event) {
    		    		
    		x = (int)event.getX();
    		y = (int)event.getY();
    		
    		
    		switch(event.getAction()) {
	    		
    		case MotionEvent.ACTION_DOWN:
	    		{
	    			if(_iGameState == 0) {
	    				m_bCatched = _home.setGrip(x,y);
	    			}
	    		}
	    		break;
	    		
		   
    		case MotionEvent.ACTION_MOVE:
		    	{
		    		if(m_bCatched) {
		    			_home.setPos(new Vector2d((float)x,(float)y));
		    		}
		    	}
		    	break;
		    	
		    
    		case MotionEvent.ACTION_UP:
		    	{
		    		_home.setGripRelease();		    		  		
		    		m_bCatched = false;
		    	}
		    	break;
	    	
	    	} 		
    		
    		
    		return true;
    	}
    }
    
    class GameGroundThread extends Thread {
    	private SurfaceHolder surfaceholder;
    	private GameView gameview;
    	private boolean running = false;
    	
    	private long _lPrevTime = 0L;
    	
    	private float _fElapsedTime = 0.0f;
    	
    	public GameGroundThread(SurfaceHolder surfaceHolder , GameView gameView) {
    		surfaceholder = surfaceHolder;
    		gameview = gameView;
    		
    		_lPrevTime = System.currentTimeMillis();
    	}
    	
    	public void setRunning(boolean run) {
    		running = run;
    	}
    	
    	@Override
    	public void run() {
    		Canvas c;
    		
    		while(running) {
    			
    			long currentTime = System.currentTimeMillis();    			
    			long lElpasedTime = currentTime - _lPrevTime;
    			_fElapsedTime = (float)lElpasedTime*0.001f;
    			gameview.update(_fElapsedTime);
    			
    			c = null;
    			
    			_lPrevTime = currentTime;
    			
    			try {
    				c = surfaceholder.lockCanvas(null);
    				synchronized(surfaceholder) {
    					gameview.render(c);
    				}
    			} finally {
    				if(c != null) {
    					surfaceholder.unlockCanvasAndPost(c);
    				}  				
    			}    			
    		}
    	}
    }
}