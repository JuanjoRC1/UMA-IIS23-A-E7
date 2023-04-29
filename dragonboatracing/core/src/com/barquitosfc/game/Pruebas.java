package com.barquitosfc.game;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.barquitosfc.game.DragonBoatGame.GameState;

import junit.framework.TestCase;

import static org.junit.Assert.*;
import  org.mockito.Mockito; 	

public class Pruebas extends TestCase{
	
	private Array<Rectangle> Rocas,Troncos,Cocodrilos;
	protected Stage stage;
	protected Table table;
	protected SpriteBatch batch;
	
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	

	

	public void testTienda() {
		
		 Tienda tienda = new Tienda();
	     tienda.iniciar(table,batch,stage);
		
	}
	
	public void testMovimiento() {
		
		DragonBoatGame game = new DragonBoatGame();
	     game.handleInput();
		
	}
	
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
	
	public void testMenu() {
		GameState gameState = GameState.MENU;
		stage = new Stage();
		table=new Table();
		table.setPosition(260,1080/7);
		table.setFillParent(true);
		table.setHeight(200);
		stage.addActor(table);	
		table.clear();
        batch.begin();
        batch.draw(board, 0, 0);
        batch.end();
		//Botones Inicio
		Button buttonPlay= new Button(new Button.ButtonStyle(spriteBInicio,spriteBInicio,spriteBInicio));
//		TextButton buttonPlay= new TextButton("Inicio",getSkin());
		buttonPlay.setPosition(table.getOriginX(), table.getOriginY());
		buttonPlay.setSize(200,40);
		buttonPlay.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				gameState=GameState.PLAY;
				return false;
				
			}
		});
		table.addActor(buttonPlay);
		//BOTON
//		TextButton buttonConfig= new TextButton("Opciones",getSkin());
		Button buttonConfig= new Button(new Button.ButtonStyle(spriteBAjustes,spriteBAjustes,spriteBAjustes));
		buttonConfig.setPosition(buttonPlay.getX()+buttonPlay.getWidth()+200, table.getOriginY());
		buttonConfig.setSize(200,40);

		buttonConfig.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				gameState=GameState.CONFIG;
				return false;
			}
		});
		table.addActor(buttonConfig);
		//BOTON
		Button buttonShop= new Button(new Button.ButtonStyle(spriteBTienda,spriteBTienda,spriteBTienda));
//		TextButton buttonShop= new TextButton("Tienda",getSkin());
		buttonShop.setPosition(buttonConfig.getX()+buttonConfig.getWidth()+200, table.getOriginY());
		buttonShop.setWidth(200);
		buttonShop.setHeight(40);
		buttonShop.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				gameState=GameState.SHOP;
				return false;
			}
		});
		table.addActor(buttonShop);
		//BOTON
//		TextButton buttonQuit= new TextButton("Salir",skin);
		Button buttonQuit= new Button(new Button.ButtonStyle(spriteBSalir,spriteBSalir,spriteBSalir));
		buttonQuit.setPosition(buttonShop.getX()+buttonShop.getWidth()+200, table.getOriginY());
		buttonQuit.setWidth(200);
		buttonQuit.setHeight(40);
		buttonQuit.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				gameState=GameState.QUIT;
				return false;
			}
		});
		table.addActor(buttonQuit);
		//stage.act(Gdx.graphics.getDeltaTime()); Asumimos que es True
		stage.draw();
		//Gdx.input.setInputProcessor(stage); Asumimos que es True
	}
	
	
	
	
}
