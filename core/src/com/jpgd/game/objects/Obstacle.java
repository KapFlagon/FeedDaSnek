package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Obstacle extends Tile {

    public Obstacle(TextureRegion textureRegion, float posX, float posY) {
        super(textureRegion, posX, posY);
        setValue(100 * (-1));
    }
}
