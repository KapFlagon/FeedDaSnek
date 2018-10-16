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

    /*
    Constructors
     */
    public GameAssetManager() {

        assetManager = new AssetManager();

        // initiate strings
        textureAtlasPath = "textures/FeedDaSnek.atlas";


        loadImages();
        loadFonts();
        /*
        // call methods to load assets
        loadMusic();
        loadSFX();
        */
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

    /*
    Loader methods
     */
    public void loadMusic() {

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

    }

    public void loadSkin() {

    }

    public void done() {
        assetManager.finishLoading();

        if(assetManager.isLoaded(textureAtlasPath)){
            assetManager.getLogger().info("TextureAtlas loaded");
            textureAtlas = assetManager.get(textureAtlasPath, TextureAtlas.class);
        } else {
            assetManager.getLogger().info("TextureAtlas not loaded");
        }



    }

    public void dispose() {
        assetManager.unload(textureAtlasPath);

    }

}
