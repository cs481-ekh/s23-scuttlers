
package com.antscuttle.game.Level;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.Projectile;
import com.antscuttle.game.Util.GameData;
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
 *
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

    protected float deltaTime;

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
    public void addEndSpace(LevelObject o){
        endSpaces.add(o);
    }
    public Set<LevelObject> getEndSpaces(){
        return endSpaces;
    }
    public Set<Projectile> getProjectiles(){
        return projectiles;
    }
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }
    public void removeProjectile(Projectile p){
        projectiles.remove(p);
    }
    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
    
    public Set<LevelObject> getAllObjects() {
        return allObjects;
    }

    public void setDeltaTime(float delta) {
        deltaTime = delta;
    }
    public float getDeltaTime() {
        return deltaTime;
    }

    public void setAllObjects(Set<LevelObject> allObjects) {
        this.allObjects = allObjects;
    }
    public void addToAllObjects(LevelObject o){
        if(allObjects != null)
            allObjects.add(o);
    }
    public void removeFromAllObjects(LevelObject o){
        if(allObjects != null)
            allObjects.remove(o);
        removeAttackableObject(o);
        removeInteractableObject(o);
        removeCollidableObject(o);
        removeHazardousObject(o);
    }
    public Graph<String,DefaultEdge> getLevelGraph(int intelligence){
        if(intelligence < 1)
            return zeroIntelligenceGraph;
        return normalIntelligenceGraph;
    }

    public void setZeroIntelligenceGraph(Graph<String,DefaultEdge> zeroIntelligenceGraph) {
        this.zeroIntelligenceGraph = zeroIntelligenceGraph;
    }

    public void setNormalIntelligenceGraph(Graph<String,DefaultEdge> normalIntelligenceGraph) {
        this.normalIntelligenceGraph = normalIntelligenceGraph;
    }
    public Set<LevelObject> getAttackableObjects() {
        return attackableObjects;
    }

    public void setAttackableObjects(Set<LevelObject> attackableObjects) {
        this.attackableObjects = attackableObjects;
    }
    public void addAttackableObject(LevelObject o){
        if(attackableObjects != null)
            attackableObjects.add(o);
    }
    public void removeAttackableObject(LevelObject o){
        if(attackableObjects != null)
            attackableObjects.remove(o);
    }
    public Set<LevelObject> getInteractableObjects() {
        return interactableObjects;
    }

    public void setInteractableObjects(Set<LevelObject> interactableObjects) {
        this.interactableObjects = interactableObjects;
    }

    public void addInteractableObject(LevelObject o){
        if(interactableObjects != null)
            interactableObjects.add(o);
    }
    public void removeInteractableObject(LevelObject o){
        if(interactableObjects != null)
            interactableObjects.remove(o);
    }
    public Set<LevelObject> getCollidableObjects() {
        return collidableObjects;
    }
    public void addCollidableObject(LevelObject o){
        if(collidableObjects != null){
            collidableObjects.add(o);
            addToGraphs(o);
        }
    }
    public void removeCollidableObject(LevelObject o){
        if(collidableObjects != null){
            collidableObjects.remove(o);
            removeFromGraphs(o);
        }
    }
    public Set<LevelObject> getHazardousObjects() {
        return hazardousObjects;
    }

    public void setHazardousObjects(Set<LevelObject> hazardousObjects) {
        this.hazardousObjects = hazardousObjects;
    }
    public void addHazardousObject(LevelObject o){
        if(hazardousObjects != null)
            hazardousObjects.add(o);
    }
    public void removeHazardousObject(LevelObject o){
        if(hazardousObjects != null)
            hazardousObjects.remove(o);
    }
    public void setCollidableObjects(Set<LevelObject> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }

    public Set<Ant> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Ant> enemies) {
        this.enemies = enemies;
    }
    public void addEnemy(Ant o){
        if(enemies != null)
            enemies.add(o);
    }
    public void removeEnemy(Ant o){
        if(enemies != null)
            enemies.remove(o);
    }
    public void removeFromGraphs(LevelObject obj){
        Vector2 pos = obj.getPos();
        int tileX = LevelObjPosToGraphPos(pos.x);
        int tileY = LevelObjPosToGraphPos(pos.y);
        String vertex = GraphUtils.getVertexName(tileX, tileY);
        zeroIntelligenceGraph.removeVertex(vertex);
        normalIntelligenceGraph.removeVertex(vertex);
    }

    private void addToGraphs(LevelObject obj) {
        Vector2 pos = obj.getPos();
        int tileX = LevelObjPosToGraphPos(pos.x);
        int tileY = LevelObjPosToGraphPos(pos.y);
        GraphUtils.addToGraph(zeroIntelligenceGraph, tileX, tileY);
        GraphUtils.addToGraph(normalIntelligenceGraph, tileX, tileY);
    }
    public int LevelObjPosToGraphPos(float x){
        return (int)x/16;
    }
    public int AntPosToGraphPos(float x){
        return (int)x/32;
    }
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
    public Set<LevelObject> getTargetableObjects(){
        Set<LevelObject> targetables = new HashSet<>();
        targetables.addAll(attackableObjects);
        targetables.addAll(interactableObjects);
        targetables.addAll(hazardousObjects);
        targetables.addAll(collidableObjects);
        targetables.addAll(endSpaces);
        return targetables;
    }
    public Ant enemyAt(Point p){
        for(Ant enemy:enemies){
            if(AntPosToGraphPos(enemy.getPos().x) == p.x
                    && AntPosToGraphPos(enemy.getPos().y) == p.y)
                return enemy;
        }
        return null;
    }
    public LevelObject objAt(Point p){
        for(LevelObject obj: allObjects){
            if(LevelObjPosToGraphPos(obj.getPos().x) == p.x
                    && LevelObjPosToGraphPos(obj.getPos().y) == p.y)
                return obj;
        }
        return null;
    }
}
