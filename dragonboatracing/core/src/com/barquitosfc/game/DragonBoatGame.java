package com.barquitosfc.game;

import java.util.Iterator;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import java.awt.Color;
import java.awt.Graphics;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	public Sound bum;
	public Music omega;
	float leftLimit, rightLimit, topLimit, bottomLimit,leftLimitmini, rightLimitmini, topLimitmini, bottomLimitmini;
	protected Vector2 velocity = new Vector2(0,0);
	protected Vector2 velocitybar = new Vector2();
	protected Vector2 acceleration = new Vector2(0,0);
	protected Texture fondofla;
	protected  float gravity=(float) -1.4;
	protected int aceler;
	protected int barcoDef,vidas,vPunta,dinero;
	protected float ilit = 500; 
	protected Barco boat;
	protected BitmapFont font;
	protected Texture bInicio,bAjustes,bTienda,bSalir;
	protected Texture board,boardPlay,boardminit,boatTexture,	Barra1Texture,Barra2Texture,BolaTexture,TRoca,TTronco,TCoco,Tuboabt,Tuboart,fin;
	protected Stage stage;
	protected Table table;
	public   static GameState gameState;
	protected OrthographicCamera camera;
	protected OrthographicCamera camfla;
	protected SpriteBatch batch;
	protected Sprite Bird;
	protected Sprite Barra1;
	protected Sprite Barra2;
	protected Sprite Bola;
	protected SpriteDrawable spriteBInicio;
	protected SpriteDrawable spriteBAjustes;
	protected SpriteDrawable spriteBTienda;
	protected SpriteDrawable spriteBSalir;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	protected Array<Rectangle> Rocas,Troncos,Cocodrilos;
	protected Array<Rectangle> Tuboar,Tuboab;
	protected long lastDropTimeRoca,lastDropTimeTroncos,lastDropTimeCocodrilos,lastDropTimeTuboab,lastDropTimeTuboar;
	protected Tienda tienda;
	protected Juego juego;
	protected minijuego minijuego;

   
    
//Para el minijuego
	protected int speedx = 200; 
	protected int speedy = 200; 
	public DragonBoatGame() {
		this(0,0,0,0);
	}
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
		batch = new SpriteBatch();
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
		 boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Rio.png"));
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

		 
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 
		 //TIENDA
		 tienda= new Tienda();
		 tienda.inicializar();
		 
		 // Juego 
		 juego = new Juego();
		 juego.inicializar(); 
		 
		 //minijuego
		 Tuboart = new Texture(Gdx.files.internal("minijuego/palochino.png"));
		 Tuboabt = new Texture(Gdx.files.internal("minijuego/palochino(abajo).png"));
		 fin = new Texture(Gdx.files.internal("minijuego/fin.png"));
		 Tuboar = new Array<Rectangle>();
		 Tuboab = new Array<Rectangle>();
			camfla = new OrthographicCamera();
			leftLimitmini = camfla.position.x - Gdx.graphics.getWidth() / 2;
			rightLimitmini = camfla.position.x + Gdx.graphics.getWidth() / 2;
			topLimitmini = camfla.position.y + Gdx.graphics.getHeight() / 2;
			bottomLimitmini = camfla.position.y - Gdx.graphics.getHeight() / 2;
			camfla.setToOrtho(false,WIDTH,HEIGHT);
			
			camfla.update();
		 minijuego=new minijuego();
		 minijuego.inicializar();
		 bum =  Gdx.audio.newSound(Gdx.files.internal("sonidos/tumuerto.mp3"));
		 omega =  Gdx.audio.newMusic(Gdx.files.internal("sonidos/omegaelfuelte.mp3"));
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
			
			juego.iniciar(table, batch, stage);
//			PINTAR LOS OBSTACULOS
			batch.begin();	
			 for(Rectangle roca: Rocas) {batch.draw(TRoca, roca.x, roca.y);}
			 for(Rectangle tronco: Troncos) {batch.draw(TTronco, tronco.x, tronco.y);}
			 for(Rectangle cocodrilo: Cocodrilos) {batch.draw(TCoco, cocodrilo.x, cocodrilo.y);}
			batch.end();
			
			batch.begin();
			font.draw(batch, "x: " + juego.jugador.getX() +"Y: "+ juego.jugador.getY(), 100, juego.jugador.getY()+100);
			batch.end();
