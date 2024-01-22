package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Scaling;
import com.rivelbop.rivelgdx.screen.Core;

public class Main extends Core {
	private boolean gameLoaded;
	
	public Main() {
		super(Scaling.stretch, 640, 360);
	}
	
	@Override
	public void init() {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}

	@Override
	public void update() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		if(!gameLoaded && Main.assets.isFinished()) {
			setScreen(new MainMenu(this));
			gameLoaded = true;
		}
	}
}