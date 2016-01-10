package yogurtolic.games.handfootball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class GRacketAI extends GBase {
	
	private float _fActiveTime;
	
	private float _fPrevPosTime;
	private boolean _bSetPos;
	
	private Vector2d _vMin , _vMax;
	
	public GRacketAI() {
		super();
		
		radius = 32.0f;
		
		_fActiveTime = 0.0f;
		_fPrevPosTime = 0.0f;
		_bSetPos = false;
	}
	
	public GRacketAI(Vector2d pos , Vector2d dir) {	
		super(pos,dir);	
		
		radius = 32.0f;
		
		_fActiveTime = 0.0f;
		_fPrevPosTime = 0.0f;
		
		_velocity = 100.0f;
		
		_vMin = new Vector2d();
		_vMax = new Vector2d();
	}
	
	public void setRange(Vector2d vMin , Vector2d vMax) {
		_vMin.copy(vMin);
		_vMax.copy(vMax);
	}
	
	public void render(Canvas canvas) {
		if(_isVisible == false) {
			return;
		}
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		//paint.setAntiAlias(true);
		canvas.drawCircle(_pos._x, _pos._y, radius , paint);
	}
	
	
	
	public void update(float fElapsedTime)
	{
		_fActiveTime += fElapsedTime;
		
		Vector2d dir = new Vector2d(_dir);
		dir.normalize();
				
		dir = dir.multiply(_velocity*fElapsedTime);
		
		_pos.copy(_pos.add(dir));		
		
		if( _pos._x < _vMin._x) {
			_dir.copy(_dir.multiply(-1.0f));
			_pos._x = _vMin._x;
		}
		else if(  _pos._x > _vMax._x ) {
			_dir.copy(_dir.multiply(-1.0f));
			_pos._x = _vMax._x;
		}
			
		
//		if(_bSetPos) {
//			_bSetPos = false;
//			
//			Vector2d vDistance = new Vector2d(_pos.minus(_prevPos));			
//			_velocity = vDistance.size()/(_fActiveTime - _fPrevPosTime);
//			
//			_fPrevPosTime = _fActiveTime;
//		}				
		
		
	}
	
	

}
