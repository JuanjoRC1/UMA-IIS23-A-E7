package com.barquitosfc.game;

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

public class Ajustes {
//protected int volumen; por si metemos el sonido
protected Texture guardar,reset,fondoAjustes;
protected SpriteDrawable guardarS,resetS;
	public void inicializar() {
		reset = new Texture(Gdx.files.internal("data/Reset.png"));
		resetS = new SpriteDrawable(new Sprite(reset));
		fondoAjustes = new Texture(Gdx.files.internal("fondos/FONDO_TIENDA_HD.png"));
		guardar = new Texture(Gdx.files.internal("data/Reset.png"));
		guardarS = new SpriteDrawable(new Sprite(reset));
	}
	public Ajustes() {
		this.inicializar();
	}
	public void iniciar(Table table,SpriteBatch batch,Stage stage) {
		table.clear();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(fondoAjustes,0,0);
        batch.end();
        
        
        
        stage = new Stage();
		table=new Table();
		table.setPosition(0,0);
		table.setFillParent(true);
		table.setHeight(200);
		stage.addActor(table);
        
		Button guardarJuego= new Button(new Button.ButtonStyle(guardarS,guardarS,guardarS));
		guardarJuego.setPosition(1220, 600);
		guardarJuego.setSize(70,70);
		guardarJuego.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				
				return false;
				
			}
		});
		table.addActor(guardarJuego);
        
		Button resetJuego= new Button(new Button.ButtonStyle(resetS,resetS,resetS));
		resetJuego.setPosition(700, 600);
		resetJuego.setSize(70,70);
		resetJuego.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				
				return false;
				
			}
		});
		table.addActor(resetJuego);
        
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Gdx.input.setInputProcessor(stage);
        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
