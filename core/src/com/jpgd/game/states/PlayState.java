package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Direction;
import com.jpgd.game.objects.Food;
import com.jpgd.game.objects.GameOver;
import com.jpgd.game.objects.Obstacle;
import com.jpgd.game.objects.Snake;
import com.jpgd.game.objects.Tile;

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
        initializeFoods();

        obstacles = new ArrayList<Obstacle>();
        initializeObstacles();

        initializePositions();

        assignSounds();

        dt_total = 0;
        score = 0;
    }


    /*
    Other methods
     */
    public void update(float delta) {

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
                generateTilePosition(food);
            }
        }

        for(Obstacle obstacle : obstacles) {
            if(snake.getBodyPoints().get(0).epsilonEquals(obstacle.getPosition())) {
                // Shares position, snake has eaten food item
                score = score + obstacle.getValue();
                snake.shrink();
                generateTilePosition(obstacle);

                if(snake.getBodyPoints().size() < 3) {
                    snake.getDeathSounds().get(randomizer.nextInt(snake.getDeathSounds().size())).play();
                    checkForNewHighScore();
                    feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_3).setScoreNumLabels(score));
                }
            }
        }

        updateFoodsAndObstacles();
        // Move the snake
        snake.move(delta);
    }

    public void processInput(int keycode) {
        // TODO Remove bug where if User is quick enough, they can direct the snake back into itself. Add check for multiple blocks in a row maybe?
        if(keycode == Input.Keys.LEFT) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.LEFT);
            }
        }

        if(keycode == Input.Keys.RIGHT) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.RIGHT);
            }
        }

        if(keycode == Input.Keys.UP) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.UP);
            }
        }

        if(keycode == Input.Keys.DOWN) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                snake.changeDirection(Direction.DOWN);
            }
        }
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

    public void initializeFoods() {
        foods.clear();
        foods.add(new Food(gameAssetManager.getTextureAtlas()));
    }

    public void initializeObstacles() {
        obstacles.clear();
        obstacles.add(new Obstacle(gameAssetManager.getTextureAtlas()));
    }

    public void initializePositions() {
        snake.initializeSnake();
        for (Food food : foods) {
            generateTilePosition(food);
        }
        for (Obstacle obstacle : obstacles) {
            generateTilePosition(obstacle);
        }
    }

    public void assignSounds() {
        snake.setDeathSounds(gameAssetManager.getDeathSounds());
        snake.setEatSounds(gameAssetManager.getEatSounds());
        snake.setSickSounds(gameAssetManager.getSickSounds());
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

    public void updateFoodsAndObstacles() {
        float increment = (float) Math.floor(score / 100);
        if (score != 0) {
            // Get the nearest lower increment
            if( ((foods.size() - 1) < increment) && ((obstacles.size() - 1) < increment) ) { // If the size of the arraylists (minus 1) is less than or equal to the increment, add a Food object
                foods.add(new Food(gameAssetManager.getTextureAtlas()));
                generateTilePosition(foods.get(foods.size() - 1));
                obstacles.add(new Obstacle(gameAssetManager.getTextureAtlas()));
                generateTilePosition(obstacles.get(obstacles.size() - 1));
            } else if( ((foods.size() - 1) > increment) && ((obstacles.size() - 1) > increment) ) {  // if the size is greater than the increment then remove an entry
                foods.remove(foods.size() - 1);
                obstacles.remove(obstacles.size() - 1);
            } else {
                // Do nothing, size of the arraylist is the same as the increment
            }
        } else {
            // Check if score was reduced to zero by checking size of ArrayLists, if yes, ensure ArrayLists are only size: 1
            if ( ((foods.size() - 1) > increment) && (obstacles.size() - 1) > increment) {
                foods.remove(foods.size() - 1);
                obstacles.remove(obstacles.size() - 1);
            }
        }
    }

    private void generateTilePosition(Tile tile) {
        Vector2 tempVec;
        boolean duplicatePositionFlag;
        do {
            duplicatePositionFlag = false;
            tempVec = randomTilePosition(tile.getWidth(), tile.getHeight());

            for (Food food : foods) {
                if (tempVec.epsilonEquals(food.getPosition())) {
                    duplicatePositionFlag = true;
                    break;
                }
            }

            for (Obstacle obstacle : obstacles) {
                if (tempVec.epsilonEquals(obstacle.getPosition())) {
                    duplicatePositionFlag = true;
                    break;
                }
            }

            for (int snakeIter = 0; snakeIter < snake.getBodyPoints().size(); snakeIter++) {
                if (tempVec.epsilonEquals(snake.getBodyPoints().get(snakeIter))) {
                    duplicatePositionFlag = true;
                    break;
                }
            }
        } while(duplicatePositionFlag == true);
        tile.setPosition(tempVec);
    }

    private Vector2 randomTilePosition(float width, float height) {
        float screenX = Gdx.app.getGraphics().getWidth();
        float screenY = Gdx.app.getGraphics().getHeight();

        float tempX = randomizer.nextInt((int)(screenX / width));
        float tempY = randomizer.nextInt((int)(screenY / height));

        return new Vector2(tempX * width, tempY * height);
    }


    /*
    Overridden methods
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown (int keycode) {
                processInput(keycode);
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0.2f, 0.1f, 0.016f, 1);
        // W3 schools "SaddleBrown" colour
        Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //processInput(delta);
        update(delta);
        draw(delta);
    }

    // TODO Perform more research on sizing and scaling for other screens etc.
    @Override
    public void resize(int width, int height) {
        orthographicCamera.viewportWidth = width;
        orthographicCamera.viewportHeight = height;
        orthographicCamera.update();
    }

    // TODO Add pause logic
    @Override
    public void pause() {

    }

    // TODO Add resume logic
    @Override
    public void resume() {

    }

    // TODO Add hide screen logic
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
