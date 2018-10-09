package com.jpgd.game.states;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.utilities.GameAssetManager;

public abstract class State implements Screen {

    /*
    Variables
     */
    protected SpriteBatch spriteBatch;
    protected GameAssetManager gameAssetManager;
    protected OrthographicCamera orthographicCamera;
    protected Viewport viewport;


    /*
    Constructors
     */
    public State(FeedDaSnek feedDaSnek) {
        this.spriteBatch = feedDaSnek.getSpriteBatch();
        this.gameAssetManager = feedDaSnek.getGameAssetManager();
        this.orthographicCamera = feedDaSnek.getOrthographicCamera();

    }


    /*
    Getters
     */
    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
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
    public void dispose() {
        spriteBatch.dispose();
    }

}
