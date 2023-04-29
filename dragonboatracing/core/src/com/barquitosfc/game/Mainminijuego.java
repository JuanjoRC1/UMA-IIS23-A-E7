package com.barquitosfc.game;

import com.badlogic.gdx.Game;
import com.barquitosfc.game.minigame.flappy.GameScreen;

public class Mainminijuego extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assetsmini.load();
		setScreen(new GameScreen(this));
	}

}
