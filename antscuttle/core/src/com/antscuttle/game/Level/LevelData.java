
package com.antscuttle.game.Level;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.Projectile;
import com.antscuttle.game.Util.GraphUtils;
import com.badlogic.gdx.math.Vector2;
import java.awt.Point;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Holds data for the current level being played
 * @author antho
 */
public class LevelData implements Serializable{
    protected Set<LevelObject> attackableObjects;
    protected Set<LevelObject> interactableObjects;
    protected Set<LevelObject> collidableObjects;
    protected Set<LevelObject> hazardousObjects;
    protected Set<LevelObject> allObjects;
    protected Set<LevelObject> endSpaces;
    protected Set<Projectile> projectiles;
    protected Set<Ant> enemies;
    protected Graph<String, DefaultEdge> zeroIntelligenceGraph;
    protected Graph<String, DefaultEdge> normalIntelligenceGraph;
    protected boolean gameFinished = false;
    protected boolean gameWon = false;

    public LevelData(){
        attackableObjects = new HashSet<>();
        interactableObjects = new HashSet<>();
        collidableObjects = new HashSet<>();
        hazardousObjects = new HashSet<>();
        enemies = new HashSet<>();
        allObjects = new HashSet<>();
        projectiles = new HashSet<>();
        endSpaces = new HashSet<>();
        zeroIntelligenceGraph = new SimpleGraph<>(DefaultEdge.class);
        normalIntelligenceGraph = new SimpleGraph<>(DefaultEdge.class);
    }
    /**
     * 
     * @param o 
     */
    public void addEndSpace(LevelObject o){
        endSpaces.add(o);
    }
    /**
     * 
     * @return all End spaces
     */
    public Set<LevelObject> getEndSpaces(){
        return endSpaces;
    }
    /**
     * 
     * @return Projectiles currently in level
     */
    public Set<Projectile> getProjectiles(){
        return projectiles;
    }
    /**
     * 
     * @param p 
     */
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }
    /**
     * 
     * @param p 
     */
    public void removeProjectile(Projectile p){
        projectiles.remove(p);
    }
    /**
     * 
     * @return if the game is finished
     */
    public boolean isGameFinished() {
        return gameFinished;
    }
    /**
     * 
     * @param gameFinished 
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
    /**
     * 
     * @return if the game was won
     */
    public boolean isGameWon() {
        return gameWon;
    }
    /**
     * 
     * @param gameWon 
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
    /**
     * 
     * @return all LevelObjects in level
     */
    public Set<LevelObject> getAllObjects() {
        return allObjects;
    }
    /**
     * 
     * @param allObjects 
     */
    public void setAllObjects(Set<LevelObject> allObjects) {
        this.allObjects = allObjects;
    }
    /**
     * 
     * @param o 
     */
    public void addToAllObjects(LevelObject o){
        if(allObjects != null)
            allObjects.add(o);
    }
    /**
     * 
     * @param o 
     */
    public void removeFromAllObjects(LevelObject o){
        if(allObjects != null)
            allObjects.remove(o);
        removeAttackableObject(o);
        removeInteractableObject(o);
        removeCollidableObject(o);
        removeHazardousObject(o);
    }
    /**
     * 
     * @param intelligence
     * @return pathfinding Graph associated with given intelligence level
     */
    public Graph<String,DefaultEdge> getLevelGraph(int intelligence){
        if(intelligence < 1)
            return zeroIntelligenceGraph;
        return normalIntelligenceGraph;
    }

    /**
     * 
     * @param zeroIntelligenceGraph 
     */
    public void setZeroIntelligenceGraph(Graph<String,DefaultEdge> zeroIntelligenceGraph) {
        this.zeroIntelligenceGraph = zeroIntelligenceGraph;
    }
    /**
     * 
     * @param normalIntelligenceGraph 
     */
    public void setNormalIntelligenceGraph(Graph<String,DefaultEdge> normalIntelligenceGraph) {
        this.normalIntelligenceGraph = normalIntelligenceGraph;
    }
    /**
     * 
     * @return attackable objects in level
     */
    public Set<LevelObject> getAttackableObjects() {
        return attackableObjects;
    }
    /**
     * 
     * @param attackableObjects 
     */
    public void setAttackableObjects(Set<LevelObject> attackableObjects) {
        this.attackableObjects = attackableObjects;
    }
    /**
     * 
     * @param o 
     */
    public void addAttackableObject(LevelObject o){
        if(attackableObjects != null)
            attackableObjects.add(o);
    }
    /**
     * 
     * @param o 
     */
    public void removeAttackableObject(LevelObject o){
        if(attackableObjects != null)
            attackableObjects.remove(o);
    }
    /**
     * 
     * @return interactable objects in level
     */
    public Set<LevelObject> getInteractableObjects() {
        return interactableObjects;
    }
    /**
     * 
     * @param interactableObjects 
     */
    public void setInteractableObjects(Set<LevelObject> interactableObjects) {
        this.interactableObjects = interactableObjects;
    }
    /**
     * 
     * @param o 
     */
    public void addInteractableObject(LevelObject o){
        if(interactableObjects != null)
            interactableObjects.add(o);
    }
    /**
     * 
     * @param o 
     */
    public void removeInteractableObject(LevelObject o){
        if(interactableObjects != null)
            interactableObjects.remove(o);
    }
    /**
     * 
     * @return collidable objects in level
     */
    public Set<LevelObject> getCollidableObjects() {
        return collidableObjects;
    }
    /**
     * 
     * @param o 
     */
    public void addCollidableObject(LevelObject o){
        if(collidableObjects != null){
            collidableObjects.add(o);
            addToGraphs(o);
        }
    }
    /**
     * 
     * @param o 
     */
    public void removeCollidableObject(LevelObject o){
        if(collidableObjects != null && collidableObjects.contains(o)){
            collidableObjects.remove(o);
            removeFromGraphs(o);
        }
    }
    /**
     * 
     * @return hazardous objects in level
     */
    public Set<LevelObject> getHazardousObjects() {
        return hazardousObjects;
    }
    /**
     * 
     * @param hazardousObjects 
     */
    public void setHazardousObjects(Set<LevelObject> hazardousObjects) {
        this.hazardousObjects = hazardousObjects;
    }
    /**
     * 
     * @param o 
     */
    public void addHazardousObject(LevelObject o){
        if(hazardousObjects != null)
            hazardousObjects.add(o);
    }
    /**
     * 
     * @param o 
     */
    public void removeHazardousObject(LevelObject o){
        if(hazardousObjects != null)
            hazardousObjects.remove(o);
    }
    /**
     * 
     * @param collidableObjects 
     */
    public void setCollidableObjects(Set<LevelObject> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }
    /**
     * 
     * @return living enemies in level
     */
    public Set<Ant> getEnemies() {
        return enemies;
    }
    /**
     * 
     * @param enemies 
     */
    public void setEnemies(Set<Ant> enemies) {
        this.enemies = enemies;
    }
    /**
     * 
     * @param o 
     */
    public void addEnemy(Ant o){
        if(enemies != null)
            enemies.add(o);
    }
    /**
     * 
     * @param o 
     */
    public void removeEnemy(Ant o){
        if(enemies != null)
            enemies.remove(o);
    }
    /**
     * 
     * @param obj collidable object to remove from pathfinding graphs
     */
    public void removeFromGraphs(LevelObject obj){
        Vector2 pos = obj.getPos();
        int tileX = LevelObjPosToGraphPos(pos.x);
        int tileY = LevelObjPosToGraphPos(pos.y);
        String vertex = GraphUtils.getVertexName(tileX, tileY);
        zeroIntelligenceGraph.removeVertex(vertex);
        normalIntelligenceGraph.removeVertex(vertex);
    }
    /**
     * 
     * @param obj collidable object to add to pathfinding graphs
     */
    public void addToGraphs(LevelObject obj) {
        Vector2 pos = obj.getPos();
        int tileX = LevelObjPosToGraphPos(pos.x);
        int tileY = LevelObjPosToGraphPos(pos.y);
        GraphUtils.addToGraph(zeroIntelligenceGraph, tileX, tileY);
        GraphUtils.addToGraph(normalIntelligenceGraph, tileX, tileY);
    }
    /**
     * 
     * @param x the float number from a LevelObject's position
     * @return the tile coordinate
     */
    public int LevelObjPosToGraphPos(float x){
        return (int)x/16;
    }
    /**
     * 
     * @param x the float number from an Ant's position
     * @return the tile coordinate
     */
    public int AntPosToGraphPos(float x){
        return (int)x/32;
    }
    /**
     * 
     * @return tiles surrounding enemies
     */
    public Set<Point> getEnemyNeighboringPoints(){
        Set<Point> points = new HashSet<>();
        for(Ant enemy: enemies){
            Vector2 pos = enemy.getPos();
            int enemyX = AntPosToGraphPos(pos.x);
            int enemyY = AntPosToGraphPos(pos.y);
            points.addAll(GraphUtils.getVertexNeighborsAsPoints(enemyX, enemyY));
        }
        return points;
    }
    /**
     * 
     * @return objects targetable by DecisionBlocks
     */
    public Set<LevelObject> getTargetableObjects(){
        Set<LevelObject> targetables = new HashSet<>();
        targetables.addAll(attackableObjects);
        targetables.addAll(interactableObjects);
        targetables.addAll(collidableObjects);
        targetables.addAll(endSpaces);
        return targetables;
    }
    /**
     * 
     * @param p enemy's tile position
     * @return enemy if found, else null
     */
    public Ant enemyAt(Point p){
        for(Ant enemy:enemies){
            if(AntPosToGraphPos(enemy.getPos().x) == p.x
                    && AntPosToGraphPos(enemy.getPos().y) == p.y)
                return enemy;
        }
        return null;
    }
    /**
     * 
     * @param p object's tile position
     * @return LevelObject if found, else null
     */
    public LevelObject objAt(Point p){
        for(LevelObject obj: allObjects){
            if(LevelObjPosToGraphPos(obj.getPos().x) == p.x
                    && LevelObjPosToGraphPos(obj.getPos().y) == p.y)
                return obj;
        }
        return null;
    }
    /**
     * 
     * @param targetType a class name of an object
     * @param p tile position
     * @return LevelObject if found, else null
     */
    public LevelObject findObjectAroundPoint(String targetType, Point p){
        for(LevelObject obj: allObjects){
            if(obj.getClass().getSimpleName().equals(targetType)){
                Point objTile = new Point(
                        (int)(obj.getPos().x/16),
                        (int)(obj.getPos().y/16));
                if((objTile.x == p.x-1 && objTile.y == p.y)
                        || (objTile.x == p.x+1 && objTile.y == p.y)
                        || (objTile.x == p.x && objTile.y == p.y+1)
                        || (objTile.x == p.x && objTile.y == p.y-1))
                    return obj;
            }
        }
        return null;
    }
}
