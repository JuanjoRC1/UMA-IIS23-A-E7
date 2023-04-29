package com.barquitosfc.game.minigame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.barquitosfc.game.DragonBoatGame;
import com.barquitosfc.game.Mainminijuego;


public abstract class Screens extends InputAdapter implements Screen {
	public static final float SCREEN_WIDTH =1920 ;
	public static final float SCREEN_HEIGHT= 1080;
	public static final float WORLD_WIDTH = 4.8f;
    public static final float WORLD_HEIGHT = 8;
    public Mainminijuego game;
    
    public OrthographicCamera oCam;
    public SpriteBatch spriteBatch;
    public Stage stage;
    
    public Screens(Mainminijuego dragonBoatGame ) {
    	this.game=dragonBoatGame;
    	stage= new Stage(new StretchViewport(SCREEN_WIDTH,SCREEN_HEIGHT));
    	oCam= new OrthographicCamera(SCREEN_WIDTH,SCREEN_HEIGHT);
    	oCam.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
    	//Inputs
    	 InputMultiplexer input = new InputMultiplexer(this, stage); 
         Gdx.input.setInputProcessor(input);
         
         spriteBatch = new SpriteBatch();
    }
    //dibujar la user interface y el juego
    public abstract void draw(float delta);
//actualizar el juego
    public abstract void update(float delta);
    @Override
    public void show() {
    	
    }
    @Override
    public void render(float delta) {
        update(delta);

        // Update the stage (mostly UI elements)
        stage.act(delta);

        // Clear everything on the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the game elements on the screen
        draw(delta);

        // Draw the stage element on the screen
        stage.draw();
    }
    //resize en el caso de que la user interface cambie de tamaño
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