//			
            handleInput();
			update(Gdx.graphics.getDeltaTime());
            break;
			
		case CONFIG:
			/*Ventana ventana = new Ventana();
			Thread t1= new Thread();
			
			t1.start();*/
			minijuego.iniciar(table, batch,stage);
			batch.begin();
			for(Rectangle tuboar: Tuboar) {batch.draw(Tuboart, tuboar.x, tuboar.y);}
			for(Rectangle tuboab: Tuboab) {batch.draw(Tuboabt, tuboab.x, tuboab.y);}
			// for(Rectangle tuboab: Tuboab) {batch.draw(Tuboabt, tuboab.x, tuboab.y);}
			batch.end();
			batch.begin();
			font.draw(batch, "x: " + minijuego.jugador.getX() +"Y: "+ minijuego.jugador.getY(), 100, minijuego.jugador.getY()+100);
			batch.end();
			//  handleInputflapi();
			updateflapi(Gdx.graphics.getDeltaTime()*250);
//			table=new Table();
//			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
//			Gdx.gl.glClearColor(0, 0, 0, 1);
//	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	        
//			ScreenUtils.clear(0, 0, 0.2f, 1);		
//			batch.begin();
//			for(int i = 0; i < 100 ; i++) {
//				batch.draw(boardminit,0,HEIGHT*i);
//			}
//			
//			batch.draw(BolaTexture,Bola.getX(),Bola.getY() );
//			batch.draw(Barra1Texture, Barra1.getX(),Barra1.getY());
//			batch.draw(Barra2Texture, Barra2.getX(),Barra2.getY());
//			batch.end();
//			 handleInputm();
//	         updatemin(Gdx.graphics.getDeltaTime());
			
			break;
			
		case SHOP:
			tienda.iniciar(table,batch,stage);
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
	// SPAWN DE OBSTACULOS
	 protected void spawnRoca(Array<Rectangle> Rocas) {
	      Rectangle roca = new Rectangle(MathUtils.random(0, WIDTH-64),(MathUtils.random(topLimit+360, topLimit+HEIGHT)),64,64);
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 protected void spawnTronco(Array<Rectangle>Troncos) {
	      Rectangle tronco = new Rectangle(MathUtils.random(0, WIDTH-64),(MathUtils.random(topLimit+360, topLimit+HEIGHT)),90,40);
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 protected void spawnCocodrilo(Array<Rectangle> Cocodrilos) {
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
	            	if(juego.jugador.getY()>(ilit)) 
	                    acceleration.y -= 200;
	            }
	            if (Gdx.input.isKeyPressed(Keys.D)|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
	                    acceleration.x += 200;
//	            	boat.setPosition(boat.getX() + 4, boat.getY());
	                    if(juego.jugador.getRotation() > -10)
	                    	juego.jugador.rotate(-1);
	            }
	            
	            if (Gdx.input.isKeyPressed(Keys.A)|| Gdx.input.isKeyPressed(Keys.LEFT)) {
	                    acceleration.x -= 200;
//	            	boat.setPosition(boat.getX() - 4, boat.getY());
	                    if(juego.jugador.getRotation()< 10)
	                    	juego.jugador.rotate(1);
	            }
	            if (Gdx.input.isKeyPressed(Keys.SPACE)) {
	                stopBoat();
	            }
	            if(!Gdx.input.isKeyPressed(Keys.ANY_KEY))
	            if(juego.jugador.getRotation() < 0)
	            	juego.jugador.rotate(+1);
	            else if(juego.jugador.getRotation() > 0)
	            	juego.jugador.rotate(-1);
	        }
	     protected void reset() {
			 Bola.setX(WIDTH/2);
			 Bola.setY(HEIGHT/2);
			 }
	 		
	     public void updateflapi(float deltaTime) {
	    	 		
//					camfla.position.x = minijuego.jugador.getX();
				
				omega.play();
	    	 		camfla.update();
				    leftLimitmini = camfla.position.x - 1920 / 2;
				    rightLimitmini = camfla.position.x + 1920 / 2;
				    topLimitmini = camfla.position.y + 1080 / 2;
				    bottomLimitmini = camfla.position.y - 1080 / 2;
//					movimiento del pajaro
				    minijuego.jugador.setX(minijuego.jugador.getX() +  deltaTime);
				    minijuego.jugador.setY((minijuego.jugador.getY()+gravity*deltaTime));
				    if(Gdx.input.isKeyPressed(Keys.SPACE)) {
				    	minijuego.jugador.setY((float) (minijuego.jugador.getY()+5.2*deltaTime));
				    }
				    if(minijuego.jugador.getY()==1080) {
				    	  minijuego.jugador.setY((minijuego.jugador.getY()+gravity*deltaTime));

				    }
				    final int tiempoDeEsperaEntreObstaculosmini = 1250; // espera 100 milisegundos entre cada generaci�n de obst�culos
					 if (TimeUtils.millis() - lastDropTimeTuboab > tiempoDeEsperaEntreObstaculosmini) {
					     spawntuboab(Tuboab,Tuboar);
					     //spawntuboar(Tuboar);
					     
					 }
//					 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
//					      Rectangle roca = iter.next();
////					    
//					      if(roca.y + 64 < bottomLimit+100) iter.remove();
//					      if(roca.overlaps(rect1)) {
//						         iter.remove();
//						         juego.jugador.getVidas();
//						      }
//					   }
					 Rectangle drag= minijuego.jugador.getBoundingRectangle();
					 for (Iterator<Rectangle> iter = Tuboab.iterator(); iter.hasNext(); ) {
						 Iterator<Rectangle> iterar = Tuboar.iterator();
					      Rectangle Tuboab = iter.next();
					      Rectangle Tuboar = iterar.next();
//					      roca.y -= 100 * Gdx.graphics.getDeltaTime();
				      if(Tuboab.overlaps(drag)||Tuboar.overlaps(drag)) {
				    		 bum.play();
				    		batch.begin();
				    		batch.draw(fin, camfla.position.x, camfla.position.y);
				    		batch.end();
				    		 try {
				    			    Thread.sleep(1300); // 5000 milisegundos son equivalentes a 5 segundos
				    			} catch (InterruptedException e) {
				    			    e.printStackTrace();
				    			}
					    	  gameState=GameState.QUIT;
					      }
					   }
	     }
//	     public void handleInputflapi() { 
//	    	 if (Gdx.input.isKeyPressed(Keys.G) ) {
//                 gravity = (float) 1.01;
//	     }
	   //  }
	        public void update(float deltaTime) {

//				Actualizar la cámara cuando el barco se encuentre fuera de ciertos límites
				if (juego.jugador.getY() < camera.position.y - HEIGHT / 4) {
				    camera.position.y = juego.jugador.getY() + HEIGHT / 4;
				}
				if (juego.jugador.getY() > camera.position.y + HEIGHT / 4) {
				    camera.position.y = juego.jugador.getY() - HEIGHT / 4;
				}
				
//				Actualizar zonas para la aparicion de objetos 
			    camera.update();
			    leftLimit = camera.position.x - Gdx.graphics.getWidth() / 2;
			    rightLimit = camera.position.x + Gdx.graphics.getWidth() / 2;
			    topLimit = camera.position.y + Gdx.graphics.getHeight() / 2;
			    bottomLimit = camera.position.y - Gdx.graphics.getHeight() / 2;
			    
//	   			MOVIMINETO DEL BARCO

	        		velocity.add(acceleration.x * deltaTime, acceleration.y * deltaTime);
	        	    if(velocity.y > juego.jugador.getvPunta() +1) {
				    	velocity.y = juego.jugador.getvPunta(); 
				    	acceleration.y = 0;  
				    }

				    if(velocity.x > juego.jugador.getAgilidad() +1) {
				    	velocity.x = juego.jugador.getAgilidad(); 
				    	acceleration.x = 0;  
				    }
				    
				    if(velocity.x < - juego.jugador.getAgilidad() -1) {
				    	velocity.x = - juego.jugador.getAgilidad(); 
				    	acceleration.x = 0; 
				    }


				    juego.jugador.setX(juego.jugador.getX() + velocity.x * deltaTime);
				    juego.jugador.setY(juego.jugador.getY() + velocity.y * deltaTime);
	            Barra1.setY(Barra1.getY() + velocity.y * deltaTime);
	       
//	    		Bol
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
/*	 		 LIMITES de la barra VERTICAL
				if (Barra1.getY() < 0) {
				    Barra1.setY(0);
				    velocity.y = 0; 
	            }
				if (Barra1.getY() > 700) {
				    Barra1.setY(700);
				    velocity.y = 0; 
	            }*/
	            
//			     MOVIMIENTO DEL BARCO
				if(juego.jugador.getY() > (ilit)+1) {
					juego.jugador.setY(juego.jugador.getY() + aceler * Gdx.graphics.getDeltaTime());
	                acceleration.set(0, -6f);
	            }else {
	            	stopBoat();
	            }
	            
//				  LIMITES DEL BARCO HORIZONTAL
				
				if (juego.jugador.getX() < juego.Carril(1)) {
					juego.jugador.setX(juego.Carril(1));
				    velocity.x = 0; 
				    camera.position.x = WIDTH / 2;
				}
				if (juego.jugador.getX() > WIDTH - juego.Carril2(1)) {
					juego.jugador.setX(WIDTH-juego.Carril2(1));
				    velocity.x = 0; 
				    camera.position.x = WIDTH / 2;
				}
				//	LIMITES DEL BARCO VERTICAL
				if(ilit <  bottomLimit) ilit = bottomLimit; 
				
				if (juego.jugador.getY()<(ilit+10)) {
					juego.jugador.setY((ilit)+10);
					if(velocity.y<0) stopBoat();
				}
	            
	            
	   		 final int tiempoDeEsperaEntreObstaculos = 400; // espera 100 milisegundos entre cada generaci�n de obst�culos
			 if (TimeUtils.millis() - lastDropTimeRoca > tiempoDeEsperaEntreObstaculos && Rocas.size<15) {
			     spawnRoca(Rocas);
			     
			 }
			 if (TimeUtils.millis() - lastDropTimeTroncos > tiempoDeEsperaEntreObstaculos && Troncos.size<14) {
			     spawnTronco(Troncos);
			    
			 }
			 if (TimeUtils.millis() - lastDropTimeCocodrilos > tiempoDeEsperaEntreObstaculos && Cocodrilos.size<15) {
			     spawnCocodrilo(Cocodrilos);
			  
			 }
			 
			    Rectangle rect1 = juego.jugador.getBoundingRectangle(); 

			 for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
			      Rectangle roca = iter.next();
//			      roca.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(roca.y + 64 < bottomLimit+100) iter.remove();
			      if(roca.overlaps(rect1)) {
				         iter.remove();
				         juego.jugador.getVidas();
				      }
			   }
 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(tronco.y +64<bottomLimit+100) iter.remove();
			      if(tronco.overlaps(rect1)) {
				         iter.remove();
				         juego.jugador.setVidas();
				  }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      if(cocodrilo.x < WIDTH/ 2)
			    	  cocodrilo.x += 20 * Gdx.graphics.getDeltaTime();
			      if(cocodrilo.y + 64 < bottomLimit+100) iter.remove();
			      if(cocodrilo.overlaps(rect1)) {
				         iter.remove();
				         juego.jugador.setVidas();
				      }
			 }
			 
