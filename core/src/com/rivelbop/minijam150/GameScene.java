package com.rivelbop.minijam150;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.rivelbop.rivelgdx.graphics.ParticleEmitter;
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
	public ArrayList<Wolf> wolfs; 
	public ArrayList <Ghost> ghosts;
	public Font counterTorch; 
	public int torchCounter = 5;
	public ArrayList<ParticleEmitter> particles;
	public Sprite portal;
	public int level;

	private final Level[] LEVELS = {
			// Level 1
			new Level() {
				@Override
				public void obstacles() {
					portalX = 33;
					portalY = 2;
				}
			},
			
			// Level 2
			new Level() {
			@Override
			public void obstacles() {
					spikes.add(new Spike(5, 4));
					spikes.add(new Spike(5, 3));
					spikes.add(new Spike(15, 4));
					spikes.add(new Spike(18, 1));
					spikes.add(new Spike(22, 1));
					spikes.add(new Spike(22, 2));
					spikes.add(new Spike(25, 2));
					spikes.add(new Spike(27, 4));
					spikes.add(new Spike(32, 1));
					spikes.add(new Spike(32, 2));
					
					portalX = 34;
					portalY = 2;
				}
			},
		
			// Level 3
			new Level() {
				@Override
				public void obstacles() {
					spikes.add(new Spike(5, 8));
					spikes.add(new Spike(6, 8));
					spikes.add(new Spike(7, 8));
					spikes.add(new Spike(13, 6));
					spikes.add(new Spike(12, 8));
					wolves.add(new Wolf(11,5));
					wolves.add(new Wolf(14,7));
					
					portalX = 17;
					portalY = 2;
				}
			},
			
			// Level 4
			new Level() {
				@Override
				public void obstacles() {
					spikes.add(new Spike(2, 1));
					spikes.add(new Spike(7,6));
					spikes.add(new Spike(5,8));
					spikes.add(new Spike(10,6));
					spikes.add(new Spike(12,8));
					wolves.add(new Wolf(16,3));
					wolves.add(new Wolf(20,1));
					wolves.add(new Wolf(24,3));
					ghosts.add(new Ghost(20,6));
					ghosts.add(new Ghost(32,6));
					
					portalX = 39;
					portalY = 2;
				}
			}
	};
	
	public GameScene(Core core, int level) {
		super(core);
		this.level = level;
	}

	@Override
	public void init() {
		player = new Player(64, 64);
		torches = new ArrayList<>();
		
		LEVELS[level - 1].obstacles();
		portal = new Sprite(Main.assets.get("portal.png", Texture.class));
		portal.setCenter(LEVELS[level - 1].portalX * Tile.SIZE + Tile.SIZE / 2f, LEVELS[level - 1].portalY * Tile.SIZE + Tile.SIZE / 2f);
		
		spikes = new ArrayList<>();
		for(Spike s : LEVELS[level - 1].spikes) {
			Spike spike = new Spike(0, 0);
			spike.sprite.setPosition(s.sprite.getX(), s.sprite.getY());
			spikes.add(spike);
		}
		
		wolfs = new ArrayList<>();
		for(Wolf w : LEVELS[level - 1].wolves) {
			Wolf wolf = new Wolf(0, 0);
			wolf.sprite.setPosition(w.sprite.getX(), w.sprite.getY());
			wolfs.add(wolf);
		}
		
		ghosts = new ArrayList<>();
		for(Ghost g : LEVELS[level - 1].ghosts) {
			Ghost ghost = new Ghost(0, 0);
			ghost.sprite.setPosition(g.sprite.getX(), g.sprite.getY());
			ghosts.add(g);
		}
		
		particles = new ArrayList<>();
		
		counterTorch = new Font("antiquity-print.ttf", 20, Color.YELLOW);
		
		startFade = new FadeEffect(1f, false);
		
		// Load Map
		int[][] map = TileMap.readToID("lvl" + level);
		tiles = new Tile[map.length][map[0].length];
		
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[y].length; x++) {
				tiles[y][x] = Tile.TILES[map[y][x]].create(x * Tile.SIZE, y * Tile.SIZE);
				System.out.print(map[y][x] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void update() {
		player.update(tiles);
		
		// Place Torch
		if(Gdx.input.isKeyJustPressed(Keys.SPACE) && torchCounter > 0) {
			if(tiles[(int) ((player.sprite.getY() + player.sprite.getHeight() / 2f - 16f) / Tile.SIZE)][(int) ((player.sprite.getX() + player.sprite.getWidth() / 2f) / Tile.SIZE)].sprite.getColor().a != 1f) {
				torches.add(new Torch((int) ((player.sprite.getX() + player.sprite.getWidth() / 2f) / Tile.SIZE) * Tile.SIZE + Tile.SIZE / 2f - 8f, (int) ((player.sprite.getY() + player.sprite.getHeight() / 2f - 16f) / Tile.SIZE) * Tile.SIZE + Tile.SIZE / 2f - 8f));
				torchCounter --;
				Audio.playSound("placeTorch.wav", 1f, 1f);
			}
		}
		
		if(player.isMoving && player.walkParticleTimer >= 0.2f) {
			ParticleEmitter p = new ParticleEmitter(player.sprite.getX() + player.sprite.getWidth() / 2f, player.sprite.getY(), 0.5f, "dustparticle.p");
			p.getParticle().scaleEffect(0.3f);			
			particles.add(p);
			player.walkParticleTimer = 0f;
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
			if(particles.get(i).isComplete()) {
				particles.remove(i);
				i--;
			}
		}
		
		// Reset Lighting
		for(Tile[] ty : tiles) {
			for(Tile t : ty) {
				t.sprite.setAlpha(0f);
			}
		}
		
		// Create Lighting
		for(Torch t : torches) {
			int gridX = (int) (t.sprite.getBoundingRectangle().x / Tile.SIZE);
			int gridY = (int) (t.sprite.getBoundingRectangle().y / Tile.SIZE);
			
			for(int dirY = -2; dirY < 2; dirY++) {
				for(int dirX = -2; dirX < 2; dirX++) {
					if(gridX + dirX > -1 && gridY + dirY > -1 && gridY + dirY < tiles.length && gridX + dirX < tiles[gridY + dirY].length) {
						if(tiles[gridY + dirY][gridX + dirX].sprite.getColor().a == 0f || (1f - (0.15f * Math.sqrt(dirY * dirY + dirX * dirX))) > tiles[gridY + dirY][gridX + dirX].sprite.getColor().a) {
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
		
		for(Spike s : spikes) {
			int gridX = (int)((s.sprite.getX() + s.sprite.getWidth() / 2f) / Tile.SIZE);
			int gridY = (int)((s.sprite.getY() + s.sprite.getHeight() / 2f) / Tile.SIZE);
			
			if(gridX > -1 && gridY > -1 && gridY < tiles.length && gridX < tiles[gridY].length) {
				s.sprite.setAlpha(tiles[gridY][gridX].sprite.getColor().a);
			}
			
			if(player.sprite.getBoundingRectangle().overlaps(s.sprite.getBoundingRectangle())) {
				CORE.setScreen(new DeathMenu(CORE, level));
			}
		}
		
		for(Wolf w : wolfs) {
			w.update();
			
			int gridX = (int)((w.sprite.getX() + w.sprite.getWidth() / 2f) / Tile.SIZE);
			int gridY = (int)((w.sprite.getY() + w.sprite.getHeight() / 2f) / Tile.SIZE);
			
			if(gridX > -1 && gridY > -1 && gridY < tiles.length && gridX < tiles[gridY].length) {
				w.sprite.setAlpha(tiles[gridY][gridX].sprite.getColor().a);
			}
			
			if(player.sprite.getBoundingRectangle().overlaps(w.sprite.getBoundingRectangle())) {
				CORE.setScreen(new DeathMenu(CORE, level));
			}
		}
		
		for(Ghost g : ghosts) {
			g.update(player);
			int gridX = (int)((g.sprite.getX() + g.sprite.getWidth() / 2f) / Tile.SIZE);
			int gridY = (int)((g.sprite.getY() + g.sprite.getHeight() / 2f) / Tile.SIZE);
			
			if(gridX > -1 && gridY > -1 && gridY < tiles.length && gridX < tiles[gridY].length) {
				g.speed = g.NORMAL_SPEED * (1f - tiles[gridY][gridX].sprite.getColor().a * 0.5f);
			}
			
			if(player.sprite.getBoundingRectangle().overlaps(g.sprite.getBoundingRectangle())) {
				CORE.setScreen(new DeathMenu(CORE, level));
			}
		}
		
		if(level < 4 && player.sprite.getBoundingRectangle().overlaps(portal.getBoundingRectangle())) {
			level++;
			CORE.setScreen(new GameScene(CORE, level));
		}
		
		if(level == 4 && player.sprite.getBoundingRectangle().overlaps(portal.getBoundingRectangle())) {
			CORE.setScreen(new MainMenu(CORE));
		}
	}

	@Override
	public void render() {
		Main.viewport.getCamera().position.set(new Vector2(player.sprite.getX(), player.sprite.getY()), 0f);
		Main.viewport.getCamera().update();
		
		Main.batch.setProjectionMatrix(Main.viewport.getCamera().combined);
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
		
		for(Wolf w : wolfs) {
			w.render();
		}
		
		for(Ghost g : ghosts) {
			g.render();
		}
		
		for(ParticleEmitter p : particles) {
			p.render();
		}
		
		portal.draw(Main.batch);
		
		Main.batch.end();
		
		Main.uiBatch.begin();
		counterTorch.renderCenter(75, Main.getHeight() + Main.getHeight() / 2f - 50f, "Fire: " + Integer.toString(torchCounter));
		Main.uiBatch.end();
		
		Main.shapeRenderer.begin();
		startFade.render();
		Main.shapeRenderer.end();
	}

	@Override
	public void dispose() {
		counterTorch.dispose();
	}
}