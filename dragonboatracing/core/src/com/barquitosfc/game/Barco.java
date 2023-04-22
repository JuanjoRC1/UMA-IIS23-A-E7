package com.barquitosfc.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Barco extends Sprite{
	private int vidas = 1;
	private int vPunta = 300;
	private int agilidad = 200;
	
	
	public Barco(Texture boatTexture) {
		super(boatTexture);
		setTexture(boatTexture);
	}

	public void draw2(SpriteBatch batch) {
		this.draw(batch);
	}
	
	public int getVidas() {
		return vidas;
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

	
}