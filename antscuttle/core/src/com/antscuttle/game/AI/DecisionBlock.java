
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
    // Execution result represents whether the block was able to
    // achieve its goal. This is used during tree traversal.
    private Boolean executionResult;
    // Finished represents whether the block is done executing.
    // This is used to determine whether to continue using this block.
    private boolean finished = false;
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
    
    public boolean getExecutionResult(){
        return executionResult;
    }

    public void setExecutionResult(boolean bool) {
        executionResult = bool;
    }
    public Boolean isFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    public void execute(GameData gameData, LevelData levelData){
        // Temp code
        executionResult = true;
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return null;
    }
}
