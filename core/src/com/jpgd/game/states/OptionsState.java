package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;

public class OptionsState extends State {

    Stage stage;
    Window window;
    TextButton submitButton, backButton;
    Label label_volumeSfx, label_volumeMusic;
    CheckBox checkBoxMusic, checkBoxSfx;
    Slider sliderMusic, sliderSfx;


    // TODO Create variables for SFX volume, SFX enabled/disabled, music volume, music enabled/disabled etc.

    public OptionsState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        stage = new Stage();

        window = new Window("Options", feedDaSnek.getGameAssetManager().getSkin());
        window.setSkin(feedDaSnek.getGameAssetManager().getSkin());
        //window.setFillParent(true);
        window.align(Align.center);

        submitButton = new TextButton("Submit", gameAssetManager.getSkin());
        submitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO add logic to submit changes and save them

                getFeedDaSnek().getPreferences().putBoolean("musicOn", checkBoxMusic.isChecked());
                getFeedDaSnek().getPreferences().putBoolean("sfxOn", checkBoxSfx.isChecked());
                getFeedDaSnek().getPreferences().putFloat("musicVolume", (sliderMusic.getValue() / 100));
                getFeedDaSnek().getPreferences().putFloat("sfxVolume", (sliderSfx.getValue() / 100));
                getFeedDaSnek().getPreferences().flush();
                getFeedDaSnek().updateAudio();
                getFeedDaSnek().setScreen(new StartState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

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

        checkBoxMusic = new CheckBox("Music On/Off", feedDaSnek.getGameAssetManager().getSkin());
        checkBoxMusic.setChecked(feedDaSnek.getPreferences().getBoolean("musicOn", true));
        checkBoxSfx = new CheckBox("SFX On/Off", feedDaSnek.getGameAssetManager().getSkin());
        checkBoxSfx.setChecked(feedDaSnek.getPreferences().getBoolean("sfxOn", true));

        label_volumeMusic = new Label("Music volume:", gameAssetManager.getLabelStyle());
        label_volumeSfx = new Label("SFX volume:", gameAssetManager.getLabelStyle());

        sliderMusic = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
        sliderMusic.setValue(feedDaSnek.getPreferences().getFloat("musicVolume", 1f) * 100);
        sliderSfx = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
        sliderSfx.setValue(feedDaSnek.getPreferences().getFloat("sfxVolume",1f) * 100);
    }

    /*
    Other Methods
     */
    public void buildTable() {
        window.layout();
        window.add(checkBoxMusic).pad(5);
        window.add(checkBoxSfx).pad(5);
        window.row();
        window.add(label_volumeMusic).pad(5);
        window.add(sliderMusic).pad(5);
        window.row();
        window.add(label_volumeSfx).pad(5);
        window.add(sliderSfx).pad(5);
        window.row();
        window.add(submitButton).pad(5);
        window.add(backButton).pad(5);

        // Packs the window to the size of the child elements
        window.pack();
        window.setPosition((Gdx.graphics.getWidth() - window.getWidth()) / 2 , (Gdx.graphics.getHeight() - window.getHeight()) / 2);

        stage.addActor(window);
    }

    /*
    Overidden methods
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        buildTable();
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
