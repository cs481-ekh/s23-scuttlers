
package com.antscuttle.game.AI;


import com.antscuttle.game.AI.implementations.MoveBlock;
import java.io.Serializable;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Util.GraphUtils;
import com.badlogic.gdx.math.Vector2;
import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


/**
 *
 * @author antho
 */
public abstract class DecisionBlock implements Serializable{
    // Execution result represents whether the block was able to
    // achieve its goal. This is used during tree traversal.
    private Boolean executionResult;
    // Finished represents whether the block is done executing.
    // This is used to determine whether to continue using this block.
    private boolean finished = false;
    protected BlockOptions options;
    
    
    public DecisionBlock(BlockOptions blockOptions){
        this.options = blockOptions;
    }
    public boolean hasOptionOne(){
        return options.hasOptionOne();
    };
    public boolean hasOptionTwo(){
        return options.hasOptionTwo();
    };
    public String[] getAllOptionOnes(){
        return options.getAllOptionOnes();
    };
    public String[] getAllOptionTwos(){
        return options.getAllOptionTwos();
    };
    public BlockOptions getChosenOptions(){
        return options;
    }
    
    public boolean getExecutionResult(){
        return executionResult;
    }

    public void setExecutionResult(boolean bool) {
        executionResult = bool;
    }
    public Boolean isFinished(){
        return finished;
    }
    public void setFinished(boolean finished){
        this.finished = finished;
    }
    public void execute(GameData gameData, LevelData levelData, Ant dbOwner){
        // Temp code
        executionResult = true;
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return null;
    }
    public Set<Point> findTargets(LevelData levelData, GameData gameData, String targetType, Graph<String,DefaultEdge> g, Ant dbOwner){
        Set<Point> targets = new HashSet<>();
        Set<LevelObject> levelObjs = new HashSet<>();
        Ant player = dbOwner;
        Vector2 playerPos = player.getPos();
        int antX = levelData.AntPosToGraphPos(playerPos.x);
        int antY = levelData.AntPosToGraphPos(playerPos.y);
        switch(targetType){
            case "Ant": 
                Set<Point> enemyPoints = new HashSet<>();
                if(player.getName().equals("npc")){
                    Vector2 pos = gameData.getCurrentAnt().getPos();
                    int posx = (int)(pos.x/32);
                    int posy = (int)(pos.y/32);
                    enemyPoints.addAll(GraphUtils.getVertexNeighborsAsPoints(posx, posy));
                }else
                    enemyPoints = levelData.getEnemyNeighboringPoints();
                targets.addAll(enemyPoints);
                break;
            case "Random": 
                Point n = getRandomNeighbor(antX, antY, g);
                if(n != null)
                    targets.add(n);
                break;
            default: // LevelObject
                levelObjs = levelData.getTargetableObjects();
                for(LevelObject obj : levelObjs){
                    String className = obj.getClass().getSimpleName();
                    if(className.equals(targetType)){
                        Vector2 pos = obj.getPos();
                        int x = levelData.LevelObjPosToGraphPos(pos.x);
                        int y = levelData.LevelObjPosToGraphPos(pos.y);
                        // Check if it's collidable, because you can't target a collidable in the graph
                        if(levelData.getCollidableObjects().contains(obj))
                            targets.addAll(GraphUtils.getVertexNeighborsAsPoints(x, y));
                        else
                            targets.add(new Point(x, y));
                    }
                }

                
        }
        return targets;
    }
    
   public Point getRandomNeighbor(int x, int y, Graph<String,DefaultEdge> g){
        Set<Point> neighbors = GraphUtils.getVertexNeighborsAsPoints(x, y);
        int rand = new Random().nextInt(neighbors.size());
        int i=0;
        for(Point neighbor: neighbors){
            if(!g.containsVertex(GraphUtils.getVertexName(neighbor.x, neighbor.y))){
                if(rand == i)
                    rand++;
                i++;
                continue;
            }
            if(rand == i++)
                return neighbor;
        }
        
        return null;
    }
   public abstract void resetBlock();
}
