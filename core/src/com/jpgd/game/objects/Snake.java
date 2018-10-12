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
    private Vector2 headPosition, direction;
    private ArrayList<Vector2> bodyPoints;
    private float texWidth, texHeight, speed;


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
    }

    /*
    Getters
     */
    public float getSpeed() {
        return speed;
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
        direction = new Vector2(0, 0);
        headPosition = new Vector2(0, 0);
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
        System.out.println("headPosition.x: " + headPosition.x + "\theadPosition.y: " + headPosition.y);
        bodyPoints.add(headPosition);

        // Select random side to add other pieces too
        // Ensure that there is enough space for 2 additional body parts
        float widthSpaceNeeded = texWidth * size;
        float heightSpaceNeeded = texHeight * size;

        boolean initialOrientation = randomizer.nextBoolean();

        if (initialOrientation == true) {
            // Orient via X axis
            System.out.println("Oriented on x axis");
            for (int sizeIter = 1; sizeIter < size; sizeIter++) {
                tempX = 0;

                if (widthSpaceNeeded > (screenX - (headPosition.x + texWidth))) {
                    // Add pieces to left of headPosition.x
                    tempX = headPosition.x - (texWidth * sizeIter);
                } else {
                    // Add pieces to right of headPosition.x
                    tempX = headPosition.x + (texWidth * sizeIter);
                }
                System.out.println("X point: " + tempX + "\tY point: " + headPosition.y);
                bodyPoints.add(new Vector2(tempX, headPosition.y));
            }
        } else {
            // Orient via Y axis
            System.out.println("Oriented on x axis");
            for (int sizeIter = 1; sizeIter < size; sizeIter++) {
                tempY = 0;

                if (heightSpaceNeeded > (screenY - (headPosition.y + texHeight))) {
                    // Add pieces below headPosition.y
                    tempY = headPosition.y - (texHeight * sizeIter);
                } else {
                    // Add pieces above headPosition.y
                    tempY = headPosition.y + (texHeight * sizeIter);
                }
                System.out.println("X point: " + headPosition.x + "\tY point: " + tempY);
                bodyPoints.add(new Vector2(headPosition.x, tempY));
            }
        }

    }

    public void move() {

    }

    public void changeDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void eat() {

    }

    public void grow() {

    }

    public void render(SpriteBatch spriteBatch) {
        for(int bodyPointsIter = 0; bodyPointsIter < bodyPoints.size(); bodyPointsIter++) {
            if(bodyPointsIter == 0) {
                spriteBatch.draw(head_TexReg, headPosition.x, headPosition.y, head_TexReg.getRegionWidth(),head_TexReg.getRegionHeight());
            } else if (bodyPointsIter == (bodyPoints.size()-1)) {
                spriteBatch.draw(tail_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, tail_TexReg.getRegionWidth(), tail_TexReg.getRegionHeight());
            } else {
                /*
                Compare current vector to next vector
                Next vector is either:
                    1. straight ahead of current vector
                    2. To the left of current vector
                    3. To the right of current vector
                Select
                 */
                spriteBatch.draw(bodyStraight_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).y, bodyStraight_TexReg.getRegionWidth(), bodyStraight_TexReg.getRegionHeight());
            }
        }
    }
}
