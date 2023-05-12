package com.barquitosfc.game;





import java.security.PublicKey;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.barquitosfc.game.DragonBoatGame.GameState;

public class Tienda {
	protected static int dinero = 0;
	protected static int vidas1= 5;
	protected static int vPunta1= 10;
	protected int vidas2= 10;
	protected int vPunta2= 5;
	protected int vidas3= 5;
	protected int vPunta3= 5;
	protected int HEIGHT= 1920;
	protected static int vidasS=vidas1;
	protected static int vPuntaS=vPunta1;
	protected static int eleccionBarco = 0;
	protected int dineroSeguridad = dinero;
    protected int barcoSeguridad = eleccionBarco;    
    protected int vidaSeguridad1 = vidas1;
    protected int velocidadSeguridad1 = vPunta1;
	protected int unidadesD;
	protected int decenasD;
	protected int unidadesVid;
	protected int decenasVid;
	protected int unidadesVel;
	protected int decenasVel;
	protected Texture siguiente;
	protected Texture anterior;
	protected SpriteDrawable spriteSiguiente;
	protected SpriteDrawable spriteAnterior;
	protected Texture barraVida[] = new Texture[21];
	protected Texture barraVelocidad[] = new Texture[21];
	protected Texture barcosElec[] = new Texture[3];
	protected Texture unidadesA[] = new Texture[10];
	protected Texture decenasA[] = new Texture[10];
	protected Texture fondoTienda;
	protected Texture masR, menosR,masM,menosM, casa;
	protected SpriteDrawable spriteMasR, spriteMenosR,spriteMasM,spriteMenosM, spriteBCasa,spriteBConfirmar;
	protected Texture barra0;
	protected Texture barra1;
	protected Texture barra2;
	protected Texture barra3;
	protected Texture barra4;
	protected Texture barra5;
	protected Texture barra6;
	protected Texture barra7;
	protected Texture barra8;
	protected Texture barra9;
	protected Texture barra10;
	protected Texture barra11;
	protected Texture barra12;
	protected Texture barra13;
	protected Texture barra14;
	protected Texture barra15;
	protected Texture barra16;
	protected Texture barra17;
	protected Texture barra18;
	protected Texture barra19;
	protected Texture barra20;
	protected Texture barraV0;
	protected Texture barraV1;
	protected Texture barraV2;
	protected Texture barraV3;
	protected Texture barraV4;
	protected Texture barraV5;
	protected Texture barraV6;
	protected Texture barraV7;
	protected Texture barraV8;
	protected Texture barraV9;
	protected Texture barraV10;
	protected Texture barraV11;
	protected Texture barraV12;
	protected Texture barraV13;
	protected Texture barraV14;
	protected Texture barraV15;
	protected BitmapFont font;
	protected Texture barraV16;
	protected Texture barraV17;
	protected Texture barraV18;
	protected Texture barraV19;
	protected Texture barraV20;
	protected Texture barco1;
	protected Texture barco2;
	protected Texture barco3;
	protected Texture n0;
	protected Texture n1;
	protected Texture n2;
	protected Texture n3;
	protected Texture n4;
	protected Texture n5;
	protected Texture n6;
	protected Texture n7;
	protected Texture n8;
	protected Texture n9;	
	protected Texture velocidadB;
	protected Texture vidaB;
	protected Texture barcosE;
    protected Texture letreroVida;
    protected Texture letreroVelocidad;
    protected Texture unidadesB;
    protected Texture decenasB;
    protected Texture unidadesV;
    protected Texture decenasV;
    protected Texture unidadesVp;
    protected Texture decenasVp;
    protected Texture confirmar;
    protected Texture grifoCoin;


    
    public void inicializar() {
    	siguiente = new Texture(Gdx.files.internal("data/Flecha_Derecha.png"));
   	 	anterior = new Texture(Gdx.files.internal("data/Flecha_Izquierda.png"));
   	 	masR = new Texture(Gdx.files.internal("ui/Simbolo_Mas.png")); 
   	 	menosR = new Texture(Gdx.files.internal("ui/Simbolo_Menos.png"));
   	 	masM = new Texture(Gdx.files.internal("ui/Boton_Mas_Morado.png"));
   	 	menosM = new Texture(Gdx.files.internal("ui/Boton_Menos_Morado.png"));
   	 	confirmar = new Texture(Gdx.files.internal("ui/Boton_Tick.png"));
   	 	casa = new Texture(Gdx.files.internal("data/Reset.png"));
   	 	letreroVida = new Texture(Gdx.files.internal("data/Cartel_Vida.png"));
   	 	letreroVelocidad = new Texture(Gdx.files.internal("data/Cartel_Velocidad.png"));
   	 	grifoCoin = new Texture(Gdx.files.internal("data/grifocoin.png"));
   	 	fondoTienda = new Texture(Gdx.files.internal("fondos/FONDO_TIENDA_HD.png"));
   	 	spriteMasR = new SpriteDrawable(new Sprite(masR)); 
   	 	spriteMenosR = new SpriteDrawable(new Sprite(menosR)); 
   	 	spriteMasM = new SpriteDrawable(new Sprite(masM)); 
   	 	spriteMenosM = new SpriteDrawable(new Sprite(menosM)); 
   	 	spriteBCasa = new SpriteDrawable(new Sprite(casa));
   	 	spriteBConfirmar = new SpriteDrawable(new Sprite(confirmar));
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
   	 	barco1 = new Texture(Gdx.files.internal("data/BARCO_FIRE.png"));
   	 	barco2 = new Texture(Gdx.files.internal("data/BARCO_CANARIO.png"));
   	 	barco3 = new Texture(Gdx.files.internal("data/BARCO_NATURE.png"));
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
    
	 
	 
	public Tienda() {
		this.inicializar();
		
		
		
		
	}
	public void sumaMonedas(int cant) {
		dinero+=cant;
	}
	public void iniciar(Table table,SpriteBatch batch,Stage stage) {
		table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        unidadesD =dinero%10;
        decenasD = dinero/10;
        unidadesVid =vidasS%10;
        decenasVid = vidasS/10;
        unidadesVel =vPuntaS%10;
        decenasVel = vPuntaS/10;
       
        font = new BitmapFont();
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
		batch.draw(barcosE, 775, 490, 400, 300);
		batch.draw(velocidadB, 700, 310, 500, 40);
		batch.draw(vidaB, 700, 160, 500, 40);
		batch.draw(letreroVida, 840, 215, 200, 30);
		batch.draw(letreroVelocidad, 740, 365, 400, 30);
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
		table.setPosition(0,0);
		table.setFillParent(true);
		table.setHeight(200);
		stage.addActor(table);
		
		Button siguienteBarco= new Button(new Button.ButtonStyle(spriteSiguiente,spriteSiguiente,spriteSiguiente));
		siguienteBarco.setPosition(1220, 600);
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
		anteriorBarco.setPosition(660, 600);
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
		masVelocidad.setPosition(1250, 300);
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
		masVida.setPosition(1250, 150);
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
		menosVelocidad.setPosition(600, 300);
		menosVelocidad.setSize(56,56);
		menosVelocidad.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				if(eleccionBarco==0 && 10<vPunta1){
				vPunta1--;
				vPuntaS--;
				dinero++;
				}else if(eleccionBarco==1 && 5<vPunta2) {
					vPunta2--;
					vPuntaS--;
					dinero++;
				}else if(eleccionBarco==2 && 5<vPunta3) {
					vPunta3--;
					vPuntaS--;
					dinero++;
				}
				return false;
				
			}
		});
		table.addActor(menosVelocidad);
		
		Button menosVida= new Button(new Button.ButtonStyle(spriteMenosR,spriteMenosR,spriteMenosR));
		menosVida.setPosition(600, 150);
		menosVida.setSize(56,56);
		menosVida.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				if(eleccionBarco==0 &&5<vidas1) {
				vidas1--;
				vidasS--;
				dinero++;
				}else if(eleccionBarco==1 && 10<vidas2) {
				vidas2--;
				vidasS--;
				dinero++;
				}else if(eleccionBarco==2 && 5<vidas3) {
				vidas3--;
				vidasS--;
				dinero++; 
				}
				return false;
				
			}
		});
		table.addActor(menosVida);
		
		Button volverMenu= new Button(new Button.ButtonStyle(spriteBCasa,spriteBCasa,spriteBCasa));
		volverMenu.setPosition(50, 920);
		volverMenu.setSize(100,100);
		volverMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				eleccionBarco= barcoSeguridad;
				vidasS=vidaSeguridad1;
				vPuntaS = velocidadSeguridad1;
				dinero = dineroSeguridad;
				DragonBoatGame.gameState=DragonBoatGame.GameState.MENU;	
				return false;
			}
		});
		table.addActor(volverMenu);
		
		Button confirmar= new Button(new Button.ButtonStyle(spriteBConfirmar,spriteBConfirmar,spriteBConfirmar));
		confirmar.setPosition(1700, 100);
		confirmar.setSize(100,100);
		confirmar.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				//DragonBoatGame cambio= new DragonBoatGame(eleccionBarco,vidasS,vPuntaS,dinero);
				DragonBoatGame.gameState=DragonBoatGame.GameState.MENU;				
				return false;				
			}
		});
		table.addActor(confirmar);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Gdx.input.setInputProcessor(stage);

	}
	public void setStatsBarco(int vidas, int vPunta) {
		vidasS = vidas;
		vPuntaS = vPunta;
		
	}

		
	
}
	



