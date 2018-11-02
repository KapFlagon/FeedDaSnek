package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;

public class HighScoresState extends State {

    private Stage stage;
    private Window window;
    private TextButton backButton;

    // TODO Create "High Score" table logic etc

    public HighScoresState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        stage = new Stage();
        window = new Window("High Scores", feedDaSnek.getGameAssetManager().getSkin());
        window.setSkin(feedDaSnek.getGameAssetManager().getSkin());
        window.setFillParent(true);
        window.align(Align.center);

        backButton = new TextButton("Back", gameAssetManager.getSkin());
        backButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                getFeedDaSnek().setScreen(new StartState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    /*
    Other methods
     */
    public void buildWindow() {
        window.row();
        window.add(backButton);

        // Packs the window to the size of the child elements
        window.pack();
        window.setPosition((Gdx.graphics.getWidth() - window.getWidth()) / 2 , (Gdx.graphics.getHeight() - window.getHeight()) / 2);

        stage.addActor(window);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        buildWindow();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
}
