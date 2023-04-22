
package com.antscuttle.game.AI.options;

import com.antscuttle.game.AI.BlockOptions;

/**
 * Blank options for RootBlock, just because it needs something
 * @author antho
 */
public class RootOptions extends BlockOptions{

    public RootOptions(){
        super(null,null);
    }
    @Override
    public boolean hasOptionOne() {
        return false;
    }

    @Override
    public boolean hasOptionTwo() {
        return false;
    }

    @Override
    public String[] getAllOptionOnes() {
        return null;
    }

    @Override
    public String[] getAllOptionTwos() {
        return null;
    }
    
}
