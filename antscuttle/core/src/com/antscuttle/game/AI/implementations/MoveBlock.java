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

    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(GameData gameData, LevelData levelData){
        float delta = Gdx.graphics.getDeltaTime();
        Set<Point> potentialTargets = new HashSet<>();
        
        if(!setup) {
            ant = gameData.getCurrentAnt();
            g = levelData.getLevelGraph(ant.getIntelligence());
            shortestPath = new BFSShortestPath<>(g);
            currEdge = path.peek();
            // Find all potential targets of type targetType
            String targetType = options.getFirstOptionChoice();
            potentialTargets = findTargets(levelData, gameData, targetType);
            for(Point p: potentialTargets){
                // Find path for each potential target
                System.out.println("potential target: " + p.x + ", " + p.y);
                
                // Out of these potential paths, choose the shortest one
                
            }
            
            setup = true;
        }
        int antX = (int)(ant.getPos().x/32);
        int antY = (int)(ant.getPos().y/32);
        //System.out.println("antX: " + antX + ", antY: " + antY);
        
        
        
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
    
    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
