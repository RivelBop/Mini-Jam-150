package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.rivelbop.rivelgdx.graphics.ui.Font;
import com.rivelbop.rivelgdx.math.FadeEffect;
import com.rivelbop.rivelgdx.screen.Core;
import com.rivelbop.rivelgdx.screen.Scene;
import com.rivelbop.rivelgdx.utils.Audio;

public class MainMenu extends Scene{
	public Font startUpText, titleText;
	public FadeEffect startFade, spaceFade;
	public boolean isSpacePressed;
	public Music music;
	
	public MainMenu(Core core) {
		super(core);
	}

	@Override
	public void init() {
		titleText = new Font("antiquity-print.ttf", 40, Color.WHITE);
		startUpText = new Font("antiquity-print.ttf", 20, Color.YELLOW);
		startFade = new FadeEffect(1f, false);
		spaceFade = new FadeEffect(1f, true);
		music = Audio.playMusic("cavemusic.mp3", 0.5f, true);
		music.play();
	}

	@Override
	public void update() {
		if(!isSpacePressed && Gdx.input.isKeyJustPressed(Keys.SPACE) && startFade.isComplete()) {
			isSpacePressed = true; 
			Audio.playSound("start.wav", 1f, 1f);
		}
		
		if(spaceFade.isComplete()) {
			CORE.setScreen(new GameScene(CORE, 1));
		}
	}

	@Override
	public void render() {
		Main.uiBatch.begin();
		startUpText.renderCenter(Main.getWidth() / 2f, Main.getHeight() / 2f - 100f, "Press Space To Start");
		titleText.renderCenter(Main.getWidth() / 2f, Main.getHeight() / 2f + 200f, "Long Lost Wizard");
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
		titleText.dispose();
		startUpText.dispose();
	}
}