/*
 * Zombie implementation
 */
package com.antscuttle.game.Ant.implementations;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DefaultZombieAI;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;

/**
 *
 * @author antho
 */
public class Zombie extends Ant{
    private String name;
    private int health = 30;
    private final int maxHealth = 30;
    private int baseDamage = 6;
    private int baseDefense = 2;
    private MeleeWeapon meleeWeapon = null;
    private RangedWeapon rangedWeapon = null;
    private Armor armor = null;
    private int speed = 20;
    private AI ai = null;
    private AI defaultAI = new DefaultZombieAI(new Node(new MoveBlock("")));
    private String moveAnimations[] = {
        "assets/animations/zombie/zombie_walk_up.png",
        "assets/animations/zombie/zombie_walk_right.png",
        "assets/animations/zombie/zombie_walk_down.png",
        "assets/animations/zombie/zombie_walk_left.png"};
    private String attackAnimations[]= {
        "assets/animations/zombie/zombie_attack_up.png",
        "assets/animations/zombie/zombie_attack_right.png",
        "assets/animations/zombie/zombie_attack_down.png",
        "assets/animations/zombie/zombie_attack_left.png"};
    
    public Zombie(String name){
        this.name = name;
        setAIToDefault();
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
