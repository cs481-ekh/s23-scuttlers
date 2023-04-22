package com.antscuttle.game.Level;
/**
 * Level: abstract class for level implementations
 */
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
    /**
     * Init level stuff
     */
    public void startLevel() {
        // Load the level's resources
        loadResources();
        initLevelData();

        // Start playing the soundtrack
        if(soundtrack != null)
            soundtrack.play();
    }
    /**
     * 
     * @return name of the level
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return Tiled map file location
     */
    public String getTiledMap(){
        return tiledMapLoc;
    }
    /**
     * 
     * @return player's start tile position 
     */
    public Point getPlayerStartLoc(){
        return playerStartLoc;
    }
    /**
     * perform init tasks
     */
    protected abstract void initLevelData();
    /**
     * if using asset manager, load resources 
     */
    protected abstract void loadResources();
    /**
     * removes collidable objects from pathfinding graphs, so they will
     * be pathfound-around
     */
    protected void removeCollidablesFromGraph(){
        // Remove collidable levelobjects from the graph
        for(LevelObject obj: levelData.getCollidableObjects()){
            levelData.removeFromGraphs(obj);
        }
    }
    /**
     * Remove hazardous objects from intelligent graph
     */
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
    /**
     * 
     * @param batch 
     */
    public void render(SpriteBatch batch){
        if(levelData != null)
            for(LevelObject o : levelData.allObjects){
                o.render(batch);
            }
        
    }
    /**
     * 
     * @param obj
     * @param x tile pos
     * @param y tile pos
     */
    protected void addObjAtPos(LevelObject obj, int x, int y){
        obj.setPos(x*16, y*16);
        levelData.addToAllObjects(obj);
    }
    /**
     * initialize pathfinding graphs
     */
    protected void initGraphs(){
        levelData.setNormalIntelligenceGraph(GraphUtils.getFreshSimpleGraph());
        levelData.setZeroIntelligenceGraph(GraphUtils.getFreshSimpleGraph());
    }
}
