package com.jpgd.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.utilities.AudioManager;

import java.util.ArrayList;
import java.util.Random;

import sun.font.TrueTypeFont;

public class Snake {

    /*
    Variables
     */
    private Random randomizer;
    private TextureRegion head_TexReg, tail_TexReg, bodyStraight_TexReg, bodyBend_TexReg;
    private int size;
    private Vector2 headPosition, tempDirectionVec;
    private Direction direction;
    private ArrayList<Vector2> bodyPoints;
    private float texWidth, texHeight, speed, dt_total, rotationVal, imageScaling;
    private ArrayList<Sound> deathSounds, eatSounds, sickSounds;
    private boolean snakeCanMove;



    /*
    Constructors
     */
    public Snake(TextureAtlas textureAtlas) {
        randomizer = new Random();
        imageScaling = 1;
        // Find texture regions in provided atlas
        head_TexReg = textureAtlas.findRegion("Snake_Head");
        tail_TexReg = textureAtlas.findRegion("Snake_Tail");
        bodyStraight_TexReg = textureAtlas.findRegion("Snake_Body_S");
        bodyBend_TexReg = textureAtlas.findRegion("Snake_Body_B");

        texWidth = head_TexReg.getRegionWidth();
        texHeight = head_TexReg.getRegionHeight();

        dt_total = 0;
        rotationVal = 0;
        snakeCanMove = false;
    }

    /*
    Getters
     */
    public float getSpeed() {
        return speed;
    }

    public ArrayList<Vector2> getBodyPoints() {
        return bodyPoints;
    }

    public Direction getDirection() {
        return direction;
    }

    public ArrayList<Sound> getDeathSounds() {
        return deathSounds;
    }

    public float getTexWidth() {
        return texWidth;
    }

    public float getTexHeight() {
        return texHeight;
    }

    public boolean getSnakeCanMove() {
        return snakeCanMove;
    }

    /*
        Setters
         */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDeathSounds(ArrayList<Sound> deathSounds) {
        this.deathSounds = deathSounds;
    }

    public void setEatSounds(ArrayList<Sound> eatSounds) {
        this.eatSounds = eatSounds;
    }

    public void setSickSounds(ArrayList<Sound> sickSounds) {
        this.sickSounds = sickSounds;
    }

    public void setSnakeCanMove(boolean snakeCanMove) {
        this.snakeCanMove = snakeCanMove;
    }

    public void setImageScaling(float imageScaling){
        this.imageScaling = imageScaling;
        texHeight = texHeight * imageScaling;
        texWidth = texWidth * imageScaling;
    }

    /*
        Other methods
         */
    public void initializeSnake(float widthBounds, float heightBounds) {

        size = 3;
        // Initialize the direction as 0, changed at first key press
        this.direction = Direction.NONE;
        headPosition = new Vector2(0, 0);
        tempDirectionVec = new Vector2(0, 0);
        bodyPoints = new ArrayList<Vector2>();

        generateStartPosition(widthBounds, heightBounds);

    }
    private void generateStartPosition(float widthBounds, float heightBounds) {
        float tempX, tempY;

        float screenX = widthBounds;
        float screenY = heightBounds;

        tempX = randomizer.nextInt((int)(screenX / texWidth));
        tempY = randomizer.nextInt((int)(screenY / texHeight));

        headPosition.set(tempX * texWidth, tempY * texHeight);
        bodyPoints.add(headPosition);

        // Select random side to add other pieces too
        // Ensure that there is enough space for 2 additional body parts
        float widthSpaceNeeded = texWidth * size;
        float heightSpaceNeeded = texHeight * size;

        boolean initialOrientation = randomizer.nextBoolean();

        if (initialOrientation == true) {
            // Orient via X axis
            for (int sizeIter = 1; sizeIter < size; sizeIter++) {
                tempX = 0;

                if (widthSpaceNeeded > (screenX - (headPosition.x + texWidth))) {
                    // Add pieces to left of headPosition.x
                    tempX = headPosition.x - (texWidth * sizeIter);
                } else {
                    // Add pieces to right of headPosition.x
                    tempX = headPosition.x + (texWidth * sizeIter);
                }
                bodyPoints.add(new Vector2(tempX, headPosition.y));
            }
        } else {
            // Orient via Y axis
            for (int sizeIter = 1; sizeIter < size; sizeIter++) {
                tempY = 0;

                if (heightSpaceNeeded > (screenY - (headPosition.y + texHeight))) {
                    // Add pieces below headPosition.y
                    tempY = headPosition.y - (texHeight * sizeIter);
                } else {
                    // Add pieces above headPosition.y
                    tempY = headPosition.y + (texHeight * sizeIter);
                }

                bodyPoints.add(new Vector2(headPosition.x, tempY));
            }
        }
        System.out.println("positions generated");
        printSnake();
    }

