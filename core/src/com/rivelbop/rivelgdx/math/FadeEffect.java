package com.rivelbop.rivelgdx.math;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.rivelbop.rivelgdx.graphics.ShapeRenderer;
import com.rivelbop.rivelgdx.screen.Core;

// Create a "fade effect" by using a black rectangle and changing its opacity
public class FadeEffect {
    private Interpolator fade;
    private Rectangle fadeBox;
    private boolean fadeIn;
    
    // Creates a fade effect with the provided duration and whether or not it should fade in or out
    public FadeEffect(float lifetime, boolean fadeIn) {
        fade = new Interpolator(Interpolation.fade, lifetime);
        fadeBox = new Rectangle();
        fadeBox.setSize(Core.getWidth(), Core.getHeight());
        this.fadeIn = fadeIn;
    }
    
    // Update and render the fadeBox on the GameCore ShapeRenderer
    public void render() {
        if (!isComplete()) {
            if (fadeIn) {
            	Core.shapeRenderer.setColor(0f, 0f, 0f, fade.update());
            } else {
            	Core.shapeRenderer.setColor(0f, 0f, 0f, 1f - fade.update());
            }
            Core.shapeRenderer.rect(fadeBox.x, fadeBox.y, fadeBox.width, fadeBox.height);
        }
    }
    
    // Update and render the fadeBox on the provided ShapeRenderer
    public void render(ShapeRenderer renderer) {
        if (!isComplete()) {
            if (fadeIn) {
            	renderer.setColor(0f, 0f, 0f, fade.update());
            } else {
            	renderer.setColor(0f, 0f, 0f, 1f - fade.update());
            }
            renderer.rect(fadeBox.x, fadeBox.y, fadeBox.width, fadeBox.height);
        }
    }
    
    // Update and render the fadeBox on the GameCore ShapeRenderer with the provided maximum opacity
    public void render(float maxOpacity) {
    	if (!isComplete()) {
            if (fadeIn) {
            	Core.shapeRenderer.setColor(0f, 0f, 0f, fade.update() * maxOpacity);
            } else {
            	Core.shapeRenderer.setColor(0f, 0f, 0f, (1f - fade.update()) * maxOpacity);
            }
            Core.shapeRenderer.rect(fadeBox.x, fadeBox.y, fadeBox.width, fadeBox.height);
        }
    }
    
    // Update and render the fadeBox on the provided ShapeRenderer with the provided maximum opacity
    public void render(ShapeRenderer renderer, float maxOpacity) {
        if (!isComplete()) {
            if (fadeIn) {
            	renderer.setColor(0f, 0f, 0f, fade.update() * maxOpacity);
            } else {
            	renderer.setColor(0f, 0f, 0f, (1f - fade.update()) * maxOpacity);
            }
            renderer.rect(fadeBox.x, fadeBox.y, fadeBox.width, fadeBox.height);
        }
    }
    
    // Checks to see if the fade interpolator is finished interpolating
    public boolean isComplete() {
        return fade.isComplete();
    }
}