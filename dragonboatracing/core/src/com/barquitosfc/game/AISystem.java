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
	
	protected int vidas = Tienda.vidasS+1;
	protected int velocidad;
	protected int distanciaEsquivar = 150;
	protected long tiempoInicio;
	
	public AISystem(Barco barquito, Array<Rectangle> tronquitos, Array<Rectangle> roquitas,Array<Rectangle> cocodrilitos,int speed,long time) {
		barco = barquito;
		rocas = roquitas;
		troncos = tronquitos;
		cocodrilos = cocodrilitos;
		velocidad = speed;
		tiempoInicio = time;
	}
	
	
	public void update(float delta,int numCarril) {
        float centroBarcoX = barco.getBoundingRectangle().getX() + barco.getWidth() / 2;
		float puntaBarco = barco.getBoundingRectangle().getY() + barco.getHeight();
		
		if(TimeUtils.timeSinceMillis(tiempoInicio)>=4500) {
        barco.setY(barco.getY() + velocidad * delta);
		}
        
        for (Rectangle obstaculo : troncos) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	velocidad = velocidad-velocidad/10;
            	 eliminados.add(obstaculo);
            }
        }
        
        for (Rectangle obstaculo : rocas) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	vidas--;
            	velocidad = velocidad-velocidad/20;
           	 eliminados.add(obstaculo);
           }
        }
        
        for (Rectangle obstaculo : cocodrilos) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	vidas -=2;
            	velocidad = velocidad-velocidad/50;
            	
           	 eliminados.add(obstaculo);
           	 
           }
        } 
        for (Rectangle eliminado : eliminados) {
        	troncos.removeValue(eliminado, true);
            rocas.removeValue(eliminado, true);
            cocodrilos.removeValue(eliminado, true);
        }
        eliminados.clear();
        
		if(vidas<=0) {
			velocidad = 0;
			
		}
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
