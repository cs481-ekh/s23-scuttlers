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

        if(!setup) {
            ant = gameData.getCurrentAnt();
            g = levelData.getLevelGraph(ant.getIntelligence());
            shortestPath = new BFSShortestPath<>(g);
            path = (LinkedList<DefaultEdge>) shortestPath.getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32), GraphUtils.getVertexName(0+1, 12-1)).getEdgeList();
            setup = true;
        }
        
        // Find the nearest level object given from lvlobj
        // String target = options.getFirstOptionChoice();

        // check if not at the next tile
        if (!path.isEmpty()) {
            currEdge = path.removeFirst();
            srcX = Integer.parseInt(g.getEdgeSource(currEdge).substring(1,3));
            srcY = Integer.parseInt(g.getEdgeSource(currEdge).substring(3,5));

            targX = Integer.parseInt(g.getEdgeTarget(currEdge).substring(1,3));
            targY = Integer.parseInt(g.getEdgeTarget(currEdge).substring(3,5));
            // System.out.println("x: " + targX + " y: " + targY);
            // if (srcX != targX) {
            //     ant.setPos(targX-levelData.getDeltaTime()*ant.getSpeed(), targY*32);
            // } else {
            //     ant.setPos(targX*32, targY-levelData.getDeltaTime()*ant.getSpeed());
            // }
            ant.setPos(targX*32, targY*32);
        } else {
            finished(true);
            return;
        }


        // for (DefaultEdge edge: path) {
        // }

        System.out.println(ant.getPos());
    }




    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
