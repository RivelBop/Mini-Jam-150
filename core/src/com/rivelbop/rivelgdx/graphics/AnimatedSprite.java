package com.rivelbop.rivelgdx.graphics;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rivelbop.rivelgdx.screen.Core;

// Animated variant of the LibGDX Sprite class
public class AnimatedSprite extends Sprite {
    private float animationTime, frameDelay;
    private String atlas;
    private PlayMode playMode;
    private HashMap<String, AtlasAnimation> animations;
    private AtlasAnimation currentAnimation;
	
    // Takes the position, default animation, frame delay, and playmode to create an animated sprite
	public AnimatedSprite(float x, float y, String atlas, String baseAnimation, float frameDelay, PlayMode playMode) {
		super(Core.assets.get(atlas, TextureAtlas.class).findRegion(baseAnimation));
		setPosition(x, y);
		
		animations = new HashMap<>();
		animations.put(baseAnimation, new AtlasAnimation(atlas, baseAnimation, frameDelay, playMode));
		currentAnimation = animations.get(baseAnimation);
		
		this.atlas = atlas;
		this.frameDelay = frameDelay;
		this.playMode = playMode;
	}
	
	// Update the animation and render the sprite on the GameCore SpriteBatch
	public void render() {
		updateCurrentAnimation();
		draw(Core.batch);
	}
	
	// Update the animation and render the sprite on the given SpriteBatch
	public void render(SpriteBatch batch) {
		updateCurrentAnimation();
		draw(batch);
	}
	
	// Change the current animation of the sprite to the provided name
	public void setAnimation(String animation) {
		if(animations.containsKey(animation)) {
			currentAnimation = animations.get(animation);
			return;
		}
		
		animations.put(animation, new AtlasAnimation(atlas, animation, frameDelay, playMode));
		currentAnimation = animations.get(animation);
	}
	
	// Set the frame delay of the sprite's animations to the provided value
	public void setFrameDelay(float frameDelay) {
		this.frameDelay = frameDelay;
		
		for(AtlasAnimation animation : animations.values()) {
			animation.getAnimation().setFrameDuration(frameDelay);
		}
	}
	
	// Set the play mode of the sprite's animation to the provided value
	public void setPlayMode(PlayMode playMode) {
		this.playMode = playMode;
		
		for(AtlasAnimation animation : animations.values()) {
			animation.getAnimation().setPlayMode(playMode);
		}
	}
	
	// Updates the animation, flips it if necessary, and sets the sprite to it
	private void updateCurrentAnimation() {
		animationTime += Gdx.graphics.getDeltaTime();
		currentAnimation.update(animationTime).flip(currentAnimation.update(animationTime).isFlipX() != isFlipX(), false);
		setRegion(currentAnimation.update(animationTime));
	}
}