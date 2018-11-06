package com.jpgd.game.objects;

public class Score implements Comparable<Score>{
    /*
    Variables
     */
    private String name;
    private int score;

    /*
    Constructors
     */
    public Score() {
        this.score = 0;
        this.name = "NEWPLAYER";
    }
    public Score(Score scoreObject) {
        this.score = scoreObject.getScore();
        this.name = scoreObject.getName();
    }
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /*
    Getters
     */
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }

    /*
    Setters
     */
    public void setScore(int score) {
        this.score = score;
    }
    public void setName(String name) {
        this.name = name;
    }

    /*
    Overridden methods
     */
    @Override
    public int compareTo(Score scoreObject) {
        if(this.score < scoreObject.getScore()) {
            return 1;
        } else if(this.score > scoreObject.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }
}