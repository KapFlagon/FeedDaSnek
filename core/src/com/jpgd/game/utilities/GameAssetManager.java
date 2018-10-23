package com.jpgd.game.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Random;

public class GameAssetManager {

    AssetManager assetManager;

    // asset paths
    private String musicPath;
    private String sfx_path_01, sfx_path_02, sfx_path_03, sfx_path_04;
    private String sfx_path_05, sfx_path_06, sfx_path_07, sfx_path_08;
    private String sfx_path_09, sfx_path_10, sfx_path_11, sfx_path_12;
    private String sfx_path_13, sfx_path_14, sfx_path_15, sfx_path_16;
    private String fontPath;
    private String textureAtlasPath;
    private String skin01Path;

    // asset variables
    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private ArrayList<Sound> deathSounds;
    private ArrayList<Sound> eatSounds;
    private ArrayList<Sound> sickSounds;
    private Music music;

    /*
    Constructors
     */
    public GameAssetManager() {

        assetManager = new AssetManager();

        // initiate strings
        textureAtlasPath = "textures/FeedDaSnek.atlas";
        sfx_path_01 = "sfx/Death_01.ogg";
        sfx_path_02 = "sfx/Death_02.ogg";
        sfx_path_03 = "sfx/Death_03.ogg";
        sfx_path_04 = "sfx/Death_04.ogg";
        sfx_path_05 = "sfx/Eat_01.ogg";
        sfx_path_06 = "sfx/Eat_02.ogg";
        sfx_path_07 = "sfx/Eat_03.ogg";
        sfx_path_08 = "sfx/Eat_04.ogg";
        sfx_path_09 = "sfx/Eat_05.ogg";
        sfx_path_10 = "sfx/Eat_06.ogg";
        sfx_path_11 = "sfx/Eat_07.ogg";
        sfx_path_12 = "sfx/Sick_01.ogg";
        sfx_path_13 = "sfx/Sick_02.ogg";
        sfx_path_14 = "sfx/Sick_03.ogg";
        sfx_path_15 = "sfx/Sick_04.ogg";
        sfx_path_16 = "sfx/Sick_05.ogg";

        musicPath = "music/FeedDaSnek_Theme.mp3";

        skin01Path = "skins/shade/uiskin.json";

        deathSounds = new ArrayList<Sound>();
        eatSounds = new ArrayList<Sound>();
        sickSounds = new ArrayList<Sound>();


        loadImages();
        loadFonts();
        loadSFX();
        loadMusic();
        loadSkin();

    }


    /*
    Getters
     */
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
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

    public Music getMusic() {
        return music;
    }

    public Skin getSkin() {
        return skin;
    }

    /*
    Loader methods
     */
    public void loadMusic() {
        assetManager.load(musicPath, Music.class);
        assetManager.getLogger().info("Music set for loading");
    }

    public void loadFonts() {
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle(font, Color.WHITE);
    }

    public void loadImages() {
        assetManager.load(textureAtlasPath, TextureAtlas.class);
        assetManager.getLogger().info("TextureAtlas asset set for loading");
    }

    public void loadSFX() {
        assetManager.load(sfx_path_01, Sound.class);
        assetManager.load(sfx_path_02, Sound.class);
        assetManager.load(sfx_path_03, Sound.class);
        assetManager.load(sfx_path_04, Sound.class);
        assetManager.load(sfx_path_05, Sound.class);
        assetManager.load(sfx_path_06, Sound.class);
        assetManager.load(sfx_path_07, Sound.class);
        assetManager.load(sfx_path_08, Sound.class);
        assetManager.load(sfx_path_09, Sound.class);
        assetManager.load(sfx_path_10, Sound.class);
        assetManager.load(sfx_path_11, Sound.class);
        assetManager.load(sfx_path_12, Sound.class);
        assetManager.load(sfx_path_13, Sound.class);
        assetManager.load(sfx_path_14, Sound.class);
        assetManager.load(sfx_path_15, Sound.class);
        assetManager.load(sfx_path_16, Sound.class);
    }

    public void loadSkin() {
        assetManager.load(skin01Path, Skin.class);
    }


    /*
    Checkers to verify asset is loaded in done() method
     */
    private void checkAndAssignTextureAtlas() {
        if(assetManager.isLoaded(textureAtlasPath)){
            assetManager.getLogger().info("TextureAtlas loaded");
            textureAtlas = assetManager.get(textureAtlasPath, TextureAtlas.class);
        } else {
            assetManager.getLogger().info("TextureAtlas not loaded");
        }
    }

