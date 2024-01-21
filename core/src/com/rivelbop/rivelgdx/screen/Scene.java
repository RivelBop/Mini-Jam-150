package com.rivelbop.rivelgdx.screen;

import com.badlogic.gdx.Screen;

// A "more efficient" variant of the default LibGDX Screen class
public abstract class Scene implements Screen {
	public final Core CORE;
	
	// Creates a screen based off the provided core
	public Scene(Core core) {
		CORE = core;
	}
	
	// All necessary screen methods used to initialize, update, render, and dispose of the screen
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void dispose();
	
	// Used to call newly implemented init() method (which simply has a better name over 'show()')
	@Override
	public void show() {
		init();
	}
	
	// Default LibGDX render method, now used to called update() and render()
	@Override
	public void render(float delta) {
		update();
		render();
	}
	
	// Resizes the GameCore viewport if necessary (if window is resized)
	@Override
	public void resize(int width, int height) {
		Core.viewport.update(width, height);
	}
	
	// Useless default LibGDX method
	@Override
	public void pause() {
		// EMPTY
	}
	
	// Useless default LibGDX method
	@Override
	public void resume() {
		// EMPTY
	}
	
	// Disposes of the screen when the screen is no longer active
	@Override
	public void hide() {
		dispose();
	}
}