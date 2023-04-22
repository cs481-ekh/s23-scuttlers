
package com.antscuttle.game.AI.options;

import com.antscuttle.game.AI.BlockOptions;

/**
 * Options for InteractBlock
 * @author antho
 */
public class InteractOptions extends BlockOptions{
    private static final String targetTypes[] = { "Door"};
    public InteractOptions(String option1){
        super(option1, null);
    }

    @Override
    public String[] getAllOptionOnes() {
        return targetTypes;
    }

    @Override
    public String[] getAllOptionTwos() {
        return null;
    }

    @Override
    public boolean hasOptionOne() {
        return true;
    }

    @Override
    public boolean hasOptionTwo() {
        return false;
    }
}
