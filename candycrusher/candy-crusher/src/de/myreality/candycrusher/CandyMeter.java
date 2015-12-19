package de.myreality.candycrusher;

import de.myreality.candycrusher.util.Timer;

public class CandyMeter {
	
	public static final int START_INTERVAL = 1500;
	
	public static final float DEGREE = 0.05f;

	private float value;
	
	private int interval;
	
	public Timer timer;
	
	private int totalDegree;
	
	public CandyMeter() {
		timer = new Timer();
		timer.start();
		interval = START_INTERVAL;
		value = 1f;
	}
	
	
	public void update(float delta) {
		
		if (timer.getTicks() >= interval) {
			timer.reset();
			value -= DEGREE;
			interval -= interval / 120;
		}
		
		if (value < 0.0f) {
			value = 0;
			timer.stop();
		}		
	}
	
	public void reset() {
		value = 1f;
		totalDegree = 0;
		interval = START_INTERVAL;
		timer.reset();
		timer.start();
	}
	
	public float getCurrentCandyDegree() {
		return value;
	}
	
	public boolean isEmpty() {
		return value <= 0.0f;
	}
	
	public int getTotalCandyDegree() {
		return totalDegree;
	}
	
	public void collect(Candy candy) {
		value += candy.getSize() / 10000f;
		
		if (value > 1f) {
			value = 1f;
		}
		
		totalDegree += candy.getSize();
	}
}
