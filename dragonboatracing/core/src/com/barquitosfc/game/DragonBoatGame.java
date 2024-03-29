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
import com.barquitosfc.game.DragonBoatGame.GameState;

import java.awt.Color;
import java.awt.Graphics;
import com.badlogic.gdx.utils.Array;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP,MINIJUEGO, ESCCONFIG,COUNTDOWN, COUNTDOWNMINI,FASE;
	}
	public int tiempo=1/120;
	public Sound bum;
	public int ct;
	public Music omega;
	public TextureRegion[] pajaroani= new TextureRegion[3];
	float leftLimit, rightLimit, topLimit, bottomLimit,leftLimitmini, rightLimitmini, topLimitmini, bottomLimitmini;
	protected Vector2 velocity = new Vector2(0,0); //V
	protected Vector2 acceleration = new Vector2(0,0);
	protected Texture fondofla;
	protected  float gravity=(float) -1.4; // Gravedad usada para el minijuego
	protected int aceler;
	protected int barcoDef;
	protected  int vidas = Tienda.vidasS;  // Vidas seleccionadas en la tienda.
	protected int vPunta = Tienda.vPuntaS; // Velocidad seleccionada en la tienda
	protected int dinero = Tienda.dinero;  // Dinero actual.
	protected int unidadVida;
	protected int decenaVida,tiempoP,unidadE,decenaE,posicion;
	protected float pos1,pos2,pos3,mipos;
	
	private int nRonda=0;
	private long lastDropEscudo;
	protected Texture unidadS,contadorVida,decenaS,unidadCt,decenaCt,unidadV,decenaV,posicionT,panelPos;
	int unidadC,decenaC;
	protected Array<Rectangle> AChampion,AEscudo,OEscudo,AUnidad,ADecena;
	protected Texture unidad[] = new Texture[10];
	protected Texture decena[] = new Texture[10];
	protected Texture posicionA[] = new Texture[4];
	protected float ilit = HEIGHT / 7; 
	protected Barco boat;
	protected BitmapFont font;
	protected Texture bInicio,bAjustes,bTienda,bSalir,ronda1,ronda2,ronda3,rondaFinal,enhorabuena,primero,segundo,tercero,cuarto;
	protected Texture board,boardPlay,boardminit,boatTexture,TRoca,TTronco,TCoco,TCoco2,Tuboabt,Tuboart,fin,bReanudar,fondoEscape,TEscudo,TChampion,vuela,rema,TlineaMeta;
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
	protected SpriteDrawable spriteBReanudar;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	protected Array<Rectangle>Troncos,Rocas,Cocodrilos;
	protected Array<Rectangle> Tuboar,Tuboab;
	protected Rectangle escudo,champion,unidadR,decenaR,escudoI;
	protected Rectangle lineaMeta;
	protected long lastDropTimeRoca,lastDropTimeTroncos,lastDropTimeCocodrilos,lastDropTimeTuboab,lastDropTimeTuboar;
	protected Tienda tienda;
	protected EscAjustes escAjustes;
	protected Juego juego;
	protected minijuego minijuego;
	protected Texture n0,n1,n2,n3,n4,n5,n6,n7,n8,n9;
	protected Carril carril; 
	protected long tiempoInicio;
	 protected long tiempoFase,tiempoMini;
	protected AISystem AI1,AI2,AI3;
	protected List<Boolean>estadosMovimiento;
	protected Texture currentFrame,currentFramechrum; 
	private Sound jump; 
	protected boolean escudosu=false,act=false,esc=false;
	protected int vInicial=Tienda.vidasS;
	protected int championx,championy,escudox,escudoy;
	private boolean perdisteIA=false,perdisteVidas=false;
	private Texture Tperdiste,TpVidas,tCampeon;
	protected int facil = 30;
	protected int normal = 50;
	protected int dificil = 80;
	protected int experto = 100;
	protected int IA1Stats = 1;
	protected int IA2Stats = 2;
	protected int IA3Stats = 0;

	
	



	
	public DragonBoatGame() {
		this(0,Tienda.vidasS,Tienda.vPuntaS,Tienda.dinero);
	}
	public DragonBoatGame(int eleccionBarco,int vidasS,int vPuntaS,int dinero) {
		this.barcoDef=eleccionBarco;
		this.vidas=vidasS;
		this.vPunta=vPuntaS*30;
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
		 boardminit = new Texture(Gdx.files.internal("minijuego/fondomini2.png"));
		 boatTexture= new Texture(Gdx.files.internal("data/BARCO_FIRE_OV.png"));
		 

		 

		 tCampeon= new Texture(Gdx.files.internal("data/Campeon.png"));
		 Tperdiste=new Texture(Gdx.files.internal("data/No_Llegaste_A_Meta.png"));
		 TpVidas= new Texture(Gdx.files.internal("data/Sin_Vidas.png"));
		 TlineaMeta= new Texture(Gdx.files.internal("fondos/Linea_Meta2.png"));
		 lineaMeta= new Rectangle(100,939+(1080*1),1706,98);

		 ronda1= new Texture(Gdx.files.internal("data/Ronda_1.png"));
		 ronda2= new Texture(Gdx.files.internal("data/Ronda_2.png"));
		 ronda3= new Texture(Gdx.files.internal("data/Ronda_3.png"));
		 rondaFinal= new Texture(Gdx.files.internal("data/Ronda_Final.png"));
		 enhorabuena= new Texture(Gdx.files.internal("data/Enhorabuena.png"));
		 
		 spriteBInicio= new SpriteDrawable(new Sprite(bInicio));
		 spriteBAjustes= new SpriteDrawable(new Sprite(bAjustes));
		 spriteBTienda= new SpriteDrawable(new Sprite(bTienda));
		 spriteBSalir= new SpriteDrawable(new Sprite(bSalir)); 
		 
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
	   	 	primero = new Texture(Gdx.files.internal("data/Posicion1.png"));
	   	 	segundo = new Texture(Gdx.files.internal("data/Posicion2.png"));
	   	 	tercero = new Texture(Gdx.files.internal("data/Posicion3.png"));
	   	 	cuarto = new Texture(Gdx.files.internal("data/Posicion4.png"));
	   	 	panelPos = new Texture(Gdx.files.internal("data/Panel_Posicion.png"));
		 Tuboart = new Texture(Gdx.files.internal("minijuego/palochino(abajo).png"));
		 Tuboabt = new Texture(Gdx.files.internal("minijuego/palochino.png"));
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
		 currentFrame=new Texture(Gdx.files.internal("minijuego/dragonflappy2.png"));
		 vuela = new Texture(Gdx.files.internal("minijuego/vuela.png"));
		 
		 //Obstaculos
		 
		 TRoca = new Texture(Gdx.files.internal("data/Rocap.png"));
		 TCoco2 = new Texture(Gdx.files.internal("data/Icocop.png"));
		 TCoco = new Texture(Gdx.files.internal("data/Dcocop.png"));
		 TTronco = new Texture(Gdx.files.internal("data/Troncop.png"));
		 TEscudo = new Texture(Gdx.files.internal("data/boost_shield.png"));
		 TChampion = new Texture(Gdx.files.internal("data/boost3.png"));
		 Rocas = new Array<Rectangle>();
		 Troncos = new Array<Rectangle>();
		 Cocodrilos = new Array<Rectangle>();
		 escudo = new Rectangle();
		 champion = new Rectangle();
		 escudoI = new Rectangle();
		 escudo = new Rectangle(MathUtils.random(550, 940),
				 MathUtils.random(4320, 17280),52,49);
		 champion = new Rectangle(MathUtils.random(550, 940),
				 MathUtils.random(4320, 17280),52,49);
		 escudoI = new Rectangle(140,juego.jugador.getY() -300,100,100);
		 AEscudo = new Array<Rectangle>();
		 AChampion = new Array<Rectangle>();
		 OEscudo = new Array<Rectangle>();
		 OEscudo.add(escudoI);
		 AEscudo.add(escudo);
		 AChampion.add(champion);
		 
		 //IA
		 AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
		 AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
		 AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
		 
		 //Numeros para la vida
		 unidad[0] = n0;
		 unidad[1] = n1;
		 unidad[2] = n2;
		 unidad[3] = n3;
		 unidad[4] = n4;
		 unidad[5] = n5;
		 unidad[6] = n6;
		 unidad[7] = n7;
		 unidad[8] = n8;
		 unidad[9] = n9;
		 decena[0] = n0;
		 decena[1] = n1;
		 decena[2] = n2;
		 decena[3] = n3;
		 decena[4] = n4;
		 decena[5] = n5;
		 decena[6] = n6;
		 decena[7] = n7;
		 decena[8] = n8;
		 decena[9] = n9;
		 posicionA[0] = primero;
		 posicionA[1] = segundo;
		 posicionA[2] = tercero;
		 posicionA[3] = cuarto;
		 
		 contadorVida = new Texture(Gdx.files.internal("data/Contador_Vida.png"));
		 
		 rema = new Texture(Gdx.files.internal("data/rema.png"));
		 vuela = new Texture(Gdx.files.internal("minijuego/vuela.png"));
		 
			bSalir = new Texture(Gdx.files.internal("ui/Salir_ESC.png"));
			spriteBSalir = new SpriteDrawable(new Sprite(bSalir));
			
			bReanudar = new Texture(Gdx.files.internal("ui/Reanudar.png"));
			spriteBReanudar = new SpriteDrawable(new Sprite(bReanudar));
			
			bAjustes = new Texture(Gdx.files.internal("ui/Salir_ESC.png"));
			spriteBAjustes = new SpriteDrawable(new Sprite(bAjustes));
			
			fondoEscape = new Texture(Gdx.files.internal("ui/menuPausa1.png"));
			
	}

	public void setValoresBarco(int eleccionBarco,int vidasS,int vPuntaS,int dineroS) {
        this.barcoDef = eleccionBarco;
        this.vidas = Tienda.vidasS;
        this.vPunta = vPuntaS*30;
        this.dinero = Tienda.dinero;
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
			buttonPlay.setPosition(table.getOriginX(), table.getOriginY());
			buttonPlay.setSize(300,40);
			buttonPlay.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					tiempoInicio = TimeUtils.millis();
					gameState=GameState.COUNTDOWN;
					return false;
					
				}
			});
			table.addActor(buttonPlay);
			
			//BOTON
			Button buttonShop= new Button(new Button.ButtonStyle(spriteBTienda,spriteBTienda,spriteBTienda));
			buttonShop.setPosition(table.getOriginX() +550, table.getOriginY());
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
			Button buttonQuit= new Button(new Button.ButtonStyle(spriteBSalir,spriteBSalir,spriteBSalir));
			buttonQuit.setPosition(buttonShop.getX()+buttonShop.getWidth()+300, table.getOriginY());
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
			unidadVida = vidas%10;
			decenaVida = vidas/10;
			unidadS = unidad[unidadVida];
			decenaS = decena[decenaVida];
			
			if(mipos>pos1 && mipos>pos2 && mipos>pos3) posicion=0;
			if((mipos>pos1 && mipos>pos2 && mipos<pos3) || (mipos>pos1 && mipos<pos2 && mipos>pos3) || (mipos<pos1 && mipos>pos2 && mipos>pos3)) posicion=1;
			if((mipos>pos1 && mipos<pos2 && mipos<pos3) || (mipos<pos1 && mipos>pos2 && mipos<pos3) || (mipos>pos1 && mipos<pos2 && mipos>pos3)) posicion=2;	
			if(pos1>mipos && pos2>mipos && pos3>mipos) posicion = 3;	
			posicionT = posicionA[posicion];
			
			juego.setSkinBarcos(barcoDef,true,0);
			juego.iniciar(table, batch, stage);
			
			 AI1.tiempoInicio = tiempoInicio;
			 AI2.tiempoInicio = tiempoInicio;
			 AI3.tiempoInicio = tiempoInicio;
			
			

