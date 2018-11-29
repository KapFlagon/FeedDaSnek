package com.jpgd.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
    private String localRoot;
    private String textureAtlasPath;
    private String skin01Path;

    // asset variables
    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private Music music;
    private FileHandle fileHandle_highScores;

    /*
    Constructors
     */
    public GameAssetManager() {

        assetManager = new AssetManager();

        /*
        Initiate strings for file paths
         */
        // Texture Atlas
        textureAtlasPath = "textures/FeedDaSnek.atlas";

        // Sfx
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

        // Music
        musicPath = "music/FeedDaSnek_Theme.mp3";

        // Skins
        skin01Path = "skins/shade/uiskin.json";


        loadImages();
        loadFonts();
        loadSFX();
        loadMusic();
        loadSkin();
        loadDataFiles();
    }


    /*
    Getters
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Label.LabelStyle getLabelStyle() {
        return labelStyle;
    }

    public Skin getSkin() {
        return skin;
    }

    public FileHandle getFileHandle_highScores() {
        return fileHandle_highScores;
    }


    /*
    Getters for paths
     */

    public String getMusicPath() {
        return musicPath;
    }

    public String getSfx_path_01() {
        return sfx_path_01;
    }

    public String getSfx_path_02() {
        return sfx_path_02;
    }

    public String getSfx_path_03() {
        return sfx_path_03;
    }

    public String getSfx_path_04() {
        return sfx_path_04;
    }

    public String getSfx_path_05() {
        return sfx_path_05;
    }

    public String getSfx_path_06() {
        return sfx_path_06;
    }

    public String getSfx_path_07() {
        return sfx_path_07;
    }

    public String getSfx_path_08() {
        return sfx_path_08;
    }

    public String getSfx_path_09() {
        return sfx_path_09;
    }

    public String getSfx_path_10() {
        return sfx_path_10;
    }

    public String getSfx_path_11() {
        return sfx_path_11;
    }

    public String getSfx_path_12() {
        return sfx_path_12;
    }

    public String getSfx_path_13() {
        return sfx_path_13;
    }

    public String getSfx_path_14() {
        return sfx_path_14;
    }

    public String getSfx_path_15() {
        return sfx_path_15;
    }

    public String getSfx_path_16() {
        return sfx_path_16;
    }

    public String getLocalRoot() {
        return localRoot;
    }

    public String getTextureAtlasPath() {
        return textureAtlasPath;
    }

    public String getSkin01Path() {
        return skin01Path;
    }

    /*
        Loader methods
         */
    public void loadMusic() {
        assetManager.load(musicPath, Music.class);
        assetManager.getLogger().info("Music asset is loaded.");
    }

    public void loadFonts() {
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle(font, Color.WHITE);
    }

    public void loadImages() {
        assetManager.load(textureAtlasPath, TextureAtlas.class);
        assetManager.getLogger().info("TextureAtlas asset is loaded.");
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
        assetManager.getLogger().info("SFX assets are loaded.");
    }

    public void loadSkin() {
        assetManager.load(skin01Path, Skin.class);
    }

    // TODO Move all asset assignments into their relevant state classes
    // TODO move all unloading into the relevant state classes
    public void loadDataFiles() {
        // Local data storage
        if(Gdx.files.isExternalStorageAvailable()) {
            // TODO figure out an AssetManager centric means to load the data before assigning it to a variable
            fileHandle_highScores = Gdx.files.external("FeedDaSnek/data/SavedData.txt");
        } else {
            fileHandle_highScores = Gdx.files.local("FeedDaSnek/data/SavedData.txt");
        }
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


    private void checkMusic() {
        if(assetManager.isLoaded(musicPath)){
            assetManager.getLogger().info("Music loaded");
            music = assetManager.get(musicPath, Music.class);
            music.setLooping(true);
        } else {
            assetManager.getLogger().info("Music not loaded");
        }
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
        //checkAndAssignSounds();
        //checkMusic();
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
