package yogurtolic.games.handfootball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class GRacket extends GBase {
	
	private boolean _bGriped;
	
	private float _fActiveTime;
	
	private float _fPrevPosTime;
	private boolean _bSetPos;
	
	public GRacket() {
		super();
		_bGriped = false;
		
		radius = 14.0f;
		
		_fActiveTime = 0.0f;
		_fPrevPosTime = 0.0f;
		_bSetPos = false;
	}
	
	public GRacket(Vector2d pos , Vector2d dir) {
		super(pos,dir);
		
		_bGriped = false;		
		radius = 32.0f;
	}
	
	public void render(Canvas canvas) {
		if(_isVisible == false) {
			return;
		}
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		//paint.setAntiAlias(true);
		canvas.drawCircle(_pos._x, _pos._y, radius , paint);
	}
	
	public boolean setGrip(int x , int y) {
			
		_fPrevPosTime = _fActiveTime;

		Vector2d vDistance = new Vector2d(x,y);  		
				
		if( vDistance.minus(_pos).size() < radius ) {			
			_bGriped = true;			
			return true;
		}
		
		return false;
	}
	
	
	public void setGripRelease() {
		_bGriped = false;
	}
	
	public void setPos(Vector2d pos) {
		_prevPos.copy(_pos);		
		_pos.copy(pos);
		
		_dir.copy(_pos.minus(_prevPos));
		_dir.normalize();
				
		
		_bSetPos = true;
	}
	
	public void update(float fElapsedTime)
	{
		_fActiveTime += fElapsedTime;
		
		if(_bSetPos) {
			_bSetPos = false;
			
			Vector2d vDistance = new Vector2d(_pos.minus(_prevPos));			
			_velocity = vDistance.size()/(_fActiveTime - _fPrevPosTime);
			
			_fPrevPosTime = _fActiveTime;
		}				
	}
	
	

}
