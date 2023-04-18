
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.RootOptions;

/**
 *
 * @author antho
 */
public class RootBlock extends DecisionBlock {
    public RootBlock(){
        super(new RootOptions());
        execute(null, null);
    }
    @Override
    public void resetBlock(){};
}
