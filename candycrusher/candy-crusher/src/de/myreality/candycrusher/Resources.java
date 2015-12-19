package de.myreality.candycrusher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class Resources {

	public static Texture CANDY;
	
	public static Texture SUN;
	
	public static BitmapFont BITMAP_FONT_REGULAR;
	
	public static void load() {
		dispose();
		
		CANDY = new Texture(Gdx.files.internal("candy.png"));
		SUN = new Texture(Gdx.files.internal("sun.png"));
		BITMAP_FONT_REGULAR = new BitmapFont(Gdx.files.internal("medium.fnt"));
	}
	
	public static void dispose() {
		
		if (CANDY != null) {
			CANDY.dispose();
		}
		
		if (SUN != null) {
			SUN.dispose();
		}
		
		if (BITMAP_FONT_REGULAR != null) {
			BITMAP_FONT_REGULAR.dispose();
		}
	}
}
