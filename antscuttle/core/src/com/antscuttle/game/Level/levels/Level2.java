package com.antscuttle.game.Level.levels;


import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Level.LevelData;
import java.awt.Point;


public class Level2 extends Level{
    private static final String tiledMapLoc = "levels/level2.tmx";
    private static final Point playerStartLoc = new Point(25, 0);
    public Level2(){
        // Temporary nulls
        super(null,null,tiledMapLoc, "Level 2", playerStartLoc);
    }
    
    @Override
    protected void loadResources() {
        
    }

    @Override
    protected void initLevelData() {
        
    }
}
