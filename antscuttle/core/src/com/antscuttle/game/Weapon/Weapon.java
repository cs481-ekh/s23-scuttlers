
package com.antscuttle.game.Weapon;

import java.io.Serializable;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author 
 */
public abstract class Weapon implements Serializable{
    private String name;
    private int damage;
    private DamageType damageType;
    private int minRange;
    private int maxRange;
    private String img;
    private String attackSound;

    public Weapon(String name, int damage, DamageType damageType, int minRange, int maxRange, String img, String attackSound) {
        this.name = name;
        this.damage = damage;
        this.damageType = damageType;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.img = img;
        this.attackSound = attackSound;
    }
    
    
    public void playAttackSound(AntScuttleGame game){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(attackSound));
        sound.play(game.VOLUME);
    }

    public String getName() {
        return name;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public Texture getImg() {
        Texture texture = new Texture(img);
        return texture;
    }
    public int getDamage(){
        return damage;
    }
    public DamageType getDamageType(){
        return damageType;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
