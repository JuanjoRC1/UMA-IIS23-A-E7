package com.barquitosfc.game;


import com.badlogic.gdx.math.Rectangle;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class AISystem {
	protected Barco barco;
	protected Array<Rectangle> troncos;
	protected Array<Rectangle> rocas;
	protected Array<Rectangle> cocodrilos;
	protected Array<Rectangle> eliminados = new Array<Rectangle>();
	
	protected int vidas;
	protected int velocidad;
	protected int distanciaEsquivar = 0;
	protected long tiempoInicio;
	protected int numeroBarco = 0;
	
	public AISystem(Barco barquito, Array<Rectangle> tronquitos, Array<Rectangle> roquitas,Array<Rectangle> cocodrilitos,long time,int dificultad,int numBarco,int speed) {
		barco = barquito;
		rocas = roquitas;
		troncos = tronquitos;
		cocodrilos = cocodrilitos;
		tiempoInicio = time;
		distanciaEsquivar = dificultad;
		numeroBarco = numBarco;
		
		switch (numeroBarco) { // Dependiendo del barco escogido por la IA tendrá unas características u otras
		case 0:
			velocidad = speed-10;
			vidas = 5;
			break;
		case 1:
			velocidad = speed-20;
			vidas = 8;

			break;
		case 2:
			velocidad = speed-15;
			vidas = 6;

			break;
		}
		switch(distanciaEsquivar) { // Dependiendo de la distancia de esquive (que está asociada a la dificultad)
									//  que a su vez está asocidada a cada fase la IA aumenta su velocidad.
			case 30:
				break;
			case 50:
				velocidad = velocidad+5;
				break;
			case 80:
				velocidad = velocidad+10;
				break;
			case 100:
				velocidad = velocidad+15;
				break;
			
		}
		
	}
	
	
	public void update(float delta,int numCarril) {
		
        float centroBarcoX = barco.getBoundingRectangle().getX() + barco.getWidth() / 2;
		float puntaBarco = barco.getBoundingRectangle().getY() + barco.getHeight();
		
		if(TimeUtils.timeSinceMillis(tiempoInicio)>=5500) {
        barco.setY(barco.getY() + velocidad * delta);
		}
        // Modifica los parametros de la IA cuando se choca con un tronco
        for (Rectangle obstaculo : troncos) { 
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	vidas--;
            	velocidad = velocidad-velocidad/20;
            	 eliminados.add(obstaculo);
            }
        }
        // Modifica los parametros de la IA cuando se choca con una roca
        for (Rectangle obstaculo : rocas) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	velocidad = velocidad-velocidad/10;
           	 eliminados.add(obstaculo);
           }
        }
        // Modifica los parametros de la IA cuando se choca con un cocodrilo
        for (Rectangle obstaculo : cocodrilos) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	vidas -=2;
            	velocidad = velocidad-velocidad/50;
            	
           	 eliminados.add(obstaculo);
           	 
           }
        } //elimina los obstáculos con los que se ha chocado la IA
        for (Rectangle eliminado : eliminados) {
        	troncos.removeValue(eliminado, true);
            rocas.removeValue(eliminado, true);
            cocodrilos.removeValue(eliminado, true);
        }
        eliminados.clear();
        
        
    }

		

		
		private void esquivarObstaculo(Rectangle obstaculo, float centroBarcoX,float puntaBarco,float delta,int numCarril) {
			
			Carril carril = new Carril(numCarril);
			float distanciaObstaculo = (float)obstaculo.getY()-puntaBarco;
			float centroObstaculo = obstaculo.getX() + obstaculo.getWidth()/2;
			
			//si la distancia a la que esta el barco del obstaculoes menor que la de esquivar:
			if(distanciaObstaculo<distanciaEsquivar) { 
				//comprueba si estan a la misma altura el barco y el obstaculo
				if(choca(barco.getBoundingRectangle().getX(),barco.getBoundingRectangle().getY(), 
						barco.getBoundingRectangle().getWidth(), obstaculo.getX(),obstaculo.getY(), obstaculo.getWidth())) {
					//Si el barco tiene el centro mas a la izquierda que el centro del obstaculo entonces esquiva a la derecha
					if (centroObstaculo < centroBarcoX && barco.getX()<carril.getLateral2()-barco.getWidth()) {
	                    barco.setX(barco.getX() + velocidad * delta);
	                } else {
	                	//Esquiva a la izquierda
	                    barco.setX(barco.getX() - velocidad * delta);
	                }
				}
			}
			//Establecer limite de los carriles
			if(barco.getX()<carril.getLateral1()+barco.getWidth()) {
				barco.setX(carril.getPuntoSpawn() + velocidad * delta);
			} else if(barco.getX()>carril.getLateral2()-barco.getWidth()) {
				barco.setX(carril.getPuntoSpawn() - velocidad * delta);
				
			}
		}
		//Metodo para saber si el barco va a chocar con un obstaculo
		private boolean choca(float xBarco,float yBarco, float widthBarco, float xObs,float yObs, float widthObs) {
			float finBarco = xBarco + widthBarco;
		    float finObs = xObs + widthObs;

		    if(yBarco<yObs) {
		    if (finBarco < xObs || xBarco > finObs) {
		        // No hay superposición
		        return false;
		    } else {
		        // Hay superposición
		        return true;
		    }
		    }
		    else {
		    	return false;
		    }
		}

}
