
package com.antscuttle.game.Weapon;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author
 */
public abstract class RangedWeapon extends Weapon{
    private Texture bulletImg;
    private int bulletSpeed;

    public Texture getBulletImg() {
        return bulletImg;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }
}
