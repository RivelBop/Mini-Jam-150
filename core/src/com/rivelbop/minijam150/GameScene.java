package com.rivelbop.minijam150;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.rivelbop.rivelgdx.graphics.ui.Font;
import com.rivelbop.rivelgdx.math.FadeEffect;
import com.rivelbop.rivelgdx.screen.Core;
import com.rivelbop.rivelgdx.screen.Scene;
import com.rivelbop.rivelgdx.utils.Audio;

public class GameScene extends Scene {
	public Player player;
	public FadeEffect startFade;
	public Tile[][] tiles;
	public ArrayList<Spike> spikes;
	public ArrayList<Torch> torches;
	//public ArrayList<Wolf> wolfs; 
	public Font counterTorch; 
	public int torchCounter = 10;

	public GameScene(Core core) {
		super(core);
	}

	@Override
	public void init() {
		player = new Player(64, 64);
		
		spikes = new ArrayList<>();
		spikes.add(new Spike(1,3));
		spikes.add(new Spike(1,2));
		spikes.add(new Spike(2,4));
		spikes.add(new Spike(2,5));
		
		torches = new ArrayList<>();
		/*
		wolfs = new ArrayList<>();
		wolfs.add(new Wolf(5,4));
		*/
		counterTorch = new Font("antiquity-print.ttf", 30, Color.YELLOW);
		
		startFade = new FadeEffect(1f, false);
		
		// Load Map
		int[][] map = TileMap.readToID("lvl1");
		tiles = new Tile[map.length][map[0].length];
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length; x++) {
				tiles[y][x] = Tile.TILES[map[y][x]].create(x * Tile.SIZE, y * Tile.SIZE);
			}
		}
	}

	@Override
	public void update() {
		player.update(tiles);
		
		// Place Torch
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && torchCounter > 0) {
			torches.add(new Torch(player.sprite.getX(), player.sprite.getY()));
			torchCounter --;
			Audio.playSound("placeTorch.wav", 1f, 1f);
		}
		
		// Reset Lighting
		for(Tile[] ty : tiles) {
			for(Tile t : ty) {
				t.sprite.setAlpha(0.075f);
			}
		}
		
		// Create Lighting
		for(Torch t : torches) {
			int gridX = (int) (t.sprite.getBoundingRectangle().x / Tile.SIZE);
			int gridY = (int) (t.sprite.getBoundingRectangle().y / Tile.SIZE);
			
			for(int dirY = -2; dirY < 2; dirY++) {
				for(int dirX = -2; dirX < 2; dirX++) {
					if(gridX + dirX > -1 && gridY + dirY > -1 && gridY + dirY < tiles.length && gridX + dirX < tiles[gridY + dirY].length) {
						if(tiles[gridY + dirY][gridX + dirX].sprite.getColor().a == 0.075f || (1f - (0.15f * Math.sqrt(dirY * dirY + dirX * dirX))) > tiles[gridY + dirY][gridX + dirX].sprite.getColor().a) {
							tiles[gridY + dirY][gridX + dirX].sprite.setAlpha((float) (1f - (0.15f * Math.sqrt(dirY * dirY + dirX * dirX))));
						}
					}
				}
			}
			
		}
		
		// Pickup Torch
		if(torchCounter != 10) {
			for(int i = torches.size() - 1; i > -1; i--) {
				if(player.sprite.getBoundingRectangle().overlaps(torches.get(i).sprite.getBoundingRectangle()) && Gdx.input.isKeyJustPressed(Keys.E)) {
					torches.remove(i);
					torchCounter ++;
					Audio.playSound("pickupTorch.wav", 1f, 1f);
					break;
				}
			}
		}
		
		// Check spike collisions
		for(Spike s : spikes) {
			if(player.sprite.getBoundingRectangle().overlaps(s.sprite.getBoundingRectangle())) {
				CORE.setScreen(new DeathMenu(CORE));
			}
		}
		/*
		// Check spike collisions
		for(Wolf w : wolfs) {
			if(player.sprite.getBoundingRectangle().overlaps(w.sprite.getBoundingRectangle())) {
				CORE.setScreen(new DeathMenu(CORE));
			}
		}*/
	}

	@Override
	public void render() {
		Main.batch.begin();
		
		for(Tile[] ty : tiles) {
			for(Tile t : ty) {
				t.render();
			}
		}
		player.render();
		for(Spike s : spikes) {
			s.render();
		}
		for(Torch t : torches) {
			t.render();
		}
		
		/*for(Wolf w : wolfs) {
			w.render();
		}*/
		Main.batch.end();
		
		Main.uiBatch.begin();
		counterTorch.renderCenter(20, Main.getHeight() / 2f + 200f, Integer.toString(torchCounter));
		Main.uiBatch.end();
		
		Main.shapeRenderer.begin();
		startFade.render();
		Main.shapeRenderer.end();
	}

	@Override
	public void dispose() {
		
	}
}