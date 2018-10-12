package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Food;
import com.jpgd.game.objects.Obstacle;
import com.jpgd.game.objects.Snake;
import com.jpgd.game.objects.Tile;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State{

    /*
    Variables
     */
    private float rows, cols;

    private Random randomizer;
    private Snake snake;
    private ArrayList<Food> foods;
    private ArrayList<Obstacle> obstacles;


    /*
    Constructors
     */
    public PlayState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        randomizer = new Random();

        rows = FeedDaSnek.V_HEIGHT / 16;
        cols = FeedDaSnek.V_WIDTH / 16;

        snake = new Snake(gameAssetManager.getTextureAtlas());
        snake.initializeSnake(randomizer);
        foods = new ArrayList<Food>();
        foods.add(new Food(gameAssetManager.getTextureAtlas()));

        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(gameAssetManager.getTextureAtlas()));


    }


    /*
    Other methods
     */
    public void update(float delta) {
        spriteBatch.begin();

        snake.render(spriteBatch);

        for(Food food : foods) {
            // if(food.getPosition().x )
            /*
            if food x or food y shares a position with the existing snake or obstacle, randomize to another position
             */
            food.render(spriteBatch);
        }

        for(Obstacle obstacle : obstacles) {
            obstacle.render(spriteBatch);
        }

        spriteBatch.end();
    }

    public void processInput(float delta) {

    }


    /*
    Overridden methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.09f, 0.006f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        processInput(delta);
        update(delta);
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
