
package com.antscuttle.game.Weapon;

import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author
 */
public abstract class RangedWeapon extends Weapon{
    private Texture projectileImg;
    private int projectileSpeed;
    
    public RangedWeapon(String name, 
            int damage, 
            DamageType damageType, 
            int minRange, 
            int maxRange, 
            Texture img, 
            Sound attackSound, 
            Texture projectileImg, 
            int projectileSpeed)
    {
        super(name, damage, damageType, minRange, maxRange, img, attackSound);
        this.projectileImg = projectileImg;
        this.projectileSpeed = projectileSpeed;
    }
    public Texture getBulletImg() {
        return projectileImg;
    }

    public int getBulletSpeed() {
        return projectileSpeed;
    }
}
