
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.Util.GameData;

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
    public void execute(GameData gameData, LevelData levelData){
        
        super.execute(gameData, levelData);
        // target.interact()?
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
