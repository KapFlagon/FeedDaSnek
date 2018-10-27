package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private TextButton playButton, highScoreButton;


    /*
    Constructors
     */
    public EndState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        playButton = new TextButton("Play again", gameAssetManager.getSkin());
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

        highScoreButton = new TextButton("High Scores", gameAssetManager.getSkin());
        highScoreButton.addListener(new InputListener() {
            // TODO Update High Score button logic
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center);
        //table.setDebug(true);

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
        table.add(gameOverLabel).align(Align.left);
        table.row();
        table.add(currentScoreTextLabel).align(Align.left);
        table.add(currentScoreNumLabel).align(Align.left);
        table.row();
        table.add(highScoreTextLabel).align(Align.left);
        table.add(highScoreNumLabel).align(Align.left);

        table.row();
        table.add(playButton).align(Align.center);
        table.row();
        table.add(highScoreButton).align(Align.center);

        stage.addActor(table);


    }

    // TODO Add some sort of User input to tag name to high score
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
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        // W3 schools "SaddleBrown" colour
        Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //handleInput(delta);
        stage.draw();
    }

    // TODO Add resize logic
    @Override
    public void resize(int width, int height) {

    }

    // TODO Add pause logic
    @Override
    public void pause() {

    }

    // TODO Add resume logic
    @Override
    public void resume() {

    }

    // TODO Add hide logic
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
