
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.BaseAnt;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.ai.pfa.Graph;

/**
 *
 * @author antho
 */
public class InteractBlock extends DecisionBlock{
    
    MoveBlock moveBlock;
    public InteractBlock(InteractOptions options){
        super(options);
        moveBlock = new MoveBlock(new MoveOptions(options.getFirstOptionChoice()));
    }
    
    @Override

    public void execute(GameData gameData, LevelData levelData, Ant dbOwner){

        
    
        //System.out.println(moveBlock.getExecutionResult());
        if(!moveBlock.isFinished()) {
            moveBlock.execute(gameData, levelData, dbOwner) ;
        }  else {
            
            // Successfully traveled to target
            if (moveBlock.getExecutionResult()) {
                InteractableLevelObject ilo = (InteractableLevelObject) moveBlock.objectTarget;
                ilo.interact(gameData.getCurrentAnt(), levelData);
                setFinished(true);
                setExecutionResult(true);
            }  else { // Could not travel to target
                setFinished(true);
                setExecutionResult(false);
            }
        }
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
    @Override
    public void resetBlock(){
        moveBlock = new MoveBlock(new MoveOptions(options.getFirstOptionChoice()));
    }
}
