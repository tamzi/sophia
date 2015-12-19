package de.myreality.candycrusher.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.candycrusher.CandyMeter;
import de.myreality.candycrusher.Resources;

public class CandyMeterUI extends Actor {

	private CandyMeter target;
	
	private Sprite life, background;

    private float padding;

    private float currentValue;
    
    private Label label;
	
	public CandyMeterUI(CandyMeter candyMeter) {
		this.target = candyMeter;
		currentValue = 1f;
		 Pixmap map = new Pixmap(128, 128, Format.RGBA8888);
		 Color color = new Color(Color.PINK);
		 color.a = 0.3f;
         map.setColor(color);
         map.fill();

         Texture texture = new Texture(map);
         map.dispose();
         
         background = new Sprite(texture);
         
         map = new Pixmap(128, 128, Format.RGBA8888);
         map.setColor(Color.PINK);
         map.fill();

         texture = new Texture(map);
         map.dispose();
         
         life = new Sprite(texture);
         
         LabelStyle style = new LabelStyle();
         style.font = Resources.BITMAP_FONT_REGULAR;
         style.fontColor = Color.WHITE;
         
         label = new Label("0", style);
	}
	
	 @Override
     public void draw(Batch batch, float parentAlpha) {
		 
             super.draw(batch, parentAlpha);

             if (currentValue != target.getCurrentCandyDegree()) {

      	 			 getColor().a = 1f;

                     if (target.getCurrentCandyDegree() > currentValue) {
                    	 currentValue += ((target.getCurrentCandyDegree() - currentValue) / 5);

                             if (currentValue > target.getCurrentCandyDegree()) {
                            	 currentValue = target.getCurrentCandyDegree();
                             }
                     } else {
                    	 currentValue -= ((currentValue - target.getCurrentCandyDegree()) / 5);

                         if (currentValue < 0) {
                        	 currentValue = 0;
                         }
                     }

             }
             
             background.setColor(getColor());
             background.setBounds(getX(), getY(), getWidth(), getHeight());
             background.draw(batch);

             float lifeWidth = getWidth() * currentValue - padding * 2;

             if (lifeWidth < 0) {
                     lifeWidth = 0;
             }

             life.setColor(getColor());
             life.setBounds(getX() + padding, getY() + padding, lifeWidth,
                             getHeight() - padding * 2);
             life.draw(batch);
             
             label.setText(target.getTotalCandyDegree() + "");
             label.setFontScale(getHeight() / 100f);
             
             while (label.getPrefWidth() >= getWidth() - getWidth() / 10) {
            	 label.setFontScale(label.getFontScaleX() / 1.1f);
             }
             
             label.setBounds(getX() + getWidth() / 2f - label.getPrefWidth() / 2f,
            		 		 getY() + getHeight() / 2f - label.getPrefHeight() / 2f, label.getPrefWidth(), label.getPrefHeight());
             label.draw(batch, parentAlpha);

     }
	
	
}
