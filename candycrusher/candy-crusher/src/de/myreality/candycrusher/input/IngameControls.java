package de.myreality.candycrusher.input;

import com.badlogic.gdx.scenes.scene2d.Stage;

import de.myreality.candycrusher.CandyCrusherGame;
import de.myreality.candycrusher.screens.IngameScreen;

public class IngameControls extends Stage {

	private IngameScreen screen;
	
	private CandyCrusherGame game;

	/**
	 * @param screen
	 * @param game
	 */
	public IngameControls(int width, int height, IngameScreen screen, CandyCrusherGame game) {
		super(width, height, false);
		this.screen = screen;
		this.game = game;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screen.setTouched(true);
		return true;
	}
	
	
}
