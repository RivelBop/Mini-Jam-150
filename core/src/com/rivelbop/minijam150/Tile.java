package com.rivelbop.minijam150;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Tile {
	public static final float SIZE = 64f;
	public static final Tile[] TILES = {
			// 0 = Floor
			new Tile("dirt.png", false),
			
			// 1 = Wall
			new Tile("wall.png", true)
	};
	
	public Sprite sprite;
	public String texture;
	public boolean canCollide;
	
	public Tile(String texture, boolean canCollide) {
		sprite = new Sprite(Main.assets.get(texture, Texture.class));
		
		this.texture = texture;
		this.canCollide = canCollide;
	}
	
	public Tile(String texture, boolean canCollide, float x, float y) {
		sprite = new Sprite(Main.assets.get(texture, Texture.class));
		sprite.setPosition(x, y);
		
		this.texture = texture;
		this.canCollide = canCollide;
	}
	
	public void render() {
		sprite.draw(Main.batch);
	}
	
	public Rectangle rect() {
		return sprite.getBoundingRectangle();
	}
	
	public Tile create(float x, float y) {
		return new Tile(texture, canCollide, x, y);
	}
}