package com.antscuttle.game.Ant;

import java.io.Serializable;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author antho
 */
public class AntDecorator implements Ant, Serializable{
    private final int damage = 5;
    private final DamageType damageType;
    private final Ant wrappedAnt;
    
    public AntDecorator(Ant ant, DamageType damageType){
        wrappedAnt = ant;
        this.damageType = damageType;
    }
    
    @Override
    public int attack(Object target, String attackType){
        int damageDone = 0;
        damageDone = ((InteractableLevelObject)target).receiveAttack(damage, damageType);
        damageDone += wrappedAnt.attack(target, attackType);
        return damageDone;
    }
    
    @Override
    public Texture getAnimation(BaseAnt.AnimationType animationType, BaseAnt.AnimationDirection animationDir) {
        return wrappedAnt.getAnimation(animationType, animationDir);
    }

    @Override
    public Texture[] getAntPreviewAnimation() {
        return wrappedAnt.getAntPreviewAnimation();
    }

    @Override
    public void equipMeleeWeapon(MeleeWeapon weapon) {
        wrappedAnt.equipMeleeWeapon(weapon);
    }

    @Override
    public void equipRangedWeapon(RangedWeapon weapon) {
        wrappedAnt.equipRangedWeapon(weapon);
    }

    @Override
    public void equipArmor(Armor armor) {
        wrappedAnt.equipArmor(armor);
    }

    @Override
    public void equipAI(AI ai) {
        wrappedAnt.equipAI(ai);
    }

    @Override
    public void setAIToDefault() {
        wrappedAnt.setAIToDefault();
    }

    @Override
    public int getSpeed() {
        return wrappedAnt.getSpeed();
    }

    @Override
    public Armor getArmor() {
        return wrappedAnt.getArmor();
    }

    @Override
    public RangedWeapon getRangedWeapon() {
        return wrappedAnt.getRangedWeapon();
    }

    @Override
    public MeleeWeapon getMeleeWeapon() {
        return wrappedAnt.getMeleeWeapon();
    }

    @Override
    public int getIntelligence(){
        return wrappedAnt.getIntelligence();
    }
    @Override
    public AI getAI() {
        return wrappedAnt.getAI();
    }

    @Override
    public int getHealth() {
        return wrappedAnt.getHealth();
    }

    @Override
    public int getMaxHealth() {
        return wrappedAnt.getMaxHealth();
    }

    @Override
    public String getName() {
        return wrappedAnt.getName();
    }

    @Override
    public int getBaseDamage() {
        return damage + wrappedAnt.getBaseDamage();
    }

    @Override
    public int getBaseDefense() {
        return wrappedAnt.getBaseDefense();
    }

    @Override
    public int getMeleeDamage() {
        return damage + wrappedAnt.getMeleeDamage();
    }

    @Override
    public int getRangedDamage() {
        return damage + wrappedAnt.getRangedDamage();
    }

    @Override
    public DamageType getMeleeDamageType() {
        return wrappedAnt.getMeleeDamageType();
    }

    @Override
    public DamageType getRangedDamageType() {
        return wrappedAnt.getRangedDamageType();
    }

    @Override
    public int receiveAttack(int damage, DamageType damageType) {
        return wrappedAnt.receiveAttack(damage, damageType);
    }

    @Override
    public Rectangle getArea() {
        return wrappedAnt.getArea();
    }

    @Override
    public void setPos(float x, float y) {
        wrappedAnt.setPos(x, y);
    }

    @Override
    public Vector2 getPos() {
        return wrappedAnt.getPos();
    }

    @Override
    public void render(SpriteBatch characterBatch) {
        wrappedAnt.render(characterBatch);
    }
}
