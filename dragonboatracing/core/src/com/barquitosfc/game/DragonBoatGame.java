package com.barquitosfc.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	
	private Vector2 velocity = new Vector2();
	private Vector2 acceleration = new Vector2();
	private int dinero;
	private Skin skin;
	private Texture bInicio;
	private Texture bAjustes;
	private Texture bTienda;
	private Texture bSalir;
	private Stage stage;
	public static GameState gameState;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture board;
	private Texture boardPlay;
	private Texture boatTexture;
	private Rectangle boat;
	private Table table;
	private SpriteDrawable spriteBInicio;
	private SpriteDrawable spriteBAjustes;
	private SpriteDrawable spriteBTienda;
	private SpriteDrawable spriteBSalir;
	private static final int WIDTH=1280;
	private static final int HEIGHT	=720;
	private Array<Rectangle> Rocas;
	private Array<Rectangle> Troncos;
	private Array<Rectangle> Cocodrilos;
	private long lastDropTimeRoca;
	private long lastDropTimeTroncos;
	private long lastDropTimeCocodrilos;
	private int aceler;
	
	@Override
	public void create () {
		camera=new OrthographicCamera();
		camera.setToOrtho(false,WIDTH,HEIGHT);
		batch= new SpriteBatch();
		camera.update();
		gameState=GameState.MENU;
		stage = new Stage();
		//BOTONES
		 bInicio= new Texture(Gdx.files.internal("ui/Boton_INICIO.png"));
		 bAjustes= new Texture(Gdx.files.internal("ui/Boton_AJUSTES.png"));
		 bTienda= new Texture(Gdx.files.internal("ui/Boton_TIENDA.png"));
		 bSalir= new Texture(Gdx.files.internal("ui/Boton_SALIR.png"));
		

		//BACKGROUND
		 board = new Texture(Gdx.files.internal("data/fondo.png"));
		 boardPlay = new Texture(Gdx.files.internal("data/fondoPlay.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/boat.jpeg"));
		 boat=crearBarco();
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));// sprite cuando esta sin apretar, apretado y con el raton encima
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 
		 //Obstaculos 
		 Rocas = new Array<Rectangle>();
		 spawnRoca();
		 Troncos = new Array<Rectangle>();
		 spawnTronco();
		 Cocodrilos = new Array<Rectangle>();
		 spawnCocodrilo();
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
			//Nueva camara que sigue al barco
			OrthographicCamera camera = new OrthographicCamera();
			camera.setToOrtho(false, WIDTH, 720);
			camera.position.set(WIDTH /2, boat.getY() + boat.getHeight() / 2, 0);
			camera.update();
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
			ScreenUtils.clear(0, 0, 0.2f, 1);
			
			//RENDERIZADO
			
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(boardPlay, 0, 0);
			batch.draw(boatTexture,boat.x,boat.y );
			batch.end();
			batch.begin();
			 for(Rectangle roca: Rocas) {
			      batch.draw(boatTexture, roca.x, roca.y);
			 }
			 for(Rectangle tronco: Troncos) {  
				 batch.draw(boatTexture, tronco.x, tronco.y);
			 }
			 for(Rectangle cocodrilo: Cocodrilos) {
				 batch.draw(boatTexture, cocodrilo.x, cocodrilo.y);
				 
			 }
			batch.end();
			 // Movimiento del barco
			if(boat.y > 0) {//boat.y -= 10 * Gdx.graphics.getDeltaTime();
				boat.y += aceler * Gdx.graphics.getDeltaTime();
				acceleration.set(0, -6f);
			}
			
		    handleInput();
		    update(Gdx.graphics.getDeltaTime());
		
