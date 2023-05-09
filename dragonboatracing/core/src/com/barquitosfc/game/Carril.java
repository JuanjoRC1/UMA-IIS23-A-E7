package com.barquitosfc.game;

public class Carril {
	
	private int lateral1; 
	private int lateral2;
	private int puntoSpawn;
	
	public Carril(int numero) {
		if(numero == 1) {
			lateral1 = 130; 
			lateral2 = 530;
			puntoSpawn = 345; 
		}
		else if(numero == 2) {
			lateral1 = 550; 
			lateral2 = 1920/2-20; 
			puntoSpawn = 755; 
		} 
		else if (numero == 3) {
			lateral1 = 1920/2;
			lateral2 = 1920/2 +390; 
			puntoSpawn = 1165; 
		}
		else {
			lateral1= 1920/2 +410; 
			lateral2 = 1730; 
			puntoSpawn = 1575; 
		}
		
	}

	public int getLateral1() {
		return lateral1;
	}



	public int getLateral2() {
		return lateral2;
	}



	public int getPuntoSpawn() {
		return puntoSpawn;
	}


	
	
}
