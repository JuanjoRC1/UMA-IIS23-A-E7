package com.barquitosfc.game;

import java.util.Iterator;
import java.util.Random;

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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import java.awt.Color;
import java.awt.Graphics;
import com.badlogic.gdx.utils.Array;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO
	}
	public Sound bum;
	public int ct;
	public Music omega;
	float leftLimit, rightLimit, topLimit, bottomLimit,leftLimitmini, rightLimitmini, topLimitmini, bottomLimitmini;
	protected Vector2 velocity = new Vector2(0,0);
	protected Vector2 acceleration = new Vector2(0,0);
	protected Texture fondofla;
	protected  float gravity=(float) -1.4;
	protected int aceler;
	protected int barcoDef;
	protected int vidas = Tienda.vidasS;
	protected int vPunta = Tienda.vPuntaS;
	protected int dinero = Tienda.dinero;
	
	protected float ilit = HEIGHT / 7; 
	protected Barco boat;
	protected BitmapFont font;
	protected Texture bInicio,bAjustes,bTienda,bSalir;
	protected Texture board,boardPlay,boardminit,boatTexture,TRoca,TTronco,TCoco,TCoco2,Tuboabt,Tuboart,fin;
	protected Stage stage;
	protected Table table;
	public   static GameState gameState;
	protected OrthographicCamera camera;
	protected OrthographicCamera camfla;
	protected SpriteBatch batch;
	protected Sprite Bird;
	protected Sprite Bola;
	protected SpriteDrawable spriteBInicio;
	protected SpriteDrawable spriteBAjustes;
	protected SpriteDrawable spriteBTienda;
	protected SpriteDrawable spriteBSalir;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	protected Array<Rectangle>Troncos,Rocas,Cocodrilos;
	protected Array<Rectangle> Tuboar,Tuboab;
	protected long lastDropTimeRoca,lastDropTimeTroncos,lastDropTimeCocodrilos,lastDropTimeTuboab,lastDropTimeTuboar;
	protected Tienda tienda;
	protected Juego juego;
	protected minijuego minijuego;
	protected Texture n0,n1,n2,n3,n4,n5,n6,n7,n8,n9;
	protected AISystem AI1,AI2,AI3;
   
    

	private Sound jump; 
	public DragonBoatGame() {
		this(0,Tienda.vidasS,Tienda.vPuntaS*30,Tienda.dinero);
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
	

		 
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 
		 //TIENDA
		 tienda= new Tienda();
		 tienda.inicializar();
		 
		 // Juego 
		 juego = new Juego();
		 //Barco
		 boat = new Barco(boatTexture,1);
		 //minijuego
			n0 = new Texture(Gdx.files.internal("data/Numero0.png"));
	   	 	n1 = new Texture(Gdx.files.internal("data/Numero1.png"));
	   	 	n2 = new Texture(Gdx.files.internal("data/Numero2.png"));
	   	 	n3 = new Texture(Gdx.files.internal("data/Numero3.png"));
	   	 	n4 = new Texture(Gdx.files.internal("data/Numero4.png"));
	   	 	n5 = new Texture(Gdx.files.internal("data/Numero5.png"));
	   	 	n6 = new Texture(Gdx.files.internal("data/Numero6.png"));
	   	 	n7 = new Texture(Gdx.files.internal("data/Numero7.png"));
	   	 	n8 = new Texture(Gdx.files.internal("data/Numero8.png"));
	   	 	n9 = new Texture(Gdx.files.internal("data/Numero9.png"));
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
		 bum =  Gdx.audio.newSound(Gdx.files.internal("sonidos/jump.mp3"));
		 jump =  Gdx.audio.newSound(Gdx.files.internal("sonidos/jump.mp3"));
		 omega =  Gdx.audio.newMusic(Gdx.files.internal("sonidos/omegaelfuelte.mp3"));
		 
		 
		 //Obstaculos
		 
		 TRoca = new Texture(Gdx.files.internal("data/Rocap.png"));
		 TCoco2 = new Texture(Gdx.files.internal("data/Icocop.png"));
		 TCoco = new Texture(Gdx.files.internal("data/Dcocop.png"));
		 TTronco = new Texture(Gdx.files.internal("data/Troncop.png")); 
		 Rocas = new Array<Rectangle>();
		 Troncos = new Array<Rectangle>();
		 Cocodrilos = new Array<Rectangle>();
		 
		 //IA
		 AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos);
		 AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos);
		 AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos);
		 

	}

	public void setValoresBarco(int eleccionBarco,int vidasS,int vPuntaS,int dineroS) {
        this.barcoDef = eleccionBarco;
        this.vidas = vidasS;
        this.vPunta = vPuntaS*30;
        this.dinero = dineroS;
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
			buttonPlay.setPosition(table.getOriginX()-200, table.getOriginY());
			buttonPlay.setSize(300,40);
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
			buttonConfig.setSize(300,40);

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
			buttonShop.setWidth(300);
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
			buttonQuit.setWidth(300);
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
			
			juego.setSkinBarcos(barcoDef);
			juego.iniciar(table, batch, stage);
//			PINTAR LOS OBSTACULOS
			batch.begin();	
			 for(Rectangle roca: Rocas) {batch.draw(TRoca, roca.x, roca.y);}
			 for(Rectangle tronco: Troncos) {batch.draw(TTronco, tronco.x, tronco.y);}
			 for(Rectangle cocodrilo: Cocodrilos) {
				 if (cocodrilo.x < WIDTH / 2) batch.draw(TCoco, cocodrilo.x, cocodrilo.y);
				 else batch.draw(TCoco2, cocodrilo.x, cocodrilo.y);
			 }
			batch.end();
			actualizarIA();
			batch.begin();
			
			font.draw(batch, "VIDAS: " + vidas +"VELOCIDAD: "+ vPunta , 100, juego.jugador.getY()+100);
			batch.end();
//			
            handleInput();
			update(Gdx.graphics.getDeltaTime());
            break;
			
		case CONFIG:
		
			 
			float pos=minijuego.jugador.getX()+1500;
			minijuego.iniciar(table, batch,stage);
			batch.begin();
			for(Rectangle tuboar: Tuboar) {batch.draw(Tuboart, tuboar.x, tuboar.y);
			 
			}
			for(Rectangle tuboab: Tuboab) {batch.draw(Tuboabt, tuboab.x, tuboab.y);}
			if(minijuego.jugador.getX()==pos) {
				 ct++;
			 }
	
			batch.end();
			batch.begin();
			font.draw(batch, "x: " + minijuego.jugador.getX() +"Y: "+ minijuego.jugador.getY(), 100, minijuego.jugador.getY()+100);
			batch.end();
			
			
			updateflapi(Gdx.graphics.getDeltaTime()*250);

			
			break;
			
		case SHOP:
			setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
			tienda.iniciar(table,batch,stage);
			juego.setStatsBarco(vidas, vPunta);

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
	      Rectangle roca = new Rectangle(MathUtils.random(0, WIDTH-64),
	    		  (MathUtils.random(topLimit+360, topLimit+HEIGHT)),52,49);
	      Rocas.add(roca);
	      lastDropTimeRoca = TimeUtils.millis();
	   }
	 protected void spawnTronco(Array<Rectangle>Troncos) {
	      Rectangle tronco = new Rectangle(MathUtils.random(0, WIDTH-64),
	    		  (MathUtils.random(topLimit+360, topLimit+HEIGHT)),18,51);
	      Troncos.add(tronco);
	      lastDropTimeTroncos = TimeUtils.millis();
	   }
	 protected void spawnCocodrilo(Array<Rectangle> Cocodrilos) {
	      Rectangle cocodrilo = new Rectangle(MathUtils.random(0, WIDTH-64),
	    		  (MathUtils.random(topLimit+360, topLimit+HEIGHT)),78,22);
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
	            
	            if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
	            	ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos);	
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
	                gameState = GameState.MENU;
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
				
			//	omega.play();
	    	 		camfla.update();
				    leftLimitmini = camfla.position.x - 1920 / 2;
				    rightLimitmini = camfla.position.x + 1920 / 2;
				    topLimitmini = camfla.position.y + 1080 / 2;
				    bottomLimitmini = camfla.position.y - 1080 / 2;
