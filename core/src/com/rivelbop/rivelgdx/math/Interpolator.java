package com.rivelbop.rivelgdx.math;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;

// Used to simplify interpolation with the built in LibGDX interpolation variants
public class Interpolator {
    private final Interpolation interpolation;
    private final float lifeTime;
    private float currentTime;
    private boolean isComplete;
    
    // Create an interpolation based off the specified interpolation type and duration
    public Interpolator(Interpolation interpolation, float lifeTime) {
        this.interpolation = interpolation;
        this.lifeTime = lifeTime;
    }
    
    // Update the interpolation
    public float update() {
        currentTime += Gdx.graphics.getDeltaTime();

        float interVal = interpolation.apply(Math.min(1f, currentTime / lifeTime));
        isComplete = (interVal == 1);
        return interVal;
    }
    
    // Lets the user know if the interpolation is complete
    public boolean isComplete() {
        return isComplete;
    }
}