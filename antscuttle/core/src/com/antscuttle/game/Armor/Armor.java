/*
 * Armor class
 */
package com.antscuttle.game.Armor;

/**
 *
 * @author antho
 */
public abstract class Armor {
    private String name;
    private int defense;
    private String img;
    
    public String getImgLocation(){
        return img;
    }
    
    public int getDefense(){
        return defense;
    }
    
    public String getName(){
        return name;
    }
}
