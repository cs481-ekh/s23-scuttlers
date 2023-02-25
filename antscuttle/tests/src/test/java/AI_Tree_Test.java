import com.antscuttle.game.AI.*;
import com.antscuttle.game.AI.implementations.*;
import com.antscuttle.game.AI.implementations.MoveBlock.MoveDirection;
import com.antscuttle.game.AI.implementations.MoveBlock.MoveType;
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
        Node node = new Node(db);
        assertNotNull(db);
        assertNotNull(node);
    }
    @Test
    public void Tree_Root_Only_HasNext(){
        AI ai = new AI(new Node(new RootBlock()));
        assertNotNull(ai.getRoot());
        Iterator it = ai.iterator();
        assertFalse(it.hasNext());
    }
    @Test
    public void Tree_One_Child_HasNext(){
        Node root = new Node(new RootBlock());
        root.addChild(new Node(new MoveBlock(MoveDirection.DOWN, 10)));
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        assertTrue(it.hasNext());
    }
    @Test
    public void Tree_Order_Three_Nodes(){
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10));
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10));
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        AI ai = new AI(root);
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
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10));
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10));
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10));
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        AI ai = new AI(root);
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
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10));
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10));
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(a);
        AI ai = new AI(root);
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
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10));
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10));
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(c);
        AI ai = new AI(root);
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
        Node root = new Node(new RootBlock());
        Node a = new Node(new MoveBlock(MoveDirection.DOWN, 10));
        Node b = new Node(new MoveBlock(MoveDirection.UP, 10));
        Node c = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        Node d = new Node(new MoveBlock(MoveDirection.RIGHT, 10));
        Node e = new Node(new MoveBlock(MoveDirection.LEFT, 10));
        Node f = new Node(new MoveBlock(MoveDirection.UP, 10));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        root.removeChild(d);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"DOWN", "UP", "LEFT", "UP"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute(null);
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
        }
    }
    
    
    
}