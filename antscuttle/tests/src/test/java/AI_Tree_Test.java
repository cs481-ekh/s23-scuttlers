import com.antscuttle.game.AI.*;
import com.antscuttle.game.AI.implementations.*;
import com.antscuttle.game.AI.implementations.MoveBlock.MoveDirection;
import com.antscuttle.game.AI.implementations.MoveBlock.MoveType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Iterator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.NullPointerException;


/**
 *
 * @author antho
 */
public class AI_Tree_Test {
    
    @Test
    public void CreateNode(){
        DecisionBlock db = new RootBlock();
        Node node = new Node(db, new Image());
        assertNotNull(db);
        assertNotNull(node);
    }
    @Test
    public void Tree_Root_Only_HasNext(){
        AI ai = new AI(new Node(new RootBlock(), new Image()), "test");
        assertNotNull(ai.getRoot());
        Iterator it = ai.iterator();
        assertFalse(it.hasNext());
    }
    @Test
    public void Tree_One_Child_HasNext(){
        Node root = new Node(new RootBlock(), new Image());
        root.addChild(new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image()));
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        assertTrue(it.hasNext());
    }
    @Test
    public void Tree_Order_Three_Nodes(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"DOWN", "UP", "LEFT"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null);
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            
        }
    }
    
    @Test
    public void Tree_Order_Six_Nodes(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10), new Image());
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"DOWN", "UP", "LEFT","RIGHT", "LEFT", "UP"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
                tblock.execute(null);
                assertEquals(s[i], ((MoveBlock)tblock).getDirection());
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_First(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(a);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"LEFT"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null);
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_Last(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(c);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"DOWN", "UP"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null);
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
        }
    }
    @Test
    public void Tree_Order_Six_Nodes_Remove_Middle(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10), new Image());
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        root.removeChild(d);
        AI ai = new AI(root, "test");
        Iterator it = ai.iterator();
        
        String s[] = {"DOWN", "UP", "LEFT", "UP"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null);
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
        }
    }
    @Test
    public void Tree_Max_Child_Count_Is_Three_v1(){
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10), new Image());
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        
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
        Node root = new Node(new RootBlock(), new Image());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10), new Image());
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10), new Image());
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10), new Image());
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10), new Image());
        
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
        AI ai = new AI(new Node(new RootBlock(), new Image()), "test");
        assertEquals(ai.mostChildren(ai.getRoot()), 0);
    }
    
}
