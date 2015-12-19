package de.myreality.candycrusher;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.myreality.candycrusher.screens.IngameScreen;
import de.myreality.candycrusher.tweens.ActorTween;
import de.myreality.candycrusher.tweens.ColorTween;

public class CandyCrusherGame extends Game {

	@Override
	public void create() {
		
		Resources.load();
		
		Tween.registerAccessor(Color.class, new ColorTween());
		Tween.registerAccessor(Actor.class, new ActorTween());
		
		setScreen(new IngameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Resources.dispose();
	}
	
	
}
