package com.jpgd.game.objects;

public enum GameOver {
    GO_1("Snake has gone out of bounds!"),
    GO_2("Snake has eaten itself!"),
    GO_3("Snake has eaten too much poison!");

    // GO_1 = Snake has moved out of bounds
    // GO_2 = Snake has eaten itself

    private String reason;

    GameOver (String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
