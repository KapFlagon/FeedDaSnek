package com.jpgd.game.objects;

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
    private float width, height;
    private int value;
    private Random randomizer;


    /*
    Constructors
     */
    public Tile(TextureRegion textureRegion) {
        randomizer = new Random();

        this.textureRegion = textureRegion;
        ransomizePosition();
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


    /*
    Other Methods
    */
    public void ransomizePosition() {
        position.set(randomizer.nextFloat(), randomizer.nextFloat());
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, position.x, position.y, width, height);
    }


}
