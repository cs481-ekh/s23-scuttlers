/*
 * Armor class
 */
package com.antscuttle.game.Armor;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public abstract class Armor {
    public String name;
    private int defense;
    private Texture img;
    
    public Armor(String name, int defense, Texture img){
        this.name = name;
        this.defense = defense;
        this.img = img;
    }
    public Texture getTexture(){
        return img;
    }
    
    public int getDefense(){
        return defense;
    }
    
    public String getName(){
        return name;
    }
}
