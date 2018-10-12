package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Food extends Tile {

    public Food(TextureAtlas textureAtlas) {
        super(textureAtlas.findRegion("Mouse"));
        setValue(100);
    }
}
