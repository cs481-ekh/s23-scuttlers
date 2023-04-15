package com.antscuttle.game.Level.levels;


import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.Door;
import com.antscuttle.game.LevelObject.implementations.End;
import com.antscuttle.game.LevelObject.implementations.PressurePlate;
import com.antscuttle.game.LevelObject.implementations.Tree;
import com.antscuttle.game.LevelObject.implementations.Wall;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.TileUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;


public class Level2 extends Level{
    private static final String tiledMapLoc = "levels/level2plain.tmx";
    private static final Point playerStartLoc = new Point(25, 0);
    
    private static final Point enemies[] = {
        new Point(5,3),
        new Point(3,2),
        new Point(6,12),
        new Point(16,19),
        new Point(22,12),
        new Point(24,12),
        new Point(23,13),
        new Point(24,14),
        new Point(25,14),
        new Point(23,15),
        new Point(23,16),
        new Point(24,16),
        new Point(23,18)
    };
    private static final Point walls[] = {
        new Point(1,0),
        new Point(2,0),
        new Point(3,0),
        new Point(4,0),
        new Point(5,0),
        new Point(6,0),
        new Point(7,0),
        new Point(8,0),
        new Point(9,0),
        new Point(10,0),
        new Point(11,0),
        new Point(12,0),
        new Point(13,0),
        new Point(14,0),
        new Point(15,0),
        new Point(16,0),
        new Point(17,0),
        new Point(18,0),
        new Point(19,0),
        new Point(20,0),
        new Point(21,0),
        new Point(1,5),
        new Point(2,5),
        new Point(3,5),
        new Point(4,5),
        new Point(5,5),
        new Point(18,5),
        new Point(19,5),
        new Point(20,5),
        new Point(21,5),
        new Point(22,5),
        new Point(23,5),
        new Point(24,5),
        new Point(25,5),
        new Point(1,10),
        new Point(2,10),
        new Point(3,10),
        new Point(4,10),
        new Point(5,10),
        new Point(6,10),
        new Point(7,10),
        new Point(8,10),
        new Point(9,10),
        new Point(10,10),
        new Point(11,10),
        new Point(12,10),
        new Point(22,10),
        new Point(23,10),
        new Point(24,10),
        new Point(25,10),
        new Point(1,15),
        new Point(2,15),
        new Point(3,15),
        new Point(4,15),
        new Point(5,15),
        new Point(6,15),
        new Point(8,15),
        new Point(9,15),
        new Point(10,15),
        new Point(11,15),
        new Point(12,15),
        new Point(13,15),
        new Point(14,15),
        new Point(15,15),
        new Point(16,15),
        new Point(1,21),
        new Point(2,21),
        new Point(3,21),
        new Point(4,21),
        new Point(5,21),
        new Point(6,21),
        new Point(7,21),
        new Point(8,21),
        new Point(9,21),
        new Point(10,21),
        new Point(11,21),
        new Point(12,21),
        new Point(13,21),
        new Point(14,21),
        new Point(15,21),
        new Point(16,21),
        new Point(17,21),
        new Point(18,21),
        new Point(19,21),
        new Point(20,21),
        new Point(22,21),
        new Point(23,21),
        new Point(24,21),
        new Point(25,21),
        new Point(22,0),
        new Point(26,10),
        new Point(13,10),
        new Point(0,0),
        new Point(21,10),
        new Point(17,5),
        new Point(0,6),
        new Point(0,11),
        new Point(0,16),
        new Point(13,6),
        new Point(13,11),
        new Point(22,1),
        new Point(7,16),
        new Point(21,22),
        new Point(7,6),
        new Point(26,6),
        new Point(17,16),
        new Point(26,22),
        new Point(7,1),
        new Point(13,1),
        new Point(7,11),
        new Point(0,1),
        new Point(17,6),
        new Point(21,11),
        new Point(0,2),
        new Point(0,3),
        new Point(0,4),
        new Point(0,5),
        new Point(0,7),
        new Point(0,8),
        new Point(0,9),
        new Point(0,10),
        new Point(0,12),
        new Point(0,13),
        new Point(0,14),
        new Point(0,15),
        new Point(0,17),
        new Point(0,18),
        new Point(0,19),
        new Point(0,20),
        new Point(0,21),
        new Point(7,2),
        new Point(7,3),
        new Point(7,4),
        new Point(7,5),
        new Point(7,12),
        new Point(7,13),
        new Point(7,14),
        new Point(7,15),
        new Point(13,2),
        new Point(13,3),
        new Point(13,4),
        new Point(13,5),
        new Point(17,7),
        new Point(17,8),
        new Point(17,9),
        new Point(17,10),
        new Point(17,11),
        new Point(17,12),
        new Point(17,13),
        new Point(17,14),
        new Point(17,15),
        new Point(21,12),
        new Point(21,13),
        new Point(21,14),
        new Point(21,15),
        new Point(21,16),
        new Point(21,17),
        new Point(21,18),
        new Point(21,19),
        new Point(21,20),
        new Point(21,21),
        new Point(26,12),
        new Point(26,13),
        new Point(26,14),
        new Point(26,15),
        new Point(26,16),
        new Point(26,17),
        new Point(26,18),
        new Point(26,19),
        new Point(26,20),
        new Point(26,21),
        new Point(26,2),
        new Point(26,3),
        new Point(26,4),
        new Point(26,5),
        new Point(1,22),
        new Point(2,22),
        new Point(3,22),
        new Point(4,22),
        new Point(5,22),
        new Point(6,22),
        new Point(7,22),
        new Point(8,22),
        new Point(9,22),
        new Point(10,22),
        new Point(11,22),
        new Point(12,22),
        new Point(13,22),
        new Point(14,22),
        new Point(15,22),
        new Point(16,22),
        new Point(17,22),
        new Point(18,22),
        new Point(19,22),
        new Point(20,22),
        new Point(22,22),
        new Point(23,22),
        new Point(24,22),
        new Point(25,22),
        new Point(1,16),
        new Point(2,16),
        new Point(3,16),
        new Point(4,16),
        new Point(5,16),
        new Point(6,16),
        new Point(8,16),
        new Point(9,16),
        new Point(10,16),
        new Point(11,16),
        new Point(12,16),
        new Point(13,16),
        new Point(14,16),
        new Point(15,16),
        new Point(16,16),
        new Point(1,11),
        new Point(2,11),
        new Point(3,11),
        new Point(4,11),
        new Point(5,11),
        new Point(6,11),
        new Point(8,11),
        new Point(9,11),
        new Point(10,11),
        new Point(11,11),
        new Point(12,11),
        new Point(22,11),
        new Point(23,11),
        new Point(24,11),
        new Point(25,11),
        new Point(1,6),
        new Point(2,6),
        new Point(3,6),
        new Point(4,6),
        new Point(5,6),
        new Point(6,6),
        new Point(18,6),
        new Point(19,6),
        new Point(20,6),
        new Point(21,6),
        new Point(22,6),
        new Point(23,6),
        new Point(24,6),
        new Point(25,6),
        new Point(1,1),
        new Point(2,1),
        new Point(3,1),
        new Point(4,1),
        new Point(5,1),
        new Point(6,1),
        new Point(8,1),
        new Point(9,1),
        new Point(10,1),
        new Point(11,1),
        new Point(12,1),
        new Point(14,1),
        new Point(15,1),
        new Point(16,1),
        new Point(17,1),
        new Point(18,1),
        new Point(19,1),
        new Point(20,1),
        new Point(21,1),
        new Point(26,11),
        new Point(0,22),
        new Point(26,1),
        new Point(26,0)
    };
    private static final Point furniture[] = {
        new Point(1,14),
        new Point(5,14),
        new Point(6,14),
        new Point(2,3),
        new Point(3,3),
        new Point(4,3)
    };
    private static final Point end[] = {
        new Point(26,7),
        new Point(26,8),
        new Point(26,9)
    };
    
    
    public Level2(){
        // Temporary nulls
        super(null,null,tiledMapLoc, "Level 2", playerStartLoc);
    }
    
