package com.jpgd.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jpgd.game.states.PlayState;
import com.jpgd.game.utilities.GameAssetManager;

public class FeedDaSnek extends Game {

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
	private OrthographicCamera orthographicCamera;
	private float speed;

	/*
	Getters
	 */

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public GameAssetManager getGameAssetManager() {
		return gameAssetManager;
	}

	public OrthographicCamera getOrthographicCamera() {
		return orthographicCamera;
	}

	/*
            Overridden methods from "Game" class
             */
	// The "Create()" method replaces a constructor for this class...
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		gameAssetManager = new GameAssetManager();
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		speed = 1;

		this.setScreen(new PlayState(this));
	}

	@Override
	public void render () {
		super.render();		// Call super class "Game"'s render method to use screens
	}
	
	@Override
	public void dispose () {
		spriteBatch .dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
