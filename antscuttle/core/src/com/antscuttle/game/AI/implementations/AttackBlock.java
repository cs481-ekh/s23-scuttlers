
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.AttackOptions;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.BaseAnt.AnimationDirection;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.LevelObject.implementations.Projectile;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Util.GraphUtils;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author antho
 */
public class AttackBlock extends DecisionBlock{
    private Point targetTile;
    
    Ant ant;
    Graph<String,DefaultEdge> g;
    BFSShortestPath<String, DefaultEdge> shortestPath;
    LinkedList<DefaultEdge> path;
    
    private boolean setup = false;
    int targX;
    int targY;
    int srcX;
    int srcY;
    DefaultEdge currEdge;
    Point srcTile;
    int pathCounter;
    Point finalTarget;
    Object objectTarget;
    int attackCounter;
    int attackCooldown = 40;
    
    public AttackBlock(AttackOptions options){
        super(options);
    }
    
    
    @Override
    public void execute(GameData gameData, LevelData levelData){
        ant = gameData.getCurrentAnt();
        String attackType = options.getFirstOptionChoice();
        float delta = Gdx.graphics.getDeltaTime();
        
        
        if(!setup) {
            pathCounter = 0;
            attackCounter = 0;
            ant = gameData.getCurrentAnt();
            g = levelData.getLevelGraph(ant.getIntelligence());
            srcTile = new Point((int)((ant.getPos().x)/32),(int)((ant.getPos().y)/32));
            shortestPath = new BFSShortestPath<>(g);
            findTargetAndPath(gameData, levelData);
            setup = true;
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
        if(attackCounter < attackCooldown)
            attackCounter++;
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
        if(attackType.equals("Ranged") && playerHasLineOfSight(finalTarget,path)){
            Ant enemy = (Ant)objectTarget;
            // No movement required, attack
            int damageDone = 0;
            int playerDamage = ant.getRangedDamage();
            DamageType damageType = ant.getRangedDamageType();
            // Make bullet, add it to the levelData
            RangedWeapon rw = ant.getRangedWeapon();
            if(attackCounter == attackCooldown){
                attackCounter = 0;
                if(rw != null && enemy != null){
                    if(enemy.getHealth()>0)
                        ant.attack(enemy, "Ranged", levelData);
                    else{
                        System.out.println("enemy out of health");
                        objectTarget = null;
                        setExecutionResult(true);
                        setFinished(true);
                    }

                } else {
                    setExecutionResult(false);
                    setFinished(true);
                }
            }
            return;
        }
        if((antX == targX && antY == targY)){
            // Ant has reached edge target
            if(path == null){
                if(antX == finalTarget.x && antY == finalTarget.y){
                    // Ant reached target
                    // Attack the target
                    int damageDone = 0;
                    int damage = ant.getMeleeDamage();
                    DamageType damageType = ant.getMeleeDamageType();
                    String targetType = options.getSecondOptionChoice();
                    if(damageType == null)
                        return;
                    
                     if(!targetType.equals("Ant")){
                         objectTarget = levelData.objAt(finalTarget);
                         damageDone = ((InteractableLevelObject)objectTarget).receiveAttack(damage, damageType);
                     }else {
                         objectTarget = levelData.enemyAt(finalTarget);
                         damageDone = ((Ant)objectTarget).receiveAttack(damage, damageType, levelData);
                     }
                    if(damageDone > 0)
                        setExecutionResult(true);
                    else
                        setExecutionResult(false);
                    return;
                    
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
            // Movement towards target, if necessary, has completed
            
        
    }
    protected void findTargetAndPath(GameData gameData, LevelData levelData){
        Set<Point> potentialTargets;
        // Find all potential targets of type targetType
        String attackType = options.getFirstOptionChoice();
        String targetType = options.getSecondOptionChoice();
        boolean targetIsCollidable;
        List<DefaultEdge> potentialPath;
        
        // Is it collidable?
        if(targetType.equals("Ant"))
            targetIsCollidable = true;
        else {
            Set<LevelObject> collidables = levelData.getCollidableObjects();
            targetIsCollidable = false;
            for(LevelObject obj : collidables){
                if(obj.getClass().getSimpleName().equals(targetType)){
                    targetIsCollidable = true;
                    break;
                }
                
            }
        }
        
        potentialTargets = new HashSet<>();
        if(targetType.equals("Ant") && attackType.equals("Ranged"))
            for(Ant enemy: levelData.getEnemies()){
                potentialTargets.add(new Point(
                        levelData.AntPosToGraphPos(enemy.getPos().x),
                        levelData.AntPosToGraphPos(enemy.getPos().y)));
            }
        else
            potentialTargets = findTargets(levelData, gameData, targetType, g);
       
        if(targetIsCollidable && attackType.equals("Ranged")){
            
        }
        potentialPath = null;
        for(Point p: potentialTargets){
                potentialPath = findPath(p);

            // Out of these potential paths, choose the shortest one
            if(potentialPath != null && (path == null || potentialPath.size()< path.size())){
                path = (LinkedList<DefaultEdge>)potentialPath;
                finalTarget = p;
                if(targetType.equals("Ant"))
                    objectTarget = levelData.enemyAt(p);
            }
        }
        if(path != null)
            currEdge = path.peek();
    }
    protected boolean playerHasLineOfSight(Point target, List<DefaultEdge> neighborPath){
        if(srcTile.x != target.x && srcTile.y != target.y)
            return false;
        if(srcTile.x == target.x){
            return pathHasStaticX(target.x, neighborPath);
        } else {
            return pathHasStaticY(target.y, neighborPath);
        }
    }
    protected boolean pathHasStaticX(int x, List<DefaultEdge> path){
        int srcx, targx;
        for(DefaultEdge edge : path){
            //System.out.println("srcx: "+edge.toString().substring(2,4)+" targx: "+edge.toString().substring(10,12)+" x: "+x);
            srcx = Integer.parseInt(edge.toString().substring(2,4));
            targx = Integer.parseInt(edge.toString().substring(10,12));
            
            if(srcx != x && targx != x)
                return false;
        }
        return true;
    }
    protected boolean pathHasStaticY(int y, List<DefaultEdge> path){
        int srcy, targy;
        for(DefaultEdge edge : path){
            srcy = Integer.parseInt(edge.toString().substring(4,6));
            targy = Integer.parseInt(edge.toString().substring(12,14));
            if(srcy != y && targy != y)
                return false;
        }
        return true;
    }
    protected LinkedList<DefaultEdge> findPath(Point sink){
        List<DefaultEdge> potentialPath;
        try{
            GraphPath gp = shortestPath
                    .getPath(GraphUtils.getVertexName((int) ant.getPos().x / 32, (int) ant.getPos().y / 32),
                            GraphUtils.getVertexName(sink.x, sink.y));
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
        return AttackOptions.class;
    }

}
