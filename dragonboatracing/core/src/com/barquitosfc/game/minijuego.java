package com.barquitosfc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;

public class minijuego {
	public float st;
	public int frame=0;
	public static final int WIDTH=1920;
	public static final int HEIGHT	=1080;
	public Bird jugador; 
	public Texture pajarote, boardPlay,fin; 
	public Sound bum;
	public Texture[] pajaroani= new Texture[4];
	public Texture[] churumb= new Texture[3];
	public Animation<Texture> animacion,churumani;
	public void inicializar() {
		
		pajarote = new Texture(Gdx.files.internal("minijuego/dragonflappy2.png"));
		boardPlay = new Texture(Gdx.files.internal("minijuego/fondomini2.png"));
		fin= new Texture(Gdx.files.internal("minijuego/fin.png"));
		pajaroani[0] = new Texture(Gdx.files.internal("minijuego/dragonflappy.png"));
		pajaroani[1] = new Texture(Gdx.files.internal("minijuego/dragonflappy.png"));
		pajaroani[2] = new Texture(Gdx.files.internal("minijuego/dragonflappy2.png"));
		pajaroani[3] = new Texture(Gdx.files.internal("minijuego/dragonflappy2.png"));
		churumb[0] = new Texture(Gdx.files.internal("minijuego/chrumgif.png"));
		churumb[1] = new Texture(Gdx.files.internal("minijuego/Sujeto.png"));
		churumb[2] = new Texture(Gdx.files.internal("minijuego/churumgif2.png"));

		churumani = new Animation<>(100.f, churumb);
		churumani.setPlayMode(Animation.PlayMode.LOOP);
		animacion = new Animation<>(100.f, pajaroani);
		animacion.setPlayMode(Animation.PlayMode.LOOP);
		
		jugador = new Bird(pajaroani[frame]);
		jugador.setPosition(WIDTH/4, HEIGHT/2);
	}
	
	public void iniciar(Table table,SpriteBatch batch,Stage stage) {
		
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
		
		
	}
	

}

