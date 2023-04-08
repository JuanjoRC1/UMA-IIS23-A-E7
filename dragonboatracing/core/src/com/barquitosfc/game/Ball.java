package com.barquitosfc.game;
import java.awt.Color;
import java.awt.Graphics;
public class Ball {
public static final int SIZE = 16;
	
	private int x, y;
	private int xVel, yVel;
	private int speed = 5;
	public Ball() {
		reset();
	}
	private void reset() {
		x = 1280/2 - SIZE/2;
		y = 720/2 - SIZE/2;
		
		xVel = 2;
		yVel = 2;
	}
	public void changeXDir() {
		xVel *= -1;
	}
	
	public void changeYDir() {
		yVel *= -1;
	}

	public void draw(Graphics g) {
		
		g.setColor(Color.white);
		g.fillOval(x, y, SIZE, SIZE);
		
	}

	public void update(Barra Barra1, Barra Barra2) {
		// ball movement
		x += xVel * speed;
		y += yVel * speed;
		
		// for collisions with upper and lower walls
		if(y >= DragonBoatGame.HEIGHT - SIZE || y<= 0) {
			changeYDir();
		}
		
		// for paddle miss
		if(x >= DragonBoatGame.WIDTH - SIZE) {
			Barra1.addPoint();
			reset();
		}
		if(x <= 0) {
			Barra2.addPoint();
			reset();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}


