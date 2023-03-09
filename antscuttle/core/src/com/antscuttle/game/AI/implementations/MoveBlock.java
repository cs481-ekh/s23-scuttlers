package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelObject;

/**
 *
 * @author antho
 */
public class MoveBlock extends DecisionBlock{
    public enum MoveType { DIRECTIONAL, TARGET }
    public enum MoveDirection { LEFT, RIGHT, UP, DOWN, RANDOM }
    private final MoveType type;
    private final MoveDirection dir;
    private final LevelObject target; 
    
    public MoveBlock(MoveDirection dir, int duration){
        super();
        this.type = MoveType.DIRECTIONAL;
        this.target = null;
        this.dir = dir;
        this.duration = duration;
    }
    public MoveBlock(LevelObject target){
        super();
        this.type = MoveType.TARGET;
        this.target = target;
        this.dir = null;
        this.duration = Integer.MAX_VALUE;
    }
    public String getDirection(){
        return dir.toString();
    }
    public String getType(){
        return type.toString();
    }
    public Object getTarget(){
        return target;
    }
    @Override
    public void execute(Ant ant){
        super.execute(ant);
        // If directional, move coords to that dir
        // If target, get the targets coords and move towards it
    }
}
