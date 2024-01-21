package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.rivelbop.rivelgdx.graphics.ui.Button;
import com.rivelbop.rivelgdx.graphics.ui.ButtonListener;
import com.rivelbop.rivelgdx.graphics.ui.Font;
import com.rivelbop.rivelgdx.math.FadeEffect;
import com.rivelbop.rivelgdx.screen.Core;
import com.rivelbop.rivelgdx.screen.Scene;
import com.rivelbop.rivelgdx.utils.Audio;

public class DeathMenu extends Scene{
	
	public Font deathText, spaceText; 
	public FadeEffect startFade, spaceFade; 
	public boolean isSpacePressed;


	public DeathMenu(Core core) {
		super(core);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		deathText = new Font("antiquity-print.ttf", 40, Color.RED);
		spaceText = new Font("antiquity-print.ttf", 25,Color.YELLOW);
		startFade = new FadeEffect(1f, false);
		spaceFade = new FadeEffect(1f, true);
		Audio.playSound("deathSound.wav", 1f, 1f);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			isSpacePressed = true;
		}
		
		if(spaceFade.isComplete()) {
			CORE.setScreen(new GameScene(CORE));
		}
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Main.uiBatch.begin();
		deathText.renderCenter(Main.getWidth() / 2f, Main.getHeight() / 2f + 200f, "You Died");
		spaceText.renderCenter(Main.getWidth() / 2f, Main.getHeight() / 2f - 100f, "Press Space To Retry");
		Main.uiBatch.end();
		
		Main.shapeRenderer.begin();
		startFade.render();
		if(isSpacePressed) {
			spaceFade.render();
		}
		Main.shapeRenderer.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		deathText.dispose();
		spaceText.dispose();
		
	}
	
	

}
