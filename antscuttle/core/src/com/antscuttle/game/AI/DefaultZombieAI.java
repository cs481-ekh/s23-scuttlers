
package com.antscuttle.game.AI;

import com.antscuttle.game.AI.implementations.AttackBlock;
import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.AI.options.AttackOptions;
import com.antscuttle.game.AI.options.MoveOptions;

/**
 *
 * @author antho
 */
public class DefaultZombieAI extends AI{    
    public DefaultZombieAI(Node root, String name){
        super(root, name);
        DecisionBlock move1 = new AttackBlock(new AttackOptions("Melee","Ant"));
        Node n = new Node(move1);
        root.addChild(n);
    }

    @Override
    public String toString() {
        return "Default Zombie AI";
    }
}
