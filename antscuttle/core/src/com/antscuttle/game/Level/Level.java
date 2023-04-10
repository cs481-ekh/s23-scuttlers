package com.antscuttle.game.Level;

import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.Util.GraphUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.awt.Point;

public abstract class Level implements java.io.Serializable{
    // Fields
    protected Music soundtrack;
    protected Stage stage;
    protected String name;
    protected LevelData levelData;
    protected String tiledMapLoc;
    protected Point playerStartLoc;

    public LevelData getLevelData() {
        return levelData;
    }

    // Constructor
    public Level(Music soundtrack, 
            Stage stage, 
            String tiledMapLocation, 
            String name,
            Point playerStartLoc) {
        this.soundtrack = soundtrack;
        this.stage = stage;
        this.tiledMapLoc = tiledMapLocation;
        this.name = name;
        this.levelData = new LevelData();
        this.playerStartLoc = playerStartLoc;
    }

    // Methods
    public void startLevel() {
        // Load the level's resources
        loadResources();
        initLevelData();

        // Start playing the soundtrack
        if(soundtrack != null)
            soundtrack.play();
    }
    public String getName(){
        return name;
    }
    public String getTiledMap(){
        return tiledMapLoc;
    }
    public Point getPlayerStartLoc(){
        return playerStartLoc;
    }
    protected abstract void initLevelData();
    protected abstract void loadResources();
    
    protected void removeCollidablesFromGraph(){
        // Remove collidable levelobjects from the graph
        for(LevelObject obj: levelData.getCollidableObjects()){
            levelData.removeFromGraphs(obj);
        }
    }
    protected void removeHazardsFromGraph(){
        // Removes hazardous objs from normalIntGraph, but 
        // not zeroIntGraph
        for(LevelObject obj: levelData.getHazardousObjects()){
            GraphUtils.removeFromGraph(
                levelData.getLevelGraph(1),
                (int)obj.getX(),
                (int)obj.getY());
        }
    }
    public void render(SpriteBatch batch){
        if(levelData != null)
            for(LevelObject o : levelData.allObjects){
                o.render(batch);
            }
        
    }
    protected void addObjAtPos(LevelObject obj, int x, int y){
        obj.setPos(x*16, y*16);
        levelData.addToAllObjects(obj);
    }
    protected void initGraphs(){
        levelData.setNormalIntelligenceGraph(GraphUtils.getFreshSimpleGraph());
        levelData.setZeroIntelligenceGraph(GraphUtils.getFreshSimpleGraph());
    }
}
