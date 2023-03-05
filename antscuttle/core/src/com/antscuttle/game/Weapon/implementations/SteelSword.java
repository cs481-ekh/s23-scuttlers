/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class SteelSword extends Sword{
    public SteelSword(){
        super("Sword", 
                20, 
                DamageType.PHYSICAL, 
                0, 
                25, 
                new Texture("weapon/sword.png"), 
                Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/swish-1.wav")));
    }
}
