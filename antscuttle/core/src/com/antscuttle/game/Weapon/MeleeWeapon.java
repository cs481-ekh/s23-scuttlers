
package com.antscuttle.game.Weapon;

import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author 
 */
public abstract class MeleeWeapon extends Weapon{
    public MeleeWeapon(String name, 
            int damage, 
            DamageType damageType, 
            int minRange, int maxRange, 
            String img, String attackSound)
    {
        super(name, damage, damageType, minRange, maxRange, img, attackSound);
    }
}
