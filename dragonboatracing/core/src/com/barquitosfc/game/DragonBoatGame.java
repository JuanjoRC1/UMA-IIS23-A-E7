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
	float leftLimit, rightLimit, topLimit, bottomLimit;
	private Vector2 velocity = new Vector2(0,0);
	private Vector2 velocitybar = new Vector2();
	private Vector2 acceleration = new Vector2(0,0);
	private int dinero = 12;
	private int vidas1= 5;
	private int vPunta1= 10;
	private int vidas2= 10;
	private int vPunta2= 5;
	private int vidas3= 5;
	private int vPunta3= 5;
	private int vidasS=vidas1;
	private int vPuntaS=vPunta1;
	private int agilidad = 2;
	private int eleccionBarco=0;
	private int dineroSeguridad = dinero;
    private int barcoSeguridad = eleccionBarco;    
    private int vidaSeguridad1 = vidas1;
    private int velocidadSeguridad1 = vPunta1;
	private int unidadesD;
	private int decenasD;
	private int unidadesVid;
	private int decenasVid;
	private int unidadesVel;
	private int decenasVel;
	private int contadorFondo;
	private int aceler;
	private float ilit = 500; 
	private BitmapFont font;
	private Texture bInicio,bAjustes,bTienda,bSalir;
	private Stage stage;
	public static GameState gameState;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture board,boardPlay,boardminit,boatTexture,
	Barra1Texture,Barra2Texture,BolaTexture,TRoca,TTronco,TCoco;
	private Texture siguiente;
	private Texture anterior;
	private SpriteDrawable spriteSiguiente;
	private SpriteDrawable spriteAnterior;
	private Texture barraVida[] = new Texture[21];
	private Texture barraVelocidad[] = new Texture[21];
	private Texture barcosElec[] = new Texture[3];
	private Texture unidadesA[] = new Texture[10];
	private Texture decenasA[] = new Texture[10];
	private Barco boat;
	private Sprite Barra1;
	private Sprite Barra2;
	private Sprite Bola;
	private Table table;
	private Texture fondoTienda;
	private Texture masR, menosR,masM,menosM, casa;
	private SpriteDrawable spriteBInicio;
	private SpriteDrawable spriteBAjustes;
	private SpriteDrawable spriteBTienda;
	private SpriteDrawable spriteBSalir;
	private SpriteDrawable spriteMasR, spriteMenosR,spriteMasM,spriteMenosM, spriteBCasa,spriteBConfirmar;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	private Array<Rectangle> Rocas,Troncos,Cocodrilos;
	private long lastDropTimeRoca,lastDropTimeTroncos,lastDropTimeCocodrilos;
	private Texture barra0;
	private Texture barra1;
	private Texture barra2;
	private Texture barra3;
	private Texture barra4;
	private Texture barra5;
	private Texture barra6;
	private Texture barra7;
	private Texture barra8;
	private Texture barra9;
	private Texture barra10;
	private Texture barra11;
	private Texture barra12;
	private Texture barra13;
	private Texture barra14;
	private Texture barra15;
	private Texture barra16;
	private Texture barra17;
	private Texture barra18;
	private Texture barra19;
	private Texture barra20;
	private Texture barraV0;
	private Texture barraV1;
	private Texture barraV2;
	private Texture barraV3;
	private Texture barraV4;
	private Texture barraV5;
	private Texture barraV6;
	private Texture barraV7;
	private Texture barraV8;
	private Texture barraV9;
	private Texture barraV10;
	private Texture barraV11;
	private Texture barraV12;
	private Texture barraV13;
	private Texture barraV14;
	private Texture barraV15;
	private Texture barraV16;
	private Texture barraV17;
	private Texture barraV18;
	private Texture barraV19;
	private Texture barraV20;
	private Texture barco1;
	private Texture barco2;
	private Texture barco3;
	private Texture n0;
	private Texture n1;
	private Texture n2;
	private Texture n3;
	private Texture n4;
	private Texture n5;
	private Texture n6;
	private Texture n7;
	private Texture n8;
	private Texture n9;	
	private Texture velocidadB;
	private Texture vidaB;
	private Texture barcosE;
    private Texture letreroVida;
    private Texture letreroVelocidad;
    private Texture unidadesB;
    private Texture decenasB;
    private Texture unidadesV;
    private Texture decenasV;
    private Texture unidadesVp;
    private Texture decenasVp;
    private Texture confirmar;
    private Texture grifoCoin;
   
    
