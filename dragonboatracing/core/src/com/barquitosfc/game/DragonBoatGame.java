package com.barquitosfc.game;

import java.util.Iterator;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.barquitosfc.game.DragonBoatGame.GameState;

import java.awt.Color;
import java.awt.Graphics;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	float leftLimit, rightLimit, topLimit, bottomLimit;
	private Vector2 velocity = new Vector2(0,0);
	private Vector2 velocitybar = new Vector2();
	private Vector2 acceleration = new Vector2(0,0);
	private int aceler;
	private int barcoDef,vidas,vPunta,dinero;
	private float ilit = 500; 
	private Barco boat;
	private BitmapFont font;
	private Texture bInicio,bAjustes,bTienda,bSalir;
	private Texture board,boardPlay,boardminit,boatTexture,	Barra1Texture,Barra2Texture,BolaTexture,TRoca,TTronco,TCoco;
	private Stage stage;
	private Table table;
	public   static GameState gameState;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite Barra1;
	private Sprite Barra2;
	private Sprite Bola;
	private SpriteDrawable spriteBInicio;
	private SpriteDrawable spriteBAjustes;
	private SpriteDrawable spriteBTienda;
	private SpriteDrawable spriteBSalir;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	private Array<Rectangle> Rocas,Troncos,Cocodrilos;
	private long lastDropTimeRoca,lastDropTimeTroncos,lastDropTimeCocodrilos;
	private Tienda tienda;

   
    
