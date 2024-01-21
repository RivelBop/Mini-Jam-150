package com.rivelbop.minijam150;

import com.badlogic.gdx.utils.Scaling;
import com.rivelbop.rivelgdx.screen.Core;

public class Main extends Core {
	private boolean gameLoaded;
	
	public Main() {
		super(Scaling.stretch, 640, 360);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		if(!gameLoaded && Main.assets.isFinished()) {
			setScreen(new DeathMenu(this));
			gameLoaded = true;
		}
	}
}