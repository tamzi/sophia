package yogurtolic.games.handfootball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class GBall extends GBase {
	
	public GBall() {				
		super();	
		
		radius = 16.0f;
	}
	
	public GBall(Vector2d pos , Vector2d dir) {
		super(pos,dir);	
		
		radius = 16.0f;
	}
	
	
	
	public void update(float fElapsedTime)
	{
		super.update(fElapsedTime);
		
		if(_velocity > 500.0f) {
			_velocity = 500.0f;
		}
		
		_velocity -= (fElapsedTime*10.0f);
		
		if(_velocity < 0.0f) {
			_velocity = 0.0f;
			
			_dir._x = 0.0f;
			_dir._y = 0.0f;
			
			return;
		}
		
		Vector2d dir = new Vector2d(_dir);
		dir.normalize();
				
		dir = dir.multiply(_velocity*fElapsedTime);
		
		_pos.copy(_pos.add(dir));
		
	}
	
	public void render(Canvas canvas) {
		if(_isVisible == false) {
			return;
		}
		
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		//paint.setAntiAlias(true);
		
		canvas.drawCircle(_pos._x, _pos._y, radius, paint);
	}

}
