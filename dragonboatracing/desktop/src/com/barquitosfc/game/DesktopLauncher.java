package com.barquitosfc.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.barquitosfc.game.DragonBoatGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Dragonboatracing");
		config.setWindowedMode(1920, 1080);
		config.useVsync(true);
		config.setForegroundFPS(120);
		new Lwjgl3Application(new DragonBoatGame(), config);
	}
}
