
package com.antscuttle.game.AI;

import java.util.LinkedList;

/**
 *
 * @author antho
 */
public class Node {
    // The current block
    private final DecisionBlock block;
    // Children of this block
    private LinkedList<Node> children;
    
    public Node(DecisionBlock block){
        this.block = block;
        children = new LinkedList<Node>();
    }
    
    public void addChild(DecisionBlock child){
        children.add(new Node(child));
    }
    
    public Boolean addChildAt(int index, Node child){
        try{
            children.add(index, child);
            return true;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }
    public DecisionBlock getBlock(){
        return block;
    }
    
    public LinkedList<Node> getChildren(){
        return children;
    }
    
    public Boolean removeChild(Node child){
        return children.remove(child);
    }
    
    public void setChildren(LinkedList<Node> children){
        this.children = children;
    }
}
