package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpgd.game.FeedDaSnek;

public class Hud implements Disposable {
    /*
    Variables
     */
    private SpriteBatch spriteBatch;
    private Viewport hudViewport;
    private Stage hudStage;
    private Table table;
    private Label.LabelStyle labelStyle;
    private Label scoreText, scoreValue;


    /*
    Constructors
     */
    public Hud(FeedDaSnek feedDaSnek) {
        this.spriteBatch = feedDaSnek.getSpriteBatch();
        // TODO Revisit the viewport later
        hudViewport = new ExtendViewport(FeedDaSnek.V_WIDTH, FeedDaSnek.V_HEIGHT, feedDaSnek.getOrthographicCamera());
        hudStage = new Stage(hudViewport, spriteBatch);
        labelStyle = new Label.LabelStyle(feedDaSnek.getGameAssetManager().getFont(), Color.WHITE);

        scoreText = new Label("Score: ", labelStyle);
        scoreValue = new Label("", labelStyle);

        table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(scoreText).expandX().padTop(5);
        table.add(scoreValue).expandX().padTop(5);

        hudStage.addActor(table);
    }

    /*
    Getters
     */
    public Stage getHudStage() {
        return hudStage;
    }

    /*
    Setters
     */

    /*
    Other Methods
     */
    public void updateScoreValueLabel(int score) {
        scoreValue.setText(String.format("%d", score));
    }

    @Override
    public void dispose() {

    }
}
