package com.jpgd.game.objects;

import com.badlogic.gdx.math.Vector2;

public enum Direction implements Comparable<Direction>{
    NONE(0, 0),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1);

    private Vector2 vector;
    Direction(float x, float y) {
        vector = new Vector2(x, y);
    }

    public Vector2 getVector() {
        return vector;
    }

}
