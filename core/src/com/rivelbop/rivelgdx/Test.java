package com.rivelbop.rivelgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.rivelbop.rivelgdx.graphics.AnimatedSprite;
import com.rivelbop.rivelgdx.graphics.ui.Font;
import com.rivelbop.rivelgdx.screen.Core;
import com.rivelbop.rivelgdx.screen.Scene;

public class Test extends Scene {
	AnimatedSprite sprite;
	Font font;
	
	public Test(Core core) {
		super(core);
	}
	
	//float x, float y, String atlas, String baseAnimation, float frameDelay, PlayMode playMode
	
	@Override
	public void init() {
		sprite = new AnimatedSprite(100f, 100f, "player.atlas", "walk", 0.6f, PlayMode.LOOP);
		font = new Font("font.otf", 72, Color.YELLOW);
		sprite.flip(true, false);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		Main.batch.begin();
		sprite.render();
		font.render(600f, 400f, "Hey There!", Main.batch);
		Main.batch.end();
	}

	@Override
	public void dispose() {
		font.dispose();
	}
}