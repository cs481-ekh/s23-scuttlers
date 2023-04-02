
package com.antscuttle.game.AI;

import java.io.Serializable;

/**
 *
 * @author antho
 */
public abstract class BlockOptions implements Serializable{
    String optionOne, optionTwo;
    
    public BlockOptions(String chosenOptionOne, 
            String chosenOptionTwo)
    {
        this.optionOne = chosenOptionOne;
        this.optionTwo = chosenOptionTwo;
    }
    
    public String getFirstOptionChoice(){
        return optionOne;
    }
    
    public String getSecondOptionChoice(){
        return optionTwo;
    }
    public abstract boolean hasOptionOne();
    public abstract boolean hasOptionTwo();
    public abstract String[] getAllOptionOnes();
    public abstract String[] getAllOptionTwos();
}
