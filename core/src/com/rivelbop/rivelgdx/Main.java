package com.rivelbop.rivelgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.rivelbop.rivelgdx.graphics.ui.ButtonListener;
import com.rivelbop.rivelgdx.math.FadeEffect;
import com.rivelbop.rivelgdx.screen.Core;

public class Main extends Core {
	Sprite sprite;
	FadeEffect fade;
	TestButton button;
	
	@Override
	public void init() {
		sprite = new Sprite(new Texture("badlogic.jpg"));
		fade = new FadeEffect(1f, false);
		
	}

	@Override
	public void update() {
		if(button == null && Main.assets.isFinished()) {
			button = new TestButton("Press", 100f, 100f, new ButtonListener() {

				@Override
				public void onPressed() {
					// TODO Auto-generated method stub
					System.out.println("Button Pressed!");
					Main.super.setScreen(new Test(Main.this));
				}
				
			});
		}
		
		//System.out.println(Gdx.input.getX() + " " + (-Gdx.input.getY() + Main.getHeight()));
		
		if(button != null) {
			button.isPressed();
		}
		
		Main.batch.begin();
		//sprite.draw(Main.batch);
		Main.batch.end();
		
		Main.uiBatch.begin();
		button.render();
		Main.uiBatch.end();
		
		Main.shapeRenderer.begin();
		fade.render();
		Main.shapeRenderer.end();
	}
}