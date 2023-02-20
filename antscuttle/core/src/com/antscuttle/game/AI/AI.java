
package com.antscuttle.game.AI;

import java.util.Iterator;

/**
 *
 * @author antho
 */
public class AI implements Iterable{
    
    private final Node root;
    
    public AI(Node root){
        this.root = root;
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
}
