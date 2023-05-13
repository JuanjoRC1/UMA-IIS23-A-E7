package com.barquitosfc.game;

import java.util.Iterator;
import java.util.Random;



import javax.management.RuntimeErrorException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Juego {
	
	public int salvavida;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	public Barco jugador,IA1,IA2,IA3; 
	public Texture mainBarco, boardPlay,TIA1,TIA2,TIA3;
	public Carril C1,C2,C3,C4;
	public Rectangle lineaMeta;
	public Texture TlineaMeta;
	public int Duracion = 20;

	
	public  Juego() {
		
		mainBarco = new Texture(Gdx.files.internal("data/BARCO_FIRE_OV.png")); 
		TIA1 = new Texture(Gdx.files.internal("data/BARCO_CANARIO_OV.png"));
		TIA2 = new Texture(Gdx.files.internal("data/BARCO_NATURE_OV.png"));
		TIA3 = new Texture(Gdx.files.internal("data/BARCO_EVIL_OV.png"));
		TlineaMeta= new Texture(Gdx.files.internal("fondos/Linea_Meta2.png"));
		lineaMeta= new Rectangle(100,939+(1080*1),1706,98);
		boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Rio.png"));
	

		
		//ASIGNACION DE SKIN AL USUARIO	
		
		
		boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Rio.png"));
		
		mainBarco = new Texture(Gdx.files.internal("data/BARCO_FIRE_OV.png"));
		TIA1 = new Texture(Gdx.files.internal("data/BARCO_CANARIO_OV.png"));
		TIA2 = new Texture(Gdx.files.internal("data/BARCO_NATURE_OV.png"));
		TIA3 = new Texture(Gdx.files.internal("data/BARCO_EVIL_OV.png"));
		

		C1= new Carril(1);
		C2 = new Carril(2); 
		C3= new Carril(3);
		C4= new Carril(4);
		
		jugador = new Barco(mainBarco,2); 
		jugador.setPosition(C2.getPuntoSpawn(), HEIGHT/7);

		IA1= new Barco(TIA1,1);
		IA1.setPosition(C1.getPuntoSpawn(), HEIGHT / 7);
		
		IA2= new Barco(TIA2,3);
		IA2.setPosition(C3.getPuntoSpawn(), HEIGHT / 7);
		 
		IA3= new Barco(TIA3,3);
		IA3.setPosition(C4.getPuntoSpawn(), HEIGHT / 7);
	
	}
	//METODO PARA ASIGNAR LA SKIN SELECCIONADA EN LA TIENDA ANTERIORMENTE
	public void setSkinBarcos(int barco) {  
 
		Texture barcoFuego = new Texture(Gdx.files.internal("data/BARCO_FIRE_OV.png"));
		Texture barcoCanario = new Texture(Gdx.files.internal("data/BARCO_CANARIO_OV.png"));
		Texture barcoNatura = new Texture(Gdx.files.internal("data/BARCO_NATURE_OV.png"));
		Texture barcoEvil = new Texture(Gdx.files.internal("data/BARCO_EVIL_OV.png"));

		//ASIGNACION DE SKIN AL BARCO DEL USUARIO
		switch(barco) {  
		case 0: mainBarco.load(barcoFuego.getTextureData());
			break;
		case 1: mainBarco.load(barcoCanario.getTextureData());
			break;
		case 2: mainBarco.load(barcoNatura.getTextureData());
		break;
		default: throw new RuntimeException("Barco no Valido");
		}
		
		//ASIGNACION DE SKIN AL BARCO IA 1.
		switch(barco) {
		case 0: TIA1.load(barcoCanario.getTextureData());
			break;
		case 1: TIA1.load(barcoEvil.getTextureData());
			break;
		case 2: TIA1.load(barcoCanario.getTextureData());
			break;
		default: throw new RuntimeException("Barco no Valido");
		}
		

		switch(barco) {  
		case 0: mainBarco.load(barcoFuego.getTextureData());
			break;
		case 1: mainBarco.load(barcoCanario.getTextureData());
			break;
		case 2: mainBarco.load(barcoNatura.getTextureData());
		break;
		default: throw new RuntimeException("Barco no Valido");
		}
		
		
		//ASIGNACION DE SKIN AL BARCO IA 1.
		switch(barco) {
		case 0: TIA1.load(barcoCanario.getTextureData());
			break;
		case 1: TIA1.load(barcoEvil.getTextureData());
			break;
		case 2: TIA1.load(barcoCanario.getTextureData());
			break;
		default: throw new RuntimeException("Barco no Valido");
		}
		

		//ASIGNACION DE SKIN AL BARCO IA 2.
		switch(barco) { 
		case 0: TIA2.load(barcoNatura.getTextureData()); 
			break;
		case 1: TIA2.load(barcoNatura.getTextureData());
			break;
		case 2: TIA2.load(barcoEvil.getTextureData());
			break;
		default: throw new RuntimeException("Barco no Valido");
		}

		
		//ASIGNACION DE SKIN AL BARCO IA 3.
		switch(barco) {
		case 0: TIA3.load(barcoEvil.getTextureData());
			break;
		case 1: TIA3.load(barcoFuego.getTextureData());
			break;
		case 2: TIA3.load(barcoFuego.getTextureData());
			break;
		default: throw new RuntimeException("Barco no Valido");
		}

	}
	
	public void iniciar(Table table,SpriteBatch batch,Stage stage) {
		

		table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
		
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//			Nueva camara que sigue al barco
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, 720);
		camera.position.set(WIDTH /2, jugador.getY() + 200 + jugador.getHeight() / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		camera.update();
		

		// Genera el mapa Hay que mejorar
		batch.begin();
		for(int i = 0; i < Duracion ; i++) {
			batch.draw(boardPlay,0,HEIGHT*i);
			
			if(i == Duracion-1) {
				batch.draw(TlineaMeta,lineaMeta.x,HEIGHT*i);
				lineaMeta.setY(HEIGHT*i +110);
			}
		}
		batch.end();
		
		
		
		//Dibuja al jugador
		batch.begin();
		jugador.draw(batch);		batch.end();
		
		batch.begin();
		IA1.draw(batch);
		batch.end();
		
		batch.begin();
		IA2.draw(batch);
		batch.end();
		
		batch.begin();
		IA3.draw(batch);
		batch.end();
	
	}
	public void setStatsBarco(int vidillas, int velocidad) {
		jugador.setVidas(vidillas);
		jugador.setvPunta(velocidad);
	}
		
	public float getLineaMeta() {
		return lineaMeta.y;
	}
	
	
	public int CarrilIzq(Carril i) {
		int ladoIz = 0; 
		ladoIz = i.getLateral1();
		 return ladoIz; 
		
	}
	
	public int CarrilDer(Carril i) {
		int ladoDer = 0;
		ladoDer = i.getLateral2();
		return ladoDer; 
	}
	

}