//Para el minijuego
	private int speedx = 200; 
	private int speedy = 200; 

	public DragonBoatGame(int eleccionBarco,int vidasS,int vPuntaS,int dinero) {
		this.barcoDef=eleccionBarco;
		this.vidas=vidasS;
		this.vPunta=vPuntaS;
		this.dinero=dinero;
	}
	
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
		 board = new Texture(Gdx.files.internal("fondos/fondoMENU.png"));
		 boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Prueba.png"));
		 boardminit = new Texture(Gdx.files.internal("fondos/fondomini.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/boat.jpeg"));
		 Barra1Texture=new Texture(Gdx.files.internal("data/barco_rojo.png"));
		 Barra2Texture=new Texture(Gdx.files.internal("data/barco_azul.png"));
		 BolaTexture= new Texture(Gdx.files.internal("data/bala.png"));
		
		 Barra1= new Sprite(Barra1Texture);
		 Barra1.setY(HEIGHT/2);
		 Barra1.setX(WIDTH-1670);
		 Barra2= new Sprite(Barra2Texture);
		 Barra2.setY(HEIGHT/2);
		 Barra2.setX(WIDTH-250);
		 Bola= new Sprite(BolaTexture);
		 Bola.setX(WIDTH/2);
		 Bola.setY(HEIGHT/2);
		 boat = new Barco(boatTexture);
		 boat.setPosition(WIDTH/2, HEIGHT/7);
		 boat.setScale(1f); 
		 
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 
		 //TIENDA
		 tienda= new Tienda();
		 tienda.inicializar();
		 
		 //Obstaculos
		 
		 TRoca = new Texture(Gdx.files.internal("data/Roca.png"));
		 TCoco = new Texture(Gdx.files.internal("data/Icoco.png"));
		 TTronco = new Texture(Gdx.files.internal("data/Tronco.png")); 
		 Rocas = new Array<Rectangle>();
		 Troncos = new Array<Rectangle>();
		 Cocodrilos = new Array<Rectangle>();

	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		switch(gameState) {
		case MENU:

			stage = new Stage();
			table=new Table();
			table.setPosition(260,HEIGHT/7);
			table.setFillParent(true);
			table.setHeight(200);
			stage.addActor(table);	
			table.clear();
	        batch.begin();
	        batch.draw(board, 0, 0);
	        batch.end();
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
			camera.position.set(WIDTH /2, boat.getY() + 200 + boat.getHeight() / 2, 0);
			camera.update();
			batch.setProjectionMatrix(camera.combined);

			camera.update();
			
			//PINTAR EL FONDO
			batch.begin();
			for(int i = 0; i < 100000 ; i++) {
				batch.draw(boardPlay,0,HEIGHT*i);
			}
			batch.end();
			//PINTAR BARCO
			batch.begin();
//			batch.draw(boatTexture,boat.getX(),boat.getY());
			boat.draw(batch);
			
			batch.end();
			
//			PINTAR LOS OBSTACULOS
			batch.begin();	
			 for(Rectangle roca: Rocas) {batch.draw(TRoca, roca.x, roca.y);}
			 for(Rectangle tronco: Troncos) {batch.draw(TTronco, tronco.x, tronco.y);}
			 for(Rectangle cocodrilo: Cocodrilos) {batch.draw(TCoco, cocodrilo.x, cocodrilo.y);}
			batch.end();
			
			batch.begin();
			font.draw(batch, "x: " + boat.getX() +"Y: "+ boat.getY(), 100, boat.getY()+100);
			batch.end();
			
            handleInput();
            update(Gdx.graphics.getDeltaTime());
            break;
			
		case CONFIG:
			Assetsmini.load();
			/*table=new Table();
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
			ScreenUtils.clear(0, 0, 0.2f, 1);		
			batch.begin();
			for(int i = 0; i < 100 ; i++) {
				batch.draw(boardminit,0,HEIGHT*i);
			}
			
			batch.draw(BolaTexture,Bola.getX(),Bola.getY() );
			batch.draw(Barra1Texture, Barra1.getX(),Barra1.getY());
			batch.draw(Barra2Texture, Barra2.getX(),Barra2.getY());
			batch.end();
			 handleInputm();
	         updatemin(Gdx.graphics.getDeltaTime());
			*/
			break;
			
		case SHOP:
			// METODO PARA HACER LA TIENDA EN UNA SOLA LINEAAA
			
			tienda.iniciar(table,batch,stage,gameState);

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
		boat.getTexture().dispose();
	}
	// SPAWN DE OBSTACULOS
	 private void spawnRoca() {
	      Rectangle roca = new Rectangle(MathUtils.random(0, WIDTH-64),(MathUtils.random(topLimit+360, topLimit+HEIGHT)),64,64);
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 private void spawnTronco() {
	      Rectangle tronco = new Rectangle(MathUtils.random(0, WIDTH-64),(MathUtils.random(topLimit+360, topLimit+HEIGHT)),90,40);
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 private void spawnCocodrilo() {
	      Rectangle cocodrilo = new Rectangle(MathUtils.random(0, WIDTH-64),(MathUtils.random(topLimit+360, topLimit+HEIGHT)),64,64);
	      Cocodrilos.add(cocodrilo);
	      lastDropTimeCocodrilos = TimeUtils.millis();
	   }

	         //Controles
	     public void handleInput() { 
	            if (Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed(Keys.UP) ) {
	                    acceleration.y += 200;
	            }
	            if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
	            	if(boat.getY()>(ilit)) 
	                    acceleration.y -= 200;
	            }
	            if (Gdx.input.isKeyPressed(Keys.D)|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
	                    acceleration.x += 200;
//	            	boat.setPosition(boat.getX() + 4, boat.getY());
	                    if(boat.getRotation() > -10)
	                    	boat.rotate(-1);
	            }
	            
	            if (Gdx.input.isKeyPressed(Keys.A)|| Gdx.input.isKeyPressed(Keys.LEFT)) {
	                    acceleration.x -= 200;
//	            	boat.setPosition(boat.getX() - 4, boat.getY());
	                    if(boat.getRotation()< 10)
	                    	boat.rotate(1);
	            }
	            if (Gdx.input.isKeyPressed(Keys.SPACE)) {
	                stopBoat();
	            }
	            if(!Gdx.input.isKeyPressed(Keys.ANY_KEY))
	            if(boat.getRotation() < 0)
	            	boat.rotate(+1);
	            else if(boat.getRotation() > 0)
	            	boat.rotate(-1);
	        }
	     private void reset() {
			 Bola.setX(WIDTH/2);
			 Bola.setY(HEIGHT/2);
			 }
	 		
	        public void update(float deltaTime) {

//				Actualizar la cámara cuando el barco se encuentre fuera de ciertos límites
				if (boat.getY() < camera.position.y - HEIGHT / 4) {
				    camera.position.y = boat.getY() + HEIGHT / 4;
				}
				if (boat.getY() > camera.position.y + HEIGHT / 4) {
				    camera.position.y = boat.getY() - HEIGHT / 4;
				}
				
//				Actualizar zonas para la aparicion de objetos 
			    camera.update();
			    leftLimit = camera.position.x - Gdx.graphics.getWidth() / 2;
			    rightLimit = camera.position.x + Gdx.graphics.getWidth() / 2;
			    topLimit = camera.position.y + Gdx.graphics.getHeight() / 2;
			    bottomLimit = camera.position.y - Gdx.graphics.getHeight() / 2;
			    
//	   			MOVIMINETO DEL BARCO

	        		velocity.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
	        	    if(velocity.y > boat.getvPunta() +1) {
				    	velocity.y = boat.getvPunta(); 
				    	acceleration.y = 0;  
				    }

				    if(velocity.x > boat.getAgilidad() +1) {
				    	velocity.x = boat.getAgilidad(); 
				    	acceleration.x = 0;  
				    }
				    
				    if(velocity.x < - boat.getAgilidad() -1) {
				    	velocity.x = - boat.getAgilidad(); 
				    	acceleration.x = 0; 
				    }


	            boat.setX(boat.getX() + velocity.x * deltaTime);
	            boat.setY(boat.getY() + velocity.y * deltaTime);
	            Barra1.setY(Barra1.getY() + velocity.y * deltaTime);
	       
//	    		Bola?
	            Bola.setX(Bola.getX()+speedx*deltaTime);
	            Bola.setY(Bola.getY()+speedy*deltaTime);
	            Rectangle bar1 = Barra1.getBoundingRectangle(); 
	            Rectangle bar2 = Barra2.getBoundingRectangle(); 
	            Rectangle bol=Bola.getBoundingRectangle();
				if(Bola.getX()<=25&& Bola.getY()>=Barra1.getY()&& Bola.getY()<=Barra1.getY()-100||bar1.overlaps(bol)||bar2.overlaps(bol))
				{
					speedx*=-1;
					speedy *=-1;
				}
				if (Bola.getX() < 25||Bola.getX()>WIDTH) {
					reset();
					speedx*=-1;
				}
				if (Bola.getY() >700||Bola.getY()<0) {
					speedy *=-1;
				}

//			     MOVIMIENTO DEL BARCO
				if(boat.getY() > (ilit)+1) {
					boat.setY(boat.getY() + aceler * Gdx.graphics.getDeltaTime());
	                acceleration.set(0, -6f);
	            }else {
	            	stopBoat();
	            }
	            
//				  LIMITES DEL BARCO HORIZONTAL
				
				if (boat.getX() < 0) {
				    boat.setX(1);
				    velocity.x = 0; 
				    camera.position.x = WIDTH / 2;
				}
				if (boat.getX() > WIDTH - 64) {
				    boat.setX(WIDTH-65);
				    velocity.x = 0; 
				    camera.position.x = WIDTH / 2;
				}
				//	 			 LIMITES DEL BARCO VERTICAL
				if(ilit <  bottomLimit) ilit = bottomLimit; 
				
				if (boat.getY()<(ilit+10)) {
					boat.setY((ilit)+10);
					if(velocity.y<0) stopBoat();
				}
	            
	            
	   		 final int tiempoDeEsperaEntreObstaculos = 400; // espera 400 milisegundos entre cada generaci�n de obst�culos
			 if (TimeUtils.millis() - lastDropTimeRoca > tiempoDeEsperaEntreObstaculos && Rocas.size<15) {
			     spawnRoca();
			     
			 }
			 if (TimeUtils.millis() - lastDropTimeTroncos > tiempoDeEsperaEntreObstaculos && Troncos.size<14) {
			     spawnTronco();
			    
			 }
			 if (TimeUtils.millis() - lastDropTimeCocodrilos > tiempoDeEsperaEntreObstaculos && Cocodrilos.size<15) {
			     spawnCocodrilo();
			  
			 }
			 
			    Rectangle rect1 = boat.getBoundingRectangle(); 

			 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
			      Rectangle roca = iter.next();
//			      roca.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(roca.y + 64 < bottomLimit+100) iter.remove();
			      if(roca.overlaps(rect1)) {
				         iter.remove();
				         boat.getVidas();
				      }
			   }
 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(tronco.y +64<bottomLimit+100) iter.remove();
			      if(tronco.overlaps(rect1)) {
				         iter.remove();
				         boat.setVidas();
				  }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      if(cocodrilo.x < WIDTH/ 2)
			    	  cocodrilo.x += 20 * Gdx.graphics.getDeltaTime();
			      if(cocodrilo.y + 64 < bottomLimit+100) iter.remove();
			      if(cocodrilo.overlaps(rect1)) {
				         iter.remove();
				         boat.setVidas();
				      }
			 }
			 
			 if(boat.getVidas() == 0) gameState=GameState.MENU;
	        }
	        
	        public void stopBoat() {
	            // Establece la velocidad en cero
	            velocity.set(0, 0);
	            // Establece la aceleración en cero
	            acceleration.set(0, 0);
	        }

	        //Para el minijuego

	        //Controles
	        public void handleInputm() { 
	            if (Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed(Keys.UP) ) {
	                    velocity.y = 100;
	                    
	            }
	            if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
	                    	velocity.y = -100;
	            }

	        }
	        
	        public void updatemin(float deltaTime) {


	        	
	            Barra1.setY(Barra1.getY() + velocity.y * deltaTime);
	       
//	    		Bol
	            Bola.setX(Bola.getX()+speedx*deltaTime);
	            Bola.setY(Bola.getY()+speedy*deltaTime);
	            Rectangle bar1 = Barra1.getBoundingRectangle(); 
	            Rectangle bar2 = Barra2.getBoundingRectangle(); 
	            Rectangle bol=Bola.getBoundingRectangle();
				if(Bola.getX()<=25&& Bola.getY()>=Barra1.getY()&& Bola.getY()<=Barra1.getY()-400||bar1.overlaps(bol)||bar2.overlaps(bol))
				{
					speedx*=-1;
					speedy *=-1.2;
				}
				if (Bola.getX() < 25||Bola.getX()>WIDTH) {
					speedx*=-1;
				}
				if (Bola.getY() >840 ||Bola.getY()<200) {
					speedy *=-0.8;
				}
// 		 LIMITES de la barra VERTICAL
				if (Barra1.getY() <= 200) {
				    
				    velocity.y = +400; 
	            }
				if (Barra1.getY() >= 750) {
				    
				    velocity.y = -400; 
	            }
	            

	       
	        }
		 
		     

	}