    public void move(float delta) {
        dt_total = dt_total + delta;
        //if (dt_total > 0.08f) {
        if (dt_total > speed) {
            tempDirectionVec = new Vector2((bodyPoints.get(0).x + (direction.getVector().x * texHeight)), (bodyPoints.get(0).y + (direction.getVector().y * texHeight)));

            if (tempDirectionVec.epsilonEquals(bodyPoints.get(0))) {
                // Do nothing
            } else {
                System.out.println("before actual move");
                printSnake();
                bodyPoints.add(0, tempDirectionVec);
                bodyPoints.remove(bodyPoints.size() - 1);
                System.out.println("after actual move");
            }
            dt_total = 0;
        }
    }

    public float determineHeadRotation() {
        float rotation = 0;
        switch (direction) {
            case NONE:
                if(bodyPoints.get(0).x > bodyPoints.get(1).x) {
                    // Head is to the right of the body
                    rotation = 270;
                } else if(bodyPoints.get(0).x < bodyPoints.get(1).x){
                    // Head is to the left of the body
                    rotation = 90;
                } else if(bodyPoints.get(0).y > bodyPoints.get(1).y) {
                    // Head is above the body
                    rotation = 0;
                } else {
                    // head is below the body
                    rotation = 180;
                }
                break;
            case LEFT:
                rotation = 90;
                break;
            case RIGHT:
                rotation = 270;
                break;
            case UP:
                rotation = 0;
                break;
            case DOWN:
                rotation = 180;
                break;
        }
        return rotation;
    }

    public float determineRotation(int bodyPointIter) {
        float rotation = 0;
        // Compare previous piece (bodyPointIter - 1) and current piece (bodyPointIter)
        // Compare their x and y positions

        if ((bodyPoints.get(bodyPointIter - 1).x) > (bodyPoints.get(bodyPointIter).x)) {
            // leading bodypoint is to the right of current bodypoint
            rotation = 270;
        } else if (((bodyPoints.get(bodyPointIter - 1).x) < (bodyPoints.get(bodyPointIter).x))) {
            // leading bodypoint is to the left of current bodypoint
            rotation = 90;
        }

        if ((bodyPoints.get(bodyPointIter - 1).y) > (bodyPoints.get(bodyPointIter).y)) {
            // leading bodypoint is above current bodypoint
            rotation = 0;
        } else if (((bodyPoints.get(bodyPointIter - 1).y) < (bodyPoints.get(bodyPointIter).y))) {
            // leading bodypoint is below current bodypoint
            rotation = 180;
        }
        return rotation;
    }

