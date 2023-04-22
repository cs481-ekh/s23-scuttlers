/*
 * Armor class
 */
package com.antscuttle.game.Armor;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public abstract class Armor implements Serializable {
    public String name;
    private int defense;
    private String img;
    
    /**
     * 
     * @param name
     * @param defense
     * @param img 
     */
    public Armor(String name, int defense, String img){
        this.name = name;
        this.defense = defense;
        this.img = img;
    }
    /**
     * 
     * @return Texture for armor
     */
    public Texture getTexture(){
        Texture texture = new Texture(img);
        return texture;
    }
    /**
     * 
     * @return defense 
     */
    public int getDefense(){
        return defense;
    }
    /**
     * 
     * @return armor name 
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return String classname
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
