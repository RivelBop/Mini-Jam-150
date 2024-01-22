package com.rivelbop.minijam150;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Main main = new Main();
		config.useVsync(true); 
		config.setTitle("Long Lost Wizard");
		//config.setWindowedMode(Main.getWidth(), Main.getHeight());
		//config.setFullscreenMode(Gdx.graphics.getDisplayMode());
		new Lwjgl3Application(main, config);
	}
}