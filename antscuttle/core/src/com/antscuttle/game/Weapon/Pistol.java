
package com.antscuttle.game.Weapon;

import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public abstract class Pistol extends RangedWeapon{
    public Pistol(String name, 
            int damage, 
            DamageType damageType, 
            int minRange, 
            int maxRange, 
            Texture img, 
            Sound attackSound, 
            Texture projectileImg, 
            int projectileSpeed)
    {
        super(name, damage, damageType, minRange, maxRange, img, attackSound, projectileImg, projectileSpeed);
    }
            
}
