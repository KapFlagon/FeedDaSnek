package com.jpgd.game.utilities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jpgd.game.FeedDaSnek;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    // TODO make this class the central point for reading/writing the high score data
    FeedDaSnek feedDaSnek;
    FileHandle fileHandle_highScores;
    Json json;
    HighScores highScores;

    public ScoreManager(FeedDaSnek feedDaSnek) {
        this.feedDaSnek = feedDaSnek;
        fileHandle_highScores = this.feedDaSnek.getGameAssetManager().getFileHandle_highScores();
        json = new Json();
        json.setElementType(HighScores.class,"listOfHighScores", Score.class);
        highScores = new HighScores();
    }

    /*
    Other methods
     */
    public void saveScoreData() {
        System.out.println("\nSaving");
        String tempText_uncoded = json.toJson(highScores, HighScores.class);
        System.out.print(json.prettyPrint(tempText_uncoded));
        String tempText_encoded = Base64Coder.encodeString(tempText_uncoded);
        fileHandle_highScores.writeString(tempText_encoded, false);
    }

    public void loadScoreData() {
        System.out.println("\nLoading");
        if(fileHandle_highScores.exists()) {
            System.out.println("\nSaved game data exists");
            String tempText_encoded = fileHandle_highScores.readString();
            String tempText_decoded = Base64Coder.decodeString(tempText_encoded);
            System.out.print(json.prettyPrint(tempText_decoded));
            highScores = json.fromJson(HighScores.class, tempText_decoded);
        }
        else {
            System.out.println("\nNo score data");
            for(int generatorIter = 0; generatorIter < 10; generatorIter++) {
                highScores.addNewScore(new Score());
            }
        }
    }

    public boolean updateScoreData(Score newScore) {
        boolean newHighScore = highScores.addNewScore(newScore);
        return newHighScore;
    }
    public boolean updateScoreData(String playerName, int score) {
        Score tempScore = new Score(playerName, score);
        boolean newHighScore = highScores.addNewScore(tempScore);
        return newHighScore;
    }

    public boolean checkForNewHighScore(int score) {
        return highScores.isNewHighScore(score);
    }

    // TODO Move inner classes to outer classes and test further
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
            name = "NEWPLAYER";
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

    // Inner class
    private class HighScores {
        /*
        Variables
         */
        private ArrayList<Score> listOfHighScores;

        /*
        Constructors
         */
        public HighScores() {
            listOfHighScores = new ArrayList<Score>();
        }
        public HighScores(ArrayList<Score> listOfHighScores) {
            this.listOfHighScores = listOfHighScores;
        }

        /*
        Getters
         */
        public ArrayList<Score> getListOfHighScores() {
            return listOfHighScores;
        }

        /*
        Setters
         */
        public void setHighScores(ArrayList<Score> listOfHighScores) {
            this.listOfHighScores = listOfHighScores;
            filterScores();
        }

        /*
        Other Methods
         */
        public boolean addNewScore(Score newScore) {
            boolean newHighScore = isNewHighScore(newScore.getScore());
            listOfHighScores.add(newScore);
            filterScores();
            return newHighScore;
        }

        public void filterScores() {
            Collections.sort(listOfHighScores);
            if(listOfHighScores.size() > 10) {
                for(int iter = 0; iter < (listOfHighScores.size() - 10); iter++) {
                    listOfHighScores.remove(listOfHighScores.size() - 1);
                }
            }
        }

        public boolean isNewHighScore(int score) {
            boolean newHighScore = false;
            for(int scoreIter = 0; scoreIter < highScores.getListOfHighScores().size(); scoreIter++) {
                if(score > highScores.getListOfHighScores().get(scoreIter).getScore()) {
                    newHighScore = true;
                }
            }
            return newHighScore;
        }
    }

}
