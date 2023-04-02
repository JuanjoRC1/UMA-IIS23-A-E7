package com.barquitosfc.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ScreenUtils;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	private int dinero;
	private Skin skin;
	private Texture bInicio;
	private Texture bAjustes;
	private Texture bTienda;
	private Texture bSalir;
	private Texture bInicioCheck;
	private Stage stage;
	public static GameState gameState;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture board;
	private Texture boardPlay;
	private Texture boatTexture;
	private Barco boat;
	private Table table;
	private SpriteDrawable spriteBInicio;
	private SpriteDrawable spriteBAjustes;
	private SpriteDrawable spriteBTienda;
	private SpriteDrawable spriteBSalir;
	private SpriteDrawable spriteBInicioCheck;

	
	@Override
	public void create () {
		camera=new OrthographicCamera();
		camera.setToOrtho(false,1280,720);
		batch= new SpriteBatch();
		camera.update();
		gameState=GameState.MENU;
		stage = new Stage();
		//BOTONES
		Texture bInicio= new Texture(Gdx.files.internal("ui/Boton_INICIO.png"));
		Texture bAjustes= new Texture(Gdx.files.internal("ui/Boton_AJUSTES.png"));
		Texture bTienda= new Texture(Gdx.files.internal("ui/Boton_TIENDA.png"));
		Texture bSalir= new Texture(Gdx.files.internal("ui/Boton_SALIR.png"));
		Texture bInicioCheck= new Texture(Gdx.files.internal("ui/Boton_INICIOCHECK.png"));

		//BACKGROUND
		 board = new Texture(Gdx.files.internal("data/fondo.png"));
		 boardPlay = new Texture(Gdx.files.internal("data/fondoPlay.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/boat.jpeg"));
		 boat=crearBarco();
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));// sprite cuando esta sin apretar, apretado y con el raton encima
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 spriteBInicioCheck = new SpriteDrawable(new Sprite(bInicioCheck));
		
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(board, 0, 0);
        batch.end();
		
		switch(gameState) {
		case MENU:
			stage = new Stage();
			table=new Table();
			table.setPosition(150,720/7);
			table.setFillParent(true);
			table.setHeight(200);
			stage.addActor(table);
			//Botones Inicio
			Button buttonPlay= new Button(new Button.ButtonStyle(spriteBInicio,spriteBInicio,spriteBInicio));
//			TextButton buttonPlay= new TextButton("Inicio",getSkin());
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
//			TextButton buttonConfig= new TextButton("Opciones",getSkin());
			Button buttonConfig= new Button(new Button.ButtonStyle(spriteBAjustes,spriteBAjustes,spriteBAjustes));
			buttonConfig.setPosition(table.getOriginX()+250, table.getOriginY());
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
//			TextButton buttonShop= new TextButton("Tienda",getSkin());
			buttonShop.setPosition(table.getOriginX()+500, table.getOriginY());
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
//			TextButton buttonQuit= new TextButton("Salir",skin);
			Button buttonQuit= new Button(new Button.ButtonStyle(spriteBSalir,spriteBSalir,spriteBSalir));
			buttonQuit.setPosition(table.getOriginX()+750, table.getOriginY());
			buttonQuit.setWidth(200);
			buttonQuit.setHeight(40);
			buttonQuit.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					gameState=GameState.QUIT;
					return false;
				}
			});
			table.addActor(buttonQuit);
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
			Gdx.input.setInputProcessor(stage);
			break;
			
		case PLAY:
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
			batch= new SpriteBatch();
			ScreenUtils.clear(0, 0, 0.2f, 1);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(boardPlay, 0, 0);
			batch.draw(boatTexture,boat.x,boat.y );
			batch.end();
			 // Movimiento del barco
			if(boat.y > 0) boat.y -= 10 * Gdx.graphics.getDeltaTime();
			
		
			if((Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D)) && !(boat.x>=1240)) {
				boat.x += 200 * Gdx.graphics.getDeltaTime();
			}
			if((Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))&& !(boat.y>=640)){
				boat.y += 200 * Gdx.graphics.getDeltaTime();
			}
			if((Gdx.input.isKeyPressed(Keys.DOWN)|| Gdx.input.isKeyPressed(Keys.S))&& !(boat.y<=0)) {
				boat.y -= 200 * Gdx.graphics.getDeltaTime();
			}
			if((Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A))&& !(boat.x<0)) {
				boat.x -= 200 * Gdx.graphics.getDeltaTime();
			}


			break;
			
			
		case CONFIG:
			
			break;
			
		case SHOP:
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			break;
			
		case MINIJUEGO:
			break;
			
		case QUIT:
			System.exit(0);

			break;
			
			}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		stage.dispose();
	}
	

	

	private Barco crearBarco(){
		Barco boat = new Barco(1280/2,720/5,40,80);
//		boat.x = 1280/2; 
//		boat.y = 720/5; 
//		boat.width = 40;
//		boat.height = 80;
		return boat;
	}
	
}
