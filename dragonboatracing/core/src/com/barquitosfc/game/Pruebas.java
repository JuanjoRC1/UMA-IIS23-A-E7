package com.barquitosfc.game;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import junit.framework.TestCase;

import static org.junit.Assert.*;

public class Pruebas extends TestCase{
	



	public void testTexturas() {
		
		 DragonBoatGame game = new DragonBoatGame();
	     game.create();
	     game.render();
		
	}
	
	public void testDispose() {
		
		DragonBoatGame game = new DragonBoatGame();
	     game.dispose();
		
	}
	
	public void testSpawnRoca() {
		
		DragonBoatGame game = new DragonBoatGame();
	    game.spawnRoca();
		
	}

	public void testSpawnTronco() {
	
		DragonBoatGame game = new DragonBoatGame();
		game.spawnTronco();
	
	}

	public void testSpawnCocodrilo() {
	
		DragonBoatGame game = new DragonBoatGame();
		
		game.spawnCocodrilo();
	
	}
	

	
	
	
	
}
