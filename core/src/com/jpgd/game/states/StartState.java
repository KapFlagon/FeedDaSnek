package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;

public class StartState extends State {

    private Table table;
    private Image title;
    private TextButton playButton, optionsButton, highScoreButton;
    // TODO Update table so that it uses the viewport min sizes for the button positions

    /*
    Constructors
     */
    public StartState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center);

        title = new Image(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("FeedDaSnek_Title"));
        playButton = new TextButton("Play", gameAssetManager.getSkin());
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                getFeedDaSnek().setScreen(new PlayState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        optionsButton = new TextButton("Options", gameAssetManager.getSkin());
        optionsButton.addListener(new InputListener() {
            // TODO Update Options button logic
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                getFeedDaSnek().setScreen(new OptionsState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        highScoreButton = new TextButton("High Scores", gameAssetManager.getSkin());
        highScoreButton.addListener(new InputListener() {
            // TODO Update High Score button logic
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                getFeedDaSnek().setScreen(new HighScoresState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        table.add(playButton);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(highScoreButton);

        stateStage.addActor(title);
        stateStage.addActor(table);

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
        super.show();
    }

    @Override
    public void render(float delta) {
        //handleInput(delta);
        super.render(delta);
        stateStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
