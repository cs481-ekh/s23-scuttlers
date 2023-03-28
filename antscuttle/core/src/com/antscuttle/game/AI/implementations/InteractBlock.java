
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.Ant.Ant;

/**
 *
 * @author antho
 */
public class InteractBlock extends DecisionBlock{
    
    
    public InteractBlock(InteractOptions options){
        super(options);
        this.duration = 1; // I don't know if we even need duration
    }
    
    @Override
    public void execute(Ant ant){
        super.execute(ant);
        // target.interact()?
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
