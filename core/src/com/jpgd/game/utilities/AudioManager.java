package com.jpgd.game.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;

public class AudioManager {

    /*
    Variables
     */

    private GameAssetManager gameAssetManager;

    private Music music;
    private ArrayList<Sound> deathSounds;
    private ArrayList<Sound> eatSounds;
    private ArrayList<Sound> sickSounds;

    private String musicPath;
    private String sfx_path_01, sfx_path_02, sfx_path_03, sfx_path_04;
    private String sfx_path_05, sfx_path_06, sfx_path_07, sfx_path_08;
    private String sfx_path_09, sfx_path_10, sfx_path_11, sfx_path_12;
    private String sfx_path_13, sfx_path_14, sfx_path_15, sfx_path_16;


    /*
    Constructors
     */
    public AudioManager(GameAssetManager gameAssetManager) {
        this.gameAssetManager = gameAssetManager;

        // Variables for actual assets
        deathSounds = new ArrayList<Sound>();
        eatSounds = new ArrayList<Sound>();
        sickSounds = new ArrayList<Sound>();

        // Paths data for assets
        musicPath = gameAssetManager.getMusicPath();

        sfx_path_01 = gameAssetManager.getSfx_path_01();
        sfx_path_02 = gameAssetManager.getSfx_path_02();
        sfx_path_03 = gameAssetManager.getSfx_path_03();
        sfx_path_04 = gameAssetManager.getSfx_path_04();
        sfx_path_05 = gameAssetManager.getSfx_path_05();
        sfx_path_06 = gameAssetManager.getSfx_path_06();
        sfx_path_07 = gameAssetManager.getSfx_path_07();
        sfx_path_08 = gameAssetManager.getSfx_path_08();
        sfx_path_09 = gameAssetManager.getSfx_path_09();
        sfx_path_10 = gameAssetManager.getSfx_path_10();
        sfx_path_11 = gameAssetManager.getSfx_path_11();
        sfx_path_12 = gameAssetManager.getSfx_path_12();
        sfx_path_13 = gameAssetManager.getSfx_path_13();
        sfx_path_14 = gameAssetManager.getSfx_path_14();
        sfx_path_15 = gameAssetManager.getSfx_path_15();
        sfx_path_16 = gameAssetManager.getSfx_path_16();

        assignSFX();
        assignMusic();
    }

    /*
    Getters
     */

    public Music getMusic() {
        return music;
    }

    public ArrayList<Sound> getDeathSounds() {
        return deathSounds;
    }

    public ArrayList<Sound> getEatSounds() {
        return eatSounds;
    }

    public ArrayList<Sound> getSickSounds() {
        return sickSounds;
    }


    /*
    Other methods
     */
    private void assignSFX(){
        // 4 deaths
        // 7 eats
        // 5 sicks
        // Randomise 3 sounds from each batch?

        deathSounds.add(gameAssetManager.getAssetManager().get(sfx_path_01, Sound.class));
        deathSounds.add(gameAssetManager.getAssetManager().get(sfx_path_02, Sound.class));
        deathSounds.add(gameAssetManager.getAssetManager().get(sfx_path_03, Sound.class));

        eatSounds.add(gameAssetManager.getAssetManager().get(sfx_path_05, Sound.class));
        eatSounds.add(gameAssetManager.getAssetManager().get(sfx_path_06, Sound.class));
        eatSounds.add(gameAssetManager.getAssetManager().get(sfx_path_07, Sound.class));

        sickSounds.add(gameAssetManager.getAssetManager().get(sfx_path_12, Sound.class));
        sickSounds.add(gameAssetManager.getAssetManager().get(sfx_path_13, Sound.class));
        sickSounds.add(gameAssetManager.getAssetManager().get(sfx_path_14, Sound.class));

        gameAssetManager.getAssetManager().getLogger().info("SFX assets are assigned.");
    }

    private void assignMusic() {
        if(gameAssetManager.getAssetManager().isLoaded(musicPath)){
            gameAssetManager.getAssetManager().getLogger().info("Music asset is assigned.");
            music = gameAssetManager.getAssetManager().get(musicPath, Music.class);
            music.setLooping(true);
        } else {
            gameAssetManager.getAssetManager().getLogger().info("Music asset is not loaded yet.");
        }
    }

    public void dispose() {
        gameAssetManager.getAssetManager().unload(musicPath);
        gameAssetManager.getAssetManager().unload(sfx_path_01);
        gameAssetManager.getAssetManager().unload(sfx_path_02);
        gameAssetManager.getAssetManager().unload(sfx_path_03);
        gameAssetManager.getAssetManager().unload(sfx_path_04);
        gameAssetManager.getAssetManager().unload(sfx_path_05);
        gameAssetManager.getAssetManager().unload(sfx_path_06);
        gameAssetManager.getAssetManager().unload(sfx_path_07);
        gameAssetManager.getAssetManager().unload(sfx_path_08);
        gameAssetManager.getAssetManager().unload(sfx_path_09);
        gameAssetManager.getAssetManager().unload(sfx_path_10);
        gameAssetManager.getAssetManager().unload(sfx_path_11);
        gameAssetManager.getAssetManager().unload(sfx_path_12);
        gameAssetManager.getAssetManager().unload(sfx_path_13);
        gameAssetManager.getAssetManager().unload(sfx_path_14);
        gameAssetManager.getAssetManager().unload(sfx_path_15);
        gameAssetManager.getAssetManager().unload(sfx_path_16);
    }
}
