package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;

public class Player {
	public AnimatedSprite sprite;
	public float speed;
	private boolean isMoving;
	
	private boolean topCollision, bottomCollision, leftCollision, rightCollision;
    public Rectangle topCollider, bottomCollider, leftCollider, rightCollider;
    private static final float COLLIDER_THICKNESS = 5f;
	
	public Player(float x, float y) {
		sprite = new AnimatedSprite(x, y,"player.atlas", "idle", 0.06f, PlayMode.LOOP);
		speed = 100f;
		
		topCollider = new Rectangle(getBounds().x, getBounds().y + getBounds().height, getBounds().width, COLLIDER_THICKNESS);
        bottomCollider = new Rectangle(getBounds().x, getBounds().y - COLLIDER_THICKNESS, getBounds().width, COLLIDER_THICKNESS);
        leftCollider = new Rectangle(getBounds().x - COLLIDER_THICKNESS, getBounds().y, COLLIDER_THICKNESS, getBounds().height);
        rightCollider = new Rectangle(getBounds().x + getBounds().width, getBounds().y, COLLIDER_THICKNESS, getBounds().height);
	}
	
	public void update(Tile[][] tiles) {
		isMoving = false;
        updateCollisions(tiles);
        
		if(Gdx.input.isKeyPressed(Keys.W) && !topCollision) {
			sprite.translateY(speed * Gdx.graphics.getDeltaTime());
			isMoving = true;
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) && !leftCollision) {
			sprite.translateX(-speed * Gdx.graphics.getDeltaTime());
			sprite.setFlip(true, false);
			isMoving = true;
		}
		
		if(Gdx.input.isKeyPressed(Keys.S) && !bottomCollision) {
			sprite.translateY(-speed * Gdx.graphics.getDeltaTime());
			isMoving = true;
		}
		
		if(Gdx.input.isKeyPressed(Keys.D) && !rightCollision) {
			sprite.translateX(speed * Gdx.graphics.getDeltaTime());
			sprite.setFlip(false, false);
			isMoving = true;
		}
		
		if(isMoving) {
			sprite.setAnimation("walk");
		} else {
			sprite.setAnimation("idle");
		}	
	}
	
	public void render() {
		sprite.render();
	}
	
	public Rectangle getBounds() {
		return sprite.getBoundingRectangle();
	}
	
	public void updateCollisions(Tile[][] tileMap) {
		// Update collision box positions
        topCollider.setPosition(getBounds().x, getBounds().y + getBounds().height);
        bottomCollider.setPosition(getBounds().x, getBounds().y - COLLIDER_THICKNESS);
        leftCollider.setPosition(getBounds().x - COLLIDER_THICKNESS, getBounds().y);
        rightCollider.setPosition(getBounds().x + getBounds().width, getBounds().y);

        // Reset collision detections
        topCollision = false;
        bottomCollision = false;
        leftCollision = false;
        rightCollision = false;
        
		int gridX = (int) (getBounds().x / Tile.SIZE);
		int gridY = (int) (getBounds().y / Tile.SIZE);
		
		if(tileMap != null && gridX > -1 && gridY > -1 && gridY < tileMap.length && gridX < tileMap[gridY].length) {
			topCollision =
	                isColliding(topCollider, tileMap, gridX, gridY, 0, 1) ||
	                isColliding(topCollider, tileMap, gridX, gridY, -1, 1) ||
	                isColliding(topCollider, tileMap, gridX, gridY, 1, 1);
	        bottomCollision =
	                isColliding(bottomCollider, tileMap, gridX, gridY, 0, -1) ||
	                isColliding(bottomCollider, tileMap, gridX, gridY, -1, -1) ||
	                isColliding(bottomCollider, tileMap, gridX, gridY, 1, -1);
	        leftCollision =
	                isColliding(leftCollider, tileMap, gridX, gridY, -1, 0) ||
	                isColliding(leftCollider, tileMap, gridX, gridY, -1, 1) ||
	                isColliding(leftCollider, tileMap, gridX, gridY, -1, -1);
	        rightCollision =
	                isColliding(rightCollider, tileMap, gridX, gridY, 1, 0) ||
	                isColliding(rightCollider, tileMap, gridX, gridY, 1, 1) ||
	                isColliding(rightCollider, tileMap, gridX, gridY, 1, -1);
		}
	}
	
	private boolean isColliding(Rectangle collider, Tile[][] walls, int gridX, int gridY, int dirX, int dirY) {
		if(walls != null && gridX > -1 && gridY > -1 && gridY < walls.length && gridX < walls[gridY].length) {
			if(gridX + dirX > -1 && gridY + dirY > -1 && gridY + dirY < walls.length && gridX + dirX < walls[gridY + dirY].length)
			return walls[gridY + dirY][gridX + dirX] != null && walls[gridY + dirY][gridX + dirX].canCollide && collider.overlaps(walls[gridY + dirY][gridX + dirX].sprite.getBoundingRectangle());
		}
		return false;
    }
}