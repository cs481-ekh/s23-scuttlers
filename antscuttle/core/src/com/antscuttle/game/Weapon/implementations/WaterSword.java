
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
public class WaterSword extends Sword{
    
    public WaterSword(){
//        String name = "Water Sword";
//        int damage = 20;
//        DamageType damageType = DamageType.WATER;
//        int minRange = 0;
//        int maxRange = 25;
//        Texture img = new Texture("weapon/ice_sword.png");
//        Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav"));
        super("Water Sword", 
                20, 
                DamageType.Water, 
                0, 
                25, 
                new String("weapon/ice_sword.png"), 
                new String("sounds/weapon/swish-1.wav"));
    }
}
