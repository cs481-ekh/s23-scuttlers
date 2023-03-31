package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Ant.BaseAnt;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.ai.pfa.Graph;

public class MoveBlock extends DecisionBlock {
    
    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(GameData gameData, LevelData levelData){
        BaseAnt ant = (BaseAnt) gameData.getCurrentAnt();
        // Graph g = levelData.getLevelGraph(ant.getIntelligence());
        
        System.out.println(ant.getCoords());
        System.out.println(options.getFirstOptionChoice());
        ant.setCoords(1, 1);
        System.out.println(ant.getCoords());

        // If directional, move coords to that dir
        // If target, get the targets coords and move towards it
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
