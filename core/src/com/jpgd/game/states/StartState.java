package com.jpgd.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jpgd.game.FeedDaSnek;

public class StartState extends State {

    private Stage stage;
    private Table table;

    /*
    Constructors
     */
    public StartState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        stage = new Stage();
        table = new Table();
    }


    /*
    Overridden methods
     */
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
        super.dispose();
    }
}
