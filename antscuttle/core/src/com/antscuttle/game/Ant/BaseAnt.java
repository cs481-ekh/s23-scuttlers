/*
 * BaseAnt: the base class for all ants
 */
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Pistol;
import com.antscuttle.game.Weapon.Sword;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author antho
 */
public abstract class BaseAnt implements Ant, Serializable{
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
    private String[] moveAnimationUnarmed;
    private String[] moveAnimationSword;
    private String[] moveAnimationPistol;
    private String[] attackAnimationUnarmed;
    private String[] attackAnimationSword;
    private String[] attackAnimationPistol;
    
    private Point pos, dim;

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
            String[] moveAnimationUnarmed, 
            String[] moveAnimationSword, 
            String[] moveAnimationPistol, 
            String[] attackAnimationUnarmed, 
            String[] attackAnimationSword, 
            String[] attackAnimationPistol) {
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
        this.pos = new Point();
        this.dim = new Point();
        this.pos.x = 0;
        this.pos.y = 0;
        this.dim.x = 40;
        this.dim.y = 40;
    }

    public Rectangle getArea(){
        return new Rectangle(pos.x, pos.y, dim.x, dim.y);
    }
    public Point getCoords() {
        return pos;
    }
    public void setCoords(int x, int y) {
        pos.move(x, y);
    }
    @Override
    public Texture[] getAntPreviewAnimation() {
        Texture[] textures = new Texture[moveAnimationUnarmed.length];

        for (int i = 0; i < moveAnimationUnarmed.length; i++) {
        textures[i] = new Texture(moveAnimationUnarmed[i]);
        }
        return textures;
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
        Texture[] move = new Texture[moveAnimationSword.length];
        for (int i = 0; i < moveAnimationSword.length; i++) {
            move[i] = new Texture(moveAnimationSword[i]);
        }
        if(meleeWeapon instanceof Sword){
            switch(dir){
                case Up: return move[0];
                case Right: return move[1];
                case Down: return move[2];
                case Left: return move[3];
            }
        }
        return null;
    }
    protected Texture getRangedMoveAnimation(AnimationDirection dir){
        Texture[] move = new Texture[moveAnimationPistol.length];
        for (int i = 0; i < moveAnimationPistol.length; i++) {
            move[i] = new Texture(moveAnimationPistol[i]);
        }
        if(rangedWeapon instanceof Pistol){
            switch(dir){
                case Up: return move[0];
                case Right: return move[1];
                case Down: return move[2];
                case Left: return move[3];
            }
        }
        return null;
    }
    protected Texture getUnarmedMoveAnimation(AnimationDirection dir){
        Texture[] move = new Texture[moveAnimationUnarmed.length];
        for (int i = 0; i < moveAnimationUnarmed.length; i++) {
            move[i] = new Texture(moveAnimationUnarmed[i]);
        }
        switch(dir){
            case Up: return move[0];
            case Right: return move[1];
            case Down: return move[2];
            case Left: return move[3];
        }
        return null;
    }
    protected Texture getMeleeAttackAnimation(AnimationDirection dir){
        if(meleeWeapon == null)
            return getUnarmedAttackAnimation(dir);
        return getMeleeWeaponAttackAnimation(dir);
    }
    protected Texture getMeleeWeaponAttackAnimation(AnimationDirection dir){
        Texture[] move = new Texture[attackAnimationSword.length];
        for (int i = 0; i < attackAnimationSword.length; i++) {
            move[i] = new Texture(attackAnimationSword[i]);
        }
        if(meleeWeapon instanceof Sword){
            switch(dir){
                case Up: return move[0];
                case Right: return move[1];
                case Down: return move[2];
                case Left: return move[3];
            }
        }
        return null;
    }
    protected Texture getUnarmedAttackAnimation(AnimationDirection dir) {
        Texture[] move = new Texture[attackAnimationUnarmed.length];
        for (int i = 0; i < attackAnimationUnarmed.length; i++) {
            move[i] = new Texture(attackAnimationUnarmed[i]);
        }
       switch(dir){
            case Up: return move[0];
            case Right: return move[1];
            case Down: return move[2];
            case Left: return move[3];
        }
       return null;
    }
    protected Texture getRangedAttackAnimation(AnimationDirection dir) {
        Texture[] move = new Texture[attackAnimationPistol.length];
        for (int i = 0; i < attackAnimationPistol.length; i++) {
            move[i] = new Texture(attackAnimationPistol[i]);
        }
        if(rangedWeapon instanceof Pistol){
            switch(dir){
                case Up: return move[0];
                case Right: return move[1];
                case Down: return move[2];
                case Left: return move[3];
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
    public int attack(Object target, String attackType){
        int damageDone = 0;
        int damage = 0;
        DamageType damageType = DamageType.PHYSICAL;
        switch(attackType){
            case "Melee": 
                damage = getMeleeDamage();
                damageType = getMeleeDamageType();
                break;
            case "Ranged":
                damage = getRangedDamage();
                damageType = getRangedDamageType();
                break;
        }
        if(target instanceof LevelObject){
            damageDone = ((InteractableLevelObject) target).receiveAttack(damage, damageType);
        } else if(target instanceof Ant){
            damageDone = ((Ant) target).receiveAttack(damage, damageType);
        }
        return damageDone;
    }
}
