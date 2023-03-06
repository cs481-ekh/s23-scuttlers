/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antscuttle.game.AI;

/**
 *
 * @author antho
 */
public class DefaultZombieAI extends AI{
    private Node root;
    
    public DefaultZombieAI(Node root){
        super(root);
        // TODO: Build default Zombie AI tree here
    }

    @Override
    public String toString() {
        return "Default Zombie AI";
    }
}
