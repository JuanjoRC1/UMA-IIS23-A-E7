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
	private int vidas;
	private int vPunta;
	private int agilidad;
	private int contadorFondo;
	private int aceler;
	private int limiteY;
	private BitmapFont font;
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
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	private Array<Rectangle> Rocas;
	private Array<Rectangle> Troncos;
	private Array<Rectangle> Cocodrilos;
	private long lastDropTimeRoca;
	private long lastDropTimeTroncos;
	private long lastDropTimeCocodrilos;

	
	@Override
	public void create () {
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
		 font = new BitmapFont();

		//BACKGROUND
		 board = new Texture(Gdx.files.internal("data/fondoMENU.png"));
		 boardPlay = new Texture(Gdx.files.internal("data/fondoPlay.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/boat.jpeg"));
		 boat = new Sprite(boatTexture);
		 boat.setPosition(WIDTH/2, HEIGHT/5);
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
			table.setPosition(260,HEIGHT/7);
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
//			TextButton buttonShop= new TextButton("Tienda",getSkin());
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
//			TextButton buttonQuit= new TextButton("Salir",skin);
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
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
			Gdx.input.setInputProcessor(stage);
			break;
			
		case PLAY:

			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
			ScreenUtils.clear(0, 0, 0.2f, 1);
			//			Nueva camara que sigue al barco
			OrthographicCamera camera = new OrthographicCamera();
			camera.setToOrtho(false, WIDTH, 720);
			camera.position.set(WIDTH /2, boat.getY() + boat.getHeight() / 2, 0);
			camera.update();
			batch.setProjectionMatrix(camera.combined);
			//PINTAR EL FONDO
			
			
			
			camera.update();
			
			batch.begin();
			batch.draw(boardPlay, 0, 0);
			for(int i = 0; i < 100000 ; i++) {
				contadorFondo=i;
				batch.draw(boardPlay,0,HEIGHT*i);
			}
			batch.end();
			
			batch.begin();
			batch.draw(boatTexture,boat.getX(),boat.getY() );
			batch.end();
//			PINTAR LOS OBSTACULOS
			batch.begin();	
			 for(Rectangle roca: Rocas) {
			      batch.draw(bInicio, roca.x, roca.y);
			 }
			 for(Rectangle tronco: Troncos) {  
				 batch.draw(bInicio, tronco.x, tronco.y);
			 }
			 for(Rectangle cocodrilo: Cocodrilos) {
				 batch.draw(bInicio, cocodrilo.x, cocodrilo.y);
			 }
			batch.end();
			
//			  LIMITES DEL BARCO
			
			if (boat.getX() < 0) {
			    boat.setX(64);
			    camera.position.x = WIDTH / 2;
			}
			if (boat.getX() > WIDTH - 64) {
			    boat.setX(WIDTH-66);
			    camera.position.x = WIDTH / 2;
			}
			
			limiteY=(int) (boat.getY()/HEIGHT);
			if (boat.getY()<(limiteY*HEIGHT+10)) {
				boat.setY((limiteY*HEIGHT)+10);
				
				if(velocity.y<0) {
					stopBoat();
				}
			}
			
			
			batch.begin();
			font.draw(batch, "Y: " + limiteY*HEIGHT+"POSBARCO: "+boat.getY(), 100, boat.getY()+100);
			batch.end();
			
			
			// Actualizar la cámara cuando el barco se encuentre fuera de ciertos límites
			if (boat.getY() < camera.position.y - HEIGHT / 4) {
			    camera.position.y = boat.getY() + HEIGHT / 4;
			}
			if (boat.getY() > camera.position.y + HEIGHT / 4) {
			    camera.position.y = boat.getY() - HEIGHT / 4;
			}

			


			
			
//		     MOVIMIENTO DEL BARCO
			if(boat.getY() > (limiteY*HEIGHT)+1) {
                boat.setY(boat.getY() + aceler * Gdx.graphics.getDeltaTime());
                acceleration.set(0, -6f);
            }else {
            	stopBoat();
            }
            handleInput();
            update(Gdx.graphics.getDeltaTime());
		    
		  

		    //Combertir el sprite en rectangle 
		    Rectangle rect1 = boat.getBoundingRectangle(); 
			//Actualizar zonas para la aparicion de objetos 
		    camera.update();
		    leftLimit = camera.position.x - Gdx.graphics.getWidth() / 2;
		    rightLimit = camera.position.x + Gdx.graphics.getWidth() / 2;
		    topLimit = camera.position.y + Gdx.graphics.getHeight() / 2;
		    bottomLimit = camera.position.y - Gdx.graphics.getHeight() / 2;
			
			 if(TimeUtils.millis() - lastDropTimeRoca > 6000) spawnRoca();
			 if(TimeUtils.millis() - lastDropTimeTroncos > 13000) spawnTronco();
			 if(TimeUtils.millis() - lastDropTimeCocodrilos > 9000) spawnRoca();
		 
			 //MOVIMIENTO DE LOS OBSTACULOS
			 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
			      Rectangle roca = iter.next();
			      roca.y -= 1000 * Gdx.graphics.getDeltaTime();
			      if(roca.y + 64 < bottomLimit+100) iter.remove();
			      if(roca.overlaps(rect1)) {
				         iter.remove();
				      }
			      
			   }
			 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 200 * Gdx.graphics.getDeltaTime();
			      if(tronco.y +64<bottomLimit+100) iter.remove();
			      if(tronco.overlaps(rect1)) {
				         iter.remove();
				      }
			      
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      cocodrilo.y -= 20 * Gdx.graphics.getDeltaTime();
			      if(cocodrilo.y + 64 < bottomLimit+100) iter.remove();
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
	 private void spawnRoca() {
	      Rectangle roca = new Rectangle();
	      roca.x = MathUtils.random(0, WIDTH-64);
	      roca.y = topLimit +360;
	      roca.width = 64;
	      roca.height = 64;
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 private void spawnTronco() {
	      Rectangle tronco = new Rectangle();
	      tronco.x = MathUtils.random(0, WIDTH-64);
	      tronco.y = topLimit + 360;
	      tronco.width = 64;
	      tronco.height = 64;
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 private void spawnCocodrilo() {
	      Rectangle cocodrilo = new Rectangle(MathUtils.random(0, WIDTH-64),topLimit + 360,64,64);
	      Cocodrilos.add(cocodrilo);
	      lastDropTimeCocodrilos = TimeUtils.millis();
	   }
	  private float maxAcceleration = 200f; //Aceleracionmaxima
	  private float maxAcceleration2 = 200f;
	         //Controles
	     public void handleInput() { 
	            if (Gdx.input.isKeyPressed(Keys.W)) {
	                if (acceleration.y < maxAcceleration) {
	                    acceleration.y += 200;
	                }
	            }
	            if (Gdx.input.isKeyPressed(Keys.S)) {
	            	if(boat.getY()>(limiteY*HEIGHT)) {
	            		if (acceleration.y < maxAcceleration) {
	                    	acceleration.y -= 200;
	                	}
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
	            if (Gdx.input.isKeyPressed(Keys.SPACE)) {
	                stopBoat();
	            }


	        }

	        public void update(float deltaTime) {
	            velocity.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
	            boat.setX(boat.getX() + velocity.x * deltaTime);
	            boat.setY(boat.getY() + velocity.y * deltaTime);
	        }
	        public void stopBoat() {
	            // Establece la velocidad en cero
	            velocity.set(0, 0);
	            // Establece la aceleración en cero
	            acceleration.set(0, 0);
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