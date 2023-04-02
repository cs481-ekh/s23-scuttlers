package com.antscuttle.game.Weapon;


import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;


/**
 *
 * @author antho
 */
public abstract class Sword extends MeleeWeapon{
    
    public Sword(String name, 
            int damage, 
            DamageType damageType, 
            int minRange, int maxRange, 
            String img, String attackSound)
    {
        super(name, damage, damageType, minRange, maxRange, img, attackSound);
    }
}
