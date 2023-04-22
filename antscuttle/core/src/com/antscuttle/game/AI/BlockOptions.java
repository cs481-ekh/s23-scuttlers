
package com.antscuttle.game.AI;

import java.io.Serializable;

/**
 *
 * @author antho
 */
public abstract class BlockOptions implements Serializable{
    String optionOne, optionTwo;
    /**
     * 
     * @param chosenOptionOne
     * @param chosenOptionTwo 
     */
    public BlockOptions(String chosenOptionOne, 
            String chosenOptionTwo)
    {
        this.optionOne = chosenOptionOne;
        this.optionTwo = chosenOptionTwo;
    }
    /**
     * 
     * @return first option choice 
     */
    public String getFirstOptionChoice(){
        return optionOne;
    }
    /**
     * 
     * @return second option choice 
     */
    public String getSecondOptionChoice(){
        return optionTwo;
    }
    /**
     * 
     * @return whether this type of blockoptions has a first choice 
     */
    public abstract boolean hasOptionOne();
    /**
     * 
     * @return whether this type of blockoptions has a second choice
     */
    public abstract boolean hasOptionTwo();
    /**
     * 
     * @return available choices 
     */
    public abstract String[] getAllOptionOnes();
    /**
     * 
     * @return available choices
     */
    public abstract String[] getAllOptionTwos();
}
