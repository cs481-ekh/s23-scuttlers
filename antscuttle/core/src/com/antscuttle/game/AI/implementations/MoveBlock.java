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

import java.util.LinkedList;

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
        if(!setup) {
            ant = gameData.getCurrentAnt();
            g = levelData.getLevelGraph(ant.getIntelligence());
            shortestPath = new BFSShortestPath<>(g);
            path = (LinkedList<DefaultEdge>) shortestPath.getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32), GraphUtils.getVertexName(0+1, 12-1)).getEdgeList();
            currEdge = path.peek();
            setup = true;
        }
        int antX = (int)(ant.getPos().x/32);
        int antY = (int)(ant.getPos().y/32);
        //System.out.println("antX: " + antX + ", antY: " + antY);
        // Find the nearest level object given from lvlobj
        // String target = options.getFirstOptionChoice();
        
        // Check if path exists
        if(currEdge == null)
            setExecutionResult(false);
        // check if not at the next tile
        srcX = Integer.parseInt(g.getEdgeSource(currEdge).substring(1,3));
        srcY = Integer.parseInt(g.getEdgeSource(currEdge).substring(3,5));

        targX = Integer.parseInt(g.getEdgeTarget(currEdge).substring(1,3));
        targY = Integer.parseInt(g.getEdgeTarget(currEdge).substring(3,5));
        
        if(antX == Integer.parseInt(g.getEdgeTarget(currEdge).substring(1, 3)) &&
            antY == Integer.parseInt(g.getEdgeTarget(currEdge).substring(3, 5))){
            path.removeFirst();
            currEdge = path.peek();
        }
        if(path.isEmpty() && currEdge == null){
            setExecutionResult(true);
            setFinished(true);
            return;
        }
        
        //System.out.println("targX: " + targX + " targY: " + targY);
         if (antX < targX) {
             ant.setPos(ant.getPos().x+delta*ant.getSpeed(), ant.getPos().y);
         } else if(antX > targX){
             ant.setPos(ant.getPos().x-delta*ant.getSpeed(), ant.getPos().y);
         } else if(antY < targY){
             ant.setPos(ant.getPos().x, ant.getPos().y+delta*ant.getSpeed());
         } else if(antY > targY){
             ant.setPos(ant.getPos().x, ant.getPos().y-delta*ant.getSpeed());
         }
            
        


        // for (DefaultEdge edge: path) {
        // }

        //System.out.println(ant.getPos());
    }

    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
