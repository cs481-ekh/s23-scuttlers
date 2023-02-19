
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
    private Node next;
    private Node prev;
    private Node parent;
    
    public Node(DecisionBlock block){
        this.block = block;
        children = new LinkedList<>();
        this.next = this.prev = this.parent = null;
    }
    
    public void addChild(Node child){
        
        if(!children.isEmpty()){
            child.prev = children.getLast();
            children.getLast().next = child;
        }
        child.parent = this;
        children.add(child);
    }
    
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
    /* hasNext: whether this node has a right sibling */
    public boolean hasRightSibling(){
        return next != null;
    }
    public Node getRightSibling(){
        return next;
    }
    public boolean hasParent(){
        return parent != null;
    }
    public Node getParent(){
        return parent;
    }
    public DecisionBlock getBlock(){
        return block;
    }
    public boolean hasChildren(){
        return !children.isEmpty();
    }
    public LinkedList<Node> getChildren(){
        return children;
    }
    
    public boolean removeChild(Node child){
        int index = children.indexOf(child);
        if(index < 0)
            return false;
        if(child.prev != null)
            child.prev.next = child.next;
        if(child.next != null)
            child.next.prev = child.prev;
        
        return children.remove(child);
    }
    
    public void setChildren(LinkedList<Node> children){
        this.children = children;
    }
}

