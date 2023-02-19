import com.antscuttle.game.AI.*;
import com.antscuttle.game.AI.implementations.*;
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
        DecisionBlock db = new MoveBlock("");
        Node node = new Node(db);
        assertNotNull(db);
        assertNotNull(node);
    }
    @Test
    public void Tree_Root_Only_HasNext(){
        AI ai = new AI(new Node(new MoveBlock("")));
        assertNotNull(ai.getRoot());
        Iterator it = ai.iterator();
        assertFalse(it.hasNext());
    }
    @Test
    public void Tree_One_Child_HasNext(){
        Node root = new Node(new MoveBlock(""));
        root.addChild(new Node(new MoveBlock("")));
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        assertTrue(it.hasNext());
    }
    @Test
    public void Tree_Order_Three_Nodes(){
        Node root = new Node(new MoveBlock(""));
        Node a = new Node(new MoveBlock("a"));
        Node b = new Node(new MoveBlock("b"));
        Node c = new Node(new MoveBlock("c"));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"a", "b", "c"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            tblock.execute();
            assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            
        }
    }
    
    @Test
    public void Tree_Order_Six_Nodes(){
        Node root = new Node(new MoveBlock(""));
        Node a = new Node(new MoveBlock("a"));
        Node b = new Node(new MoveBlock("b"));
        Node c = new Node(new MoveBlock("c"));
        Node d = new Node(new MoveBlock("d"));
        Node e = new Node(new MoveBlock("e"));
        Node f = new Node(new MoveBlock("f"));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"a", "b", "c","d", "e", "f"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            if(tblock != null){
                tblock.execute();
                assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            }
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_First(){
        Node root = new Node(new MoveBlock(""));
        Node a = new Node(new MoveBlock("a"));
        Node b = new Node(new MoveBlock("b"));
        Node c = new Node(new MoveBlock("c"));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(a);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"c"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            if(tblock != null){
                tblock.execute();
                assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            }
        }
    }
    @Test
    public void Tree_Order_Three_Nodes_Remove_Last(){
        Node root = new Node(new MoveBlock(""));
        Node a = new Node(new MoveBlock("a"));
        Node b = new Node(new MoveBlock("b"));
        Node c = new Node(new MoveBlock("c"));
        a.addChild(b);
        root.addChild(a);
        root.addChild(c);
        root.removeChild(c);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"a", "b"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            if(tblock != null){
                tblock.execute();
                assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            }
        }
    }
    @Test
    public void Tree_Order_Six_Nodes_Remove_Middle(){
        Node root = new Node(new MoveBlock(""));
        Node a = new Node(new MoveBlock("a"));
        Node b = new Node(new MoveBlock("b"));
        Node c = new Node(new MoveBlock("c"));
        Node d = new Node(new MoveBlock("d"));
        Node e = new Node(new MoveBlock("e"));
        Node f = new Node(new MoveBlock("f"));
        
        a.addChild(b);
        a.addChild(c);
        root.addChild(a);
        d.addChild(e);
        root.addChild(d);
        root.addChild(f);
        root.removeChild(d);
        AI ai = new AI(root);
        Iterator it = ai.iterator();
        
        String s[] = {"a", "b", "c", "f"};
        for(int i=0; i<s.length; i++){
            DecisionBlock tblock = (DecisionBlock)it.next();
            if(tblock != null){
                tblock.execute();
                assertEquals(s[i], ((MoveBlock)tblock).getDirection());
            }
        }
    }
    
    
    
}
