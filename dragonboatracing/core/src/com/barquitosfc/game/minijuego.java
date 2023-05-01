package com.barquitosfc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

public class minijuego {
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	public Bird jugador; 
	public Texture pajarote, boardPlay,fin; 
	public Sound bum;
	public Music omega;
	public void inicializar() {
		bum =  Gdx.audio.newSound(Gdx.files.internal("sonidos/tumuerto.mp3"));
		omega =  Gdx.audio.newMusic(Gdx.files.internal("sonidos/omegaelfuelte.mp3"));
		pajarote = new Texture(Gdx.files.internal("minijuego/dragonflappy.png"));
		boardPlay = new Texture(Gdx.files.internal("minijuego/fondoflapi.png"));
		fin= new Texture(Gdx.files.internal("minijuego/fin.png"));
		
		jugador = new Bird(pajarote);
		jugador.setPosition(WIDTH/4, HEIGHT/2);
	}
	
	public void iniciar(Table table,SpriteBatch batch,Stage stage) {
		omega.play();
		table.clear();// en vez de hacer table clear cambiamos a un nuevo stage con Gdx.input.setInputProcessor( new stage);
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//			Nueva camara que sigue al barco
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, HEIGHT);
		camera.position.set(jugador.getX() +500,WIDTH /2,  0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		camera.update();
		

		// Genera el mapa Hay que mejorar
		batch.begin();
		for(int i = 0; i < 1000 ; i++) {
			batch.draw(boardPlay,WIDTH*i,425);
		}
		batch.end();
		
		//Dibuja al jugador
		batch.begin();
		jugador.draw(batch);
		batch.end();
		
	}
	

}

