/*
 * BaseAnt: the base class for all ants
 */
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.implementations.AttackBlock.AttackType;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelObject;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Pistol;
import com.antscuttle.game.Weapon.Sword;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public abstract class BaseAnt implements Ant{
    private String name;
    private int health;
    private int maxHealth;
    private int baseDamage;
    private int baseDefense;
    private int intelligence;
    private MeleeWeapon meleeWeapon;
    private RangedWeapon rangedWeapon;
    private Armor armor;
    private int speed;
    private AI ai;
    private AI defaultAI;
    private AnimationType lastTypeUsed = null;
    // In the following arrays, store in order:
    //  up, right, down, left
    private Texture[] moveAnimationUnarmed;
    private Texture[] moveAnimationSword;
    private Texture[] moveAnimationPistol;
    private Texture[] attackAnimationUnarmed;
    private Texture[] attackAnimationSword;
    private Texture[] attackAnimationPistol;

    public enum AnimationType { Move, MeleeAttack, RangedAttack }
    public enum AnimationDirection { Up, Right, Down, Left }

    public BaseAnt(String name, 
            int health, 
            int maxHealth, 
            int baseDamage, 
            int baseDefense,
            int intelligence,
            int speed, 
            AI defaultAI, 
            Texture[] moveAnimationUnarmed, 
            Texture[] moveAnimationSword, 
            Texture[] moveAnimationPistol, 
            Texture[] attackAnimationUnarmed, 
            Texture[] attackAnimationSword, 
            Texture[] attackAnimationPistol) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.baseDamage = baseDamage;
        this.baseDefense = baseDefense;
        this.intelligence = intelligence;
        this.meleeWeapon = null;
        this.rangedWeapon = null;
        this.armor = null;
        this.speed = speed;
        this.defaultAI = defaultAI;
        this.moveAnimationUnarmed = moveAnimationUnarmed;
        this.moveAnimationSword = moveAnimationSword;
        this.moveAnimationPistol = moveAnimationPistol;
        this.attackAnimationUnarmed = attackAnimationUnarmed;
        this.attackAnimationSword = attackAnimationSword;
        this.attackAnimationPistol = attackAnimationPistol;
        this.ai = this.defaultAI;
    }

    @Override
    public Texture[] getAntPreviewAnimation() {
        return moveAnimationUnarmed;
    }

    @Override
    public Texture getAnimation(AnimationType type, AnimationDirection dir){
        switch(type){
            case MeleeAttack: 
                lastTypeUsed = AnimationType.MeleeAttack;
                return getMeleeAttackAnimation(dir); 
            case RangedAttack:
                lastTypeUsed = AnimationType.RangedAttack;
                return getRangedAttackAnimation(dir);
            case Move:
                return getMoveAnimation(dir);
            default:
                return null;
        }
    }
    protected Texture getMoveAnimation(AnimationDirection dir){
        switch(lastTypeUsed){
            case MeleeAttack: return getMeleeMoveAnimation(dir); 
            case RangedAttack: return getRangedMoveAnimation(dir);
            default: return getUnarmedMoveAnimation(dir);
        }
    }
    protected Texture getMeleeMoveAnimation(AnimationDirection dir){
        if(meleeWeapon instanceof Sword){
            switch(dir){
                case Up: return moveAnimationSword[0];
                case Right: return moveAnimationSword[1];
                case Down: return moveAnimationSword[2];
                case Left: return moveAnimationSword[3];
            }
        }
        return null;
    }
    protected Texture getRangedMoveAnimation(AnimationDirection dir){
        if(rangedWeapon instanceof Pistol){
            switch(dir){
                case Up: return moveAnimationPistol[0];
                case Right: return moveAnimationPistol[1];
                case Down: return moveAnimationPistol[2];
                case Left: return moveAnimationPistol[3];
            }
        }
        return null;
    }
    protected Texture getUnarmedMoveAnimation(AnimationDirection dir){
        switch(dir){
            case Up: return moveAnimationUnarmed[0];
            case Right: return moveAnimationUnarmed[1];
            case Down: return moveAnimationUnarmed[2];
            case Left: return moveAnimationUnarmed[3];
        }
        return null;
    }
    protected Texture getMeleeAttackAnimation(AnimationDirection dir){
        if(meleeWeapon == null)
            return getUnarmedAttackAnimation(dir);
        return getMeleeWeaponAttackAnimation(dir);
    }
    protected Texture getMeleeWeaponAttackAnimation(AnimationDirection dir){
        if(meleeWeapon instanceof Sword){
            switch(dir){
                case Up: return attackAnimationSword[0];
                case Right: return attackAnimationSword[1];
                case Down: return attackAnimationSword[2];
                case Left: return attackAnimationSword[3];
            }
        }
        return null;
    }
    protected Texture getUnarmedAttackAnimation(AnimationDirection dir) {
       switch(dir){
            case Up: return attackAnimationUnarmed[0];
            case Right: return attackAnimationUnarmed[1];
            case Down: return attackAnimationUnarmed[2];
            case Left: return attackAnimationUnarmed[3];
        }
       return null;
    }
    protected Texture getRangedAttackAnimation(AnimationDirection dir) {
        if(rangedWeapon instanceof Pistol){
            switch(dir){
                case Up: return attackAnimationPistol[0];
                case Right: return attackAnimationPistol[1];
                case Down: return attackAnimationPistol[2];
                case Left: return attackAnimationPistol[3];
            }
        }
        return null;
    }
    
    @Override
    public void equipMeleeWeapon(MeleeWeapon weapon){
        this.meleeWeapon = weapon;
    }
    
    @Override
    public void equipRangedWeapon(RangedWeapon weapon){
        this.rangedWeapon = weapon;
    }
    
    @Override
    public void equipArmor(Armor armor){
        this.armor = armor;
    }
    
    @Override
    public void equipAI(AI ai){
        this.ai = ai;
    }
    
    @Override
    public void setAIToDefault(){
        ai = defaultAI;
    }
    @Override
    public int getSpeed(){
        return speed;
    }
    
    @Override
    public Armor getArmor(){
        return armor;
    }
    
    @Override
    public RangedWeapon getRangedWeapon(){
        return rangedWeapon;
    }
    
    @Override
    public MeleeWeapon getMeleeWeapon(){
        return meleeWeapon;
    }
    
    @Override
    public int getIntelligence(){
        return intelligence;
    }
    
    @Override
    public AI getAI(){
        if(ai != null) 
           return ai;
        return defaultAI;
    }
    
    @Override
    public int getHealth(){
        return health;
    }
    
    @Override
    public int getMaxHealth(){
        return maxHealth;
    }
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public int getBaseDamage(){
        return baseDamage;
    }
    
    @Override
    public int getBaseDefense(){
        return baseDefense;
    }
    @Override
    public int getMeleeDamage(){
        return (meleeWeapon == null) ? baseDamage : meleeWeapon.getDamage();
    }
    @Override
    public int getRangedDamage(){
        return (rangedWeapon == null) ? 0 : rangedWeapon.getDamage();
    }
    @Override
    public DamageType getMeleeDamageType(){
        return (meleeWeapon == null) ? DamageType.PHYSICAL : meleeWeapon.getDamageType();
    }
    @Override
    public DamageType getRangedDamageType(){
        return (rangedWeapon == null) ? null : rangedWeapon.getDamageType();
    }
    @Override
    public int receiveAttack(int damage, DamageType damageType){
        int defense = (armor == null) ? baseDefense : baseDefense + armor.getDefense();
        int damageTaken = damage - defense;
        if(damageTaken < 1)
            return 0;
        health -= damageTaken;
        return damageTaken;
    }
    @Override
    public int attack(Object target, AttackType type){
        int damageDone = 0;
        int damage = 0;
        DamageType damageType = DamageType.PHYSICAL;
        switch(type){
            case MELEE: 
                damage = getMeleeDamage();
                damageType = getMeleeDamageType();
                break;
            case RANGED:
                damage = getRangedDamage();
                damageType = getRangedDamageType();
                break;
        }
        if(target instanceof LevelObject){
            damageDone = ((LevelObject) target).receiveAttack(damage, damageType);
        } else if(target instanceof Ant){
            damageDone = ((Ant) target).receiveAttack(damage, damageType);
        }
        return damageDone;
    }
}
