package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Direction;
import com.jpgd.game.objects.Food;
import com.jpgd.game.objects.GameOver;
import com.jpgd.game.objects.Obstacle;
import com.jpgd.game.objects.Snake;
import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State{

    /*
    Variables
     */
    private float dt_total;
    private Random randomizer;
    private Snake snake;
    private ArrayList<Food> foods;
    private ArrayList<Obstacle> obstacles;
    private int score;


    /*
    Constructors
     */
    public PlayState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        randomizer = new Random();

        snake = new Snake(gameAssetManager.getTextureAtlas());

        foods = new ArrayList<Food>();
        foods.add(new Food(gameAssetManager.getTextureAtlas()));

        obstacles = new ArrayList<Obstacle>();
        obstacles.add(new Obstacle(gameAssetManager.getTextureAtlas()));

        initializePositions();
        assignSounds();
        dt_total = 0;
        score = 0;

    }


    /*
    Other methods
     */
    public void update(float delta) {

        boolean foodsFlag = false;
        boolean obstaclesFlag = false;


        // Check if snake head moves out of bounds
        if((snake.getBodyPoints().get(0).x < 0) || (snake.getBodyPoints().get(0).x > Gdx.app.getGraphics().getWidth()) || (snake.getBodyPoints().get(0).y < 0) || snake.getBodyPoints().get(0).y > Gdx.app.getGraphics().getHeight()) {
           // Snake has extended outside of boundaries of screen, game over
            snake.getDeathSounds().get(randomizer.nextInt(snake.getDeathSounds().size())).play();
            checkForNewHighScore();
            feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_1).setScoreNumLabels(score));
        }

        // Check if snake head touches any other part of the snakes
        for(int bodyPointsIter = 1; bodyPointsIter < snake.getBodyPoints().size(); bodyPointsIter++) {
            if(snake.getBodyPoints().get(0).epsilonEquals(snake.getBodyPoints().get(bodyPointsIter))) {
                snake.getDeathSounds().get(randomizer.nextInt(snake.getDeathSounds().size())).play();
                checkForNewHighScore();
                feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_2).setScoreNumLabels(score));
            }
        }

        // Check if snake has eaten
        for(Food food : foods) {
            if(snake.getBodyPoints().get(0).epsilonEquals(food.getPosition())) {
                // Shares position, snake has eaten food item
                score = score + food.getValue();
                snake.grow();
                do {
                    food.randomizePosition(randomizer);
                    for(int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                        if (food.getPosition().epsilonEquals(snake.getBodyPoints().get(snakeIter))) {
                            foodsFlag = true;
                            continue;
                        } else {
                            foodsFlag = false;
                        }
                    }
                } while(foodsFlag == true);
            }
        }

        for(Obstacle obstacle : obstacles) {
            if(snake.getBodyPoints().get(0).epsilonEquals(obstacle.getPosition())) {
                // Shares position, snake has eaten food item
                score = score + obstacle.getValue();
                snake.shrink();
                do {
                    obstacle.randomizePosition(randomizer);

                    for (int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                        if (obstacle.getPosition().epsilonEquals(snake.getBodyPoints().get(snakeIter))) {
                            obstaclesFlag = true;
                            continue;
                        } else {
                            obstaclesFlag = false;
                        }
                    }
                    for(Food food : foods) {
                        if(obstacle.getPosition().epsilonEquals(food.getPosition())) {
                            obstaclesFlag = true;
                            continue;
                        } else {
                            obstaclesFlag = false;
                        }
                    }
                } while(obstaclesFlag == true);

                if(snake.getBodyPoints().size() < 3) {
                    snake.getDeathSounds().get(randomizer.nextInt(snake.getDeathSounds().size())).play();
                    checkForNewHighScore();
                    feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_3).setScoreNumLabels(score));
                }
            }
        }

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
        snake.move(delta);
    }

    public void draw(float delta) {
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

    public void initializePositions() {
        snake.initializeSnake();
        refreshTilePositions();
    }

    public void assignSounds() {
        System.out.println("Death Sounds length: " + gameAssetManager.getDeathSounds().size());
        System.out.println("Eat Sounds length: " + gameAssetManager.getEatSounds().size());
        System.out.println("Sick Sounds length: " + gameAssetManager.getSickSounds().size());
        snake.setDeathSounds(gameAssetManager.getDeathSounds());
        snake.setEatSounds(gameAssetManager.getEatSounds());
        snake.setSickSounds(gameAssetManager.getSickSounds());
    }

    public void refreshTilePositions() {
        boolean foodsFlag = false;
        boolean obstaclesFlag = false;

        for(Food food : foods) {
            do {
                food.randomizePosition(randomizer);
                for(int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                    if (food.getPosition().epsilonEquals(snake.getBodyPoints().get(snakeIter))) {
                        foodsFlag = true;
                        continue;
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
                    if (obstacle.getPosition().epsilonEquals(snake.getBodyPoints().get(snakeIter))) {
                        obstaclesFlag = true;
                        continue;
                    } else {
                        obstaclesFlag = false;
                    }
                }
                for(Food food : foods) {
                    if(obstacle.getPosition().epsilonEquals(food.getPosition())) {
                        obstaclesFlag = true;
                        continue;
                    } else {
                        obstaclesFlag = false;
                    }
                }
            } while(obstaclesFlag == true);
        }
    }

    public void checkForNewHighScore() {
        if (score < 0) {
            score = 0;
        }

        if (score > feedDaSnek.getPreferences().getInteger("highscore", 0)) {
            feedDaSnek.getPreferences().putInteger("highscore", score);
            feedDaSnek.getPreferences().flush();
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
        //Gdx.gl.glClearColor(0.2f, 0.1f, 0.016f, 1);
        // W3 schools "SaddleBrown" colour
        Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        processInput(delta);
        update(delta);
        draw(delta);
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
