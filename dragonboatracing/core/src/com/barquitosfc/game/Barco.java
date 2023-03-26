package com.barquitosfc.game;

import com.badlogic.gdx.math.Rectangle;

public class Barco {
	
	private Rectangle area;
	public float y,x;
	public float rotation;
	
	public Barco(float x, float y, float width, float height) {
		area = new Rectangle(x,y,width,height);
		this.x = x; 
		this.y = y;
	}
	
	public Rectangle getArea() {
		return area;
	}
	
	public void setRotation(int i) {
		 area.se
	}
}
