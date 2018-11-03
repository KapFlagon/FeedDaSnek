package com.jpgd.game.utilities;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager implements Json.Serializable {
    // TODO make this class the central point for reading/writing the high score data
    HighScores highScores;
    Json json;

    public ScoreManager() {
        highScores = new HighScores();
        json = new Json();

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
    private class Score implements Comparable<Score>{
        /*
        Variables
         */
        private int score;
        private String name;

        /*
        Constructors
         */
        public Score() {
            score = 0;
            name = "NONAME";
        }
        public Score(Score scoreObject) {
            this.score = scoreObject.getScore();
            this.name = scoreObject.getName();
        }
        public Score(int score, String name) {
            this.score = score;
            this.name = name;
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
                return -1;
            } else if(this.score > scoreObject.getScore()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Inner class
    private class HighScores {
        /*
        Variables
         */
        private ArrayList<Score> highScores;

        /*
        Constructors
         */
        public HighScores() {
            highScores = new ArrayList<Score>();
        }

        /*
        Getters
         */
        public ArrayList<Score> getHighScores() {
            return highScores;
        }

        /*
        Setters
         */
        public void setHighScores(ArrayList<Score> highScores) {
            this.highScores = highScores;
            filterScores();
        }

        /*
        Other Methods
         */
        public void addNewScore(Score newScore) {
            highScores.add(newScore);
            filterScores();
        }

        public void filterScores() {
            Collections.sort(highScores);
            if(highScores.size() > 10) {
                for(int iter = 0; iter < (highScores.size() - 10); iter++) {
                    highScores.remove(highScores.size() - 1);
                }
            }
        }
    }

}
