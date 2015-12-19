package de.myreality.candycrusher.screens;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.candycrusher.Candy;
import de.myreality.candycrusher.CandyCrusherGame;
import de.myreality.candycrusher.CandyMeter;
import de.myreality.candycrusher.Resources;
import de.myreality.candycrusher.RotatingBackground;
import de.myreality.candycrusher.input.IngameControls;
import de.myreality.candycrusher.tweens.ActorTween;
import de.myreality.candycrusher.ui.CandyMeterUI;
import de.myreality.candycrusher.util.Timer;

public class IngameScreen implements Screen {
	
	public static final int MIN_SPAWN_INTERVAL = 2000;
	public static final int SPAWN_DEGREE = 1000;
	
	private CandyCrusherGame game;
	
	private OrthographicCamera camera;
	
	private Batch batch;
	
	private Timer timer;
	
	private TweenManager tweenManager;
	
	private RotatingBackground background1, background2;
	
	private List<Candy> candies;
	
	private List<Candy> addList;
	
	private Stage stage;
	
	private boolean touched;
	
	private CandyMeter candyMeter;
	
	private Label lostLabel;
	
	public IngameScreen(CandyCrusherGame game) {
		this.game = game;
		candies = new CopyOnWriteArrayList<Candy>();
		addList = new CopyOnWriteArrayList<Candy>();
	}
	
	public void reset() {
		candies.clear();
		addList.clear();
		timer.reset();
		candyMeter.reset();
		
		LabelStyle style = new LabelStyle();
		style.fontColor = Color.WHITE;
		style.font = Resources.BITMAP_FONT_REGULAR;
		
		if (lostLabel == null) {
			lostLabel = new Label("GAME OVER!", style);
			stage.addActor(lostLabel);
		} else {
			lostLabel.getColor().a = 1f;
		}
		
		lostLabel.setFontScale(Gdx.graphics.getWidth() / 500f);
		lostLabel.setX(Gdx.graphics.getWidth() / 2 - lostLabel.getPrefWidth() / 2);
		lostLabel.setY(Gdx.graphics.getHeight() / 2 - lostLabel.getPrefHeight() / 2);
	
		tweenManager.killTarget(lostLabel);
		Tween.to(lostLabel, ActorTween.ALPHA, 2)
			 .target(0f)
			 .ease(TweenEquations.easeInQuad)
			 .start(tweenManager);
	}

	@Override
	public void render(float delta) {
		
		if (candyMeter.isEmpty()) {
			reset();
		}
		
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			reset();
		}
		
		Gdx.gl.glClearColor(1f, 0f, 0.4f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (timer.getTicks() > MIN_SPAWN_INTERVAL + Math.random() * SPAWN_DEGREE) {
			timer.reset();
			float x = (float) (Gdx.graphics.getWidth() * Math.random());
			float y = (float) (Gdx.graphics.getHeight() * Math.random());
			int size = (int) (Gdx.graphics.getHeight() / 10 + Math.random() * 40f);
			float speed = (float) (Math.random() * 1f + 1f);
			addCandy(new Candy(x, y, size, speed, tweenManager));
		}
		
		candyMeter.update(delta);
		tweenManager.update(delta);
		stage.act(delta);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		background1.updateAndRender(delta, batch);
		background2.updateAndRender(delta, batch);
		
		for (Candy c : candies) {
			
			if (touched && c.contains(Gdx.input.getX(), Gdx.input.getY())) {
				removeCandy(c);
				
				final int NEW_COUNT = 3;
				
				for (int i = 0; i < NEW_COUNT; ++i) {
					float x = c.getX() + c.getSize() / 2;
					float y = c.getY() + c.getSize() / 2;
					int size = (int) (c.getSize() / 1.5f);
					float speed = c.getSpeed() * 1.5f;
					candyMeter.collect(c);
					addList.add(new Candy(x, y, size, speed, null));
				}
			} else if (c.getX() + c.getSize() < 0 || c.getY() + c.getSize() < 0 || 
				c.getX() > Gdx.graphics.getWidth() ||
				c.getY() > Gdx.graphics.getHeight()) {
				removeCandy(c);
			} else {			
				c.updateAndRender(delta, batch);
			}
		}
		
		batch.end();
		
		stage.draw();
		
		touched = false;
		
		for (Candy candy : addList) {
			addCandy(candy);
		}
		
		addList.clear();
	}
	
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void addCandy(Candy candy) {
		candies.add(candy);
	}
	
	public void removeCandy(Candy candy) {
		candies.remove(candy);
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null) {
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(true);
		stage = new IngameControls(width, height, this, game);
		Gdx.input.setInputProcessor(stage);
		CandyMeterUI ui = new CandyMeterUI(candyMeter);
		ui.setBounds(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6f, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 8);
		stage.addActor(ui);
		} else {
			camera.viewportWidth = width;
			camera.viewportHeight = height;
			stage.setViewport(width, height);
		}
	}

	@Override
	public void show() {
		candyMeter = new CandyMeter();
		tweenManager = new TweenManager();
		timer = new Timer();
		batch = new SpriteBatch();
		background1 = new RotatingBackground(Resources.SUN, 20f);
		background2 = new RotatingBackground(Resources.SUN, 21f);
		timer.start();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
