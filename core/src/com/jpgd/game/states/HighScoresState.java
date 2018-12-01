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
import com.jpgd.game.objects.Score;
import java.util.ArrayList;

public class HighScoresState extends State {

    private Window window;
    private TextButton backButton;
    private ArrayList<Score> listOfHighScores;

    /*
    Constructors
     */
    public HighScoresState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);
        window = new Window("High Scores", feedDaSnek.getGameAssetManager().getSkin());
        window.setSkin(feedDaSnek.getGameAssetManager().getSkin());
        //window.setFillParent(true);
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
        listOfHighScores = new ArrayList<Score>();
    }

    /*
    Other methods
     */
    public void buildWindow() {
        stateStage.clear();
        stateStage.addActor(background);
        window.layout();
        listOfHighScores = feedDaSnek.getScoreManager().getHighScores().getListOfHighScores();

        for(int listIter = 0; listIter < listOfHighScores.size(); listIter++) {
            window.add(listOfHighScores.get(listIter).getName()).pad(5, 5, 5, 10).align(Align.left);
            window.add(Integer.toString(listOfHighScores.get(listIter).getScore())).pad(10,5,5,5).align(Align.left);
            window.row();
        }

        window.add(backButton).pad(5);

        // Packs the window to the size of the child elements
        window.pack();

        window.setPosition((getExtendViewport().getMinWorldWidth() - window.getWidth()) / 2 , (getExtendViewport().getMinWorldHeight() - window.getHeight()) / 2);

        stateStage.addActor(window);
    }

    @Override
    public void show() {
        super.show();
        buildWindow();
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
