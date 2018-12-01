package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
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

import java.util.Random;

public class OptionsState extends State {

    /*
    Variables
     */
    private Window window;
    private TextButton submitButton, backButton;
    private Label label_volumeSfx, label_volumeMusic;
    private CheckBox checkBoxMusic, checkBoxSfx;
    private Slider sliderMusic, sliderSfx;


    /*
    Constructors
     */
    public OptionsState(final FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        window = new Window("Options", feedDaSnek.getGameAssetManager().getSkin());
        window.setSkin(feedDaSnek.getGameAssetManager().getSkin());
        //window.setFillParent(true);
        window.align(Align.center);

        submitButton = new TextButton("Submit", gameAssetManager.getSkin());
        submitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                commitSettingsToPrefs();
                getFeedDaSnek().setScreen(new StartState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        backButton = new TextButton("Cancel", gameAssetManager.getSkin());
        backButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Update AudioManager to reset with saved prefs
                getFeedDaSnek().readPrefs();
                getFeedDaSnek().setScreen(new StartState(getFeedDaSnek()));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        checkBoxMusic = new CheckBox("Music On/Off", feedDaSnek.getGameAssetManager().getSkin());
        checkBoxMusic.setChecked(feedDaSnek.getPreferences().getBoolean("musicOn", true));
        checkBoxMusic.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(checkBoxMusic.isChecked()) {
                    audioManager.setMusicOn(true);
                } else {
                    audioManager.setMusicOn(false);
                }
                return false;
            }
        });

        checkBoxSfx = new CheckBox("SFX On/Off", feedDaSnek.getGameAssetManager().getSkin());
        checkBoxSfx.setChecked(feedDaSnek.getPreferences().getBoolean("sfxOn", true));
        checkBoxSfx.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(checkBoxSfx.isChecked()) {
                    audioManager.setSfxOn(true);
                } else {
                    audioManager.setSfxOn(false);
                }
                return false;
            }
        });

        label_volumeMusic = new Label("Music volume:", gameAssetManager.getLabelStyle());
        label_volumeSfx = new Label("SFX volume:", gameAssetManager.getLabelStyle());

        sliderMusic = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
        sliderMusic.setValue(audioManager.getMusicVolume() * 100);
        sliderMusic.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(sliderMusic.isDragging()) {
                    audioManager.setMusicVolume(sliderMusic.getValue() / 100);
                }
                return false;
            }
        });

        sliderSfx = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
        sliderSfx.setValue(audioManager.getSfxVolume() * 100);
        sliderSfx.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(sliderSfx.isDragging()) {
                    // Stop current sound, and trigger it again (stops repeated overlaps)
                    if(audioManager.isSfxOn()) {
                        audioManager.getSickSounds().get(2).stop();
                        audioManager.getSickSounds().get(2).play(sliderSfx.getValue() / 100);
                    }
                }
                return false;
            }
        });

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
        window.setPosition((getExtendViewport().getMinWorldWidth() - window.getWidth()) / 2 , (getExtendViewport().getMinWorldHeight() - window.getHeight()) / 2);

        stateStage.addActor(window);
    }

    private void commitSettingsToPrefs() {
        getFeedDaSnek().getPreferences().putBoolean("musicOn", checkBoxMusic.isChecked());
        getFeedDaSnek().getPreferences().putBoolean("sfxOn", checkBoxSfx.isChecked());
        getFeedDaSnek().getPreferences().putFloat("musicVolume", (sliderMusic.getValue() / 100));
        getFeedDaSnek().getPreferences().putFloat("sfxVolume", (sliderSfx.getValue() / 100));
        getFeedDaSnek().getPreferences().flush();
        // Update AudioManager to commit latest prefs
        getFeedDaSnek().readPrefs();
    }

    /*
    Overidden methods
     */
    @Override
    public void show() {
        super.show();
        buildTable();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
