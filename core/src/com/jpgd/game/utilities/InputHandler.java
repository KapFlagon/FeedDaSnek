package com.jpgd.game.utilities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jpgd.game.objects.Direction;
import com.jpgd.game.objects.Snake;
import java.util.ArrayList;
import java.util.Vector;


public class InputHandler extends InputAdapter {

    /*
    Variables
     */
    private Snake snake;
    private Viewport viewport;
    private boolean dragging;
    private Vector2 tempVec2;
    private ArrayList<Vector2> vecBuffer;
    private int maxBufferSize;
    // TODO Decide some way to buffer the dragged coordinates to determine directions over distance

    /*
    Constructors
     */
    public InputHandler(Snake snake, Viewport viewport) {
        this.snake = snake;
        this.viewport = viewport;
        tempVec2 = new Vector2();
        vecBuffer = new ArrayList<Vector2>();
        maxBufferSize = 40;
    }

    /*
    Other Methods
     */
    private void processInput(int keycode) {
        float tempHeadX = snake.getBodyPoints().get(0).x;
        float tempHeadY = snake.getBodyPoints().get(0).y;
        float tempBodyX = snake.getBodyPoints().get(1).x;
        float tempBodyY = snake.getBodyPoints().get(1).y;
        float tempWidth = snake.getTexWidth();
        float tempHeight = snake.getTexHeight();

        if(keycode == Input.Keys.LEFT) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                if ((tempHeadX - tempWidth) == tempBodyX) {
                    // Do nothing, no change in direction
                } else {
                    snake.changeDirection(Direction.LEFT);
                }
            }
        }

        if(keycode == Input.Keys.RIGHT) {
            if(snake.getDirection().getVector().x != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                if ((tempHeadX + tempWidth) == tempBodyX) {
                    // Do nothing, no change in direction
                } else {
                    snake.changeDirection(Direction.RIGHT);
                }
            }
        }

        if(keycode == Input.Keys.UP) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                if ((tempHeadY + tempHeight) == tempBodyY) {
                    // Do nothing, no change in direction
                } else {
                    snake.changeDirection(Direction.UP);
                }
            }
        }

        if(keycode == Input.Keys.DOWN) {
            if(snake.getDirection().getVector().y != 0) {
                // no direction change as it would
                // a) cause the snake to go back on itself
                // b) simply go in same direction
            } else {
                if ((tempHeadY - tempHeight) == tempBodyY) {
                    // Do nothing, no change in direction
                } else {
                    snake.changeDirection(Direction.DOWN);
                }
            }
        }
    }

    private void updateBuffer(Vector2 vector2) {
        if(vecBuffer.size() >= maxBufferSize) {
            vecBuffer.remove(0);
        }
        vecBuffer.add(vector2);
    }

    // TODO work on logic for this method
    // https://stackoverflow.com/questions/16514152/libgdx-coordinate-system-differences-between-rendering-and-touch-input
    // camera.unprojected(vector3 screencoords)
    private int determineDraggedDirection() {
        // Examine X and Y coordinates over time (array?)
        // If X increases or decreases more over time than Y, then direction will be up/down accordingly
        // If Y increases or decreases more over time than X, then direction will be left/right accordingly

        // Analyze the buffer for phases of "continuous" directional movement
        // Each time a phase is detected, send a directional message and flush the data from the buffer (?)

        for(int vecIter= 0; vecIter < vecBuffer.size(); vecIter++) {

        }
        /*
        if() {
            return Input.Keys.LEFT;
        } else if() {
            return Input.Keys.RIGHT;
        } else if() {
            return Input.Keys.UP;
        } else {
            return Input.Keys.DOWN;
        }
        */
        return 1;
    }


    /*
    Overridden methods
    */
    @Override
    public boolean keyDown (int keycode) {
        processInput(keycode);
        return true;
    }

    // TODO examine phone controls using gestures
    // TODO create method to convert upper left corner coordinates to lower left corner coordinates for all touch methods : https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/InputProcessor.html#touchDown-int-int-int-int-
    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        tempVec2.set(screenX, screenY);
        updateBuffer(viewport.unproject(tempVec2));
        dragging = true;
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (!dragging) {
            return false;
        } else {
            tempVec2.set(screenX, screenY);
            updateBuffer(viewport.unproject(tempVec2));
            //processInput(determineDraggedDirection());
        }
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) {
            return false;
        }
        tempVec2.set(screenX, screenY);
        updateBuffer(viewport.unproject(tempVec2));
        dragging = false;
        return true;
    }

}