//			if((Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.D)) && !(boat.x>=1240)) {
//				boat.x += 100 * Gdx.graphics.getDeltaTime();
//			}
//			if((Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W))&& !(boat.y>=640)){
//				aceler += 200 ;
//			}
//			if((Gdx.input.isKeyPressed(Keys.DOWN)|| Gdx.input.isKeyPressed(Keys.S))&& !(boat.y<=0)) {
//				boat.y -= 50 * Gdx.graphics.getDeltaTime();
//			}
//			if((Gdx.input.isKeyPressed(Keys.LEFT)||Gdx.input.isKeyPressed(Keys.A))&& !(boat.x<0)) {
//				boat.x -= 100 * Gdx.graphics.getDeltaTime();
//			}
			
			
			//OBSTACULOS 
			
			 if(TimeUtils.millis() - lastDropTimeRoca > 4000) spawnRoca();
			 if(TimeUtils.millis() - lastDropTimeTroncos > 13000) spawnTronco();
			 if(TimeUtils.millis() - lastDropTimeCocodrilos > 9000) spawnRoca();
			 
			 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
			      Rectangle roca = iter.next();
			      roca.y -= 30 * Gdx.graphics.getDeltaTime();
			      if(roca.y + 64 < 0) iter.remove();
			      if(roca.overlaps(boat)) {
				         iter.remove();
				      }
			   }
			 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 200 * Gdx.graphics.getDeltaTime();
			      if(tronco.y + 64 < 0) iter.remove();
			      if(tronco.overlaps(boat)) {
				         iter.remove();
				      }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      cocodrilo.y -= 200 * Gdx.graphics.getDeltaTime();
			      if(cocodrilo.y + 64 < 0) iter.remove();
			      if(cocodrilo.overlaps(boat)) {
				         iter.remove();
				      }
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
		boatTexture.dispose();
		
	}
	

	

	private Rectangle crearBarco(){
		Rectangle boat = new Rectangle();
		boat.x = 1280/2; 
		boat.y = 720/5; 
		boat.width = 40;
		boat.height = 80;
		return boat;
	}
	
	 private void spawnRoca() {
	      Rectangle roca = new Rectangle();
	      roca.x = MathUtils.random(0, 1280-64);
	      roca.y = 720;
	      roca.width = 64;
	      roca.height = 64;
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 private void spawnTronco() {
	      Rectangle tronco = new Rectangle();
	      tronco.x = MathUtils.random(0, 1280-64);
	      tronco.y = 720;
	      tronco.width = 64;
	      tronco.height = 64;
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 private void spawnCocodrilo() {
	      Rectangle cocodrilo = new Rectangle();
	      cocodrilo.x = MathUtils.random(0, 1280-64);
	      cocodrilo.y = 720;
	      cocodrilo.width = 64;
	      cocodrilo.height = 64;
	      Cocodrilos.add(cocodrilo);
	      lastDropTimeCocodrilos = TimeUtils.millis();
	   }
	 
	 // Barco Movimiento

	 private float maxAcceleration = 30f; //Aceleracionmaxima
	 private float maxAcceleration2 = 40f;
	 	//Controles
	 public void handleInput() { 
		    if (Gdx.input.isKeyPressed(Keys.W)) {
		        if (acceleration.y < maxAcceleration) {
		            acceleration.y += 25;
		        }
		    }
		    if (Gdx.input.isKeyPressed(Keys.S)) {
		        if (acceleration.y < maxAcceleration) {
		            acceleration.y -= 20;
		        }
		    }
		    if (Gdx.input.isKeyPressed(Keys.D)) {
		        if (acceleration.y < maxAcceleration2) {
		            acceleration.y += 1;
		            acceleration.x += 40;
		        }
		    }
		    if (Gdx.input.isKeyPressed(Keys.A)) {
		        if (acceleration.y < maxAcceleration2) {
		            acceleration.y += 1;
		            acceleration.x -= 40;
		        }
		    }
		    
		}
	 
	    public void update(float deltaTime) {
	        velocity.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
	        boat.x += velocity.x * deltaTime;
	        boat.y += velocity.y * deltaTime;
	    }
	    public Vector2 getVelocity() {
	        return velocity;
	    }

	    public Vector2 getAcceleration() {
	        return acceleration;
	    }

	    public void setVelocity(Vector2 velocity) {
	        this.velocity = velocity;
	    }

	    public void setAcceleration(Vector2 acceleration) {
	        this.acceleration = acceleration;
	    }
	 
	
}
