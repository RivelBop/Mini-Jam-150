package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;

public class Ghost {
	public AnimatedSprite sprite;
	public final float NORMAL_SPEED = 50f;
	public float speed = NORMAL_SPEED;
	
	public Ghost(int x, int y) {
		sprite = new AnimatedSprite(0f, 0f, "ghost.atlas", "ghost", 0.2f, PlayMode.LOOP);
		sprite.setCenter(x * Tile.SIZE + Tile.SIZE / 2f, y * Tile.SIZE + Tile.SIZE / 2f);
		sprite.setScale(3f);
	}
	
	public void render() {
		sprite.render();
	}
	
	public void update(Player player) {
		float dist = (float)Math.sqrt((player.sprite.getY() - sprite.getY()) * (player.sprite.getY() - sprite.getY()) + (player.sprite.getX() - sprite.getX()) * (player.sprite.getX() - sprite.getX()));
		
		if(dist <= 5 * Tile.SIZE) {
			if(sprite.getX() < player.sprite.getX()) {
				sprite.translateX(speed * Gdx.graphics.getDeltaTime());
				sprite.setFlip(false, false);
			} else if (sprite.getX() > player.sprite.getX()){
				sprite.translateX(-speed * Gdx.graphics.getDeltaTime());
				sprite.setFlip(true, false);
			}
			
			if (sprite.getY() < player.sprite.getY()) {
				sprite.translateY(speed * Gdx.graphics.getDeltaTime());
			} else if (sprite.getY() > player.sprite.getY()) {
				sprite.translateY(-speed * Gdx.graphics.getDeltaTime());
			}
		}
	}	
}