
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.BaseAnt;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.ai.pfa.Graph;

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
        // get the current ant 
        BaseAnt ant = (BaseAnt) gameData.getCurrentAnt();

        // get the graph
        Graph g = levelData.getLevelGraph(ant.getIntelligence())

        // get the ant coords
        // Point interactCoords = ant.getCoords();
        
        // get the levelObject from the graph
        // LevelObject target = g.getLevelObjectAtCoords(interactCoords)

        // interact with the levelObject
        // target.interact();
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
