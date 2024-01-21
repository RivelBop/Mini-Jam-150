package com.rivelbop.minijam150;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;
import com.rivelbop.rivelgdx.utils.Audio;

public class Torch {
	public AnimatedSprite sprite;
	
	public Torch(float x, float y) {
		sprite = new AnimatedSprite(0f,0f,"torch.atlas", "torch", 0.06f, PlayMode.LOOP);
		sprite.setPosition(x, y);
	}
	
	public void render() {
		sprite.render();
	}
	
	public void update() {
		
	}
}