//			 if(juego.jugador.getVidas() == 0) {
//				 juego.inicializar();
//				 juego.iniciar(table, batch, stage);
//				
//				 juego.jugador.setVidas(1);
//			 }
	        }
	        
	        public void stopBoat() {
	            // Establece la velocidad en cero
	            velocity.set(0, 0);
	            // Establece la aceleración en cero
	            acceleration.set(0, 0);
	        }

	        //Para el minijuego

	        //Obstaculos
	        protected void spawntuboab(Array<Rectangle> Tuboab,Array<Rectangle> Tuboar) {
	        	float y=MathUtils.random(900, 1700);
	  	     
	  	    Rectangle tuboar = new Rectangle(minijuego.jugador.getX()+1500,y,83,564);
	  	  Rectangle tuboab = new Rectangle(minijuego.jugador.getX()+1500,y-900,83,564);
	  	      Tuboab.add(tuboab);
	  	      Tuboar.add(tuboar);
	  	      lastDropTimeTuboab = TimeUtils.millis();
	  	   }
//	        protected void spawntuboar(Array<Rectangle> Tuboar) {
//	        	 Rectangle tuboar = new Rectangle((rightLimitmini+360),MathUtils.random(0, HEIGHT-64),83,564);
//		  	      Tuboar.add(tuboar);
//		  	      lastDropTimeTuboar = TimeUtils.millis();
//		  	   }
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