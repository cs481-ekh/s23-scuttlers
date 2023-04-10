
package com.antscuttle.game.Level.levels;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.Tree;
import com.antscuttle.game.LevelObject.implementations.Wall;
import com.antscuttle.game.LevelObject.implementations.Water;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.TileUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public final class Level1 extends Level{
    private static final String tiledMapLoc = "levels/level1plain.tmx";
    private static final Point zombieLocs[] = {
        new Point(3,14),
        new Point(9,13),
        new Point(19,16),
        new Point(22,17),
        new Point(23,15),
        new Point(24,17)
    };
    private static final Point lowerTreeLocs[] = {
        new Point(0,12),
        new Point(0,14),
        new Point(0,17),
        new Point(1,13),
        new Point(1,16),
        new Point(4,15),
        new Point(5,9),
        new Point(5,16),
        new Point(6,7),
        new Point(6,11),
        new Point(7,14),
        new Point(16,7),
        new Point(16,11),
        new Point(17,6),
        new Point(17,12),
        new Point(18,7),
        new Point(18,11),
        new Point(18,15),
        new Point(19,7),
        new Point(19,12),
        new Point(19,14),
        new Point(19,17),
        new Point(20,6),
        new Point(20,13),
        new Point(21,7),
        new Point(22,8),
        new Point(23,8),
        new Point(24,10),
        new Point(25, 6),
        new Point(25, 11),
        new Point(25, 14),
        new Point(26, 12),
        new Point(26, 15),
        new Point(26, 17)
    };
    private static final Point waterLocs[] = {
        new Point(0,0),
        new Point(0,1),
        new Point(0,2),
        new Point(0,4),
        new Point(0,5),
        new Point(0,6),
        new Point(0,8),
        new Point(0,9),
        new Point(0,10),
        new Point(1,0),
        new Point(1,2),
        new Point(1,3),
        new Point(1,4),
        new Point(1,5),
        new Point(1,6),
        new Point(1,7),
        new Point(1,8),
        new Point(1,9),
        new Point(2,0),
        new Point(2,1),
        new Point(2,3),
        new Point(2,4),
        new Point(3,0),
        new Point(3,1),
        new Point(4,0),
        new Point(4,1),
        new Point(5,0)
    };
    private static final Point topRightWaterLocs[]={
        new Point(1, 11),
        new Point(2, 10),
        new Point(3, 5),
        new Point(5, 2),
        new Point(6, 1),
        new Point(7, 0)
    };
    private static final Point topRightWaterJoinerLocs[]={
        new Point(1, 10),
        new Point(2, 5),
        new Point(3, 2),
        new Point(5, 1),
        new Point(6, 0)
    };
    private static final Point topWaterLocs[]={
        new Point(0,11),
        new Point(4, 2)
    };
    private static final Point rightWaterLocs[]={
        new Point(2,6),
        new Point(2,7),
        new Point(2,8),
        new Point(2,9),
        new Point(3,3),
        new Point(3,4)
    };
    private static final Point bigWaterRock = new Point(0,3);
    private static final Point rightWaterRock = new Point(2,2);
    private static final Point leftWaterRocks[]={
        new Point(0,7),
        new Point(1,1)
    };
    private static final Point leftWallTopLeft = new Point(0,21);
    private static final Point leftWallTop[] = {
        new Point(1,21),
        new Point(2,21),
        new Point(3,21),
        new Point(4,21),
        new Point(5,21),
        new Point(6,21),
        new Point(7,21),
        new Point(8,21),
        new Point(9,21)
    };
    private static final Point leftWallTopRight = new Point(10,21);
    private static final Point wallLeft[] = {
        new Point(0,20),
        new Point(16,20)
    };
    private static final Point wallBottomLeft[] = {
        new Point(0,19),
        new Point(16,19)
    };

    private static final Point wallRight[] = {
        new Point(10,20),
        new Point(26,20)
    };

    private static final Point leftWallBottomRight = new Point(10,19);
    private static final Point rightWallTopLeft = new Point(16,21);
    private static final Point rightWallTop[] = {
        new Point(17,21),
        new Point(18,21),
        new Point(19,21),
        new Point(20,21),
        new Point(21,21),
        new Point(22,21),
        new Point(23,21),
        new Point(24,21),
        new Point(25,21),
    };
    private static final Point rightWallTopRight = new Point(26,21);
    private static final Point rightWallBottomRight = new Point(26,19);
    private static final Point wallDots[] = {
        new Point(4,20),
        new Point(18,20)
    };
    private static final Point wallCracked[] = {
        new Point(3, 20),
        new Point(7, 20),
        new Point(22, 20)
    };
    private static final Point wallCrackedBottom[] = {
        new Point(2, 19),
        new Point(7,19),
        new Point(9,19),
        new Point(19,19)
    };
    private static final Point wallBottom[] = {
        new Point(1,19),
        new Point(3,19),
        new Point(4,19),
        new Point(5,19),
        new Point(6,19),
        new Point(8,19),
        new Point(17,19),
        new Point(18,19),
        new Point(20,19),
        new Point(21,19),
        new Point(22,19),
        new Point(23,19),
        new Point(24,19),
        new Point(25,19),
    };
    private static final Point wallMiddle[] = {
        new Point(1,20),
        new Point(2,20),
        new Point(5,20),
        new Point(6,20),
        new Point(8,20),
        new Point(9,20),
        new Point(17,20),
        new Point(19,20),
        new Point(20,20),
        new Point(21,20),
        new Point(23,20),
        new Point(24,20),
        new Point(25,20)
    };
    private static final Point playerStartLoc = new Point(25,0);
    
    /* Constructor */
    public Level1(){
        
        super(null,null,tiledMapLoc, "Level 1", playerStartLoc);
        
    }
    @Override
    protected void loadResources() {
        // Load assests if using asset manager
    }
    
    @Override
    protected void initLevelData() {
        initLevelObjects();
        initGraphs();
        initEnemies();
        removeCollidablesFromGraph();
        removeHazardsFromGraph();
        
    }
    
    
    protected void initEnemies(){
        ClassFactory cf = new ClassFactory();
        Ant ant;
        for(Point p: zombieLocs){
            ant = cf.newAntInstance(Zombie.class, "npc");
            ant.setPos(p.x*32, p.y*32);
            levelData.addEnemy(ant);
        }
    }
    protected void initLevelObjects(){
        // Get tiles
        
        TextureRegion[][] splitTiles = TileUtils.getAllTextures();
        
        
        // Create Trees
        LevelObject obj;
        for(Point p: lowerTreeLocs){
            // Lower tree
            obj = new Tree(splitTiles[11][15]);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
            
            // Upper tree
            obj = new Tree(splitTiles[10][15]);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y+1);
        }
        // Create water
        for(Point p: waterLocs){
            obj = new Water(splitTiles[1][3]);
            addObjAtPos(obj, p.x, p.y);
            levelData.addHazardousObject(obj);
        }
        for(Point p: topRightWaterLocs){
            obj = new Water(splitTiles[0][4]);
            addObjAtPos(obj, p.x, p.y);
        }
        for(Point p: topRightWaterJoinerLocs){
            obj = new Water(splitTiles[2][0]);
            addObjAtPos(obj, p.x, p.y);
        }
        for(Point p: topWaterLocs){
            obj = new Water(splitTiles[0][3]);
            addObjAtPos(obj, p.x, p.y);
        }
        for(Point p: rightWaterLocs){
            obj = new Water(splitTiles[1][4]);
            addObjAtPos(obj, p.x, p.y);
        }
        
        // Add rocks
        obj = new Wall(splitTiles[24][54],1);
        addObjAtPos(obj, bigWaterRock.x, bigWaterRock.y);
        levelData.addAttackableObject(obj);
        levelData.addCollidableObject(obj);
        
        obj = new Wall(splitTiles[24][54], 1);
        addObjAtPos(obj, rightWaterRock.x, rightWaterRock.y);
        levelData.addAttackableObject(obj);
        levelData.addCollidableObject(obj);
        
        for(Point p: leftWaterRocks){
            obj = new Water(splitTiles[24][55]);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
        }
        // Add Walls
        obj = new Wall(splitTiles[14][35], 200);
        obj.setZIndex(5);
        addObjAtPos(obj, leftWallTopLeft.x, leftWallTopLeft.y);
        levelData.addAttackableObject(obj);
        
        for(Point p: leftWallTop){
            obj = new Wall(splitTiles[14][36], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        obj = new Wall(splitTiles[14][37], 200);
        obj.setZIndex(5);
        addObjAtPos(obj, leftWallTopRight.x, leftWallTopRight.y);
        levelData.addAttackableObject(obj);
        
        for(Point p: wallLeft){
            obj = new Wall(splitTiles[15][38], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        for(Point p: wallBottomLeft){
            obj = new Wall(splitTiles[15][35], 200);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        for(Point p: wallRight){
            obj = new Wall(splitTiles[15][40], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        obj = new Wall(splitTiles[15][37], 200);
        addObjAtPos(obj, leftWallBottomRight.x, leftWallBottomRight.y);
        levelData.addAttackableObject(obj);
        levelData.addCollidableObject(obj);
        
        obj = new Wall(splitTiles[14][38], 200);
        obj.setZIndex(5);
        addObjAtPos(obj, rightWallTopLeft.x, rightWallTopLeft.y);
        levelData.addAttackableObject(obj);
        
        for(Point p: rightWallTop){
            obj = new Wall(splitTiles[14][39], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        obj = new Wall(splitTiles[14][40], 200);
        obj.setZIndex(5);
        addObjAtPos(obj, rightWallTopRight.x, rightWallTopRight.y);
        levelData.addAttackableObject(obj);
        
        for(Point p: wallDots){
            obj = new Wall(splitTiles[18][40], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        for(Point p: wallCracked){
            obj = new Wall(splitTiles[20][40], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        for(Point p: wallCrackedBottom){
            obj = new Wall(splitTiles[20][38], 200);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
        }
        
        obj = new Wall(splitTiles[20][39], 200);
        addObjAtPos(obj, rightWallBottomRight.x, rightWallBottomRight.y);
        levelData.addAttackableObject(obj);
        levelData.addCollidableObject(obj);
        
        for(Point p: wallBottom){
            obj = new Wall(splitTiles[15][34], 200);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
        }
        
        for(Point p: wallMiddle){
            obj = new Wall(splitTiles[15][39], 200);
            obj.setZIndex(5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
    }
}
