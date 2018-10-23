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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;

public class StartState extends State {

    private Stage stage;
    private Table table;
    Image background;
    private ImageButton playButton;

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

        playButton = new ImageButton(gameAssetManager.getSkin());
        playButton.getStyle().imageUp = new TextureRegionDrawable(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("PlayButton_Up"));
        playButton.getStyle().imageDown = new TextureRegionDrawable(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("PlayButton_Down"));
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

        table.add(playButton);

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
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        //handleInput(delta);
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
