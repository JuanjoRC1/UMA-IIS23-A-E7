 package com.barquitosfc.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Barra {
	float x;
	float y;
	private int vel = 0;
	private int speed = 10;
	private int width = 25, height = 100;
	private int score;
	private Color color;
	private boolean left;
	
	public Barra( boolean left) {
		
		if (left) { 
			x = 0;
		}
		else {
			x =DragonBoatGame.WIDTH-width-250;
		}
		y = DragonBoatGame.HEIGHT/2 - height/2;
	}
	
	public void addPoint() {
		score++;
	}

	public void draw(Graphics g) {
		
		// draw paddle
		g.setColor(color);
//		g.fillRect(x, y, width, height);
		
		// draw score
		String scoreText = Integer.toString(score);
		int sx;
		Font font = new Font("ROBOTO", Font.PLAIN, 50);
		int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
		int padding = 25;
		
		if(left) {
			sx = DragonBoatGame.WIDTH/2 - strWidth - padding;
		}
		else {
			sx = DragonBoatGame.WIDTH/2 + padding;
		}
		
		g.setFont(font);
		g.drawString(scoreText, sx, 50);
		
	}

	public void update(Ball ball) {
		// paddle movement
		if(y > DragonBoatGame.HEIGHT - height) { y = DragonBoatGame.HEIGHT - height; }
		else if(y <0) { y = 0; }
		else { y += vel; }
		
		int ballX = ball.getX();
		int ballY = ball.getY();
		
		// paddle hits
		if(left) {
			if(ballX <= width && ballY >= y-Ball.SIZE && ballY <= y+height) {
				ball.changeXDir();
			}
		}
		else {
			if(ballX >= x - Ball.SIZE && ballY >= y-Ball.SIZE && ballY <= y+height) {
				ball.changeXDir();
			}
		}
		
	}
	
	public void switchDirection(int dir) {
		
		vel = speed * dir;
		
	}
	
	public void stop() {
		
		vel = 0;
		
	}
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	public int getvel() {
		return vel;
	}
	public int getspeed() {
		return speed;
	}
	
	public void setx(float posx) {
		x=posx;
	}
	public void sety(float f) {
		y=f;
	}
	public void setvel(int vell) {
		vel=vell;
	}
	public void setspeed(int speedd) {
		speedd=speed;
	}
}
