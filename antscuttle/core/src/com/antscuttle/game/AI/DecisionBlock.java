
package com.antscuttle.game.AI;


import java.io.Serializable;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.Util.GameData;


/**
 *
 * @author antho
 */
public abstract class DecisionBlock implements Serializable{
    private Boolean executionResult = false;
    protected int duration;
    protected BlockOptions options;
    
    
    public DecisionBlock(BlockOptions blockOptions){
        this.options = blockOptions;
    }
    public boolean hasOptionOne(){
        return options.hasOptionOne();
    };
    public boolean hasOptionTwo(){
        return options.hasOptionTwo();
    };
    public String[] getAllOptionOnes(){
        return options.getAllOptionOnes();
    };
    public String[] getAllOptionTwos(){
        return options.getAllOptionTwos();
    };
    public BlockOptions getChosenOptions(){
        return options;
    }
    
    public boolean isFinished(){
        return executionResult;
    }

    public void finished(boolean bool) {
        executionResult = bool;
    }
    public int getDuration(){
        return duration;
    }
    public void execute(GameData gameData, LevelData levelData){
        // Temp code
        executionResult = true;
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return null;
    }
}
