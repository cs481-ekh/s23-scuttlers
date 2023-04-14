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
    int pathCounter;
    Point target;

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
        }
        int antX = (int)(ant.getPos().x/32);
        int antY = (int)(ant.getPos().y/32);
        
        // Refresh path every so many calls
        if(pathCounter++ == 20){
            path = findPath(target);
            pathCounter = 0;
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
        
        if(antX == targX && antY == targY){
            path.removeFirst();
            currEdge = path.peek();
        }

        if(path.isEmpty() && currEdge == null){
            setExecutionResult(true);
            setFinished(true);
            return;
        }
        
        if (antX < targX) {
            ant.setPos(ant.getPos().x+delta*ant.getSpeed(), ant.getPos().y);
        } else if(antX > targX){
            ant.setPos(ant.getPos().x-delta*ant.getSpeed(), ant.getPos().y);
        } else if(antY < targY){
            ant.setPos(ant.getPos().x, ant.getPos().y+delta*ant.getSpeed());
        } else if(antY > targY){
            ant.setPos(ant.getPos().x, ant.getPos().y-delta*ant.getSpeed());
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
            System.out.println("path: "+path);
            // Out of these potential paths, choose the shortest one
            if(potentialPath != null && (path == null || potentialPath.size()< path.size())){
                path = (LinkedList<DefaultEdge>)potentialPath;
                target = p;
                System.out.println("Target: "+target.x+", "+target.y);
            }
        }
        if(path != null)
            currEdge = path.peek();
    }
    protected LinkedList<DefaultEdge> findPath(Point sink){
        List<DefaultEdge> potentialPath;
        try{
            potentialPath = shortestPath
                    .getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32),  GraphUtils.getVertexName(sink.x, sink.y))
                    .getEdgeList();
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
