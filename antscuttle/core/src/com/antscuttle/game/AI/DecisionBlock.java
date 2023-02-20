
package com.antscuttle.game.AI;

/**
 *
 * @author antho
 */
public abstract class DecisionBlock {
    private Boolean executionResult = null;
    
    public boolean getExecutionResult(){
        if(executionResult == null)
            throw new NullPointerException("You must execute the current block before calling next");
        return executionResult;
    }
    
    public void execute(){
        // Temp code
        executionResult = true;
    }
}
