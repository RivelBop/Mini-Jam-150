package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;

public class Wolf {
	
	public AnimatedSprite sprite;
	public float move; 

	
	public Wolf(float x, float y) {
		sprite = new AnimatedSprite(0f, 0f, "wolf.atlas", "idle", 0.06f, PlayMode.LOOP);
		sprite.setPosition(x, y);
	}
	
	public void render() {
		sprite.render();
	}
	
	public void update() {
		move += Gdx.graphics.getDeltaTime();
		
		if(move < 1f) {
			sprite.translateX(100 * Gdx.graphics.getDeltaTime());
		}
		else if(move > 1f && move < 2f) {
			sprite.translateX(-100 * Gdx.graphics.getDeltaTime());
		}
		else
			move = 0f; 
		
	}
	
}
