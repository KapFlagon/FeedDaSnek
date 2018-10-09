package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Obstacle extends Tile {

    public Obstacle(TextureRegion textureRegion) {
        super(textureRegion);
        setValue(100 * (-1));
    }
}
