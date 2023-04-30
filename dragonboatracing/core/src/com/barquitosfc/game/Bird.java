package com.barquitosfc.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

	public class Bird extends Sprite{
		private int cont = 0;
		
		public Bird(Texture birdTexture) {
			super(birdTexture);
			setTexture(birdTexture);
		}

		public void draw2(SpriteBatch batch) {
			this.draw(batch);
		}
		
		public int cont() {
			return cont;
		}
		

		public void dispose() {
			// TODO Auto-generated method stub
			this.dispose();
			
		}

}
