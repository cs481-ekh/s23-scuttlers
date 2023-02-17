/*
 * Ant class
 */
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;

/**
 *
 * @author antho
 */
public abstract class Ant {
    private String name;
    private int health;
    private int maxHealth;
    private int baseDamage;
    private int baseDefense;
    private MeleeWeapon meleeWeapon;
    private RangedWeapon rangedWeapon;
    private Armor armor;
    private int speed;
    private AI ai;
    private AI defaultAI;
    private String moveAnimations[];
    private String attackAnimations[];
    
    
    public void equipMeleeWeapon(MeleeWeapon weapon){
        this.meleeWeapon = weapon;
    }
    
    public void equipRangedWeapon(RangedWeapon weapon){
        this.rangedWeapon = weapon;
    }
    
    public void equipArmor(Armor armor){
        this.armor = armor;
    }
    
    public void equipAI(AI ai){
        this.ai = ai;
    }
    
    public void setAIToDefault(){
        ai = defaultAI;
    }
    public int getSpeed(){
        return speed;
    }
    
    public Armor getArmor(){
        return armor;
    }
    
    public RangedWeapon getRangedWeapon(){
        return rangedWeapon;
    }
    
    public MeleeWeapon getMeleeWeapon(){
        return meleeWeapon;
    }
    
    public AI getAI(){
        if(ai != null) 
           return ai;
        return defaultAI;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    public String getName(){
        return name;
    }
    
    public int getBaseDamage(){
        return baseDamage;
    }
    
    public int getBaseDefense(){
        return baseDefense;
    }
    
    public String[] getMoveAnimationLocations(){
        return moveAnimations;
    }
    
    public String[] getAttackAnimationLocations(){
        return attackAnimations;
    }
    
    public int attack(int damage, String damageType){
        int defense = (armor == null) ? baseDefense : baseDefense + armor.getDefense();
        int damageTaken = damage - defense;
        if(damageTaken < 1)
            return 0;
        health -= damageTaken;
        return damageTaken;
    }
}
