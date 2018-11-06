package com.jpgd.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpgd.game.states.*;
import com.jpgd.game.utilities.GameAssetManager;
import com.jpgd.game.utilities.ScoreManager;

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
	private ScoreManager scoreManager;
	private OrthographicCamera orthographicCamera;
	private Viewport viewport;
	private Preferences preferences;
	private boolean musicOn, sfxOn;
	private float musicVolume, sfxVolume;

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

	public Preferences getPreferences() {
		return preferences;
	}

	public boolean isMusicOn() {
		return musicOn;
	}

	public boolean isSfxOn() {
		return sfxOn;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public float getSfxVolume() {
		return sfxVolume;
	}

	public ScoreManager getScoreManager() {
		return scoreManager;
	}

	/*
    Other Methods
    */
	public void updateAudio() {
		musicOn = this.getPreferences().getBoolean("musicOn", true);
		sfxOn = this.getPreferences().getBoolean("sfxOn", true);
		musicVolume = this.getPreferences().getFloat("musicVolume", 1f);
		sfxVolume = this.getPreferences().getFloat("sfxVolume", 1f);

		gameAssetManager.getMusic().setVolume(musicVolume);
		if (musicOn == true) {
			gameAssetManager.getMusic().play();
		} else {
			gameAssetManager.getMusic().stop();
		}
	}

	/*
    Overridden methods from "Game" class
    */
	// The "Create()" method replaces a constructor for this class...
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

		gameAssetManager = new GameAssetManager();
		gameAssetManager.done();
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		orthographicCamera.update();
		preferences = Gdx.app.getPreferences("Preferences");
		scoreManager = new ScoreManager(this);
		scoreManager.loadScoreData();


		//this.setScreen(new PlayState(this));
		updateAudio();

		// TODO Consider adding some screen management so new screens are not always being created
		this.setScreen(new StartState(this));
	}

	@Override
	public void render () {
		orthographicCamera.update();
		spriteBatch.setProjectionMatrix(orthographicCamera.combined);
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
// TODO Every Screen : Ensure that if player exits / pauses, data is saved safely
// TODO Every Screen : If screen resumes, make sure that data is loaded correctly
// TODO Every Screen : Overhaul the asset loading so that asset loader is used in each screen, rather than passing copies of assets