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
    
    String getImgLocation(){
        return img;
    }
    
    int getDefense(){
        return defense;
    }
    
    String getName(){
        return name;
    }
}
