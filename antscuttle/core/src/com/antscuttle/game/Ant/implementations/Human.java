/*
 * Human implementation
 */
package com.antscuttle.game.Ant.implementations;

import com.antscuttle.game.AI.DefaultHumanAI;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.Ant.Ant;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Human extends Ant{
    
    private static Texture[] moveAnimationUnarmed = {
        new Texture("animations/human/human_unarmed_walk_up.png"),
        new Texture("animations/human/human_unarmed_walk_right.png"),
        new Texture("animations/human/human_unarmed_walk_down.png"),
        new Texture("animations/human/human_unarmed_walk_left.png"),
    };
    private static Texture[] moveAnimationSword = {
        new Texture("animations/human/human_sword_walk_up.png"),
        new Texture("animations/human/human_sword_walk_right.png"),
        new Texture("animations/human/human_sword_walk_down.png"),
        new Texture("animations/human/human_sword_walk_left.png"),
    };
    private static Texture[] moveAnimationPistol = {
        new Texture("animations/human/human_pistol_walk_up.png"),
        new Texture("animations/human/human_pistol_walk_right.png"),
        new Texture("animations/human/human_pistol_walk_down.png"),
        new Texture("animations/human/human_pistol_walk_left.png"),
    };
    private static Texture[] attackAnimationUnarmed = {
        new Texture("animations/human/human_unarmed_attack_up.png"),
        new Texture("animations/human/human_unarmed_attack_right.png"),
        new Texture("animations/human/human_unarmed_attack_down.png"),
        new Texture("animations/human/human_unarmed_attack_left.png"),
    };
    private static Texture[] attackAnimationSword = {
        new Texture("animations/human/human_sword_attack_up.png"),
        new Texture("animations/human/human_sword_attack_right.png"),
        new Texture("animations/human/human_sword_attack_down.png"),
        new Texture("animations/human/human_sword_attack_left.png"),
    };
    private static Texture[] attackAnimationPistol = {
        new Texture("animations/human/human_pistol_attack_up.png"),
        new Texture("animations/human/human_pistol_attack_right.png"),
        new Texture("animations/human/human_pistol_attack_down.png"),
        new Texture("animations/human/human_pistol_attack_left.png"),
    };
    
    public Human(String name){
        super(name, 
            100, 
            100, 
            8, 
            4,
            40, 
            new DefaultHumanAI(new Node(new RootBlock()), "default"), 
            moveAnimationUnarmed, 
            moveAnimationSword, 
            moveAnimationPistol, 
            attackAnimationUnarmed, 
            attackAnimationSword, 
            attackAnimationPistol);
    }
    
}
