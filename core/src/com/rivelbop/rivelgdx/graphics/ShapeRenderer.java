package com.rivelbop.rivelgdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

// Advanced ShapeRenderer with ability to change shape opacity
public class ShapeRenderer extends com.badlogic.gdx.graphics.glutils.ShapeRenderer {
	@Override
	public void begin() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
    	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		super.begin(ShapeType.Filled);
	}
	
	@Override
	public void begin(ShapeType shapeType) {
		Gdx.gl.glEnable(GL20.GL_BLEND);
    	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		super.begin(shapeType);
	}
	
	@Override
	public void end() {
		super.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
}