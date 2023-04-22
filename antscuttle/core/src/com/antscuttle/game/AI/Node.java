
package com.antscuttle.game.AI;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * A tree node for AIs
 * @author antho
 */
public class Node implements Serializable{
    // The current block
    private final DecisionBlock block;
    //Block Coordinates
    public int pos;
    // Children of this block
    private LinkedList<Node> children;
    private Node next;
    private Node prev;
    private Node parent;
    
    /**
     * 
     * @param block 
     */
    public Node(DecisionBlock block){
        this.pos = 0;
        this.block = block;
        children = new LinkedList<>();
        this.next = this.prev = this.parent = null;
    }
    /**
     * 
     * @param child 
     */
    public void addChild(Node child){
        
        if(!children.isEmpty()){
            child.prev = children.getLast();
            children.getLast().next = child;
        }
        child.parent = this;
        children.add(child);
    }
    /**
     * 
     * @param pos 
     */
    public void setPos(int pos){
        this.pos  = pos;
    }

    /**
     * 
     * @param node
     * @return block 
     */
    public DecisionBlock getBlock(Node node){
        return node.block;
    }
    /**
     * 
     * @param index
     * @param child
     * @return success
     */
    public boolean addChildAt(int index, Node child){
        try{
            if(index > 0){
                Node prevNode = children.get(index-1);
                prevNode.next = child;
                child.prev = prevNode;
            }
            if(index < children.size()-1){
                Node nextNode = children.get(index);
                nextNode.prev = child;
                child.next = nextNode;
            }
            child.parent = this;
            children.add(index, child);
            return true;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }
    /**
     * 
     * @return whether this node has a right sib under same parent
     */
    public boolean hasRightSibling(){
        return next != null;
    }
    /**
     * 
     * @return sibling node
     */
    public Node getRightSibling(){
        return next;
    }
    /**
     * 
     * @return whether this node has a parent 
     */
    public boolean hasParent(){
        return parent != null;
    }
    /**
     * 
     * @return parent node
     */
    public Node getParent(){
        return parent;
    }
    /**
     * 
     * @return block 
     */
    public DecisionBlock getBlock(){
        return block;
    }
    /**
     * 
     * @return whether this node is a parent
     */
    public boolean hasChildren(){
        return !children.isEmpty();
    }
    /**
     * 
     * @return all children 
     */
    public LinkedList<Node> getChildren(){
        return children;
    }
    /**
     * 
     * @param child
     * @return success
     */
    public boolean removeChild(Node child){
        if(child.prev != null)
            child.prev.next = child.next;
        if(child.next != null)
            child.next.prev = child.prev;
        
        if (children.remove(child)) {
            return true;
        } else {
            // If the node was not found in this level, recursively search the children
            for (Node node : children) {
                if (node.removeChild(child)) {
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * 
     * @param children 
     */
    public void setChildren(LinkedList<Node> children){
        this.children = children;
    }
}

