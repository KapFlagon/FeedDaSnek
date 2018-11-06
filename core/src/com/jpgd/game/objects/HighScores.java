package com.jpgd.game.objects;

import java.util.ArrayList;
import java.util.Collections;

public class HighScores {
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
        this.listOfHighScores.add(newScore);
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
        for(int scoreIter = 0; scoreIter < listOfHighScores.size(); scoreIter++) {
            if(score > listOfHighScores.get(scoreIter).getScore()) {
                newHighScore = true;
            }
        }
        return newHighScore;
    }
}