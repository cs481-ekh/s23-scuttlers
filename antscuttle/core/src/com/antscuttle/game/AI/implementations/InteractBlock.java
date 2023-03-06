
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelObject;

/**
 *
 * @author antho
 */
public class InteractBlock extends DecisionBlock{
    private LevelObject target;
    
    public InteractBlock(LevelObject target){
        super();
        this.target = target;
        this.duration = 1;
    }
    
    @Override
    public void execute(Ant ant){
        super.execute(ant);
        // target.interact()?
    }
}
