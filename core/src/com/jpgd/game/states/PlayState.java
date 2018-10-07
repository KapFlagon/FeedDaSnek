package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Tile;

public class PlayState extends State{

    /*
    Variables
     */
    private Tile[][] playGrid;
    private float rows, cols;

    /*
    Constructors
     */
    public PlayState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        rows = FeedDaSnek.V_HEIGHT / 16;
        cols = FeedDaSnek.V_WIDTH / 16;
        playGrid = new Tile[(int)rows][(int)cols];
        initGrid();
    }


    /*
    Other methods
     */
    public void initGrid() {
        for(int rowIter = 0; rowIter < rows; rowIter++) {
            for(int colIter = 0; colIter < cols; colIter++) {

            }
        }
    }


    /*
    Overridden methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.17f, 0.088f, 0.006f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
