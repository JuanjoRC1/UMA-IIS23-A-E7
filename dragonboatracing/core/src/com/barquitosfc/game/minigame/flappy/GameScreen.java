package com.barquitosfc.game.minigame.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.barquitosfc.game.Assetsmini;
import com.barquitosfc.game.Mainminijuego;
import com.barquitosfc.game.minigame.Screens.Screens;

public class GameScreen extends Screens{
	 static final int STATE_READY = 0;
	    static final int STATE_RUNNING = 1;
	    static final int STATE_GAME_OVER = 2;
	    WorldGame oWorld;
	    WorldGameRenderer renderer;
	    int state;
	    Image getReady, tap, gameOver;

	public GameScreen(Mainminijuego game) {
		super(game);
		state = STATE_READY;
		tap= new Image(Assetsmini.tap); 
		getReady= new Image(Assetsmini.tap); 
		  tap.setPosition(SCREEN_WIDTH / 2f - tap.getWidth() / 2f, 310);
				// TODO Auto-generated constructor stub
		stage.addActor(tap);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(float delta) {
		 switch (state) {
         case STATE_READY:
             updateReady(delta);
             break;
         case STATE_RUNNING:
             updateRunning(delta);
             break;
         case STATE_GAME_OVER:
             updateGameOver(delta);
             break;
     }
	}
	   private void updateReady(float delta) {
	        if (Gdx.input.justTouched()) {
	            getReady.addAction(Actions.fadeOut(.3f));
	            tap.addAction(Actions.sequence(Actions.fadeOut(.3f),
	                    Actions.run(new Runnable() {
	                        @Override
	                        public void run() {
	                            getReady.remove();
	                            tap.remove();
	                            state = STATE_RUNNING;
	                        }
	                    })));
	        }

	    }

	    private void updateRunning(float delta) {
	        boolean jump = Gdx.input.justTouched();

	        oWorld.update(delta, jump);

	        if (oWorld.state == WorldGame.STATE_GAME_OVER) {
	            state = STATE_GAME_OVER;
	            stage.addActor(gameOver);
	        }
	    }

	    private void updateGameOver(float delta) {
	        if (Gdx.input.justTouched()) {
	            gameOver.addAction(Actions.sequence(Actions.fadeOut(.3f),
	                    Actions.run(new Runnable() {
	                        @Override
	                        public void run() {
	                            gameOver.remove();
	                            game.setScreen(new GameScreen(game));
	                        }
	                    })));
	        }
	    }
	    @Override
	    public void draw(float delta) {
	        renderer.render(delta);

	        oCam.update();
	        spriteBatch.setProjectionMatrix(oCam.combined);


	        spriteBatch.begin();
	        float width = Assetsmini.getTextWidth(oWorld.score + "");
	        Assetsmini.font.draw(spriteBatch, oWorld.score + "", SCREEN_WIDTH / 2f - width / 2f, 700);
	        spriteBatch.end();
	    }
		
}
