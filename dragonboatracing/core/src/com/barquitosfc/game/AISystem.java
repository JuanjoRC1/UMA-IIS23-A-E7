package com.barquitosfc.game;


import com.badlogic.gdx.math.Rectangle;


import com.badlogic.gdx.utils.Array;


public class AISystem {
	protected Barco barco;
	protected Array<Rectangle> troncos;
	protected Array<Rectangle> rocas;
	protected Array<Rectangle> cocodrilos;
	protected Array<Rectangle> eliminados = new Array<Rectangle>();

	protected int velocidad = 200;
	protected int distanciaEsquivar = 50;
	
	public AISystem(Barco barquito, Array<Rectangle> tronquitos, Array<Rectangle> roquitas,Array<Rectangle> cocodrilitos) {
		barco = barquito;
		rocas = roquitas;
		troncos = tronquitos;
		cocodrilos = cocodrilitos;
	}
	
	
	public void update(float delta,int numCarril) {
        float centroBarcoX = barco.getBoundingRectangle().getX() + barco.getWidth() / 2;
		float puntaBarco = barco.getBoundingRectangle().getY() + barco.getHeight();
		
        barco.setY(barco.getY() + velocidad * delta);

        
        for (Rectangle obstaculo : troncos) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
            	 eliminados.add(obstaculo);
            }
        }
        
        for (Rectangle obstaculo : rocas) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
           	 eliminados.add(obstaculo);
           }
        }
        
        for (Rectangle obstaculo : cocodrilos) {
            esquivarObstaculo(obstaculo, centroBarcoX, puntaBarco, delta,numCarril);
            if(barco.getBoundingRectangle().overlaps(obstaculo)) {
           	 eliminados.add(obstaculo);
           }
        } 
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
				if(choca(barco.getBoundingRectangle().getX(), barco.getBoundingRectangle().getWidth(), obstaculo.getX(), obstaculo.getWidth())) { 
					if (centroObstaculo <= centroBarcoX && barco.getX()<carril.getLateral2()-barco.getWidth()*2) {
	                    barco.setX(barco.getX() + velocidad * delta);
	                } else {
	                    barco.setX(barco.getX() - velocidad * delta);
	                }
				}
			}
			if(barco.getX()<carril.getLateral1()+barco.getWidth()) {
				barco.setX(barco.getX() + velocidad * delta);
			} else if(barco.getX()>carril.getLateral2()-barco.getWidth()) {
                barco.setX(barco.getX() - velocidad * delta);
				
			}
			
		}
	
		private boolean choca(float xBarco,float widthBarco, float xObs, float widthObs) {
			float finBarco = xBarco + widthBarco;
		    float finObs = xObs + widthObs;

		    if (finBarco < xObs || xBarco > finObs) {
		        // No hay superposición
		        return false;
		    } else {
		        // Hay superposición
		        return true;
		    }
		}

}
