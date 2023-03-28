
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.BaseAnt.AnimationType;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author antho
 */
public interface Ant {
   
    Texture getAnimation(AnimationType type, BaseAnt.AnimationDirection dir);
    public Texture[] getAntPreviewAnimation();
    public void equipMeleeWeapon(MeleeWeapon weapon);
    public void equipRangedWeapon(RangedWeapon weapon);
    public void equipArmor(Armor armor);
    public void equipAI(AI ai);
    public void setAIToDefault();
    public int getSpeed();
    public Armor getArmor();
    public RangedWeapon getRangedWeapon();
    public MeleeWeapon getMeleeWeapon();
    public AI getAI();
    public int getHealth();
    public int getIntelligence();
    public int getMaxHealth();
    public String getName();
    public int getBaseDamage();
    public int getBaseDefense();
    public int getMeleeDamage();
    public int getRangedDamage();
    public DamageType getMeleeDamageType();
    public DamageType getRangedDamageType();
    public int receiveAttack(int damage, DamageType damageType);
    public int attack(Object target, String attackType);
    public Rectangle getArea();
    
}