    private void checkAndAssignSounds_A(){
        // 4 deaths
        // 7 eats
        // 5 sicks
        // Randomise 3 sounds from each batch
        Random random = new Random();
        float checkerValue;
        for(int soundsIter = 0; soundsIter < 3; soundsIter++) {
            checkerValue = random.nextFloat();
            if(checkerValue < ((1/4)*1)) {
                deathSounds.add(assetManager.get(sfx_path_01, Sound.class));
            } else if (checkerValue >= ((1/4)*2) && checkerValue < ((1/4)*2)) {
                deathSounds.add(assetManager.get(sfx_path_02, Sound.class));
            } else if (checkerValue >= ((1/4)*2) && checkerValue < ((1/4)*3)) {
                deathSounds.add(assetManager.get(sfx_path_03, Sound.class));
            } else if (checkerValue >= ((1/4)*3)) {
                deathSounds.add(assetManager.get(sfx_path_04, Sound.class));
            }

            if (checkerValue < ((1/7)*1)) {
                eatSounds.add(assetManager.get(sfx_path_05, Sound.class));
            } else if (checkerValue >= ((1/7)*1) && checkerValue < ((1/7)*2)) {
                eatSounds.add(assetManager.get(sfx_path_06, Sound.class));
            } else if (checkerValue >= ((1/7)*2) && checkerValue < ((1/7)*3)) {
                eatSounds.add(assetManager.get(sfx_path_07, Sound.class));
            } else if (checkerValue >= ((1/7)*3) && checkerValue < ((1/7)*4)) {
                eatSounds.add(assetManager.get(sfx_path_08, Sound.class));
            } else if (checkerValue >= ((1/7)*4) && checkerValue < ((1/7)*5)) {
                eatSounds.add(assetManager.get(sfx_path_09, Sound.class));
            } else if (checkerValue >= ((1/7)*5) && checkerValue < ((1/7)*6)) {
                eatSounds.add(assetManager.get(sfx_path_10, Sound.class));
            } else if (checkerValue >= ((1/7)*6)) {
                eatSounds.add(assetManager.get(sfx_path_11, Sound.class));
            }

            if (checkerValue < ((1/5)*1)) {
                sickSounds.add(assetManager.get(sfx_path_12, Sound.class));
            } else if (checkerValue >= ((1/5)*1) && checkerValue < ((1/5)*2)) {
                sickSounds.add(assetManager.get(sfx_path_13, Sound.class));
            } else if (checkerValue >= ((1/5)*2) && checkerValue < ((1/5)*3)) {
                sickSounds.add(assetManager.get(sfx_path_14, Sound.class));
            } else if (checkerValue >= ((1/5)*3) && checkerValue < ((1/5)*4)) {
                sickSounds.add(assetManager.get(sfx_path_15, Sound.class));
            } else if (checkerValue >= ((1/5)*4)) {
                sickSounds.add(assetManager.get(sfx_path_16, Sound.class));
            }
        }
    }

    private void checkMusic() {
        if(assetManager.isLoaded(musicPath)){
            assetManager.getLogger().info("Music loaded");
            music = assetManager.get(musicPath, Music.class);
            music.setLooping(true);
        } else {
            assetManager.getLogger().info("Music not loaded");
        }
    }

    private void checkAndAssignSounds(){
        // 4 deaths
        // 7 eats
        // 5 sicks
        // Randomise 3 sounds from each batch

        deathSounds.add(assetManager.get(sfx_path_01, Sound.class));
        deathSounds.add(assetManager.get(sfx_path_02, Sound.class));
        deathSounds.add(assetManager.get(sfx_path_03, Sound.class));

        eatSounds.add(assetManager.get(sfx_path_05, Sound.class));
        eatSounds.add(assetManager.get(sfx_path_06, Sound.class));
        eatSounds.add(assetManager.get(sfx_path_07, Sound.class));

        sickSounds.add(assetManager.get(sfx_path_12, Sound.class));
        sickSounds.add(assetManager.get(sfx_path_13, Sound.class));
        sickSounds.add(assetManager.get(sfx_path_14, Sound.class));

    }

    private void checkAndAssignSkin(){
        if(assetManager.isLoaded(skin01Path)){
            assetManager.getLogger().info("Skin loaded");
            skin = assetManager.get(skin01Path, Skin.class);
        } else {
            assetManager.getLogger().info("Skin not loaded");
        }
    }

    public void done() {
        assetManager.finishLoading();

        checkAndAssignTextureAtlas();
        checkAndAssignSounds();
        checkMusic();
        checkAndAssignSkin();
    }

    public void dispose() {
        assetManager.unload(textureAtlasPath);
        assetManager.unload(sfx_path_01);
        assetManager.unload(sfx_path_02);
        assetManager.unload(sfx_path_03);
        assetManager.unload(sfx_path_04);
        assetManager.unload(sfx_path_05);
        assetManager.unload(sfx_path_06);
        assetManager.unload(sfx_path_07);
        assetManager.unload(sfx_path_08);
        assetManager.unload(sfx_path_09);
        assetManager.unload(sfx_path_10);
        assetManager.unload(sfx_path_11);
        assetManager.unload(sfx_path_12);
        assetManager.unload(sfx_path_13);
        assetManager.unload(sfx_path_14);
        assetManager.unload(sfx_path_15);
        assetManager.unload(sfx_path_16);
        assetManager.unload(musicPath);
        assetManager.unload(skin01Path);
    }

}
