
package com.antscuttle.game.AI;

import com.antscuttle.game.Ant.Ant;

/**
 *
 * @author antho
 */
public abstract class DecisionBlock {
    private Boolean executionResult = null;
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
    
    public boolean getExecutionResult(){
        return executionResult;
    }
    public int getDuration(){
        return duration;
    }
    public void execute(Ant ant){
        // Temp code
        executionResult = true;
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return null;
    }
}
