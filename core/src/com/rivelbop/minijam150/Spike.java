package com.rivelbop.minijam150;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Spike {
	public Sprite sprite;
	
	public Spike(int x, int y) {
		sprite = new Sprite(Main.assets.get("spike.png", Texture.class));
		sprite.setCenter(x * Tile.SIZE + Tile.SIZE / 2f, y * Tile.SIZE + Tile.SIZE / 2f);
	}
	
	public void render() {
		sprite.draw(Main.batch);
	}
}