//Para el minijuego
	private int speedx = 200; 
	private int speedy = 200; 


	
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
		 siguiente = new Texture(Gdx.files.internal("data/Flecha_Derecha.png"));
		 anterior = new Texture(Gdx.files.internal("data/Flecha_Izquierda.png"));
		 masR = new Texture(Gdx.files.internal("ui/Simbolo_Mas.png"));
		 menosR = new Texture(Gdx.files.internal("ui/Simbolo_Menos.png"));
		 masM = new Texture(Gdx.files.internal("ui/Boton_Mas_Morado.png"));
		 menosM = new Texture(Gdx.files.internal("ui/Boton_Menos_Morado.png"));
		 confirmar = new Texture(Gdx.files.internal("ui/Boton_Tick.png"));
		 casa = new Texture(Gdx.files.internal("data/Casa_Menu.png"));
		 letreroVida = new Texture(Gdx.files.internal("data/churumpinpon.jpg"));
		 letreroVelocidad = new Texture(Gdx.files.internal("data/Piedra1.png"));
		 grifoCoin = new Texture(Gdx.files.internal("data/grifocoin.png"));

		 font = new BitmapFont();

		//BACKGROUND
		 board = new Texture(Gdx.files.internal("fondos/fondoMENU.png"));
		 boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Prueba.png"));
		 boardminit = new Texture(Gdx.files.internal("fondos/fondomini.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/boat.jpeg"));
		 Barra1Texture=new Texture(Gdx.files.internal("data/barco_rojo.png"));
		 Barra2Texture=new Texture(Gdx.files.internal("data/barco_azul.png"));
		 BolaTexture= new Texture(Gdx.files.internal("data/bala.png"));
		 fondoTienda = new Texture(Gdx.files.internal("fondos/FONDO_TIENDA.png"));
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

		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));// sprite cuando esta sin apretar, apretado y con el raton encima
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 spriteMasR = new SpriteDrawable(new Sprite(masR)); 
		 spriteMenosR = new SpriteDrawable(new Sprite(menosR)); 
		 spriteMasM = new SpriteDrawable(new Sprite(masM)); 
		 spriteMenosM = new SpriteDrawable(new Sprite(menosM)); 
		 spriteBCasa = new SpriteDrawable(new Sprite(casa));
		 spriteBConfirmar = new SpriteDrawable(new Sprite(confirmar));
		 TRoca = new Texture(Gdx.files.internal("data/Roca.png"));
		 TCoco = new Texture(Gdx.files.internal("data/Icoco.png"));
		 TTronco = new Texture(Gdx.files.internal("data/Tronco.png"));
		 spriteSiguiente = new SpriteDrawable(new Sprite(siguiente));
		 spriteAnterior = new SpriteDrawable( new Sprite(anterior));
		 barra0 = new Texture(Gdx.files.internal("data/Barra_Vacia.png"));;
		 barra1 = new Texture(Gdx.files.internal("data/Barra_Vida1.png"));;
		 barra2 = new Texture(Gdx.files.internal("data/Barra_Vida2.png"));;
		 barra3 = new Texture(Gdx.files.internal("data/Barra_Vida3.png"));;
		 barra4 = new Texture(Gdx.files.internal("data/Barra_Vida4.png"));;
		 barra5= new Texture(Gdx.files.internal("data/Barra_Vida5.png"));;
		 barra6= new Texture(Gdx.files.internal("data/Barra_Vida6.png"));;
		 barra7= new Texture(Gdx.files.internal("data/Barra_Vida7.png"));;
		 barra8= new Texture(Gdx.files.internal("data/Barra_Vida8.png"));;
		 barra9= new Texture(Gdx.files.internal("data/Barra_Vida9.png"));;
		 barra10= new Texture(Gdx.files.internal("data/Barra_Vida10.png"));;
		 barra11= new Texture(Gdx.files.internal("data/Barra_Vida11.png"));;
	     barra12= new Texture(Gdx.files.internal("data/Barra_Vida12.png"));;
	     barra13= new Texture(Gdx.files.internal("data/Barra_Vida13.png"));;
	     barra14= new Texture(Gdx.files.internal("data/Barra_Vida14.png"));;
	     barra15= new Texture(Gdx.files.internal("data/Barra_Vida15.png"));;
	     barra16= new Texture(Gdx.files.internal("data/Barra_Vida16.png"));;
	     barra17= new Texture(Gdx.files.internal("data/Barra_Vida17.png"));;
	     barra18= new Texture(Gdx.files.internal("data/Barra_Vida18.png"));; 
	     barra19= new Texture(Gdx.files.internal("data/Barra_Vida19.png"));;
	     barra20= new Texture(Gdx.files.internal("data/Barra_Vida.png"));;
	     barcosElec[0] = barco1;
	     barcosElec[1] = barco2;
	     barcosElec[2] = barco3;
		 barraV0 = new Texture(Gdx.files.internal("data/Barra_Vacia.png"));
		 barraV1 = new Texture(Gdx.files.internal("data/Barra_Velocidad1.png"));
		 barraV2 = new Texture(Gdx.files.internal("data/Barra_Velocidad2.png"));
		 barraV3 = new Texture(Gdx.files.internal("data/Barra_Velocidad3.png"));
		 barraV4 = new Texture(Gdx.files.internal("data/Barra_Velocidad4.png"));
		 barraV5 = new Texture(Gdx.files.internal("data/Barra_Velocidad5.png"));
		 barraV6 = new Texture(Gdx.files.internal("data/Barra_Velocidad6.png"));
		 barraV7 = new Texture(Gdx.files.internal("data/Barra_Velocidad7.png"));
		 barraV8 = new Texture(Gdx.files.internal("data/Barra_Velocidad8.png"));
		 barraV9 = new Texture(Gdx.files.internal("data/Barra_Velocidad9.png"));
		 barraV10 = new Texture(Gdx.files.internal("data/Barra_Velocidad10.png"));
		 barraV11 = new Texture(Gdx.files.internal("data/Barra_Velocidad11.png"));
		 barraV12 = new Texture(Gdx.files.internal("data/Barra_Velocidad12.png"));
		 barraV13 = new Texture(Gdx.files.internal("data/Barra_Velocidad13.png"));
		 barraV14 = new Texture(Gdx.files.internal("data/Barra_Velocidad14.png"));
		 barraV15 = new Texture(Gdx.files.internal("data/Barra_Velocidad15.png"));
		 barraV16 = new Texture(Gdx.files.internal("data/Barra_Velocidad16.png"));
		 barraV17 = new Texture(Gdx.files.internal("data/Barra_Velocidad17.png"));
		 barraV18 = new Texture(Gdx.files.internal("data/Barra_Velocidad18.png"));
		 barraV19 = new Texture(Gdx.files.internal("data/Barra_Velocidad19.png"));
		 barraV20 = new Texture(Gdx.files.internal("data/Barra_Velocidad20.png"));
		 barco1 = new Texture(Gdx.files.internal("data/churumpinpon.jpg"));
		 barco2 = new Texture(Gdx.files.internal("data/Piedra1.png"));
		 barco3 = new Texture(Gdx.files.internal("data/Bola.jpg"));
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

		 //Obstaculos 
		 Rocas = new Array<Rectangle>();
		 Troncos = new Array<Rectangle>();
		 Cocodrilos = new Array<Rectangle>();

		 barraVelocidad[0] = barraV0;
		 barraVelocidad[1] = barraV1;
		 barraVelocidad[2] = barraV2;
		 barraVelocidad[3] = barraV3;
		 barraVelocidad[4] = barraV4;
		 barraVelocidad[5] = barraV5;
		 barraVelocidad[6] = barraV6;
		 barraVelocidad[7] = barraV7;
		 barraVelocidad[8] = barraV8;
		 barraVelocidad[9] = barraV9;
		 barraVelocidad[10] = barraV10;
		 barraVelocidad[11] = barraV11;
	     barraVelocidad[12] = barraV12;
	     barraVelocidad[13] = barraV13;
	     barraVelocidad[14] = barraV14;
	     barraVelocidad[15] = barraV15;
	     barraVelocidad[16] = barraV16;
	     barraVelocidad[17] = barraV17;
	     barraVelocidad[18] = barraV18;
	     barraVelocidad[19] = barraV19;
	     barraVelocidad[20] = barraV20;

		 barraVida[0] = barra0;
		 barraVida[1] = barra1;
		 barraVida[2] = barra2;
		 barraVida[3] = barra3;
		 barraVida[4] = barra4;
		 barraVida[5] = barra5;
		 barraVida[6] = barra6;
		 barraVida[7] = barra7;
		 barraVida[8] = barra8;
		 barraVida[9] = barra9;
		 barraVida[10] = barra10;
		 barraVida[11] = barra11;
	     barraVida[12] = barra12;
	     barraVida[13] = barra13;
	     barraVida[14] = barra14;
	     barraVida[15] = barra15;
	     barraVida[16] = barra16;
	     barraVida[17] = barra17;
	     barraVida[18] = barra18;
	     barraVida[19] = barra19;
	     barraVida[20] = barra20;
	     barcosElec[0] = barco1;
	     barcosElec[1] = barco2;
	     barcosElec[2] = barco3;
	     unidadesA[0] = n0;
	     unidadesA[1] = n1;
	     unidadesA[2] = n2;
	     unidadesA[3] = n3;
	     unidadesA[4] = n4;
	     unidadesA[5] = n5;
	     unidadesA[6] = n6;
	     unidadesA[7] = n7;
	     unidadesA[8] = n8;
	     unidadesA[9] = n9;
	     decenasA[0] = n0;
	     decenasA[1] = n1;
	     decenasA[2] = n2;
	     decenasA[3] = n3;
	     decenasA[4] = n4;
	     decenasA[5] = n5;
	     decenasA[6] = n6;
	     decenasA[7] = n7;
	     decenasA[8] = n8;
	     decenasA[9] = n9;

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
				contadorFondo=i;
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
			/*Ventana ventana = new Ventana();
			Thread t1= new Thread();
			t1.start();*/
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
			table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
	        unidadesD =dinero%10;
	        decenasD = dinero/10;
	        unidadesVid =vidasS%10;
	        decenasVid = vidasS/10;
	        unidadesVel =vPuntaS%10;
	        decenasVel = vPuntaS/10;
	       
	        
	        velocidadB = barraVelocidad[vPuntaS];
	        vidaB= barraVida[vidasS];
	        barcosE = barcosElec[eleccionBarco];
	        unidadesB = unidadesA[unidadesD];
	        decenasB = decenasA[decenasD];
	        unidadesV = unidadesA[unidadesVid];
	        decenasV = decenasA[decenasVid];
	        unidadesVp = unidadesA[unidadesVel];
	        decenasVp = decenasA[decenasVel];
	        
	        batch.begin();
			batch.draw(fondoTienda,0,0);
			batch.draw(barcosE, 775, 600, 400, 300);
			batch.draw(velocidadB, 700, 310, 500, 40);
			batch.draw(vidaB, 700, 160, 500, 40);
			batch.draw(letreroVida, 750, 210, 400, 40);
			batch.draw(letreroVelocidad, 750, 360, 400, 40);
			batch.draw(grifoCoin, 1800, 935, 45, 45);
			batch.draw(unidadesB, 1740, 940, 40, 40);
			batch.draw(decenasB, 1690, 940, 40, 40);
			batch.draw(unidadesV, 1210, 210, 40, 40);
			batch.draw(decenasV, 1160, 210, 40, 40);
			batch.draw(unidadesVp, 1210, 360, 40, 40);
			batch.draw(decenasVp, 1160, 360, 40, 40);
			
			batch.end();
			
			stage = new Stage();
			table=new Table();
			table.setPosition(0,HEIGHT/7);
			table.setFillParent(true);
			table.setHeight(200);
			stage.addActor(table);
			
			Button siguienteBarco= new Button(new Button.ButtonStyle(spriteSiguiente,spriteSiguiente,spriteSiguiente));
			siguienteBarco.setPosition(1220, 580);
			siguienteBarco.setSize(70,70);
			siguienteBarco.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if( eleccionBarco<2){
					eleccionBarco++;
					if(eleccionBarco==1) {
						vidasS=vidas2;
						vPuntaS = vPunta2;
					}else if(eleccionBarco==2) {
						vidasS= vidas3;
						vPuntaS= vPunta3;
					}
					}
					return false;
					
				}
			});
			table.addActor(siguienteBarco);
			
			Button anteriorBarco= new Button(new Button.ButtonStyle(spriteAnterior,spriteAnterior,spriteAnterior));
			anteriorBarco.setPosition(660, 580);
			anteriorBarco.setSize(70,70);
			anteriorBarco.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if( 0<eleccionBarco){
					eleccionBarco--;
					if(eleccionBarco==1) {
						vidasS=vidas2;
						vPuntaS=vPunta2;
					}else if(eleccionBarco==0) {
						vidasS= vidas1;
						vPuntaS = vPunta1;
					}
					}
					return false;
					
				}
			});
			table.addActor(anteriorBarco);
			
			Button masVelocidad= new Button(new Button.ButtonStyle(spriteMasM,spriteMasM,spriteMasM));
			masVelocidad.setPosition(1250, 150);
			masVelocidad.setSize(56,56);
			masVelocidad.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if(eleccionBarco==0 && vPunta1<20 && 0<dinero) {
						vPunta1++;
						vPuntaS++;
						dinero--;
					}else if((eleccionBarco==1) && vPunta2<15 && 0<dinero) {
						vPunta2++;
						vPuntaS++;
						dinero--;
					}else if(eleccionBarco==2 && vPunta3<15 &&0<dinero) {
						vPunta3++;
						vPuntaS++;
						dinero--;
					}
					return false;
					
				}
			});
			table.addActor(masVelocidad);
			
			Button masVida= new Button(new Button.ButtonStyle(spriteMasR,spriteMasR,spriteMasR));
			masVida.setPosition(1250, 0);
			masVida.setSize(56,56);
			masVida.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if(eleccionBarco==1 && vidas2<20 && 0<dinero) {
					vidas2++;
					vidasS++;
					dinero--;
					}else if((eleccionBarco ==0) && vidas1<15 && 0<dinero) {
					vidas1++;
					vidasS++;
					dinero--;
					}else if(eleccionBarco==2 && vidas3<15 && 0<dinero) {
						vidas3++;
						vidasS++;
						dinero--;
					}
					return false;
					
				}
			});
			table.addActor(masVida);
			
			
			Button menosVelocidad= new Button(new Button.ButtonStyle(spriteMenosM,spriteMenosM,spriteMenosM));
			menosVelocidad.setPosition(600, 150);
			menosVelocidad.setSize(56,56);
			menosVelocidad.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if(eleccionBarco==0 && 0<vPunta1 && 0<vPuntaS){
					vPunta1--;
					vPuntaS--;
					dinero++;
					}else if(eleccionBarco==1 && 0<vPunta2 && 0<vPuntaS) {
						vPunta2--;
						vPuntaS--;
						dinero++;
					}else if(eleccionBarco==2 && 0<vPunta3 && 0<vPuntaS) {
						vPunta3--;
						vPuntaS--;
						dinero++;
					}
					return false;
					
				}
			});
			table.addActor(menosVelocidad);
			
			Button menosVida= new Button(new Button.ButtonStyle(spriteMenosR,spriteMenosR,spriteMenosR));
			menosVida.setPosition(600, 0);
			menosVida.setSize(56,56);
			menosVida.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					if(eleccionBarco==0 && 0<vidas1 && 0<vPuntaS) {
					vidas1--;
					vidasS--;
					dinero++;
					}else if(eleccionBarco==1 && 0<vidas2 && 0<vPuntaS) {
					vidas2--;
					vidasS--;
					dinero++;
					}else if(eleccionBarco==2 && 0<vidas3 && 0<vPuntaS) {
					vidas3--;
					vidasS--;
					dinero++;
					}
					return false;
					
				}
			});
			table.addActor(menosVida);
			
			Button volverMenu= new Button(new Button.ButtonStyle(spriteBCasa,spriteBCasa,spriteBCasa));
			volverMenu.setPosition(40, 770);
			volverMenu.setSize(100,100);
			volverMenu.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					eleccionBarco= barcoSeguridad;
					vidasS=vidaSeguridad1;
					vPuntaS = velocidadSeguridad1;
					dinero = dineroSeguridad;
					return false;
					
				}
			});
			table.addActor(volverMenu);
			
			Button confirmar= new Button(new Button.ButtonStyle(spriteBConfirmar,spriteBConfirmar,spriteBConfirmar));
			confirmar.setPosition(1650, 0);
			confirmar.setSize(100,100);
			confirmar.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					gameState=GameState.MENU;
					return false;
					
				}
			});
			table.addActor(confirmar);
			
	
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
			Gdx.input.setInputProcessor(stage);

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
	            
	            
	   		 final int tiempoDeEsperaEntreObstaculos = 400; // espera 100 milisegundos entre cada generaci�n de obst�culos
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