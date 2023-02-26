
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class FireSword {
    private String name = "Sword";
    private int damage = 20;
    private DamageType damageType = DamageType.FIRE;
    private int minRange = 0;
    private int maxRange = 25;
    private Texture img = new Texture("weapon/fire_sword.png");
    private Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav"));
}
