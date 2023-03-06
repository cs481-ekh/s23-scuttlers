
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.implementations.Human;

/**
 *
 * @author antho
 */
public class RootBlock extends DecisionBlock {
    public RootBlock(){
        super();
        execute(new Human("Fake"));
    }
}
