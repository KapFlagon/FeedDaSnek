package com.jpgd.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jpgd.game.utilities.GameAssetManager;

public class FeedDaSnek extends ApplicationAdapter {

	/*
	Final Static Variables
	 */
	public final static String TITLE = "Feed Da Snek";
	public final static int V_WIDTH = 480;
	public final static int V_HEIGHT = 800;

	/*
	Variables
	 */
	private SpriteBatch spriteBatch;
	private GameAssetManager gameAssetManager;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		//gameAssetManager = new GameAssetManager();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch .begin();
		spriteBatch .end();
	}
	
	@Override
	public void dispose () {
		spriteBatch .dispose();
	}
}
