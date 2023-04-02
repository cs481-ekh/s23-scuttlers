
package com.antscuttle.game.AI;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author antho
 */
public class AI implements Iterable, Serializable{
    
    private final Node root;
    private String name;

    public AI(Node root, String name){
        this.root = root;
        this.name = name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Node getRoot(){
        return root;
    }
    // Not the Java Iterator because ours relies on DecisionBlock execution
    @Override
    public AIIterator iterator() {
        return new AIIterator(root);
    }
    
    class AIIterator implements Iterator{
        private Node root;
        private Node currentNode;
        private DecisionBlock currentBlock;
        
        public AIIterator(Node root){
            this.currentNode = this.root = root;
        }

        @Override
        public boolean hasNext() {
            
            // If current block execution failed, then don't use the children
            if((currentNode == root || currentBlock.getExecutionResult()) && 
                    currentNode.hasChildren())
                return true;
            
            // Check for siblings to the right
            if(currentNode.hasRightSibling())
                return true;
            
            // Check parents for siblings to the right
            Node parent;
            while((parent = currentNode.getParent()) != null){
                if(parent.hasRightSibling()){
                    return true;
                }
            }
            return false;
        }
        /* next: returns the next DecisionBlock */
        @Override
        public Object next() {
            Node startingNode = currentNode;
            // If current block execution succeeded and children exist, use them
            if((currentNode == root || currentBlock.getExecutionResult()) && 
                    currentNode.hasChildren()){
                currentNode = currentNode.getChildren().getFirst();
            // Else if current Node has a right sibling use it
            } else if(currentNode.hasRightSibling()){
                currentNode = currentNode.getRightSibling();
                
            // Else search parents for siblings, potentially up to root
            } else {
                while(currentNode.hasParent()){
                    currentNode = currentNode.getParent();
                    if(currentNode.hasRightSibling()){
                        currentNode = currentNode.getRightSibling();
                        break;
                    }
                }
            }
            currentBlock = currentNode.getBlock();
            return currentNode.equals(startingNode) ? null : currentNode.getBlock();
        }
    }
    /**
         * 
         * @return the highest amount of direct descendants of any node in the tree
         */
        public int mostChildren(Node root){
            int max = 0;
            if(root.hasChildren()){
                max = root.getChildren().size();
                for(Node child: root.getChildren()){
                    int childMax = mostChildren(child);
                    max = Integer.max(max, childMax);
                }
            }
            return max;
        }
}
