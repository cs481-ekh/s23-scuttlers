
package com.antscuttle.game.Level.levels;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.End;
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
    private static final String tiledMapLoc = "levels/level1.tmx";
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
    
    
    private static final Point rocks[] = {
        new Point(0,3),
        new Point(2,2),
        new Point(0,7),
        new Point(1,1)
    };
    private static final Point walls[] = {
        new Point(0,21),
        new Point(1,21),
        new Point(2,21),
        new Point(3,21),
        new Point(4,21),
        new Point(5,21),
        new Point(6,21),
        new Point(7,21),
        new Point(8,21),
        new Point(9,21),
        new Point(0,20),
        new Point(16,20),
        new Point(10,21),
        new Point(0,19),
        new Point(16,19),
        new Point(10,20),
        new Point(26,20),
        new Point(10,19),
        new Point(16,21),
        new Point(17,21),
        new Point(18,21),
        new Point(19,21),
        new Point(20,21),
        new Point(21,21),
        new Point(22,21),
        new Point(23,21),
        new Point(24,21),
        new Point(25,21),
        new Point(26,21),
        new Point(26,19),
        new Point(4,20),
        new Point(18,20),
        new Point(3, 20),
        new Point(7, 20),
        new Point(22, 20),
        new Point(2, 19),
        new Point(7,19),
        new Point(9,19),
        new Point(19,19),
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
        new Point(25,20),
        
    };
    private static final Point playerStartLoc = new Point(25,0);
    private static final Point endLocs[] = {
        new Point(15,19),
        new Point(14,19),
        new Point(13,19),
        new Point(12,19),
        new Point(11,19)
    };
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
        
        
        // Create Trees
        LevelObject obj;
        for(Point p: lowerTreeLocs){
            // Lower tree
            obj = new Tree(null);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
            
            // Upper tree
            obj = new Tree(null);
            addObjAtPos(obj, p.x, p.y+1);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
        }
        // Create water
        for(Point p: waterLocs){
            obj = new Water(null);
            addObjAtPos(obj, p.x, p.y);
            levelData.addHazardousObject(obj);
        }
        
        // Add rocks
        for(Point p: rocks){
            obj = new Wall(null,5);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
            levelData.addCollidableObject(obj);
        }
        
        // Add Walls
        
        for(Point p: walls){
            obj = new Wall(null, 100);
            addObjAtPos(obj, p.x, p.y);
            levelData.addAttackableObject(obj);
        }
        
        // Create end zone
        for(Point p: endLocs){
            obj = new End();
            addObjAtPos(obj,p.x,p.y);
            levelData.addInteractableObject(obj);
        }
    }
}