    @Override
    protected void loadResources() {
        
    }

    @Override
    protected void initLevelData() {
        initLevelObjects();
        initGraphs();
        initEnemies();
        removeCollidablesFromGraph();
        removeHazardsFromGraph();
    }

    private void initEnemies() {
    ClassFactory cf = new ClassFactory();
        Ant ant;
        for(Point p: enemies){
            ant = cf.newAntInstance(Zombie.class, "npc");
            ant.setPos(p.x*32, p.y*32);
            levelData.addEnemy(ant);
        }
    }

    private void initLevelObjects() {
        // Get tiles
        TextureRegion[][] splitTiles = TileUtils.getAllTextures();
        LevelObject obj;
        
        // Create Walls
        for(Point p:walls){
            obj = new Wall(null, 200);
            addObjAtPos(obj, p.x, p.y);
            levelData.addCollidableObject(obj);
        }
        
        // Furniture
        for(Point p:furniture){
            obj = new Tree(null);
            addObjAtPos(obj, p.x, p.y);
            levelData.addCollidableObject(obj);
        }
        // Doors
        TextureRegion closedDoorTex = splitTiles[0][33];
        TextureRegion openDoorTex = splitTiles[1][36];
        
        InteractableLevelObject door1 = new Door(closedDoorTex,openDoorTex);
        InteractableLevelObject door2 = new Door(closedDoorTex,openDoorTex);
        InteractableLevelObject door3 = new Door(closedDoorTex,openDoorTex);
        InteractableLevelObject door4 = new Door(closedDoorTex,openDoorTex);
        
        LevelObject doors[] = { door1, door2, door3, door4}; 
        
        addObjAtPos(door1, 3, 5);
        addObjAtPos(door2, 3, 10);
        addObjAtPos(door3, 10, 15);
        addObjAtPos(door4, 23, 10);
        
        for (LevelObject door: doors){    
            levelData.addCollidableObject(door);
            levelData.addAttackableObject(door);
            levelData.addInteractableObject(door);
        }
        
        // Pressure Plates
        TextureRegion[] plateTex = TileUtils.getPressurePlateTextures()[0];
        LevelObject plate1 = new PressurePlate(plateTex,door2);
        LevelObject plate2 = new PressurePlate(plateTex,door3);
        LevelObject plate3 = new PressurePlate(plateTex,door4);
        
        LevelObject plates[] = { plate1, plate2, plate3 };
        
        addObjAtPos(plate1,10,4);
        addObjAtPos(plate2,3,13);
        addObjAtPos(plate3,3,18);
        
        for(LevelObject plate: plates){
            levelData.addInteractableObject(plate);
        }
        
        for(Point p: end){
            obj = new End();
            addObjAtPos(obj, p.x, p.y);
            levelData.addInteractableObject(obj);
        }
    }
}
