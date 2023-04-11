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

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.shortestpath.*;

public class MoveBlock extends DecisionBlock {
    
    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(GameData gameData, LevelData levelData){
        Ant ant = gameData.getCurrentAnt();
        Graph<String,DefaultEdge> g = levelData.getLevelGraph(ant.getIntelligence());

        // Find the nearest level object given from lvlobj
        
        String target = options.getFirstOptionChoice();
      
        BFSShortestPath<String, DefaultEdge> shortestPath = new BFSShortestPath<>(g);

        List<DefaultEdge> path = shortestPath.getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32), GraphUtils.getVertexName(0+1, 12-1)).getEdgeList();

        for (DefaultEdge edge: path) {
            // String srcX = g.getEdgeSource(edge).substring(1, 2);
            // String srcY = g.getEdgeSource(edge).substring(1, 2);

            int targX = Integer.parseInt(g.getEdgeTarget(edge).substring(1,3));
            int targY = Integer.parseInt(g.getEdgeTarget(edge).substring(3,5));

            ant.setPos(targX*32, targY*32);
        }

        System.out.println(ant.getPos());
    }

    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
