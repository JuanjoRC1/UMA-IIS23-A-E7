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
	public Barco jugador; 
	public Texture mainBarco, boardPlay; 
	public Carril C1;
	public Carril C2;
	
	public void inicializar() {
		
		mainBarco = new Texture(Gdx.files.internal("data/boatp.jpg"));
		boardPlay = new Texture(Gdx.files.internal("fondos/Fondo_Rio.png"));
		
		Random random = new Random();
		int carril = random.nextInt(4);
		C1 = new Carril(carril); 
		jugador = new Barco(mainBarco,carril); 
		jugador.setPosition(C1.getPuntoSpawn(), HEIGHT/7);
		
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
		for(int i = 0; i < 100000 ; i++) {
			batch.draw(boardPlay,0,HEIGHT*i);
		}
		batch.end();
		
		//Dibuja al jugador
		batch.begin();
		jugador.draw(batch);
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