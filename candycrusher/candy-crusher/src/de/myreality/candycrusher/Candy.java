package de.myreality.candycrusher;

import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import de.myreality.candycrusher.tweens.ColorTween;

public class Candy {
	
	private Color color;
	
	private int size;
	
	private float x, y;
	
	private float speed;
	
	private Vector2 velocity;
	
	private Sprite sprite;
	
	private float rotationFactor;
	
	public Candy(float x, float y, int size, float speed, TweenManager tweenManager) {
		sprite = new Sprite(Resources.CANDY);
		this.x = x - size / 2;
		this.y = y - size / 2;
		this.size = size;
		this.speed = speed;
		velocity = generateRandomVelocity();
		this.color = getRandomColor();
		this.rotationFactor = getRandomRotationFactor();
		
		if (tweenManager != null) {
			Tween.to(color, ColorTween.A, 2f)
				 .target(1f)
				 .ease(TweenEquations.easeInExpo)
				 .start(tweenManager);
		} else {
			color.a = 1f;
		}
	}
	

	public void updateAndRender(float delta, Batch batch) {
		
		x += velocity.x * (delta + 1) * speed;
		y += velocity.y * (delta + 1) * speed;
		
		sprite.setColor(color);
		sprite.setPosition(x, y);
		sprite.rotate(rotationFactor * delta);
		sprite.setBounds(x, y, size, size);
		sprite.draw(batch);
	}
	
	public boolean contains(int x, int y) {		
		
		int padding = 0;
		
		if (size < 50) {
			padding = 20;
		}
		
		return (x >= getX() - padding && x <= getX() + getSize() + padding &&
			y >= getY()  - padding && y <= getY() + getSize() + padding);
	}
	
	private float getRandomRotationFactor() {
		
		Random random = new Random(System.currentTimeMillis());
		
		float rot = random.nextFloat() * 40f + 10f;
		
		if (random.nextFloat() < 0.5f) {
			rot *= -1;
		}
		
		return rot;		
	}
	
	private Color getRandomColor() {
		Random random = new Random(System.currentTimeMillis());
		
		float r = random.nextFloat() * 0.7f + 0.2f;
		float g = random.nextFloat() * 0.7f + 0.2f;
		float b = random.nextFloat() * 0.7f + 0.2f;
		
		return new Color(r, g, b, 0f);
	}
	
	private Vector2 generateRandomVelocity() {
		
		Random random = new Random(System.currentTimeMillis());
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();		
		
		Vector2 point = new Vector2(random.nextFloat() * width, random.nextFloat() * height);
		Vector2 velocity = new Vector2(point.x - x, point.y - y);
		velocity.nor();
		return velocity;		
	}

	public Color getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getSpeed() {
		return speed;
	}
	
	
}
