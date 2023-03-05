
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
                DamageType.PHYSICAL,
                0,
                Integer.MAX_VALUE,
                new Texture("weapon/Glock26Gen5.png"),
                Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/shots/pistol.wav")),
                new Texture("weapon/bullet.png"),
                200);
    }
}
