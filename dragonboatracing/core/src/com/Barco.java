package com;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Barco extends Sprite {
    private float velocidad;
    private Vector2 direccion;

    public Barco(Texture texture) {
        super(texture);
        velocidad = 0;
        direccion = new Vector2(1, 0);
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public void setDireccion(Vector2 direccion) {
        this.direccion = direccion;
    }

    public void update(float delta) {
        // Actualizar la posición del Barco en función de su velocidad y dirección
        Vector2 desplazamiento = new Vector2(direccion).scl(velocidad * delta);
        setPosition(getX() + desplazamiento.x, getY() + desplazamiento.y);
    }

//    public void draw() {
//        // Dibujar el Barco en la pantalla
//        super.draw();
//    }
}
