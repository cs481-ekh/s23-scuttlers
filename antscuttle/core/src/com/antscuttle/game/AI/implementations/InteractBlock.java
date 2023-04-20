
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
    
    
    public InteractBlock(InteractOptions options){
        super(options);
    }
    
    @Override

    public void execute(GameData gameData, LevelData levelData, Ant dbOwner){

        MoveBlock moveBlock = new MoveBlock(new MoveOptions(options.getFirstOptionChoice()));
        // get the current ant 
        // BaseAnt ant = (BaseAnt) gameData.getCurrentAnt();

        // get the graph
        // Graph g = levelData.getLevelGraph(ant.getIntelligence())

        // get the ant coords
        // Point interactCoords = ant.getCoords();
        
        // get the levelObject from the graph
        // LevelObject target = g.getLevelObjectAtCoords(interactCoords)

        // interact with the levelObject
        // target.interact();
        
        
        if(!moveBlock.isFinished()) {
            moveBlock.execute(gameData, levelData) ;
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
        
    }
}
