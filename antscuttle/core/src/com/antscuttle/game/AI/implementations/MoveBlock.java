/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.DecisionBlock;

/**
 *
 * @author antho
 */
public class MoveBlock extends DecisionBlock{
    private String direction;
    
    public MoveBlock(String dir){
        this.direction = dir;
    }
    public String getDirection(){
        return direction;
    }
}
