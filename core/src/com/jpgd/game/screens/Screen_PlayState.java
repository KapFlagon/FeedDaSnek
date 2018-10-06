package com.jpgd.game.screens;

import com.badlogic.gdx.Screen;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Tile;

public class Screen_PlayState implements Screen {

    private Tile[][] playGrid;
    private float rows, cols;

    /*
    Constructors
     */
    public Screen_PlayState() {
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
