package com.antscuttle.game.Level;

import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Level implements java.io.Serializable{
    // Fields
    protected Music soundtrack;
    protected Stage stage;
    protected String name;
    protected LevelData levelData;
    protected String tiledMapLoc;

    public LevelData getLevelData() {
        return levelData;
    }

    // Constructor
    public Level(Music soundtrack, Stage stage, String tiledMapLocation, String name) {
        this.soundtrack = soundtrack;
        this.stage = stage;
        this.tiledMapLoc = tiledMapLocation;
        this.name = name;
        this.levelData = new LevelData();
    }

    // Methods
    public void startLevel() {
        // Load the level's resources
        loadResources();

        // Add the level objects to the stage
        // for (LevelObject levelObject : levelObjects) {
        //     stage.addActor(levelObject);
        // }

        // Set the background texture
       // stage.getViewport().setBackground(backgroundTexture);

        // Start playing the soundtrack
        soundtrack.play();
    }
    public String getName(){
        return name;
    }
    public String getTiledMap(){
        return tiledMapLoc;
    }
    protected abstract void initLevelData();
    protected abstract void loadResources();
    //protected abstract List<LevelObject> createLevelObjects();
}
