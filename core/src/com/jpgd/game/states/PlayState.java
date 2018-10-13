package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Direction;
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

        foods = new ArrayList<Food>();
        foods.add(new Food(gameAssetManager.getTextureAtlas()));

        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(gameAssetManager.getTextureAtlas()));

        initializePositions();

    }


    /*
    Other methods
     */
    public void update(float delta) {
        spriteBatch.begin();

        snake.render(spriteBatch);

        for(Food food : foods) {
            food.render(spriteBatch);
        }

        for(Obstacle obstacle : obstacles) {
            obstacle.render(spriteBatch);
        }

        spriteBatch.end();
    }

    public void processInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.LEFT);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.RIGHT);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.UP);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.DOWN);
            }
        }
        snake.move();
    }

    public void initializePositions() {
        snake.initializeSnake(randomizer);
        refreshTilePositions();

    }

    public void refreshTilePositions() {
        boolean foodsFlag = false;
        boolean obstaclesFlag = false;

        for(Food food : foods) {
            do {
                food.randomizePosition(randomizer);
                for(int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                    if ((food.getPosition().x == snake.getBodyPoints().get(snakeIter).x) || (food.getPosition().y == snake.getBodyPoints().get(snakeIter).y)) {
                        foodsFlag = true;
                    } else {
                        foodsFlag = false;
                    }
                }
            } while(foodsFlag == true);
        }

        for(Obstacle obstacle : obstacles) {
            do {
                obstacle.randomizePosition(randomizer);

                for (int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                    if ((obstacle.getPosition().x == snake.getBodyPoints().get(snakeIter).x) || (obstacle.getPosition().y == snake.getBodyPoints().get(snakeIter).y)) {
                        obstaclesFlag = true;
                    } else {
                        obstaclesFlag = false;
                    }
                }
            } while(obstaclesFlag == true);
        }
    }


    /*
    Overridden methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        processInput(delta);
        Gdx.gl.glClearColor(0.3f, 0.09f, 0.006f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
