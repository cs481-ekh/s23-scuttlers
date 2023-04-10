
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
                DamageType.Electric, 
                0, 
                25, 
                new String("weapon/lightning_sword.png"), 
                new String("sounds/weapon/swish-1.wav"));
    }
}
