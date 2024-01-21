package com.rivelbop.rivelgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.rivelbop.rivelgdx.screen.Core;

// Animation using AtlasRegions from LibGDX's TextureAtlas class
public class AtlasAnimation {
	private float animationTime;
    private Animation<TextureAtlas.AtlasRegion> animation;
    
    // Create an animation based on the provided TextureAtlas, region name, frame delay, and play mode
    public AtlasAnimation(String atlas, String name, float frameDelay, PlayMode playMode) {
    	animation = new Animation<>(frameDelay, Core.assets.get(atlas, TextureAtlas.class).findRegions(name), playMode);
    }
    
    // Update the animation based on the provided (outside) timer and return the current frame of the animation
    public AtlasRegion update(float animationTime) {
        return animation.getKeyFrame(animationTime);
    }
    
    // Update the animation based on the inner (inside) timer, animationTime and return the current frame of the animation
    public AtlasRegion update() {
    	animationTime += Gdx.graphics.getDeltaTime();
    	return animation.getKeyFrame(animationTime);
    }
    
    // Get the default LibGDX Animation object stored within an AtlasAnimation object
    public Animation<TextureAtlas.AtlasRegion> getAnimation() {
    	return animation;
    }
}