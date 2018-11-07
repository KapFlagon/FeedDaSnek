package com.jpgd.game.utilities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.jpgd.game.FeedDaSnek;
import com.jpgd.game.objects.HighScores;
import com.jpgd.game.objects.Score;


public class ScoreManager {
    private FeedDaSnek feedDaSnek;
    private FileHandle fileHandle_highScores;
    private Json json;
    private HighScores highScores;

    public ScoreManager(FeedDaSnek feedDaSnek) {
        this.feedDaSnek = feedDaSnek;
        fileHandle_highScores = this.feedDaSnek.getGameAssetManager().getFileHandle_highScores();
        json = new Json();
        json.setElementType(HighScores.class,"listOfHighScores", Score.class);
        highScores = new HighScores();
    }

    /*
    Getters
     */
    public HighScores getHighScores() {
        return highScores;
    }

    /*
    Other methods
     */
    public void saveScoreData() {
        System.out.println("\nSaving");
        String tempText_uncoded = json.toJson(highScores, HighScores.class);
        String tempText_encoded = Base64Coder.encodeString(tempText_uncoded);
        fileHandle_highScores.writeString(tempText_encoded, false);
    }

    public void loadScoreData() {
        System.out.println("\nLoading");
        if(fileHandle_highScores.exists()) {
            System.out.println("\nSaved game data exists");
            // TODO Add file validation. If data is corrupted, see what can be salvaged. If below a certain threshold, generate new file.
            String tempText_encoded = fileHandle_highScores.readString();
            String tempText_decoded = Base64Coder.decodeString(tempText_encoded);
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
}