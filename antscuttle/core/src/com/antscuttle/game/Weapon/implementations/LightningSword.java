
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class LightningSword extends Sword{
    private String name = "Lightning Sword";
    private int damage = 20;
    private DamageType damageType = DamageType.ELECTRIC;
    private int minRange = 0;
    private int maxRange = 25;
    private Texture img = new Texture("weapon/lightning_sword.png");
    private Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav"));
}
