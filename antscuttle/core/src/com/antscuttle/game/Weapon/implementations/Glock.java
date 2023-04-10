
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.Pistol;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Glock extends Pistol{
    public Glock(){
        super("Glock",
                20,
                DamageType.Physical,
                0,
                Integer.MAX_VALUE,
                new String("weapon/Glock26Gen5.png"),
                new String("sounds/weapon/shots/pistol.wav"),
                new String("weapon/bullet.png"),
                200);
    }
}
