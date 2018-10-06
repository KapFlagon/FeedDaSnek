package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {

    /*
    Variables
     */
    private TextureRegion textureRegion;
    private float posX, posY;
    private float width, height;
    private int value;


    /*
    Constructors
     */
    public Tile(TextureRegion textureRegion, float posX, float posY) {
        this.textureRegion = textureRegion;
        this.posX = posX;
        this.posY = posY;
        this.width = textureRegion.getRegionWidth();
        this.height = textureRegion.getRegionHeight();
        value = 0;
    }


    /*
    Getters
     */
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
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


    /*
    Setters
     */
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setValue(int value) {
        this.value = value;
    }


    /*
    Other Methods
    */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, posX, posY, width, height);
    }


}
