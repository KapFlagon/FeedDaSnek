package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.utilities.AudioManager;
import com.jpgd.game.utilities.GameAssetManager;

public abstract class State implements Screen {

    /*
    Variables
     */
    protected FeedDaSnek feedDaSnek;
    protected SpriteBatch spriteBatch;
    protected GameAssetManager gameAssetManager;
    protected AudioManager audioManager;
    protected OrthographicCamera orthographicCamera;
    protected ExtendViewport extendViewport;
    protected String textureAtlasPath;
    protected TextureAtlas textureAtlas;
    protected Image background;
    protected Stage stateStage;


    /*
    Constructors
     */
    public State(FeedDaSnek feedDaSnek) {
        this.feedDaSnek = feedDaSnek;
        this.spriteBatch = feedDaSnek.getSpriteBatch();
        this.gameAssetManager = feedDaSnek.getGameAssetManager();
        this.audioManager = feedDaSnek.getAudioManager();
        this.orthographicCamera = feedDaSnek.getOrthographicCamera();
        this.extendViewport = feedDaSnek.getExtendViewport();

        this.textureAtlasPath = gameAssetManager.getTextureAtlasPath();
        this.textureAtlas = gameAssetManager.getAssetManager().get(textureAtlasPath, TextureAtlas.class);

        this.background = new Image(textureAtlas.findRegion("FeedDaSnek_Background"));
        this.stateStage = new Stage(this.extendViewport);

        stateStage.addActor(background);
    }


    /*
    Getters
     */
    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
    public FeedDaSnek getFeedDaSnek() {
        return feedDaSnek;
    }
    public Stage getStateStage() {
        return stateStage;
    }

    public ExtendViewport getExtendViewport() {
        return extendViewport;
    }

    /*
        Setters
         */
    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }


    /*
    Other Methods
     */


    /*
    Overridden methods
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stateStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateStage.act(delta);
        stateStage.draw();
    }
    @Override
    public void resize(int width, int height) {
        this.stateStage.getViewport().update(width, height, true);
        //this.orthographicCamera.viewportWidth = width;
        //this.orthographicCamera.viewportHeight = height;
        //this.orthographicCamera.update();
    }
    @Override
    public void dispose() {
        spriteBatch.dispose();
        stateStage.dispose();
        gameAssetManager.getAssetManager().unload(textureAtlasPath);
    }

}