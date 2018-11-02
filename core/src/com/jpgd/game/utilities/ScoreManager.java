package com.jpgd.game.utilities;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class ScoreManager implements Json.Serializable {

    // TODO make this class the central point for reading/writing the high score data

    public ScoreManager() {

    }

    /*
    Overridden methods for JSON serialization
     */
    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }

    // Inner class
    private class Score {
        /*
        Variables
         */
        private String position;
        private int score;
        private String name;

        /*
        Constructors
         */
        public Score() {
            position = "";
            score = 0;
            name = "NONAME";
        }
        public Score(Score scoreObject) {
            this.position = scoreObject.getPosition();
            this.score = scoreObject.getScore();
            this.name = scoreObject.getName();
        }
        public Score(String position, int score, String name) {
            this.position = position;
            this.score = score;
            this.name = name;
        }

        /*
        Getters
         */
        public String getPosition() {
            return position;
        }
        public int getScore() {
            return score;
        }
        public String getName() {
            return name;
        }

        /*
        Setters
         */
        public void setPosition(String position) {
            this.position = position;
        }
        public void setScore(int score) {
            this.score = score;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    // Inner class
    private class HighScores {
        // TODO should I use a PriorityQueue to order the high scores?
        private ArrayList<Score> highScores;

        public HighScores() {
            highScores = new ArrayList<Score>(10);
        }
    }

}
