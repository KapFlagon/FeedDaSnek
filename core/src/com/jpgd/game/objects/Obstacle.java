package com.jpgd.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Obstacle extends Tile {

    public Obstacle(TextureAtlas textureAtlas) {
        super(textureAtlas.findRegion("Poison"));
        setValue(-100);
    }
}
