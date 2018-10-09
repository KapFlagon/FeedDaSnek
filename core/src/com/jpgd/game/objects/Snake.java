package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

    /*
    Variables
     */
    private TextureRegion head_TexReg, tail_TexReg, bodyStraight_TexReg, bodyBend_TexReg;
    private int size;
    private Vector2 headPosition, direction;
    private ArrayList<Vector2> bodyPoints;


    /*
    Constructors
     */
    public Snake(TextureAtlas textureAtlas) {
        // Find texture regions in provided atlas
        head_TexReg = textureAtlas.findRegion("Snake_Head");
        tail_TexReg = textureAtlas.findRegion("Snake_Tail");
        bodyStraight_TexReg = textureAtlas.findRegion("Snake_Body_S");
        bodyBend_TexReg = textureAtlas.findRegion("Snake_Body_B");
    }

    /*
    Getters
     */



    /*
    Setters
     */



    /*
    Other methods
     */
    public void initializeSnake(Random randomizer) {
        size = 2;   // Offset by 1, head is not counted in the size
        // Initialize the direction as 0, changed at first key press
        direction = new Vector2(0, 0);
        headPosition = new Vector2(0, 0);
        generateStartPosition(randomizer);
        bodyPoints = new ArrayList<Vector2>();
    }
    private void generateStartPosition(Random randomizer) {
        headPosition.set(randomizer.nextFloat(), randomizer.nextFloat());
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
                spriteBatch.draw(bodyStraight_TexReg, bodyPoints.get(bodyPointsIter).x, bodyPoints.get(bodyPointsIter).x, bodyStraight_TexReg.getRegionWidth(), bodyStraight_TexReg.getRegionHeight());
            }
        }
    }
}
