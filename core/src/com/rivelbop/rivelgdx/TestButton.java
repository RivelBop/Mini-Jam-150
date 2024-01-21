package com.rivelbop.rivelgdx;

import com.badlogic.gdx.graphics.Color;
import com.rivelbop.rivelgdx.graphics.ui.Button;
import com.rivelbop.rivelgdx.graphics.ui.ButtonListener;

public class TestButton extends Button {

	public TestButton(String text, float x, float y, ButtonListener listener) {
		super(text, x, y, listener);
	}

	@Override
	public String atlas() {
		return "ui.atlas";
	}

	@Override
	public String buttonImg() {
		return "button_default";
	}

	@Override
	public String buttonPressedImg() {
		return "button_pressed";
	}

	@Override
	public String font() {
		// TODO Auto-generated method stub
		return "font.otf";
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
		return 36;
	}

	@Override
	public Color fontColor() {
		// TODO Auto-generated method stub
		return Color.RED;
	}

}
