package com.antscuttle.game.AI.implementations;


import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.InteractOptions;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Util.GraphUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Point;
import java.util.HashSet;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.shortestpath.*;

public class MoveBlock extends DecisionBlock {

    private boolean setup = false;
    Ant ant;
    Graph<String,DefaultEdge> g;
    BFSShortestPath<String, DefaultEdge> shortestPath;
    LinkedList<DefaultEdge> path;
    
    
    int targX;
    int targY;
    int srcX;
    int srcY;
    DefaultEdge currEdge;
    Point srcTile;
    int pathCounter;
    Point finalTarget;
    Object objectTarget;

    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(GameData gameData, LevelData levelData){
        float delta = Gdx.graphics.getDeltaTime();
        
        
        if(!setup) {
            pathCounter = 0;
            ant = gameData.getCurrentAnt();
            g = levelData.getLevelGraph(ant.getIntelligence());
            shortestPath = new BFSShortestPath<>(g);
            findTargetAndPath(gameData, levelData);
            setup = true;
            srcTile = new Point((int)((ant.getPos().x)/32),(int)((ant.getPos().y)/32));
            if(path == null || path.isEmpty()){
                setExecutionResult(false);
                setFinished(true);
                return;
            }
        }
        float antX = ((ant.getPos().x)/32);
        float antY = ((ant.getPos().y)/32);
        
        // Refresh path every so many calls
        if(pathCounter++ == 20){
            findTargetAndPath(gameData,levelData);
            pathCounter = 0;
            if(path == null || path.isEmpty()){
                setExecutionResult(false);
                setFinished(true);
                return;
            }
                
        }
        // Check if path exists
        if(currEdge == null) {
            setExecutionResult(false);
            setFinished(true);
            return;
        }
        // check if not at the next tile
        srcX = Integer.parseInt(g.getEdgeSource(currEdge).substring(1,3));
        srcY = Integer.parseInt(g.getEdgeSource(currEdge).substring(3,5));

        targX = Integer.parseInt(g.getEdgeTarget(currEdge).substring(1,3));
        targY = Integer.parseInt(g.getEdgeTarget(currEdge).substring(3,5));
        
        if(targX == srcTile.x && targY == srcTile.y){
            // Edge is backwards
            int temp = srcX;
            srcX = targX;
            targX = temp;
            
            temp = srcY;
            srcY = targY;
            targY = temp;
        }
        if(antX == targX && antY == targY){
            // Ant has reached edge target
            if(path == null){
                if(antX == finalTarget.x && antY == finalTarget.y){
                    // Ant reached target
                    setExecutionResult(true);
                } else {
                    setExecutionResult(false);
                }
                setFinished(true);
                return;
            }
            
            path.removeFirst();
            srcTile = new Point(targX,targY);
            currEdge = path.peek();
        }
        if((path == null || path.isEmpty()) && currEdge == null){
            setExecutionResult(true);
            setFinished(true);
            return;
        }
        if (antX < targX) {
            float newX = ant.getPos().x+delta*ant.getSpeed(); 
            ant.setPos(Float.min(newX, targX*32), ant.getPos().y);
        } else if(antX > targX){
            float newX = ant.getPos().x-delta*ant.getSpeed();
            ant.setPos(Float.max(newX, targX*32), ant.getPos().y);
        } else if(antY < targY){
            float newY = ant.getPos().y+delta*ant.getSpeed();
            ant.setPos(ant.getPos().x, Float.min(newY, targY*32));
        } else if(antY > targY){
            float newY = ant.getPos().y-delta*ant.getSpeed();
            ant.setPos(ant.getPos().x, Float.max(newY, targY*32));
        }
        
    }
    protected void findTargetAndPath(GameData gameData, LevelData levelData){
        Set<Point> potentialTargets;
        // Find all potential targets of type targetType
        String targetType = options.getFirstOptionChoice();
        potentialTargets = findTargets(levelData, gameData, targetType);
        for(Point p: potentialTargets){
            // Find path for each potential target
            List<DefaultEdge> potentialPath = findPath(p);
            // Out of these potential paths, choose the shortest one
            if(potentialPath != null && (path == null || potentialPath.size()< path.size())){
                path = (LinkedList<DefaultEdge>)potentialPath;
                finalTarget = p;
            }
        }
        if(path != null)
            currEdge = path.peek();
    }
    protected LinkedList<DefaultEdge> findPath(Point sink){
        List<DefaultEdge> potentialPath;
        try{
            GraphPath gp = shortestPath
                    .getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32),  GraphUtils.getVertexName(sink.x, sink.y));
            if(gp == null)
                return null;
            potentialPath = gp.getEdgeList();
            if(potentialPath.isEmpty())
                return new LinkedList<>();
            return (LinkedList<DefaultEdge>)potentialPath;
        } catch (IllegalArgumentException e){
        }
        // Point not in graph
        return null;
    }
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
