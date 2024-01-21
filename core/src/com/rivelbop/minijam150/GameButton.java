package com.rivelbop.minijam150;

import com.badlogic.gdx.graphics.Color;
import com.rivelbop.rivelgdx.graphics.ui.Button;
import com.rivelbop.rivelgdx.graphics.ui.ButtonListener;

public class GameButton extends Button {

	public GameButton(String text, float x, float y, ButtonListener listener) {
		super(text, x, y, listener);
	}

	@Override
	public String atlas() {
		// TODO Auto-generated method stub
		return "ui.atlas";
	}

	@Override
	public String buttonImg() {
		// TODO Auto-generated method stub
		return "button_default";
	}

	@Override
	public String buttonPressedImg() {
		// TODO Auto-generated method stub
		return "button_pressed";
	}

	@Override
	public String font() {
		// TODO Auto-generated method stub
		return "antiquity-print.ttf";
	}

	@Override
	public String hoverSound() {
		// TODO Auto-generated method stub
		return "button.wav";
	}

	@Override
	public String clickSound() {
		// TODO Auto-generated method stub
		return "button.wav";
	}

	@Override
	public int fontSize() {
		// TODO Auto-generated method stub
		return 32;
	}

	@Override
	public Color fontColor() {
		// TODO Auto-generated method stub
		return Color.WHITE;
	}

}
