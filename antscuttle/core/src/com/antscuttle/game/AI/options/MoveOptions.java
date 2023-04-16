
package com.antscuttle.game.AI.options;

import com.antscuttle.game.AI.BlockOptions;

/**
 *
 * @author antho
 */
public class MoveOptions extends BlockOptions{
    
    private static final String targetTypes[] = { "Ant",
        "Door",
        "Tree",
        "Wall",
        "Pressure Plate",
        "Water",
        "Lava",
        "Random",
        "End",
        "Rock"};
    
    public MoveOptions(String option1){
        super(option1, null);
    }
    @Override
    public boolean hasOptionOne() {
        return true;
    }

    @Override
    public boolean hasOptionTwo() {
        return false;
    }

    @Override
    public String[] getAllOptionOnes() {
        return targetTypes;
    }

    @Override
    public String[] getAllOptionTwos() {
        return null;
    }
    
}
