/*
 * Human implementation
 */
package com.antscuttle.game.Ant.implementations;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DefaultHumanAI;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.graphics.Texture;

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
    private AI defaultAI = new DefaultHumanAI(new Node(new RootBlock()));
    private Texture[] moveAnimationUnarmed = {
        new Texture("animations/human/human_unarmed_walk_up.png"),
        new Texture("animations/human/human_unarmed_walk_right.png"),
        new Texture("animations/human/human_unarmed_walk_down.png"),
        new Texture("animations/human/human_unarmed_walk_left.png"),
    };
    private Texture[] moveAnimationSword = {
        new Texture("animations/human/human_sword_walk_up.png"),
        new Texture("animations/human/human_sword_walk_right.png"),
        new Texture("animations/human/human_sword_walk_down.png"),
        new Texture("animations/human/human_sword_walk_left.png"),
    };
    private Texture[] moveAnimationPistol = {
        new Texture("animations/human/human_pistol_walk_up.png"),
        new Texture("animations/human/human_pistol_walk_right.png"),
        new Texture("animations/human/human_pistol_walk_down.png"),
        new Texture("animations/human/human_pistol_walk_left.png"),
    };
    private Texture[] attackAnimationUnarmed = {
        new Texture("animations/human/human_unarmed_attack_up.png"),
        new Texture("animations/human/human_unarmed_attack_right.png"),
        new Texture("animations/human/human_unarmed_attack_down.png"),
        new Texture("animations/human/human_unarmed_attack_left.png"),
    };
    private Texture[] attackAnimationSword = {
        new Texture("animations/human/human_sword_attack_up.png"),
        new Texture("animations/human/human_sword_attack_right.png"),
        new Texture("animations/human/human_sword_attack_down.png"),
        new Texture("animations/human/human_sword_attack_left.png"),
    };
    private Texture[] attackAnimationPistol = {
        new Texture("animations/human/human_pistol_attack_up.png"),
        new Texture("animations/human/human_pistol_attack_right.png"),
        new Texture("animations/human/human_pistol_attack_down.png"),
        new Texture("animations/human/human_pistol_attack_left.png"),
    };
    
    public Human(String name){
        this.name = name;
        setAIToDefault();
    }
    
}
