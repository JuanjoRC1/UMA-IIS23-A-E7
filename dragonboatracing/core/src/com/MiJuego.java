package com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MiJuego extends ApplicationAdapter {
    private SpriteBatch batch;
    private Barco barco;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Texture barcoTexture = new Texture("barco.png");
        barco = new Barco(barcoTexture);
        barco.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        barco.update(delta);

        batch.begin();
        barco.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

