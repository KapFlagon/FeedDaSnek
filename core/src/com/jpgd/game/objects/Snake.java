package com.jpgd.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

import sun.font.TrueTypeFont;

public class Snake {

    /*
    Variables
     */
    private TextureRegion head_TexReg, tail_TexReg, bodyStraight_TexReg, bodyBend_TexReg;
    private int size;
    private Vector2 headPosition, tempDirectionVec;
    private Direction direction;
    private ArrayList<Vector2> bodyPoints;
    private float texWidth, texHeight, speed, dt_total, rotationVal;


    /*
    Constructors
     */
    public Snake(TextureAtlas textureAtlas) {
        // Find texture regions in provided atlas
        head_TexReg = textureAtlas.findRegion("Snake_Head");
        tail_TexReg = textureAtlas.findRegion("Snake_Tail");
        bodyStraight_TexReg = textureAtlas.findRegion("Snake_Body_S");
        bodyBend_TexReg = textureAtlas.findRegion("Snake_Body_B");

        texWidth = head_TexReg.getRegionWidth();
        texHeight = head_TexReg.getRegionHeight();

        dt_total = 0;
        rotationVal = 0;
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


    /*
    Setters
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }



    /*
    Other methods
     */
    public void initializeSnake(Random randomizer) {

        size = 3;
        // Initialize the direction as 0, changed at first key press
        this.direction = Direction.NONE;
        headPosition = new Vector2(0, 0);
        tempDirectionVec = new Vector2(0, 0);
        bodyPoints = new ArrayList<Vector2>();

        generateStartPosition(randomizer);

    }
    private void generateStartPosition(Random randomizer) {
        float tempX, tempY;

        float screenX = Gdx.app.getGraphics().getWidth();
        float screenY = Gdx.app.getGraphics().getHeight();

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

    }

    public void move(float delta) {
        dt_total = dt_total + delta;
        if (dt_total > 0.08f) {
            tempDirectionVec = new Vector2((bodyPoints.get(0).x + (direction.getVector().x * 16)), (bodyPoints.get(0).y + (direction.getVector().y * 16)));

            if (tempDirectionVec.epsilonEquals(bodyPoints.get(0))) {
                // Do nothing
            } else {
                bodyPoints.add(0, tempDirectionVec);
                bodyPoints.remove(bodyPoints.size() - 1);
            }
            dt_total = 0;
        }
    }

    public float determineHeadRotation() {
        float rotation = 0;
        switch (direction) {
            case NONE:
                // Need to determine based on current orientation;
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

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow() {
        // Duplicates last item and adds to end of ArrayList
        bodyPoints.add(bodyPoints.get(bodyPoints.size() - 1));
    }

    public void shrink() {
        bodyPoints.remove(bodyPoints.size() - 1);
    }

    public void render(SpriteBatch spriteBatch) {
        for(int bodyPointsIter = 0; bodyPointsIter < bodyPoints.size(); bodyPointsIter++) {
            if(bodyPointsIter == 0) {
                rotationVal = determineHeadRotation();
                spriteBatch.draw(head_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, (head_TexReg.getRegionWidth() / 2), (head_TexReg.getRegionHeight() / 2), head_TexReg.getRegionWidth(),head_TexReg.getRegionHeight(), 1, 1, rotationVal);
            } else if (bodyPointsIter == (bodyPoints.size()-1)) {
                spriteBatch.draw(tail_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, tail_TexReg.getRegionWidth(), tail_TexReg.getRegionHeight());
            } else {
                /*
                Compare current vector to next vector
                Select
                 */

                spriteBatch.draw(bodyStraight_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, bodyStraight_TexReg.getRegionWidth(), bodyStraight_TexReg.getRegionHeight());
            }
            rotationVal = 0;
        }
    }
}
