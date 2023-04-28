package com.barquitosfc.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assetsmini {

    public static BitmapFont font;
    private static final GlyphLayout glyphLayout = new GlyphLayout();

    public static Animation<AtlasRegion> bird;

    public static TextureRegion background;
    public static TextureRegion gameOver;
    public static TextureRegion getReady;
    public static TextureRegion tap;
    public static TextureRegion downPipe;
    public static TextureRegion upPipe;

    public static void load() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/atlasmini.txt"));

        background = atlas.findRegion("fondoflao");
        gameOver = atlas.findRegion("fin");
        getReady = atlas.findRegion("fin");
        tap = atlas.findRegion("fin");
        downPipe = atlas.findRegion("palochino(abajo)");
        upPipe = atlas.findRegion("palochino");

        bird = new Animation<>(.3f,
                atlas.findRegion("dragonflappy"),
                atlas.findRegion("dragonflappypos2"),
                atlas.findRegion("dragonflappy"));

        // Use default libGDX font
        font = new BitmapFont();
        font.getData().scale(7f);
    }

    /**
     * Get the text width in order to center in the screen
     */
    public static float getTextWidth(String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }
}
