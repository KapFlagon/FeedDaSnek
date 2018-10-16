package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.GameOver;

public class EndState extends State {

    /*
    Variables
     */
    private Stage stage;
    private Table table;
    private Label gameOverLabel;
    private Label currentScoreTextLabel, currentScoreNumLabel;
    private Label highScoreTextLabel, highScoreNumLabel;
    private String gameOverReason;
    private Image button;



    /*
    Constructors
     */
    public EndState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        button = new Image(feedDaSnek.getGameAssetManager().getTextureAtlas().findRegion("PlayButton_Up"));

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center);
        table.setDebug(true);

        currentScoreTextLabel = new Label("Score: ", gameAssetManager.getLabelStyle());
        currentScoreNumLabel = new Label("", gameAssetManager.getLabelStyle());

        highScoreTextLabel = new Label("High Score: ", gameAssetManager.getLabelStyle());
        highScoreNumLabel = new Label("", gameAssetManager.getLabelStyle());

        gameOverLabel = new Label("Game Over\n", gameAssetManager.getLabelStyle());
        gameOverReason = "";
    }

    /*
    Getters
     */

    /*
    Setters
     */
    // returns EndState object for method chaining
    public EndState setGameOverReason(GameOver gameOver) {
        this.gameOverReason = gameOver.getReason();
        gameOverLabel.setText("Game Over\n" + gameOverReason);
        buildTable();
        return this;
    }

    // returns EndState object for method chaining
    public EndState setScoreNumLabels(int score) {
        currentScoreNumLabel.setText(String.format("%06d", score));
        highScoreNumLabel.setText(String.format("%06d", feedDaSnek.getPreferences().getInteger("highscore", 0)));
        buildTable();
        return this;
    }

    public void buildTable() {
        table.clearChildren();
        table.add(gameOverLabel);
        table.row();
        table.add(currentScoreTextLabel);
        table.add(currentScoreNumLabel);
        table.row();
        table.add(highScoreTextLabel);
        table.add(highScoreNumLabel);
        table.addActor(button);
        stage.addActor(table);

    }

    public void handleInput(float delta) {
        if((Gdx.input.isTouched()) || (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
            feedDaSnek.setScreen(new PlayState(feedDaSnek));
        }
    }


    /*
    Overridden Methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // W3 schools "SaddleBrown" colour
        Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput(delta);
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

    @Override
    public void dispose() {
        super.dispose();
    }
}
