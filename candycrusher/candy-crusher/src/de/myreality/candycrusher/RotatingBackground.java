package de.myreality.candycrusher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RotatingBackground {
	
	private Sprite sprite;
	
	private float speed;
	
	public RotatingBackground(Texture texture, float speed) {
		sprite = new Sprite(texture);
		this.speed = speed;
	}
	
	public void updateAndRender(float delta, Batch batch) {
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();		
		int size = width;
		
		if (size < height) {
			size = height;
		}
		
		size *= 2;

		int x = width / 2 - size / 2;
		int y = height / 2 - size / 2;
		
		sprite.setOrigin(size / 2, size / 2);
		
		sprite.setBounds(x, y, size, size);		
		sprite.rotate(speed * delta);
		sprite.draw(batch);
	}
}
