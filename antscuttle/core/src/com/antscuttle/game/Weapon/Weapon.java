
package com.antscuttle.game.Weapon;

import com.antscuttle.game.Damage.DamageType;

/**
 *
 * @author 
 */
public abstract class Weapon {
    private int damage;
    private DamageType damageType;
    
    public int getDamage(){
        return damage;
    }
    public DamageType getDamageType(){
        return damageType;
    }
}
