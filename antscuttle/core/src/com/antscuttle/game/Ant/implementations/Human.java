/*
 * Human implementation
 */
package com.antscuttle.game.Ant.implementations;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.DefaultHumanAI;
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
public class Human extends Ant{
    
    private String name;
    private int health = 50;
    private final int maxHealth = 50;
    private int baseDamage = 8;
    private int baseDefense = 4;
    private MeleeWeapon meleeWeapon = null;
    private RangedWeapon rangedWeapon = null;
    private Armor armor = null;
    private int speed = 40;
    private AI ai = null;
    private AI defaultAI = new DefaultHumanAI(new Node(new MoveBlock()));
    private String moveAnimations[] = {
        "assets/animations/human/human_unarmed_walk_up.png",
        "assets/animations/human/human_unarmed_walk_right.png",
        "assets/animations/human/human_unarmed_walk_down.png",
        "assets/animations/human/human_unarmed_walk_left.png"};
    private String attackAnimations[]= {
        "assets/animations/human/human_unarmed_attack_up.png",
        "assets/animations/human/human_unarmed_attack_right.png",
        "assets/animations/human/human_unarmed_attack_down.png",
        "assets/animations/human/human_unarmed_attack_left.png"};
    
    public Human(String name){
        this.name = name;
        setAIToDefault();
    }
    
}
