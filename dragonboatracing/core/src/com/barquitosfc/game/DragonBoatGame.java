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
	float leftLimit, rightLimit, topLimit, bottomLimit;
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
	private Sprite boat;
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

	
	@Override
	public void create () {
		camera=new OrthographicCamera();
		camera = new OrthographicCamera();
		leftLimit = camera.position.x - Gdx.graphics.getWidth() / 2;
		rightLimit = camera.position.x + Gdx.graphics.getWidth() / 2;
		topLimit = camera.position.y + Gdx.graphics.getHeight() / 2;
		bottomLimit = camera.position.y - Gdx.graphics.getHeight() / 2;
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
		 boat = new Sprite();
		 boat = new Sprite(boatTexture);
		 boat.setPosition(1248/2, 720/5);
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
//			Nueva camara que sigue al barco
			OrthographicCamera camera = new OrthographicCamera();
			camera.setToOrtho(false, WIDTH, 720);
			camera.position.set(WIDTH /2, boat.getY() + boat.getHeight() / 2, 0);
			camera.update();
//			boat.setPosition(1248/2, 720/5);// proporciones del barco
			boat.setScale(1f); 
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
			ScreenUtils.clear(0, 0, 0.2f, 1);
			
			//RENDERIZADO
			
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(boardPlay, 0, 0);
			for(int i = 0; i < 100000 ; i++) {
				batch.draw(boardPlay,0,HEIGHT*i);
			}
			boat.draw(batch);
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
//			  Movimiento del barco
			
			
			
		    // mover el barco en función de las teclas presionadas
		    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
		    	acceleration.add(-10, 0);
		    } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
		    	acceleration.add(10, 0);    
		    }else {

		    }

		    if (Gdx.input.isKeyPressed(Keys.UP)) {
		    	acceleration.add(0, 100);
		    } else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
		    	acceleration.add(0, -100);
		    } else {

		    }
		    // actualizar el barco
		    update1(Gdx.graphics.getDeltaTime());

			
//		    handleInput();
//		    update(Gdx.graphics.getDeltaTime());
				
		    //Combertir el sprite en rectangle 
		    Rectangle rect1 = boat.getBoundingRectangle(); 
			//Actualizar zonas para la aparicion de objetos 
		    camera.update();
		    leftLimit = camera.position.x - Gdx.graphics.getWidth() / 2;
		    rightLimit = camera.position.x + Gdx.graphics.getWidth() / 2;
		    topLimit = camera.position.y + Gdx.graphics.getHeight() / 2;
		    bottomLimit = camera.position.y - Gdx.graphics.getHeight() / 2;
			
			 if(TimeUtils.millis() - lastDropTimeRoca > 4000) spawnRoca();
			 if(TimeUtils.millis() - lastDropTimeTroncos > 13000) spawnTronco();
			 if(TimeUtils.millis() - lastDropTimeCocodrilos > 9000) spawnRoca();
		 
			 
			 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
			      Rectangle roca = iter.next();
			      roca.y -= 30 * Gdx.graphics.getDeltaTime();
			      if(roca.y + 64 < 0) iter.remove();
			      if(roca.overlaps(rect1)) {
				         iter.remove();
				      }
			   }
			 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 200 * Gdx.graphics.getDeltaTime();
			      if(tronco.y + 64 < 0) iter.remove();
			      if(tronco.overlaps(rect1)) {
				         iter.remove();
				      }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      cocodrilo.y -= 200 * Gdx.graphics.getDeltaTime();
			      if(cocodrilo.y + 64 < 0) iter.remove();
			      if(cocodrilo.overlaps(rect1)) {
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
	

	

//	private Sprite crearBarco(){
//		Sprite boat = new Sprite(boatTexture, 1280/2, 720/5, WIDTH, HEIGHT);
////		boat.setX(1280/2); 
////		boat.setY(720/5); 
////		boat.setSize(40, 80);
//		return boat;
//	}
	
	 private void spawnRoca() {
	      Rectangle roca = new Rectangle();
	      roca.x = MathUtils.random(0, 1280-64);
	      roca.y = topLimit +360;
	      roca.width = 64;
	      roca.height = 64;
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 private void spawnTronco() {
	      Rectangle tronco = new Rectangle();
	      tronco.x = MathUtils.random(0, 1280-64);
	      tronco.y = topLimit + 360;
	      tronco.width = 64;
	      tronco.height = 64;
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 private void spawnCocodrilo() {
	      Rectangle cocodrilo = new Rectangle();
	      cocodrilo.x = MathUtils.random(0, 1280-64);
	      cocodrilo.y = topLimit + 360;
	      cocodrilo.width = 64;
	      cocodrilo.height = 64;
	      Cocodrilos.add(cocodrilo);
	      lastDropTimeCocodrilos = TimeUtils.millis();
	   }
	 // MOvimineto con aceleracion
	 
//	 public void update(float deltaTime) {
//		    handleInput();
//		    velocity.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
//		    setPosition(getX() + velocity.x * deltaTime, getY() + velocity.y * deltaTime);
//		}
	 
	 public void update1(float delta) {
		 if(acceleration.x < maxAcceleration)
		 velocity.add(acceleration); 
		 boat.setOriginBasedPosition(velocity.x, velocity.y);;
		 
	 }
	 public void dibujar(SpriteBatch batch) {
		    // configurar la posición del sprite
		    boat.setPosition(boat.getX(), boat.getY());

		    // dibujar el sprite
		    boat.draw(batch);
		}
	 private float maxAcceleration = 100f;
	 public void actualizar() {
		    // mover el barco en función de las teclas presionadas
		    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
		    	if(acceleration.x < maxAcceleration)
		    	acceleration.add(-10, 0);
		    } 
		    if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		    	if(acceleration.x < maxAcceleration)
		    	acceleration.add(10, 0);    
		    }
		    if (Gdx.input.isKeyPressed(Keys.UP)) {
		    	if(acceleration.y < maxAcceleration)
		    	acceleration.add(0, 100);
		    }
		    if (Gdx.input.isKeyPressed(Keys.DOWN)) {
		    	if(acceleration.y < maxAcceleration)
		    	acceleration.add(0, -100);
		    }

		    update1(Gdx.graphics.getDeltaTime());
		}
	
}
