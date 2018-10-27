package com.jpgd.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jpgd.game.FeedDaSnek;

public class OptionsState extends State {

    Stage stage;
    Table table;
    TextButton backButton;
    Label label_volumeSfx, label_volumeMusic;
    CheckBox checkBoxMusic, checkBoxSfx;
    Slider sliderMusic, sliderSfx;


    // TODO Create variables for SFX volume, SFX enabled/disabled, music volume, music enabled/disabled etc.

    public OptionsState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        stage = new Stage();
        table = new Table();

        backButton = new TextButton("Back", gameAssetManager.getSkin());

        checkBoxMusic = new CheckBox("Music On/Off", feedDaSnek.getGameAssetManager().getSkin());
        checkBoxSfx = new CheckBox("SFX On/Off", feedDaSnek.getGameAssetManager().getSkin());

        label_volumeMusic = new Label("Music volume:", gameAssetManager.getLabelStyle());
        label_volumeSfx = new Label("SFX volume:", gameAssetManager.getLabelStyle());

        sliderMusic = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
        sliderSfx = new Slider(0, 100, 1, false, feedDaSnek.getGameAssetManager().getSkin());
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
}
