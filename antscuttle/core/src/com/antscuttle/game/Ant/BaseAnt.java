/*
 * BaseAnt: the base class for all ants
 */
package com.antscuttle.game.Ant;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Pistol;
import com.antscuttle.game.Weapon.Sword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

/**
 *
 * @author antho
 */
public abstract class BaseAnt implements Ant, Serializable{
    private static final long serialVersionUID = 40L;
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
    
    private Vector2 pos, dim;
    private AnimationDirection direction;
    private AnimationType state;
    private short stateTime;
    private Vector2 lastPos;
    
    public enum AnimationType { Stand, Move, MeleeAttack, RangedAttack }
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
        this.pos = new Vector2();
        this.dim = new Vector2();
        this.pos.x = 0;
        this.pos.y = 0;
        this.dim.x = 40;
        this.dim.y = 40;
        this.direction = AnimationDirection.Down;
        this.state = AnimationType.Stand;
        this.stateTime = 0;
    }
    @Override
    public Rectangle getArea(){
        return new Rectangle(pos.x, pos.y, dim.x, dim.y);
    }
    @Override
    public Vector2 getPos() {
        return pos;
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
            default:
                return getMoveAnimation(dir);
        }
    }
    protected Texture getMoveAnimation(AnimationDirection dir){
        if(lastTypeUsed == null)
            lastTypeUsed = AnimationType.Move;
        switch(lastTypeUsed){
            case MeleeAttack: return getMeleeMoveAnimation(dir); 
            case RangedAttack: return getRangedMoveAnimation(dir);
            default: return getUnarmedMoveAnimation(dir);
        }
    }
    protected Texture getMeleeMoveAnimation(AnimationDirection dir){
        if(meleeWeapon == null)
            return getUnarmedMoveAnimation(dir);
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
        if(rangedWeapon == null)
            return getUnarmedMoveAnimation(dir);
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
        if(rangedWeapon == null)
            return getUnarmedMoveAnimation(dir);
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
        return (meleeWeapon == null) ? DamageType.Physical : meleeWeapon.getDamageType();
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
        DamageType damageType = DamageType.Physical;
        switch(attackType){
            case "Melee": 
                lastTypeUsed = AnimationType.MeleeAttack;
                state = AnimationType.MeleeAttack;
                damage = getMeleeDamage();
                damageType = getMeleeDamageType();
                break;
            case "Ranged":
                lastTypeUsed = AnimationType.RangedAttack;
                state = AnimationType.RangedAttack;
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
    @Override
    public void setPos(float x, float y) {
        if(lastPos == null){
            lastPos = new Vector2();
            lastPos.x = pos.x;
            lastPos.y = pos.y;
        }
        
        // Detect movement
        if(state != AnimationType.MeleeAttack && state != AnimationType.RangedAttack)
            state = AnimationType.Move;
        // Detect direction
        if(pos.x < x)
            direction = AnimationDirection.Right;
        else if(pos.x > x)
            direction = AnimationDirection.Left;
        else if(pos.y < y)
            direction = AnimationDirection.Up;
        else if(pos.y > y)
            direction = AnimationDirection.Down;
        pos.x=x;
        pos.y=y;
    }
    @Override
    public void update(float delta){
        if(lastPos.equals(pos)){
            state = AnimationType.Stand;
            stateTime = 0;
        }
        lastPos.y = pos.y;
        lastPos.x = pos.x;
        if(state != AnimationType.Stand)
            stateTime++;
        // Doing 3 animation frames at 20 calls per frame
        if(stateTime > 59){
            stateTime = 0;
            state = AnimationType.Stand;
        }
        
    }
    
    @Override
    public void render(SpriteBatch batch){
        Texture animation = getAnimation(state, direction); 
        if(animation != null){
        TextureRegion tex = new TextureRegion(animation)
                .split(40, 40)[0][stateTime/20];
            batch.draw(tex, pos.x, pos.y);
        }
    }
}