//					movimiento del pajaro
				    minijuego.jugador.setX(minijuego.jugador.getX() +  deltaTime);
				    if(minijuego.jugador.getY()>450) minijuego.jugador.setY((minijuego.jugador.getY()+gravity*deltaTime));
				   
				   
				    if(Gdx.input.isKeyPressed(Keys.SPACE)&&minijuego.jugador.getY()<1450) {
				    	
				    	minijuego.jugador.setY((float) (minijuego.jugador.getY()+5.2*deltaTime));
				    }
				    if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
		                gameState = GameState.MENU;
		            }
				    
				    final int tiempoDeEsperaEntreObstaculosmini = 1250;
				  
				    if(TimeUtils.millis() - lastDropTimeTuboab > tiempoDeEsperaEntreObstaculosmini&&minijuego.jugador.getX()>1986) {
				    	bum.play();
				    	ct++;
				    }
				    if(minijuego.jugador.getY()==1080) {
				    	  minijuego.jugador.setY((minijuego.jugador.getY()+gravity*deltaTime));

				    }
				    batch.begin();
//				    font = new BitmapFont(Gdx.files.internal("minijuego/fuente.ttf"));
//			        font.getData().scale(7f);
					font.draw(batch, "La posicion es "+minijuego.jugador.getY(),minijuego.jugador.getX(), 1080);
					batch.end();
				    batch.begin();
				    
					font.draw(batch, "La puntuacion es "+ct, minijuego.jugador.getX()+1920/4, 1080/2);
					batch.end();
				   // espera 100 milisegundos entre cada generaci�n de obst�culos
					 if (TimeUtils.millis() - lastDropTimeTuboab > tiempoDeEsperaEntreObstaculosmini) {
					     spawntuboab(Tuboab,Tuboar);
					     
					 }

					 Rectangle drag= minijuego.jugador.getBoundingRectangle();
					 for (Iterator<Rectangle> iter = Tuboab.iterator(); iter.hasNext(); ) {
					
					      Rectangle Tuboab = iter.next();
					 
//					      roca.y -= 100 * Gdx.graphics.getDeltaTime();
				      if(Tuboab.overlaps(drag)) {
				    		 bum.play();
				    		batch.begin();
				    		batch.draw(fin, minijuego.jugador.getX()+500, camfla.position.y);
				    		batch.end();
				    		 try {
				    			    Thread.sleep(1300); // 5000 milisegundos son equivalentes a 5 segundos
				    			} catch (InterruptedException e) {
				    			    e.printStackTrace();
				    			}
					    	  gameState=GameState.PLAY;
					      }
					   }
					 for (Iterator<Rectangle> iterar = Tuboar.iterator(); iterar.hasNext(); ) {
						 
					  
					      Rectangle Tuboar = iterar.next();
					      
				      if(Tuboar.overlaps(drag)) {
				    		 bum.play();
				    		batch.begin();
				    		batch.draw(fin, minijuego.jugador.getX()+500, camfla.position.y);
				    		 batch.end();
				    		
				    		 try {
				    			    Thread.sleep(1300); // 5000 milisegundos son equivalentes a 5 segundos
				    			} catch (InterruptedException e) {
				    			    e.printStackTrace();
				    			}
				    		
					    	  gameState=GameState.PLAY;
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
	         
	       

	            
//			     MOVIMIENTO DEL BARCO
				if(juego.jugador.getY() > (ilit)+1) {
					juego.jugador.setY(juego.jugador.getY() + aceler * Gdx.graphics.getDeltaTime());
	                acceleration.set(0, -6f);
	            }else {
	            	stopBoat();
	            }
	            
//				  LIMITES DEL BARCO HORIZONTAL
				
				if (juego.jugador.getX() < juego.CarrilIzq(juego.C2)) {
					juego.jugador.setX(juego.CarrilIzq(juego.C2));
				    velocity.x = 0; 
				    camera.position.x = WIDTH / 2;
				}
				if (juego.jugador.getX() >  juego.CarrilDer(juego.C2)) {
					juego.jugador.setX(juego.CarrilDer(juego.C2));
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
			 if (TimeUtils.millis() - lastDropTimeRoca > tiempoDeEsperaEntreObstaculos && Rocas.size<50) {
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
						 juego.setStatsBarco(vidas, vPunta-vPunta/20);
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 70; 
				         acceleration.x = 0;
				      }
			   }
 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(tronco.y +64<bottomLimit+100) iter.remove();
			      if(tronco.overlaps(rect1)) { 
				         iter.remove();
						 juego.setStatsBarco(vidas-1, vPunta-vPunta/20);
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 70;
				         acceleration.x = 0;
				  }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			      Rectangle cocodrilo = iter.next();
			      if(cocodrilo.x < WIDTH/ 2) {
			    	  cocodrilo.x += 30 * Gdx.graphics.getDeltaTime();
 
			      } else { 
			    	  cocodrilo.x -= 20 * Gdx.graphics.getDeltaTime();
			      }
			      if(cocodrilo.y + 64 < bottomLimit+100) iter.remove();
			      if(cocodrilo.overlaps(rect1)) {
				         iter.remove();
						 juego.setStatsBarco(vidas-2, vPunta-vPunta/20);
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 70;
				         acceleration.x = 0;
				      }
			 }
			 

			 
			 if(vidas <= 0) {
				 	ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos);	
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					gameState=GameState.MENU;
					
					
					
			 }

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
	        	float y=MathUtils.random(900, 1000);
	  	     
		  	    Rectangle tuboar = new Rectangle(minijuego.jugador.getX()+1500,y,70,590);
		  	    Rectangle tuboab = new Rectangle(minijuego.jugador.getX()+1500,y-900,70,590);
	  	      Tuboab.add(tuboab);
	  	      Tuboar.add(tuboar);
	  	      lastDropTimeTuboab = TimeUtils.millis();
	  	   }
	        
	       public void actualizarIA() {
	    	   AI1.update(Gdx.graphics.getDeltaTime(),1);
	    	  // AI2.update(Gdx.graphics.getDeltaTime());
	    	  // AI3.update(Gdx.graphics.getDeltaTime());
	       }

	       
	       
	        
	       
	        
