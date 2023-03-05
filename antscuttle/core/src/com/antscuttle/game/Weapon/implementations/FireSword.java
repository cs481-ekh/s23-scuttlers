
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Weapon.Sword;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class FireSword extends Sword{
    public FireSword(){
        super("Fire Sword", 
                20, 
                DamageType.FIRE, 
                0, 
                25, 
                new Texture("weapon/fire_sword.png"), 
                Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav")));
    }
}
