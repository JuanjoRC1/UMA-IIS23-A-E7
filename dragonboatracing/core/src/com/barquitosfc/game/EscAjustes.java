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


public class EscAjustes {
	
	protected Texture bReanudar, bAjustes, bSalir, fondoEscape;
	protected SpriteDrawable spriteBSalir, spriteBReanudar, spriteBAjustes;
	

	
	public void iniciar(Table table,SpriteBatch batch,Stage stage,Juego juego) {
		
		table.clear();
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(fondoEscape,0,juego.jugador.getY()-340);
        batch.end();
        
		stage = new Stage();
		table=new Table();
		table.setPosition(0,0);
		table.setFillParent(true);
		table.setHeight(200);
		stage.addActor(table);
        
		//Botones Reanudar
		Button buttonReanudar= new Button(new Button.ButtonStyle(spriteBReanudar,spriteBReanudar,spriteBReanudar));
//		TextButton buttonPlay= new TextButton("Inicio",getSkin());
		buttonReanudar.setPosition(800, 500);
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
//		TextButton buttonPlay= new TextButton("Inicio",getSkin());
		buttonSalir.setPosition(800, 400);
		buttonSalir.setSize(300,40);
		buttonSalir.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				DragonBoatGame.gameState=GameState.MENU;
				return false;		
			}
		});
		table.addActor(buttonSalir);
				
				
		//Botones Ajustes
		Button buttonAjustes= new Button(new Button.ButtonStyle(spriteBAjustes,spriteBAjustes,spriteBAjustes));
//		TextButton buttonPlay= new TextButton("Inicio",getSkin());
		buttonAjustes.setPosition(500, juego.jugador.getY()+100);
		buttonAjustes.setSize(300,40);
		buttonAjustes.addListener(new InputListener() {
			public boolean touchDown(InputEvent event,float x,float y,int pointer,int button) {
				DragonBoatGame.gameState=GameState.CONFIG;
				return false;
						
			}
		});
//		table.addActor(buttonAjustes);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Gdx.input.setInputProcessor(stage);
		
	}
}
