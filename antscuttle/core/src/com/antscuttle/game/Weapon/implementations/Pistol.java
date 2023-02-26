
package com.antscuttle.game.Weapon.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Pistol extends RangedWeapon{
    private String name = "Pistol";
    private int damage = 20;
    private DamageType damageType = DamageType.PHYSICAL;
    private int minRange = 0;
    private int maxRange = Integer.MAX_VALUE;
    private Texture img = new Texture("weapon/Glock26Gen5.png");
    private Sound attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/shots/pistol.wav"));
    private Texture bulletImg = new Texture("weapon/bullet.png");
    private int bulletSpeed = 200;
            
}
