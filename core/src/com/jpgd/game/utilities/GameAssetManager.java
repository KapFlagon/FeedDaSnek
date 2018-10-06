package com.jpgd.game.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {

    AssetManager assetManager;

    // asset paths
    private String musicPath;
    private String fontPath;
    private String textureAtlasPath;
    private String skin01Path;

    // asset variables
    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private TextureAtlas textureAtlas;
    private Skin skin;

    public GameAssetManager() {
        assetManager = new AssetManager();

        // initiate strings
        //textureAtlasPath = "android/assets/TetrisAtlas.atlas";

        // call methods to load assets
        loadMusic();
        loadFonts();
        loadImages();
        loadSFX();
    }

    public void loadMusic() {

    }

    public void loadFonts() {
        font = new BitmapFont();
        labelStyle = new Label.LabelStyle(font, Color.WHITE);
    }

    public void loadImages() {
        assetManager.load(textureAtlasPath, TextureAtlas.class);
    }

    public void loadSFX() {

    }

    public void loadSkin() {

    }

    public void done() {
        textureAtlas = assetManager.get(textureAtlasPath, TextureAtlas.class);
    }

    public void dispose() {
        assetManager.dispose();
    }

}
