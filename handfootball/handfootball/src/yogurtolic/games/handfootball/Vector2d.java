package yogurtolic.games.handfootball;

public class Vector2d {
	public float _x,_y;
	
	Vector2d() {
		_x = 0;
		_y = 0;
	}
	Vector2d(Vector2d cp) {
		_x = cp._x;
		_y = cp._y;
	}
	Vector2d(float x , float y) {
		_x = x;
		_y = y;
	}		
	
	
	public Vector2d normalize() {		
		
		double size = size();
		
		_x /= size;
		_y /= size;
		
		return this;
	}
	
	public float size() {
		double x = _x , y = _y;
		
		return (float)Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
	}
	
	public Vector2d multiply(float value) {
		Vector2d vResult = new Vector2d();
			
		vResult._x = _x * value;
		vResult._y = _y * value;
		
		return vResult;
	}
	
	public float dot(Vector2d v) {
		return _x*v._x + _y*v._y;
	}
	
	public Vector2d add(Vector2d v) {
		
		Vector2d vResult = new Vector2d();
		
		vResult._x = _x + v._x;
		vResult._y = _y + v._y;
		
		return vResult;
	}
	
	public Vector2d minus(Vector2d v) {
		Vector2d vResult = new Vector2d();
		
		vResult._x = _x - v._x;
		vResult._y = _y - v._y;
		
		return vResult;
	}
	
	public Vector2d copy(Vector2d v) {
		_x = v._x;
		_y = v._y;
		
		return this;
	}
	
}
