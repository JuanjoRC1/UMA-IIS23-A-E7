package com.barquitosfc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Barco extends Sprite{
	private int vidas = 5;
	private int vPunta = 300;
	private int agilidad = 200;
	private int carril ;
	
	
	public Barco(Texture boatTexture, int i ) {
		super(boatTexture);
		setTexture(boatTexture);
		carril = i;  
	}

	public void draw2(SpriteBatch batch) {
		this.draw(batch);
	} 
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int i) {
		vidas =i ; 
	} 
	public void setVidas() {
		vidas -= 1; 
	}
	public int getvPunta() {
		return vPunta;
	}
	public void setvPunta(int vPunta) {
		this.vPunta = vPunta;
	}
	public int getAgilidad() {
		return agilidad;
	}
	public void setAgilidad(int agilidad) {
		this.agilidad = agilidad;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
		
	}

	public void addAction(Action fadeOutAction2) {
		// TODO Auto-generated method stub

		
	}

	
}
