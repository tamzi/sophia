package yogurtolic.games.handfootball;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GBase {
	
	protected Bitmap _texture = null;
	protected Vector2d _pos;
	protected Vector2d _dir;
	
	protected Vector2d _prevPos;	
	
	protected boolean _isVisible = true;
	
	protected float radius = 0.0f;
	
	protected float _velocity = 0.0f;
	
	protected GBase() {
		_pos = new Vector2d(0.0f , 0.0f);
		_dir = new Vector2d(0.0f , 0.0f);
		
		_prevPos = new Vector2d(0.0f , 0.0f);
		
		//_isVisible = true;
	}
	
	public GBase(Vector2d pos , Vector2d dir) {
			
		_pos = new Vector2d(pos._x , pos._y);
		_dir = new Vector2d(dir._x , dir._y);
		
		_prevPos = new Vector2d(0.0f , 0.0f);
	}
	
	
	public void move(Vector2d vDelta) {
		_pos._x += vDelta._x;
		_pos._y += vDelta._y;
	}
	
	public Vector2d getPos() {
		return _pos;
	}
	public void setPos(Vector2d pos) {
		_prevPos.copy(_pos);		
		_pos.copy(pos);
		
//		_dir.copy(
//				pos.minus(_prevPos)
//				);
//		
//		_dir.normalize();
	}
	
	public Vector2d getDir() {
		return _dir;
	}
	public void setDir(Vector2d dir) {
		_dir.copy(dir);
	}
	
	
	public void init(Bitmap bm) {
		_texture = bm;
		
		_isVisible = true;
	}
	
	public void resetState() {
		_velocity = 0.0f;
	}
	
	public void update(float fElapsedTime) {		
		_prevPos.copy(_pos);				
	}
	
	public void render(Canvas canvas) {
		
		if(_isVisible == false) {
			return;
		}	
		
		canvas.drawBitmap(_texture , _pos._x - _texture.getWidth()/2 , _pos._y - _texture.getHeight()/2 , null);		
	}
}
