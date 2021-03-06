package com.jpgd.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tile {

    /*
    Variables
     */
    private TextureRegion textureRegion;
    private Vector2 position;
    private float width, height, imageScaling;
    private int value;
    private boolean eaten;


    /*
    Constructors
     */
    public Tile(TextureRegion textureRegion) {
        this.position = new Vector2(0, 0);
        this.textureRegion = textureRegion;
        this.imageScaling = 1;
        this.width = textureRegion.getRegionWidth();
        this.height = textureRegion.getRegionHeight();
        this.value = 0;
        this.eaten = false;
    }


    /*
    Getters
     */
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public float getPosX() {
        return position.x;
    }

    public float getPosY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getValue() {
        return value;
    }

    public boolean getEaten() {
        return eaten;
    }


    /*
    Setters
     */
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void setPosition(float posX, float posY) {
        this.position.set(posX, posY);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public void setImageScaling(float imageScaling) {
        this.imageScaling = imageScaling;
        this.width = width * imageScaling;
        this.height = height * imageScaling;
    }


    /*
    Other Methods
    */

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, position.x, position.y, width, height);
    }


}
