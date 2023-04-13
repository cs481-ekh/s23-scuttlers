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
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Random;

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

    Vector2 pos;
    String target;

    public MoveBlock(MoveOptions options){
        super(options);
    }
    @Override
    public void execute(GameData gameData, LevelData levelData){
        float delta = Gdx.graphics.getDeltaTime();

        // determine if ant, lvlobj, or random (move in random direction)
        // System.out.println(options.getFirstOptionChoice());
        ant = gameData.getCurrentAnt();
        int antX = (int)(ant.getPos().x/32);
        int antY = (int)(ant.getPos().y/32);

        if(!setup) {
            g = levelData.getLevelGraph(ant.getIntelligence());
            shortestPath = new BFSShortestPath<>(g);

            switch (options.getFirstOptionChoice()) {
                case "Ant":
                    pos = levelData.getEnemies().iterator().next().getPos();
                    // GraphUtils.getVertexNeighbors((int) pos.x/32, (int)pos.y/32).iterator().next();
                    // for (String targetPos: GraphUtils.getVertexNeighbors(antX, antY))
                    target = GraphUtils.getVertexNeighbors((int) pos.x/32, (int)pos.y/32).iterator().next();

                    System.out.println(target);
                    break;
                case "Random":
                    Random rand = new Random();

                    for (int i = 0; i < rand.nextInt(levelData.getAllObjects().size()-1); i++) {
                        levelData.getAllObjects().iterator().next();
                    }
                    
                    pos = levelData.getAllObjects().iterator().next().getPos();
                    target = GraphUtils.getVertexNeighbors((int) pos.x/32, (int)pos.y/32).iterator().next();

                    break;
                default:
                    // System.out.println("lvl obj");
                    // for (LevelObject lvlObj: levelData.getAllObjects()) {
                        
                    // }
                    break;
            }

            path = (LinkedList<DefaultEdge>) shortestPath.getPath(GraphUtils.getVertexName(antX, antY), target).getEdgeList();
            currEdge = path.peek();
            setup = true;
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

    public static Class<? extends BlockOptions> getOptionsClass(){
        return InteractOptions.class;
    }
}
