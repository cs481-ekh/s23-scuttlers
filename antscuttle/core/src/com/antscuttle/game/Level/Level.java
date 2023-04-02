package com.antscuttle.game.Level;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public abstract class Level implements java.io.Serializable{
    // Fields
    protected Music soundtrack;
    protected Stage stage;
    protected String backgroundTexture;
    protected String name;

    // Constructor
    public Level(Music soundtrack, Stage stage, String backgroundTexture, String name) {
        this.soundtrack = soundtrack;
        this.stage = stage;
        this.backgroundTexture = backgroundTexture;
        this.name = name;
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
    protected abstract void loadResources();
    //protected abstract List<LevelObject> createLevelObjects();
}
