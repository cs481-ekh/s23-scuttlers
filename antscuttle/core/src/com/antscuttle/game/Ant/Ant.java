
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.BaseAnt.AnimationDirection;
import com.antscuttle.game.Ant.BaseAnt.AnimationType;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Ant interface
 * @author antho
 */
public interface Ant{

    /**
     * 
     * @param type AnimationType
     * @param dir movement direction
     * @return animation frame
     */
    public Texture getAnimation(AnimationType type, BaseAnt.AnimationDirection dir);
    /**
     * 
     * @return animation used in UI
     */
    public Texture[] getAntPreviewAnimation();
    /**
     * 
     * @param weapon 
     */
    public void equipMeleeWeapon(MeleeWeapon weapon);
    /**
     * 
     * @param weapon 
     */
    public void equipRangedWeapon(RangedWeapon weapon);
    /**
     * 
     * @param armor 
     */
    public void equipArmor(Armor armor);
    /**
     * 
     * @param ai 
     */
    public void equipAI(AI ai);
    /**
     * set ant's ai to the default ai selected for that ant
     */
    public void setAIToDefault();
    /**
     * 
     * @return ant speed
     */
    public int getSpeed();
    /**
     * 
     * @return equipped armor 
     */
    public Armor getArmor();
    /**
     * 
     * @return equipped RangedWeapon
     */
    public RangedWeapon getRangedWeapon();
    /**
     * 
     * @return equipped MeleeWeapon 
     */
    public MeleeWeapon getMeleeWeapon();
    /**
     * 
     * @return equipped AI 
     */
    public AI getAI();
    /**
     * 
     * @return current health 
     */
    public int getHealth();
    /**
     * 
     * @return ant's intelligence
     */
    public int getIntelligence();
    /**
     * 
     * @return ant's max health
     */
    public int getMaxHealth();
    /**
     * 
     * @return ant's name 
     */
    public String getName();
    /**
     * 
     * @return unarmed damage
     */
    public int getBaseDamage();
    /**
     * 
     * @return ant's defense without equipment 
     */
    public int getBaseDefense();
    /**
     * 
     * @return damage with weapon
     */
    public int getMeleeDamage();
    /**
     * 
     * @return damage with weapon
     */
    public int getRangedDamage();
    /**
     * 
     * @return damage type 
     */
    public DamageType getMeleeDamageType();
    /**
     * 
     * @return damage type 
     */
    public DamageType getRangedDamageType();
    /**
     * 
     * @param damage damage being done to this ant
     * @param damageType damage type
     * @param levelData 
     * @return damage successfully inflicted
     */
    public int receiveAttack(int damage, DamageType damageType, LevelData levelData);
    /**
     * 
     * @param target target for this ant to attack
     * @param attackType melee or ranged
     * @param levelData 
     * @return damage inflicted
     */
    public int attack(Object target, String attackType, LevelData levelData);
    /**
     * 
     * @return hit box
     */
    public Rectangle getArea();
    /**
     * 
     * @param x
     * @param y 
     */
    public void setPos(float x, float y);
    /**
     * 
     * @return full float pos
     */
    public Vector2 getPos();
    /**
     * 
     * @param characterBatch 
     */
    public void render(SpriteBatch characterBatch);
    /**
     * 
     * @param deltaTime 
     */
    public void update(float deltaTime);
    /**
     * 
     * @return direction ant is facing 
     */
    public AnimationDirection getDirection();
    /**
     * reset ant back to initial state
     */
    public void reset();
}