//			PINTAR LOS OBSTACULOS
			batch.begin();	
			actualizarIA();
			 for(Rectangle roca: Rocas) {batch.draw(TRoca, roca.x, roca.y);}
			 for(Rectangle tronco: Troncos) {batch.draw(TTronco, tronco.x, tronco.y);}
			 for(Rectangle cocodrilo: Cocodrilos) {
				 if(cocodrilo.getWidth()>=78) {
					 batch.draw(TCoco, cocodrilo.x, cocodrilo.y);
				 }else {
					 batch.draw(TCoco2, cocodrilo.x, cocodrilo.y);
				 }
			 }
			batch.end();
		    handleInput();
			update(Gdx.graphics.getDeltaTime());
			
			batch.begin();
	
			batch.draw(contadorVida, 80, juego.jugador.getY()-40, 160, 72);
			batch.draw(unidadS, 140, juego.jugador.getY()-15, 24, 24);
			batch.draw(decenaS, 110, juego.jugador.getY()-15, 24, 24);											
			batch.draw(panelPos, 1750,juego.jugador.getY() +400, 130, 100);
			batch.draw(posicionT, 1765, juego.jugador.getY() +400, 100, 100);
			
			for(Rectangle esc: AEscudo) {batch.draw(TEscudo, esc.x, esc.y);}
			for(Rectangle chm: AChampion) {batch.draw(TChampion, chm.x, chm.y);}
			if(escudosu==true) {
			for(Rectangle e: OEscudo) {batch.draw(TEscudo, e.x,e.y);}
			}
			
			 batch.end();
			 
			 break;
			
		case SHOP:
			setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
			tienda.iniciar(table,batch,stage);
			juego.setStatsBarco(vidas, vPunta);
			vInicial=vPunta;
			switch(barcoDef) {
				case 0:
					IA1Stats = 1;
					IA2Stats = 2;
					IA3Stats = 0;
					break;
				case 1:
					IA1Stats = 0;
					IA2Stats = 2;
					IA3Stats = 0;
					break;
				case 2:
					IA1Stats = 1;
					IA2Stats = 0;
					IA3Stats = 0;
					break;
			}
			
			break;
			
		case MINIJUEGO:
			unidadC = ct%10;
			decenaC = ct/10;
			unidadCt = unidad[unidadC];
			decenaCt = decena[decenaC];
			float pos=minijuego.jugador.getX()+1500;
			  batch = new SpriteBatch();
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
			batch.draw(currentFrame, minijuego.jugador.getX(), minijuego.jugador.getY());
			batch.end();
			batch.begin();
			
			font.draw(batch, "x: " + minijuego.jugador.getX() +"Y: "+ minijuego.jugador.getY(), 100, minijuego.jugador.getY()+100);
			batch.draw(unidadCt, minijuego.jugador.getX()+500, 1300, 30, 30);
			batch.draw(decenaCt, minijuego.jugador.getX()+470, 1300, 30, 30);
			batch.end();
			minijuego.st=Gdx.graphics.getDeltaTime()*250;
			updateflapi(Gdx.graphics.getDeltaTime()*250);
			
			break;
			
		case QUIT:
			System.exit(0);

			break;
			
		case ESCCONFIG:

			
			//updateEsc(Gdx.graphics.getDeltaTime());
			
			table.clear();
			Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        juego.iniciar(table, batch, stage);
	        
	        batch.begin();
	        batch.draw(fondoEscape,660,juego.jugador.getY()+25,600,400);
	        batch.end();
	        
			stage = new Stage();
			table=new Table();
			table.setPosition(0,0);
			table.setFillParent(true);
			table.setHeight(200);
			stage.addActor(table);
	        
			//Botones Reanudar
			Button buttonReanudar= new Button(new Button.ButtonStyle(spriteBReanudar,spriteBReanudar,spriteBReanudar));
			buttonReanudar.setPosition(810, 500);
			buttonReanudar.setSize(300,40);
			buttonReanudar.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					DragonBoatGame.gameState=GameState.PLAY;
					return false;
				}
			});
			table.addActor(buttonReanudar);
			
			//Botones Salir
			Button buttonSalir= new Button(new Button.ButtonStyle(spriteBSalir,spriteBSalir,spriteBSalir));
			buttonSalir.setPosition(805, 400);
			buttonSalir.setSize(300,40);
			buttonSalir.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					 AChampion.add(champion);
					 OEscudo.add(escudoI);
					 esc=true;
					gameState=GameState.MENU;
					
					return false;		
				}
			});
			table.addActor(buttonSalir);
			
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
			Gdx.input.setInputProcessor(stage);
		
			break;
		case COUNTDOWNMINI:
		
	    	// 
			
		
			camfla.setToOrtho(false,WIDTH,HEIGHT);
			leftLimitmini = camfla.position.x - Gdx.graphics.getWidth() / 2;
			rightLimitmini = camfla.position.x + Gdx.graphics.getWidth() / 2;
			topLimitmini = camfla.position.y + Gdx.graphics.getHeight() / 2;
			bottomLimitmini = camfla.position.y - Gdx.graphics.getHeight() / 2;
			ct=0;
			
			 batch = new SpriteBatch();
			camfla.update();
		

			Tuboar.clear();
			Tuboab.clear();
			 minijuego=new minijuego();
			 minijuego.inicializar();
			minijuego.iniciar(table, batch, stage);
			long tiempoTranscurridomini = TimeUtils.timeSinceMillis(tiempoMini);
		    if (tiempoTranscurridomini < 1000) {
		    	batch.begin();
		        batch.draw(n3,(WIDTH-200)/2, (HEIGHT-200), 200, 200);
		        batch.end();
		    } else if (tiempoTranscurridomini < 2000) {
		    	batch.begin();
		    	 batch.draw(n2,(WIDTH-200)/2, (HEIGHT-200), 200, 200);
		        batch.end();
		    } else if (tiempoTranscurridomini < 3000) {
		    	batch.begin();
		    	 batch.draw(n1,(WIDTH-200)/2, (HEIGHT-200), 200, 200);
		        batch.end();
		    } else if(tiempoTranscurridomini < 4000) {
		    	batch.begin();

		    	 batch.draw(vuela,(WIDTH-200)/2, (HEIGHT-200), 200, 200);

		        batch.end();
		    }else {

		        gameState = GameState.MINIJUEGO;
		    }
			break;
		case COUNTDOWN:
			juego.setSkinBarcos(barcoDef,true,0);
			juego.iniciar(table, batch, stage);
			AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
			AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
			AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
			long tiempoTranscurrido = TimeUtils.timeSinceMillis(tiempoInicio);
			if (tiempoTranscurrido < 1000) {
				batch.begin();
				switch(nRonda) {
				case 0:
					batch.draw(ronda1, (WIDTH-912)/2, (HEIGHT-104)/2, 912, 104);
					break;
				case 1:
					batch.draw(ronda2, (WIDTH-912)/2, (HEIGHT-104)/2, 912, 104);
					break;
				case 2:
					batch.draw(ronda3, (WIDTH-912)/2, (HEIGHT-104)/2, 912, 104);
					break;
				case 3:
					batch.draw(rondaFinal, (WIDTH-1168)/2, (HEIGHT-104)/2, 1168, 104);
					break;
					
				}
		        batch.end();
			}else if (tiempoTranscurrido < 2000) {
		    	batch.begin();
		        batch.draw(n3, (WIDTH-200)/2, (HEIGHT-200)/2, 200, 200);
		        batch.end();
		    } else if (tiempoTranscurrido < 3000) {
		    	batch.begin();
		        batch.draw(n2, (WIDTH-200)/2, (HEIGHT-200)/2, 200, 200);
		        batch.end();
		    } else if (tiempoTranscurrido < 4000) {
		    	batch.begin();
		        batch.draw(n1, (WIDTH-200)/2, (HEIGHT-200)/2, 200, 200);
		        batch.end();
		    } else if(tiempoTranscurrido < 5000) {
		    	batch.begin();
		        batch.draw(rema, (WIDTH-800)/2, (HEIGHT-200)/2, 800, 200);
		        batch.end();
		    }else {
		        gameState = GameState.PLAY;
		        
		    }
		    break;
		case FASE:
			
	        
	        long tiempoTranscurrido2 = TimeUtils.timeSinceMillis(tiempoFase);
	        if(perdisteVidas) {
	        	if (tiempoTranscurrido2 < 2000) {
	        		batch.begin();
	        		batch.draw(boardPlay,0,0);
					batch.draw(TpVidas,(WIDTH-1280)/2, (HEIGHT-198)/2, 1280, 198);
					batch.end();
					
				} else {

					ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					AChampion.add(champion);
					OEscudo.add(escudoI);
					perdisteVidas=false;
					nRonda = 0;
					gameState=GameState.MENU;
				}
	        }else if(perdisteIA) {	
	        	if (tiempoTranscurrido2 > 2000) {
					ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					 AChampion.add(champion);
					 perdisteIA=false;
					 nRonda = 0;
					gameState=GameState.MENU;
				} else {
					batch.begin();
					batch.draw(boardPlay,0,0);
					batch.draw(Tperdiste,(WIDTH-1280)/2, (HEIGHT-226)/2, 1280, 226);
					batch.end();
				}
	        	
			}else if(nRonda!=3){
				batch.begin();
				batch.draw(boardPlay,0,0);
				switch(nRonda) {
				case 0:
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					break;
				case 1:
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,normal,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,normal,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,normal,IA3Stats,Tienda.vPuntaS*30);
					break;
				case 2:
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,dificil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,dificil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,dificil,IA3Stats,Tienda.vPuntaS*30);
					break;
				}
				batch.draw(enhorabuena, (WIDTH-1280)/2, (HEIGHT-172)/2, 1280, 172);
		        batch.end();
        		if (tiempoTranscurrido2 > 2000) {
	        		tiempoMini=TimeUtils.millis();
	        		gameState= GameState.COUNTDOWNMINI;
				}
				
			}else {
				if (tiempoTranscurrido2 > 2000) {
					ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero+3);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					AChampion.add(champion);
					nRonda = 0;
					gameState=GameState.MENU;
				} else {
					batch.begin();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,experto,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,experto,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,experto,IA3Stats,Tienda.vPuntaS*30);
					batch.draw(boardPlay,0,0);
					batch.draw(tCampeon, (WIDTH-1280)/2, (HEIGHT-224)/2, 1280, 224);
					batch.end();
				}
			}
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
		    float x = MathUtils.random(0, WIDTH-64);
		    float y = MathUtils.random(topLimit+360, topLimit+HEIGHT);
		    float width = 78;
		    float height = 22;
		    boolean mueveDerecha = x < WIDTH / 2;
		    if (!mueveDerecha) {
		        width = width-1; // Si se mueve hacia la izquierda modificamos la anchura del cocodrilo en un pixel para poder llevar a cabo la comparacion fija
		    }
		    Rectangle cocodrilo = new Rectangle(x, y, width, height);
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
	            	
	            	escAjustes = new EscAjustes();
	            	gameState=GameState.ESCCONFIG;
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
	 		
	     public void updateflapi(float deltaTime) { //MINIJUEGO
	    	 		

	    	 	tiempo+=3;
	    	 	currentFrame= minijuego.animacion.getKeyFrame(tiempo, true);
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

				    final int tiempoDeEsperaEntreObstaculosmini = 1250;
				  
				    if(TimeUtils.millis() - lastDropTimeTuboab > tiempoDeEsperaEntreObstaculosmini&&minijuego.jugador.getX()>1986) {
				    	bum.play();
				    	ct++;
				    	if(ct%5==0) {
				    	tienda.sumaMonedas(1);
				    	}
				    }
				    if(minijuego.jugador.getY()==1080) {
				    	  minijuego.jugador.setY((minijuego.jugador.getY()+gravity*deltaTime));

				    }
					 if (TimeUtils.millis() - lastDropTimeTuboab > tiempoDeEsperaEntreObstaculosmini) {
						 int dis=950;
						 float ran=MathUtils.random(900, 1200);
						 if(ct>3) {
							  dis=900;
							   ran=MathUtils.random(850, 1200);
						 }else if(ct>8) {
							  dis=500;
							   ran=MathUtils.random(750, 1200);
						 }else if(ct>14) {
							  dis=450;
							   ran=MathUtils.random(750, 1200);
						 }
					     spawntuboab(Tuboab,Tuboar,dis,ran);
					     
					 }

					 Rectangle drag= minijuego.jugador.getBoundingRectangle();
					 for (Iterator<Rectangle> iterar = Tuboab.iterator(); iterar.hasNext(); ) {
						 
						  
					      Rectangle Tuboab = iterar.next();
					      
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
				    		 
				    		 	ilit = HEIGHT/7;
							 	acceleration.set(0, 0); 
							 	velocity.set(0,0);
								camera.setToOrtho(false,WIDTH,HEIGHT);
								batch = new SpriteBatch();
								camera.update();
								juego = new Juego();
								AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
								AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
								AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
								setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
								juego.jugador.setvPunta(vPunta);
								AEscudo.add(escudo);
								AChampion.add(champion);
								nRonda++;
								tiempoInicio = TimeUtils.millis();
								gameState=GameState.COUNTDOWN;
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
				    		 
				    		 	ilit = HEIGHT/7;
							 	acceleration.set(0, 0); 
							 	velocity.set(0,0);
								camera.setToOrtho(false,WIDTH,HEIGHT);
								batch = new SpriteBatch();
								camera.update();
								juego = new Juego();
								AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
								AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
								AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
								setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
								juego.jugador.setvPunta(vPunta);
								AEscudo.add(escudo);
								AChampion.add(champion);
								nRonda++;
								tiempoInicio = TimeUtils.millis();
								gameState=GameState.COUNTDOWN;
					      }
				      }
					 if(ct>=30) {
						 ilit = HEIGHT/7;
						 	acceleration.set(0, 0); 
						 	velocity.set(0,0);
							camera.setToOrtho(false,WIDTH,HEIGHT);
							batch = new SpriteBatch();
							camera.update();
							juego = new Juego();
							AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
							AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
							AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
							setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
							juego.jugador.setvPunta(vPunta);
							AEscudo.add(escudo);
							AChampion.add(champion);
							nRonda++;
							tiempoInicio = TimeUtils.millis();
							gameState=GameState.COUNTDOWN;
					 }
	     }

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
	            
            
	   		 final int tiempoDeEsperaEntreObstaculos = 400; 
	   		 final int tEscudo=10000;// espera 100 milisegundos entre cada generaci�n de obst�culos
			 if(TimeUtils.millis()-lastDropEscudo> tEscudo) {
				 escudosu=false;
			 }
			 
			 escudoI.setY(juego.jugador.getY()+50);
			 pos1 = AI1.barco.getY();
			pos2 = AI2.barco.getY();
			pos3 = AI3.barco.getY();
			mipos = juego.jugador.getY();
			 
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
			      if(roca.y + 64 < bottomLimit+100) iter.remove();
			      if(roca.overlaps(rect1)) {
				         iter.remove();
						 if(escudosu==false) {
				         juego.setStatsBarco(vidas, vPunta-vPunta/10); 	
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 70; 
				         acceleration.x = 0;
						 }
				      }
			   }
 
			 for (Iterator<Rectangle> iter = Troncos.iterator(); iter.hasNext(); ) {
			      Rectangle tronco = iter.next();
			      tronco.y -= 100 * Gdx.graphics.getDeltaTime();
			      if(tronco.y +64<bottomLimit+100) iter.remove();
			      if(tronco.overlaps(rect1)) { 
				         iter.remove();
				         if(escudosu==false) {
						 juego.setStatsBarco(vidas-1, vPunta-vPunta/20);
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 70;
				         acceleration.x = 0; 
				         }
				  }
			   }
			 
			 for (Iterator<Rectangle> iter = Cocodrilos.iterator(); iter.hasNext(); ) {
			     Rectangle cocodrilo = iter.next();
			     boolean mueveDerecha = cocodrilo.getWidth()>=78;
			     if (mueveDerecha) { // si nos estamos moviendo hacia la derecha
			         cocodrilo.x += 30 * Gdx.graphics.getDeltaTime(); // incrementar la posicion en x
			         if (cocodrilo.x >= WIDTH-64) { // si hemos llegado al borde derecho del mapa
			        	 iter.remove(); // eliminar el cocodrilo
			         }
			     } else { // si nos estamos moviendo hacia la izquierda
			         cocodrilo.x -= 30 * Gdx.graphics.getDeltaTime(); // decrementar la posicion en x
			         if (cocodrilo.x <= 64) { // si hemos llegado al borde izquierdo del mapa
			             iter.remove(); // eliminar el cocodrilo
			         }
			     }
			     if (cocodrilo.y + 64 < bottomLimit + 100) {
			         iter.remove();
			     }
			     if(cocodrilo.overlaps(rect1)) { 
				         iter.remove();
				         if(escudosu==false) {
						 juego.setStatsBarco(vidas-2, vPunta-vPunta/50);
						 vidas = juego.jugador.getVidas();
						 vPunta = juego.jugador.getvPunta();
				         velocity.y -= 300;
				         acceleration.x = 0;
				         }
				 } 
			 }
			 
			 
			 for (Iterator<Rectangle> iter = AEscudo.iterator(); iter.hasNext(); ) {
			      Rectangle es = iter.next();
			      if(es.y + 64 < bottomLimit+100) iter.remove();
			      if(es.overlaps(rect1)) {
				         iter.remove();
						escudosu=true;
						act=true;
						esc=false;
						lastDropEscudo=TimeUtils.millis();
				      }
			   }
			 for (Iterator<Rectangle> iter = AChampion.iterator(); iter.hasNext(); ) {
			      Rectangle chm = iter.next();
			      if(chm.y + 64 < bottomLimit+100) iter.remove();
			      if(chm.overlaps(rect1)) {
				         iter.remove();
				         juego.setStatsBarco(vidas, Tienda.vPuntaS*30); 	
				         vPunta=Tienda.vPuntaS*30; 	
						 
				      }
			   }
			
			 for (Iterator<Rectangle> iter = OEscudo.iterator(); iter.hasNext(); ) {
			      Rectangle e = iter.next();
			      if(e.y + 64 < bottomLimit+100) iter.remove();
			      if(escudosu==false && act==true) {
				         iter.remove();
				         act=false;
						 esc=false;
				      }else if(escudosu==true && esc==true && act ==true) {
				    	  iter.remove();
				    	  escudosu=false;
				    	  act=false;
				    	  esc=false;
				      }
			   }
			 
			 if (juego.getLineaMeta() <= juego.jugador.getY()) {
				 	ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					AChampion.add(champion);
					OEscudo.add(escudoI);
					tiempoFase=TimeUtils.millis();
					gameState=GameState.FASE;
			 }
			 

			 if (juego.getLineaMeta() <= juego.IA1.getY() || juego.getLineaMeta() <= juego.IA2.getY() ||  juego.getLineaMeta() <= juego.IA3.getY()) {
				 	ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					AChampion.add(champion);
					tiempoFase=TimeUtils.millis();
					perdisteIA=true;
					gameState=GameState.FASE;
			 }
			 
			 if(vidas <= 0) {
				 	ilit = HEIGHT/7;
				 	acceleration.set(0, 0); 
				 	velocity.set(0,0);
					camera.setToOrtho(false,WIDTH,HEIGHT);
					batch = new SpriteBatch();
					camera.update();
					juego = new Juego();
					AI1 = new AISystem(juego.IA1, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA1Stats,Tienda.vPuntaS*30);
					AI2 = new AISystem(juego.IA2, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA2Stats,Tienda.vPuntaS*30);
					AI3 = new AISystem(juego.IA3, Troncos, Rocas, Cocodrilos,tiempoInicio,facil,IA3Stats,Tienda.vPuntaS*30);
					setValoresBarco(Tienda.eleccionBarco, Tienda.vidasS, Tienda.vPuntaS, Tienda.dinero);
					juego.jugador.setvPunta(vPunta);
					AEscudo.add(escudo);
					AChampion.add(champion);
					OEscudo.add(escudoI);					
					
					tiempoFase=TimeUtils.millis();
					perdisteVidas=true;
					gameState=GameState.FASE;
					
			 }

	      }
	       
	        public void updateEsc(float deltaTime) {
	        	camera.update();
	        
	        }
	        
	        public void stopBoat() {
	            // Establece la velocidad en cero
	            velocity.set(0, 0);
	            // Establece la aceleración en cero
	            acceleration.set(0, 0);
	        }

	        //Para el minijuego

	        //Obstaculos
	        protected void spawntuboab(Array<Rectangle> Tuboab,Array<Rectangle> Tuboar,int ydis,float y) {

		  	  Rectangle tuboar = new Rectangle(minijuego.jugador.getX()+1500,y,70,590);
		  	  Rectangle tuboab = new Rectangle(minijuego.jugador.getX()+1500,y-ydis,70,590);
	  	      Tuboab.add(tuboab);
	  	      Tuboar.add(tuboar);
	  	      lastDropTimeTuboab = TimeUtils.millis();
	  	   }
	        
	       public void actualizarIA() {
	    	   AI1.update(Gdx.graphics.getDeltaTime(),1);
	    	   if(AI1.vidas<=0) {
	    		   AI1.velocidad = 0;
	    		   juego.setSkinBarcos(barcoDef, false, 1);
	    			juego.IA1.draw(batch);
	    	   }
	    	   AI2.update(Gdx.graphics.getDeltaTime(),3);
	    	   if(AI2.vidas<=0) {
	    		   AI2.velocidad = 0;
	    		   juego.setSkinBarcos(barcoDef, false,2);
	    		   juego.IA2.draw(batch);
	    	   }
	    	   AI3.update(Gdx.graphics.getDeltaTime(),4);
	    	   if(AI3.vidas<=0) {
	    		   AI3.velocidad = 0;
	    		   juego.setSkinBarcos(barcoDef, false,3);
	    		   juego.IA3.draw(batch);
	    	   }
	    	   
	       }
	       
}	       