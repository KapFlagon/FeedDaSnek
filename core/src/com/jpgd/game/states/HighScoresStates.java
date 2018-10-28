package com.jpgd.game.states;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jpgd.game.FeedDaSnek;

public class HighScoresStates extends State implements Json.Serializable {

    private Stage stage;
    private Window window;

    // TODO Create "High Score" table logic etc

    public HighScoresStates(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        stage = new Stage();
        window = new Window("High Scores", feedDaSnek.getGameAssetManager().getSkin());
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

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
