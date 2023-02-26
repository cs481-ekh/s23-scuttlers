
package com.antscuttle.game.AI;

import com.antscuttle.game.Ant.Ant;

/**
 *
 * @author antho
 */
public abstract class DecisionBlock {
    private Boolean executionResult = null;
    protected int duration;
    
    public boolean getExecutionResult(){
        if(executionResult == null)
            throw new NullPointerException("You must execute the current block before calling next");
        return executionResult;
    }
    public int getDuration(){
        return duration;
    }
    public void execute(Ant ant){
        // Temp code
        executionResult = true;
    }
}
