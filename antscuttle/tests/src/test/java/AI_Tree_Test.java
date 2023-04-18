import com.antscuttle.game.AI.*;
import com.antscuttle.game.AI.implementations.*;
import com.antscuttle.game.AI.options.MoveOptions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Iterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author antho
 */
public class AI_Tree_Test {
    
    @Test
    public void CreateNode(){
        DecisionBlock db = new RootBlock();
        Node node = new Node(db);
        assertNotNull(db);
        assertNotNull(node);
    }
    @Test
    public void Tree_Root_Only_HasNext(){
        AI ai = new AI(new Node(new RootBlock()), "test");
        assertNotNull(ai.getRoot());
        Iterator it = ai.iterator();
        assertFalse(it.hasNext());
    }
    @Test
    public void Tree_One_Child_HasNext(){
        Node root = new Node(new RootBlock());
        root.addChild(new Node(new MoveBlock(new MoveOptions("Random"))));
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        assertTrue(it.hasNext());
    }
    @Test
    public void Tree_Order_Three_Nodes(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Door")));
        Node c = new Node(new MoveBlock(new MoveOptions("Ant")));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"Random", "Door", "Ant"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null, null, null);
            assertEquals(s[i], ((MoveBlock)tblock).getChosenOptions().getFirstOptionChoice());
            
        }
    }
    
    @Test
    public void Tree_Order_Six_Nodes(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Ant")));
        Node c = new Node(new MoveBlock(new MoveOptions("Random")));
        Node d = new Node(new MoveBlock(new MoveOptions("Door")));
        Node e = new Node(new MoveBlock(new MoveOptions("Pressure Plate")));
        Node f = new Node(new MoveBlock(new MoveOptions("Random")));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"Random", "Ant", "Random","Door", "Pressure Plate", "Random"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
                tblock.execute(null, null, null);
                assertEquals(s[i], ((MoveBlock)tblock).getChosenOptions().getFirstOptionChoice());
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_First(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Random")));
        Node c = new Node(new MoveBlock(new MoveOptions("Ant")));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(a);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"Ant"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null, null, null);
            assertEquals(s[i], ((MoveBlock)tblock).getChosenOptions().getFirstOptionChoice());
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_Last(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Random")));
        Node c = new Node(new MoveBlock(new MoveOptions("Ant")));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(c);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"Random", "Random"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null, null, null);
            assertEquals(s[i], ((MoveBlock)tblock).getChosenOptions().getFirstOptionChoice());
        }
    }
    @Test
    public void Tree_Order_Six_Nodes_Remove_Middle(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Ant")));
        Node c = new Node(new MoveBlock(new MoveOptions("Door")));
        Node d = new Node(new MoveBlock(new MoveOptions("Door")));
        Node e = new Node(new MoveBlock(new MoveOptions("Random")));
        Node f = new Node(new MoveBlock(new MoveOptions("Random")));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        root.removeChild(d);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"Random", "Ant", "Door", "Random"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null, null, null);
            assertEquals(s[i], ((MoveBlock)tblock).getChosenOptions().getFirstOptionChoice());
        }
    }
    @Test
    public void Tree_Max_Child_Count_Is_Three_v1(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Random")));
        Node c = new Node(new MoveBlock(new MoveOptions("Random")));
        Node d = new Node(new MoveBlock(new MoveOptions("Random")));
        Node e = new Node(new MoveBlock(new MoveOptions("Random")));
        Node f = new Node(new MoveBlock(new MoveOptions("Random")));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        AI ai = new AI(root, "test");
        int max = ai.mostChildren(ai.getRoot());
        System.out.println(max);
        assertEquals(max, 3);
    }
    
    @Test
    public void Tree_Max_Child_Count_Is_Two(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(new MoveOptions("Random")));
        Node b = new Node(new MoveBlock(new MoveOptions("Random")));
        Node c = new Node(new MoveBlock(new MoveOptions("Random")));
        Node d = new Node(new MoveBlock(new MoveOptions("Random")));
        Node e = new Node(new MoveBlock(new MoveOptions("Random")));
        Node f = new Node(new MoveBlock(new MoveOptions("Random")));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        d.addChild(f);
        AI ai = new AI(root, "test");
        int max = ai.mostChildren(ai.getRoot());
        System.out.println(max);
        assertEquals(max, 2);
    }
    
    @Test
    public void Tree_Max_child_Count_Is_Zero_v1(){
        AI ai = new AI(new Node(new RootBlock()), "test");
        assertEquals(ai.mostChildren(ai.getRoot()), 0);
    }
    
}
