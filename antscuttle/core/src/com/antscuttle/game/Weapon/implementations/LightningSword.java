
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Weapon.Sword;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class LightningSword extends Sword{
    public LightningSword(){
        super("Lightning Sword", 
                20, 
                DamageType.ELECTRIC, 
                0, 
                25, 
                new Texture("weapon/lightning_sword.png"), 
                Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav")));
    }
}