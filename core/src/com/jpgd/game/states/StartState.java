package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;

public class StartState extends State {

    private Stage stage;
    private Table table;
    Image background;
    Image button;

    /*
    Constructors
     */
    public StartState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center);
        background = new Image(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("Start_BG"));
        button = new Image(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("PlayButton_Up"));
        table.add(button);
        stage.addActor(background);
        stage.addActor(table);

    }

    /*
    Other Methods
     */
    public void handleInput(float delta) {
        // TODO Remove bug where if user presses direction key, snake begins moving immediately. Maybe use button release
        if((Gdx.input.isTouched()) || (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
            feedDaSnek.setScreen(new PlayState(feedDaSnek));
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
        handleInput(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
        orthographicCamera.viewportWidth = width;
        orthographicCamera.viewportHeight = height;
        orthographicCamera.update();
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
