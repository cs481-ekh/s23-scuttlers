/*
 * The default AI for Humans
 */
package com.antscuttle.game.AI;

import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.AI.options.MoveOptions;

/**
 *
 * @author antho
 */
public class DefaultHumanAI extends AI{

    public DefaultHumanAI(Node root, String name){
        super(root, name);
        DecisionBlock move1 = new MoveBlock(new MoveOptions("Ant"));
        Node n = new Node(move1);
        root.addChild(n);
    }

    @Override
    public String toString() {
        return "Default Human AI";
    }
}