/*	        public void actualizarIA() {
	        	Rectangle rect1 = juego.IA1.getBoundingRectangle();
	        	Rectangle rect2 = juego.IA2.getBoundingRectangle();
	        	Rectangle rect3 = juego.IA3.getBoundingRectangle();
	        	
	    		//IA1
	    		for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
	    		      Rectangle roca = iter.next();
	    		      if ( juego.IA1.getX() <= (roca.x+roca.width)  && juego.IA1.getX()>= roca.x && roca.y-juego.IA1.getY()<300 ) {
	    		    	  if ((roca.x+(roca.width/2)-juego.IA1.getX()+(juego.IA1.getWidth()/2))>roca.x-juego.IA1.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (roca.x+roca.width)  && juego.IA1.getX()== roca.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (roca.x+roca.width)  && juego.IA1.getX()== roca.x );
	    		    	  }
	    		      }
	    		      if(roca.overlaps(rect1)) {
	    			         iter.remove();
	    			         juego.IA1.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
	    		      Rectangle tronco = iter.next();
	    		      if ( juego.IA1.getX() <= (tronco.x+tronco.width)  && juego.IA1.getX()>= tronco.x && tronco.y-juego.IA1.getY()<300 ) {
	    		    	  if ((tronco.x+(tronco.width/2)-juego.IA1.getX()+(juego.IA1.getWidth()/2))>tronco.x-juego.IA1.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (tronco.x+tronco.width)  && juego.IA1.getX()== tronco.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (tronco.x+tronco.width)  && juego.IA1.getX()== tronco.x );
	    		    	  }
	    		      }
	    		      if(tronco.overlaps(rect1)) {
	    			         iter.remove();
	    			         juego.IA1.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
	    		      Rectangle cocodrilo = iter.next();
	    		      if ( juego.IA1.getX() <= (cocodrilo.x+cocodrilo.width)  && juego.IA1.getX()>= cocodrilo.x && cocodrilo.y-juego.IA1.getY()<300 ) {
	    		    	  if ((cocodrilo.x+(cocodrilo.width/2)-juego.IA1.getX()+(juego.IA1.getWidth()/2))>cocodrilo.x-juego.IA1.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA1.getX()== cocodrilo.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA1.setX(juego.IA1.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA1.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA1.getX()== cocodrilo.x );
	    		    	  }
	    		      }
	    		      if(cocodrilo.overlaps(rect1)) {
	    			         iter.remove();
	    			         juego.IA1.getVidas();
	    			      }
	    		   }
	    		
	    		
	    		//IA2
	    		
	    		for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
	    		      Rectangle roca = iter.next();
	    		      if ( juego.IA2.getX() <= (roca.x+roca.width)  && juego.IA2.getX()>= roca.x && roca.y-juego.IA2.getY()<300 ) {
	    		    	  if ((roca.x+(roca.width/2)-juego.IA2.getX()+(juego.IA2.getWidth()/2))>roca.x-juego.IA2.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (roca.x+roca.width)  && juego.IA2.getX()== roca.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (roca.x+roca.width)  && juego.IA2.getX()== roca.x );
	    		    	  }
	    		      }
	    		      if(roca.overlaps(rect2)) {
	    			         iter.remove();
	    			         juego.IA2.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
	    		      Rectangle tronco = iter.next();
	    		      if ( juego.IA2.getX() <= (tronco.x+tronco.width)  && juego.IA2.getX()>= tronco.x && tronco.y-juego.IA2.getY()<300 ) {
	    		    	  if ((tronco.x+(tronco.width/2)-juego.IA2.getX()+(juego.IA2.getWidth()/2))>tronco.x-juego.IA2.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (tronco.x+tronco.width)  && juego.IA2.getX()== tronco.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (tronco.x+tronco.width)  && juego.IA2.getX()== tronco.x );
	    		    	  }
	    		      }
	    		      if(tronco.overlaps(rect2)) {
	    			         iter.remove();
	    			         juego.IA2.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
	    		      Rectangle cocodrilo = iter.next();
	    		      if ( juego.IA2.getX() <= (cocodrilo.x+cocodrilo.width)  && juego.IA2.getX()>= cocodrilo.x && cocodrilo.y-juego.IA2.getY()<300 ) {
	    		    	  if ((cocodrilo.x+(cocodrilo.width/2)-juego.IA2.getX()+(juego.IA2.getWidth()/2))>cocodrilo.x-juego.IA2.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA2.getX()== cocodrilo.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA2.setX(juego.IA2.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA2.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA2.getX()== cocodrilo.x );
	    		    	  }
	    		      }
	    		      if(cocodrilo.overlaps(rect2)) {
	    			         iter.remove();
	    			         juego.IA2.getVidas();
	    			      }
	    		   }
	    		
	    		
	    		//IA3
	    		
	    		for (Iterator<Rectangle> iter = Rocas.iterator(); iter.hasNext(); ) {
	    		      Rectangle roca = iter.next();
	    		      if ( juego.IA3.getX() <= (roca.x+roca.width)  && juego.IA3.getX()>= roca.x && roca.y-juego.IA3.getY()<300 ) {
	    		    	  if ((roca.x+(roca.width/2)-juego.IA3.getX()+(juego.IA3.getWidth()/2))>roca.x-juego.IA3.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (roca.x+roca.width)  && juego.IA3.getX()== roca.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (roca.x+roca.width)  && juego.IA3.getX()== roca.x );
	    		    	  }
	    		      }
	    		      if(roca.overlaps(rect3)) {
	    			         iter.remove();
	    			         juego.IA3.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
	    		      Rectangle tronco = iter.next();
	    		      if ( juego.IA3.getX() <= (tronco.x+tronco.width)  && juego.IA3.getX()>= tronco.x && tronco.y-juego.IA3.getY()<300 ) {
	    		    	  if ((tronco.x+tronco.width-juego.IA3.getX()+(juego.IA3.getWidth()/2))>tronco.x-juego.IA3.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (tronco.x+tronco.width)  && juego.IA3.getX()== tronco.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (tronco.x+tronco.width)  && juego.IA3.getX()== tronco.x );
	    		    	  }
	    		      }
	    		      if(tronco.overlaps(rect3)) {
	    			         iter.remove();
	    			         juego.IA3.getVidas();
	    			      }
	    		   }
	    		
	    		for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
	    		      Rectangle cocodrilo = iter.next();
	    		      if ( juego.IA3.getX() <= (cocodrilo.x+cocodrilo.width)  && juego.IA3.getX()>= cocodrilo.x && cocodrilo.y-juego.IA3.getY()<300 ) {
	    		    	  if ((cocodrilo.x+cocodrilo.width-juego.IA3.getX()+(juego.IA3.getWidth()/2))>cocodrilo.x-juego.IA3.getX()) {// COMPARA LOS CENTROS?
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()+100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA3.getX()== cocodrilo.x );
	    		    	  } else {
	    		    		  do {
	    		    			  juego.IA3.setX(juego.IA3.getX()-100*Gdx.graphics.getDeltaTime());
	    		    		  } while ( juego.IA3.getX() == (cocodrilo.x+cocodrilo.width)  && juego.IA3.getX()== cocodrilo.x );
	    		    	  }
	    		      }
	    		      if(cocodrilo.overlaps(rect3)) {
	    			         iter.remove();
	    			         juego.IA3.getVidas(); 	
	    			      }
	    		   }
	    		
	    	} */
		     

	}