    public float determineBendRotation(int bodyPointIter) {
        float rotation = 0;

        if ((bodyPoints.get(bodyPointIter - 1).x) > (bodyPoints.get(bodyPointIter).x)) {
            // leading bodypoint is to the right of current bodypoint
            if (bodyPoints.get(bodyPointIter).y > bodyPoints.get(bodyPointIter + 1).y) {
                // Current bodypoint is above trailing bodypoint
                rotation = 0;
            } else if (bodyPoints.get(bodyPointIter).y < bodyPoints.get(bodyPointIter + 1).y) {
                // Current bodypoint is below trailing bodypoint
                rotation = 90;
            }
        } else if (((bodyPoints.get(bodyPointIter - 1).x) < (bodyPoints.get(bodyPointIter).x))) {
            // leading bodypoint is to the left of current bodypoint
            if (bodyPoints.get(bodyPointIter).y > bodyPoints.get(bodyPointIter + 1).y) {
                // Current bodypoint is above trailing bodypoint
                rotation = 270;
            } else if (bodyPoints.get(bodyPointIter).y < bodyPoints.get(bodyPointIter + 1).y) {
                // Current bodypoint is below trailing bodypoint
                rotation = 180;
            }
        } else if ((bodyPoints.get(bodyPointIter - 1).y) > (bodyPoints.get(bodyPointIter).y)) {
            // leading bodypoint is above current bodypoint
            if (bodyPoints.get(bodyPointIter).x > bodyPoints.get(bodyPointIter + 1).x) {
                // Current bodypoint is right of trailing bodypoint
                rotation = 180;
            } else if (bodyPoints.get(bodyPointIter).x < bodyPoints.get(bodyPointIter + 1).x) {
                // Current bodypoint is left of trailing bodypoint
                rotation = 90;
            }
        } else if (((bodyPoints.get(bodyPointIter - 1).y) < (bodyPoints.get(bodyPointIter).y))) {
            // leading bodypoint is below current bodypoint
            if (bodyPoints.get(bodyPointIter).x > bodyPoints.get(bodyPointIter + 1).x) {
                // Current bodypoint is right of trailing bodypoint
                rotation = 270;
            } else if (bodyPoints.get(bodyPointIter).x < bodyPoints.get(bodyPointIter + 1).x) {
                // Current bodypoint is left of trailing bodypoint
                rotation = 0;
            }
        }
        return rotation;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow(AudioManager audioManager) {
        audioManager.playEatSound(randomizer);
        // Duplicates last item and adds to end of ArrayList
        bodyPoints.add(bodyPoints.get(bodyPoints.size() - 1));
    }

    public void shrink(AudioManager audioManager) {
        audioManager.playSickSound(randomizer);
        bodyPoints.remove(bodyPoints.size() - 1);
    }

    public void die(AudioManager audioManager) {
        audioManager.playDeathSound(randomizer);
        snakeCanMove = false;
    }

    public void render(SpriteBatch spriteBatch) {
        for(int bodyPointsIter = 0; bodyPointsIter < bodyPoints.size(); bodyPointsIter++) {
            if(bodyPointsIter == 0) {
                rotationVal = determineHeadRotation();
                spriteBatch.draw(head_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, (texWidth / 2), (texHeight / 2), texWidth, texHeight, 1, 1, rotationVal);
            } else if (bodyPointsIter == (bodyPoints.size()-1)) {
                rotationVal = determineRotation(bodyPoints.size()-1);
                spriteBatch.draw(tail_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, (texWidth / 2), (texHeight / 2), texWidth, texHeight, 1, 1, rotationVal);
            } else {
                if(
                    // If last body point x is same as current body point, and next body point x is same as current body point use straight piece. OR, same checks for y positions.
                    ( (bodyPoints.get(bodyPointsIter-1).x) == (bodyPoints.get(bodyPointsIter).x) && (bodyPoints.get(bodyPointsIter+1).x) == (bodyPoints.get(bodyPointsIter).x) )
                    || ( (bodyPoints.get(bodyPointsIter-1).y) == (bodyPoints.get(bodyPointsIter).y) && (bodyPoints.get(bodyPointsIter+1).y) == (bodyPoints.get(bodyPointsIter).y) )
                        ) {
                    rotationVal = determineRotation(bodyPointsIter);
                    spriteBatch.draw(bodyStraight_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, (texWidth / 2), (texHeight / 2), texWidth, texHeight, 1, 1, rotationVal);
                } else {
                    // Every other scenario, use bend piece
                    rotationVal = determineBendRotation(bodyPointsIter);
                    spriteBatch.draw(bodyBend_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, (texWidth / 2), (texHeight / 2), texWidth, texHeight, 1, 1, rotationVal);
                }
            }
            rotationVal = 0;
        }
    }

    public void printSnake() {
        for(int iter = 0; iter < bodyPoints.size(); iter++) {
            System.out.println("Position: " + iter + "\tX: " + bodyPoints.get(iter).x + "\tY: " + bodyPoints.get(iter).y + "\tWidth: " + texWidth + "\tHeight: " + texHeight);
        }
        System.out.println();
    }
}
