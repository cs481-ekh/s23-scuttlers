
package com.antscuttle.game.AI.options;

import com.antscuttle.game.AI.BlockOptions;

/**
 * Options for AttackBlock
 * @author antho
 */
public class AttackOptions extends BlockOptions{
    private static final String attackTypes[] = { "Ranged", "Melee" };
    private static final String targetTypes[] = { "Ant",
        "Door",
        "Tree",
        "Wall"};
    public AttackOptions(String option1, String option2){
        super(option1, option2);
    }

    @Override
    public String[] getAllOptionOnes() {
        return attackTypes;
    }

    @Override
    public String[] getAllOptionTwos() {
        return targetTypes;
    }

    @Override
    public boolean hasOptionOne() {
        return true;
    }

    @Override
    public boolean hasOptionTwo() {
        return true;
    }
    
}
