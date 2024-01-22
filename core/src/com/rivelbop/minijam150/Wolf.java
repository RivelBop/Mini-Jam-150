package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;

public class Wolf {
	public AnimatedSprite sprite;
	public float move;
	public boolean isMoving;
	public float walkParticleTimer;
	
	public Wolf(int x, int y) {
		sprite = new AnimatedSprite(0f, 0f, "wolf.atlas", "wolf", 0.2f, PlayMode.LOOP);
		sprite.setCenter(x * Tile.SIZE + Tile.SIZE / 2f, y * Tile.SIZE + Tile.SIZE / 2f);
		sprite.setScale(2f);
	}
	
	public void render() {
		sprite.render();
	}
	
	public void update() {
		move += Gdx.graphics.getDeltaTime();
		walkParticleTimer += Gdx.graphics.getDeltaTime();
		isMoving = false;
		if(move < 1f) {
			sprite.translateX(100 * Gdx.graphics.getDeltaTime());
			sprite.setFlip(false, false);
			isMoving = true;
		} else if(move < 2f) {
			sprite.translateX(-100 * Gdx.graphics.getDeltaTime());
			sprite.setFlip(true, false);
			isMoving = true;
		} else {
			move = 0f; 
		}
	}	
}