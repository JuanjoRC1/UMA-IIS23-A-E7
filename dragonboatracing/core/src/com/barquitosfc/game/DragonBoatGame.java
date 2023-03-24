package com.barquitosfc.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;



public class DragonBoatGame extends ApplicationAdapter {
	Texture img;
	public enum GameState {
		MENU,PLAY,CONFIG,QUIT,SHOP
	}
	private Skin skin;
	private Stage stage;
	public static GameState gameState;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		skin= new Skin(Gdx.files.internal("ui/uiskin.json"));
		camera=new OrthographicCamera();
		camera.setToOrtho(false,1280,720);
		batch= new SpriteBatch();
		camera.update();
		gameState=GameState.MENU;
		
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		switch(gameState) {
		case MENU:
			stage = new Stage();
			Table table=new Table();
			table.setPosition(800,720/4);
			table.setFillParent(true);
			table.setHeight(200);
			stage.addActor(table);
			Label label= new Label("Bienvenidos a Dragon Boat Racing",getSkin());
			table.addActor(label);
			//Botones
			TextButton buttonPlay= new TextButton("Inicio",getSkin());
			buttonPlay.setPosition(label.getOriginX()+100	, label.getOriginY());
			buttonPlay.setWidth(200);
			buttonPlay.setHeight(40);
			buttonPlay.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					gameState=GameState.PLAY;
					return false;
				}
			});
			table.addActor(buttonPlay);
			//BOTON
			TextButton buttonConfig= new TextButton("Opciones",getSkin());
			buttonPlay.setPosition(label.getOriginX()+250, label.getOriginY());
			buttonPlay.setWidth(200);
			buttonPlay.setHeight(40);
			buttonPlay.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					gameState=GameState.CONFIG;
					return false;
				}
			});
			table.addActor(buttonConfig);
			//BOTON
			TextButton buttonShop= new TextButton("Tienda",getSkin());
			buttonPlay.setPosition(label.getOriginX()+500, label.getOriginY());
			buttonPlay.setWidth(200);
			buttonPlay.setHeight(40);
			buttonPlay.addListener(new InputListener() {
				public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
					gameState=GameState.SHOP;
					return false;
				}
			});
			table.addActor(buttonShop);
			//BOTON
			TextButton buttonQuit= new TextButton("Salir",skin);
			buttonPlay.setPosition(label.getOriginX()+750, label.getOriginY());
			buttonPlay.setWidth(200);
			buttonPlay.setHeight(40);
			buttonPlay.addListener(new InputListener() {
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
			
			
			break;
		case CONFIG:
			break;
		case SHOP:
			break;
		case QUIT:
			break;
			}
		
		
			
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	
	protected Skin getSkin() {
		if (skin==null) {
			skin= new Skin(Gdx.files.internal("ui/uiskin.json"));
		}
		return skin;
	}
}
