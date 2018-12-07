package com.jpgd.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.Direction;
import com.jpgd.game.objects.Food;
import com.jpgd.game.objects.GameOver;
import com.jpgd.game.objects.Hud;
import com.jpgd.game.objects.Obstacle;
import com.jpgd.game.objects.Snake;
import com.jpgd.game.objects.Tile;
import com.jpgd.game.utilities.InputHandler;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State{

    /*
    Variables
     */
    private Stage dialogStage;
    private float dt_total;
    private Random randomizer;
    private Snake snake;
    private ArrayList<Food> foods;
    private ArrayList<Obstacle> obstacles;
    private int score;
    private Dialog dialog;
    private String playerName;
    private TextField playerNameField;
    // TODO Consider adding a dead zone to top of play area for score data (KB suggestion)
    private Hud hud;
    private InputHandler inputHandler;

    /*
    Constructors
     */
    public PlayState(FeedDaSnek feedDaSnek) {
        super(feedDaSnek);

        dialogStage = new Stage();
        randomizer = new Random();

        snake = new Snake(textureAtlas);
        //snake.setSpeed(0.085f);
        snake.setSpeed(0.15f);

        foods = new ArrayList<Food>();
        obstacles = new ArrayList<Obstacle>();

        assignSounds();

        dt_total = 0;

        resetGame();
        playerNameField = new TextField("", feedDaSnek.getGameAssetManager().getSkin());
        playerNameField.setMaxLength(10);

        playerNameField.setText(feedDaSnek.getPreferences().getString("playername", ""));
        hud = new Hud(feedDaSnek);
        inputHandler = new InputHandler(snake);
    }


    /*
    Other methods
     */
    public void update(float delta) {

        // Check if snake head moves out of bounds
        if((snake.getBodyPoints().get(0).x < 0) || (snake.getBodyPoints().get(0).x >= getExtendViewport().getMinWorldWidth()) || (snake.getBodyPoints().get(0).y < 0) || snake.getBodyPoints().get(0).y >= getExtendViewport().getMinWorldHeight()) {
            // Snake has extended outside of boundaries of screen, game over
            if(snake.getSnakeCanMove() == true) {
                snake.die(feedDaSnek.getAudioManager());
                //feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_1).setScoreNumLabels(score));
                updateEndGameDialog(GameOver.GO_1);
            }
        }

        // Check if snake head touches any other part of the snakes
        for(int bodyPointsIter = 1; bodyPointsIter < snake.getBodyPoints().size(); bodyPointsIter++) {
            if(snake.getBodyPoints().get(0).epsilonEquals(snake.getBodyPoints().get(bodyPointsIter))) {
                if(snake.getSnakeCanMove() == true) {
                    snake.die(feedDaSnek.getAudioManager());
                    //feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_2).setScoreNumLabels(score));
                    updateEndGameDialog(GameOver.GO_2);
                }
            }
        }

        // Check if snake has eaten
        for(Food food : foods) {
            if(snake.getBodyPoints().get(0).epsilonEquals(food.getPosition())) {
                // Shares position, snake has eaten food item
                score = score + food.getValue();
                snake.grow(feedDaSnek.getAudioManager());
                generateTilePosition(food);
            }
        }

        for(Obstacle obstacle : obstacles) {
            if(snake.getBodyPoints().get(0).epsilonEquals(obstacle.getPosition())) {
                // Shares position, snake has eaten food item
                score = score + obstacle.getValue();
                snake.shrink(feedDaSnek.getAudioManager());
                generateTilePosition(obstacle);

                if(snake.getBodyPoints().size() < 3) {
                    if(snake.getSnakeCanMove() == true) {
                        snake.die(feedDaSnek.getAudioManager());
                        //feedDaSnek.setScreen(new EndState(feedDaSnek).setGameOverReason(GameOver.GO_3).setScoreNumLabels(score));
                        updateEndGameDialog(GameOver.GO_3);
                    }
                }
            }
        }

        updateFoodsAndObstacles();
        // Move the snake
        if(snake.getSnakeCanMove() == true) {
            snake.move(delta);
        }
        hud.updateScoreValueLabel(score);
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
        hud.getHudStage().draw();
    }

    public void initializeFoods() {
        foods.clear();
        foods.add(new Food(textureAtlas));
    }

    public void initializeObstacles() {
        obstacles.clear();
        obstacles.add(new Obstacle(textureAtlas));
    }

    public void initializePositions() {
        snake.initializeSnake(getExtendViewport().getMinWorldWidth(), getExtendViewport().getMinWorldHeight());
        for (Food food : foods) {
            generateTilePosition(food);
        }
        for (Obstacle obstacle : obstacles) {
            generateTilePosition(obstacle);
        }
    }

    public void assignSounds() {
        snake.setDeathSounds(audioManager.getDeathSounds());
        snake.setEatSounds(audioManager.getEatSounds());
        snake.setSickSounds(audioManager.getSickSounds());
    }

    public void updateFoodsAndObstacles() {
        float increment = (float) Math.floor(score / 100);
        if (score != 0) {
            // Get the nearest lower increment
            if( ((foods.size() - 1) < increment) && ((obstacles.size() - 1) < increment) ) { // If the size of the arraylists (minus 1) is less than or equal to the increment, add a Food object
                foods.add(new Food(textureAtlas));
                generateTilePosition(foods.get(foods.size() - 1));
                obstacles.add(new Obstacle(textureAtlas));
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
        float screenX = getExtendViewport().getMinWorldWidth();
        float screenY = getExtendViewport().getMinWorldHeight();

        float tempX = randomizer.nextInt((int)(screenX / width));
        float tempY = randomizer.nextInt((int)(screenY / height));

        return new Vector2(tempX * width, tempY * height);
    }

    public void resetGame() {
        score = 0;
        initializeFoods();
        initializeObstacles();
        initializePositions();
        snake.setSnakeCanMove(true);
    }

    private void updateEndGameDialog(GameOver gameOver) {
        Gdx.input.setInputProcessor(dialogStage);
        dialog = new Dialog("Game Over", feedDaSnek.getGameAssetManager().getSkin()){
            protected void result (Object object) {
                if(object.equals(1L)) {
                    feedDaSnek.getPreferences().putString("playername", playerNameField.getText());
                    feedDaSnek.getPreferences().flush();
                    feedDaSnek.getScoreManager().updateScoreData(playerNameField.getText(), score);
                    feedDaSnek.getScoreManager().saveScoreData();
                    resetGame();
                    Gdx.input.setInputProcessor(inputHandler);
                } else {
                    // Redirect to main menu
                    feedDaSnek.getPreferences().putString("playername", playerNameField.getText());
                    feedDaSnek.getPreferences().flush();
                    feedDaSnek.getScoreManager().updateScoreData(playerNameField.getText(), score);
                    feedDaSnek.getScoreManager().saveScoreData();
                    feedDaSnek.setScreen(new StartState(feedDaSnek));
                }
            }
        };
        dialog.text(gameOver.getReason());
        dialog.getContentTable().row();

        // Check for new high score, and commit to score object
        boolean newHighScore = feedDaSnek.getScoreManager().checkForNewHighScore(score);

        if (newHighScore == true) {
            dialog.text("New High Score!");
            dialog.getContentTable().row();
        }
        dialog.text("Score: " + score);
        dialog.getContentTable().row();
        if (newHighScore == true) {
            dialog.getContentTable().add(playerNameField);
            dialog.getContentTable().row();
        }
        dialog.button("Play Again", 1L);
        dialog.button("Main Menu", 2L);
        dialog.show(dialogStage);
    }


    /*
    Overridden methods
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void render(float delta) {
        // W3 schools "SaddleBrown" colour
        //Gdx.gl.glClearColor(139/255f, 69/255f, 19/255f, 1);
        super.render(delta);
        update(delta);
        draw(delta);

        dialogStage.act();
        dialogStage.draw();
    }

    // TODO Perform more research on sizing and scaling for other screens etc.
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
        dialogStage.dispose();
    }
}
