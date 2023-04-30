package com.barquitosfc.game;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.barquitosfc.game.DragonBoatGame.GameState;

import junit.framework.TestCase;

import static org.junit.Assert.*;


	

public class Pruebas extends TestCase{
	
	private Array<Rectangle> Rocas,Troncos,Cocodrilos;
	protected Stage stage;
	protected Table table;
	protected SpriteBatch batch;

	
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	public Barco jugador; 

	
	public void testSpawnRoca() {
		
		DragonBoatGame game = new DragonBoatGame();
		Rocas = new Array<Rectangle>();
	    game.spawnRoca(Rocas);
		
	}

	public void testSpawnTronco() {
	
		DragonBoatGame game = new DragonBoatGame();
		Troncos = new Array<Rectangle>();
		game.spawnTronco(Troncos);
	
	}

	public void testSpawnCocodrilo() {
	
		DragonBoatGame game = new DragonBoatGame();
		Cocodrilos = new Array<Rectangle>();
		game.spawnCocodrilo(Cocodrilos);
	
	}
	


	
}
