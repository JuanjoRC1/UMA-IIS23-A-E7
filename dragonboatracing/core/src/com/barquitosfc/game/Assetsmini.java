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
	        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/atlasmp.txt"));

	        background = atlas.findRegion("fondofladefi");
	        gameOver = atlas.findRegion("gameOver");
	        getReady = atlas.findRegion("getReady");
	        tap = atlas.findRegion("tap");
	        downPipe = atlas.findRegion("chrumtuberia");
	        upPipe = atlas.findRegion("chrumtuberia");

	        bird = new Animation<>(.3f,
	                atlas.findRegion("f2 (1)"),
	                atlas.findRegion("f2 (1)"),
	                atlas.findRegion("f2 (1)"));

	        // Use default libGDX font
	        font = new BitmapFont();
	        font.getData().scale(7f);
	    }

	    /**
	     *Obtener el tamaño del texto
	     */
	    public static float getTextWidth(String text) {
	        glyphLayout.setText(font, text);
	        return glyphLayout.width;
	    }
	}
