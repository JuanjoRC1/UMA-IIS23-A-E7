package com.barquitosfc.game;

import javax.swing.JFrame;

public class Ventana extends JFrame implements Runnable {
	public Ventana() {
		this.setSize(DragonBoatGame.WIDTH, DragonBoatGame.HEIGHT);
		this.setTitle("Minijuego");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public void run() {
		while(true) {
			//haz lo que sea
			
		}
	}
}
