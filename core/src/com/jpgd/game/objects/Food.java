package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Food extends Tile {

    public Food(TextureRegion textureRegion) {
        super(textureRegion);
        setValue(100);
    }
}
