
package com.antscuttle.game.Weapon;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author 
 */
public abstract class Weapon {
    private String name;
    private int damage;
    private DamageType damageType;
    private int minRange;
    private int maxRange;
    private Texture img;
    private Sound attackSound;
    
    public void playAttackSound(AntScuttleGame game){
        attackSound.play(game.VOLUME);
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
        return img;
    }
    public int getDamage(){
        return damage;
    }
    public DamageType getDamageType(){
        return damageType;
    }
}
