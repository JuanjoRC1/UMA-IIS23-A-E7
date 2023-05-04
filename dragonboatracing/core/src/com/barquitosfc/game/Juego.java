package com.barquitosfc.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

public class Juego {
	

	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	public Barco jugador,IA1,IA2,IA3; 
	public Texture mainBarco, boardPlay,TIA1,TIA2,TIA3;
	public Carril C1, C2,C3,C4;
	
	public void inicializar() {
		TIA1=TIA2=TIA3= new Texture(Gdx.files.internal("data/boatp.jpg"));
		mainBarco = new Texture(Gdx.files.internal("data/boatp.jpg"));
		boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Rio.png"));
		
		Random random = new Random();
		
		C1 = new Carril(2); 
		C2= new Carril(1);
		C3= new Carril(3);
		C4= new Carril(4);
		jugador = new Barco(mainBarco,2); 
		jugador.setPosition(C1.getPuntoSpawn(), HEIGHT/7);
		
		
		IA1= new Barco(TIA1,1);
		IA1.setPosition(C2.getPuntoSpawn(), HEIGHT/7);
		
		
		IA2= new Barco(TIA2,3);
		IA2.setPosition(C3.getPuntoSpawn(), HEIGHT/7);
		
	
		IA3= new Barco(TIA3,3);
		IA3.setPosition(C4.getPuntoSpawn(), HEIGHT/7);
		
		
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
		for(int i = 0; i < 20 ; i++) {
			batch.draw(boardPlay,0,HEIGHT*i);
		}
		batch.end();
		
		//Dibuja al jugador
		batch.begin();
		jugador.draw(batch);
		IA1.draw(batch);
		IA2.draw(batch);
		IA3.draw(batch);
		batch.end();
		
		
	}
	

	
	public int Carril(Carril i) {
		int ladoIz = 0; 
		ladoIz = i.getLateral1();
		 
		 return ladoIz; 
		
	}
	
	public int Carril2(Carril i) {
		int ladoDer = 0;
		ladoDer = i.getLateral2();
		return ladoDer; 
	}
	

}