
package com.antscuttle.game.Weapon;

import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author
 */
public abstract class RangedWeapon extends Weapon{
    private String projectileImg;
    private int projectileSpeed;
    
    public RangedWeapon(String name, 
            int damage, 
            DamageType damageType, 
            int minRange, 
            int maxRange, 
            String img, 
            String attackSound, 
            String projectileImg, 
            int projectileSpeed)
    {
        super(name, damage, damageType, minRange, maxRange, img, attackSound);
        this.projectileImg = projectileImg;
        this.projectileSpeed = projectileSpeed;
    }
    public Texture getBulletImg() {
        Texture texture = new Texture(projectileImg);
        return texture;
    }

    public int getBulletSpeed() {
        return projectileSpeed;
    }
}
