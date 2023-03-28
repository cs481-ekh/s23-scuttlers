package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Ant.Ant;

/**
 *
 * @author antho
 */
public class MoveBlock extends DecisionBlock{
    
    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(Ant ant){
        super.execute(ant);
        // If directional, move coords to that dir
        // If target, get the targets coords and move towards it
    }

}
