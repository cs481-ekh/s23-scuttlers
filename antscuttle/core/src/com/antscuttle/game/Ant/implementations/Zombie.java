/*
 * Zombie implementation
 */
package com.antscuttle.game.Ant.implementations;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DefaultZombieAI;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.Ant.BaseAnt;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author antho
 */

public class Zombie extends BaseAnt{
    private static final int health = 25;
    private static final int maxHealth = 25;
    private static final int baseDamage = 6;
    private static final int baseDefense = 2;
    private static final int intelligence = 1;
    private static final int speed = 20;
    
    private static final String[] moveAnimationUnarmed = {
        new String("animations/zombie/zombie_walk_up.png"),
        new String("animations/zombie/zombie_walk_right.png"),
        new String("animations/zombie/zombie_walk_down.png"),
        new String("animations/zombie/zombie_walk_left.png"),
    };
    private static final String[] attackAnimationUnarmed = {
        new String("animations/zombie/zombie_attack_up.png"),
        new String("animations/zombie/zombie_attack_right.png"),
        new String("animations/zombie/zombie_attack_down.png"),
        new String("animations/zombie/zombie_attack_left.png"),
    };
    
    public Zombie(String name){
        super(name, 
            health, 
            maxHealth, 
            baseDamage, 
            baseDefense,
            intelligence,
            speed, 
            new DefaultZombieAI(new Node(new RootBlock()), "default"), 
            moveAnimationUnarmed, 
            null, 
            null, 
            attackAnimationUnarmed, 
            null, 
            null);
    }
    @Override
    public Texture getAnimation(AnimationType type, AnimationDirection dir){
        switch(type){
            case Move: return getUnarmedMoveAnimation(dir);
            default: return getUnarmedAttackAnimation(dir);
        }
    }
    @Override
    public void equipMeleeWeapon(MeleeWeapon weapon){
        // Do nothing, zombies can't use weapons
    }
    
    @Override
    public void equipRangedWeapon(RangedWeapon weapon){
        // Do nothing, zombies can't use weapons
    }
    
}
