package com.rivelbop.rivelgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.rivelbop.rivelgdx.screen.Core;

// Replacement for LibGDX's ParticleEffect class
public class ParticleEmitter implements Disposable {
    private ParticleEffect particleEffect;
    private float currentTime;
    private float lifeTime;

    // Creates particles based on the position, life time, and file provided
    public ParticleEmitter(float x, float y, float lifeTime, String name) {
        particleEffect = new ParticleEffect(Core.assets.get(name, ParticleEffect.class));
        particleEffect.setPosition(x, y);
        this.lifeTime = lifeTime;
    }

    // Update the particle effect
    public void update() {
        particleEffect.update(Gdx.graphics.getDeltaTime());
        currentTime += Gdx.graphics.getDeltaTime();
    }
    // Render the particleEffect to the GameCore SpriteBatch
    public void render() {
    	particleEffect.draw(Core.batch);
    }
    
    // Render the particleEffect to the provided batch
    public void render(SpriteBatch batch) {
        particleEffect.draw(batch);
    }
    
    // Checks to see if the particle has finished emitting
    public boolean isComplete() {
        return currentTime >= lifeTime;
    }

    // Dispose of particleEffect
    @Override
    public void dispose() {
        particleEffect.dispose();
    }

    // Get the default LibGDX ParticleEffect stored in a ParticleEmitter object
    public ParticleEffect getParticle() {
        return particleEffect;
    }
}