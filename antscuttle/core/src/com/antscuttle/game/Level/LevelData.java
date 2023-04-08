
package com.antscuttle.game.Level;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.Util.GraphUtils;

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
    protected Set<Ant> enemies;
    protected Graph<String, DefaultEdge> zeroIntelligenceGraph;
    protected Graph<String, DefaultEdge> normalIntelligenceGraph;

    public LevelData(){
        attackableObjects = new HashSet<>();
        interactableObjects = new HashSet<>();
        collidableObjects = new HashSet<>();
        hazardousObjects = new HashSet<>();
        enemies = new HashSet<>();
        allObjects = new HashSet<>();
        zeroIntelligenceGraph = new SimpleGraph<>(DefaultEdge.class);
        normalIntelligenceGraph = new SimpleGraph<>(DefaultEdge.class);
    }

    public Set<LevelObject> getAllObjects() {
        return allObjects;
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
    }
    public Graph getLevelGraph(int intelligence){
        if(intelligence < 1)
            return zeroIntelligenceGraph;
        return normalIntelligenceGraph;
    }

    public void setZeroIntelligenceGraph(Graph zeroIntelligenceGraph) {
        this.zeroIntelligenceGraph = zeroIntelligenceGraph;
    }

    public void setNormalIntelligenceGraph(Graph normalIntelligenceGraph) {
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
        if(collidableObjects != null)
            collidableObjects.add(o);
    }
    public void removeCollidableObject(LevelObject o){
        if(collidableObjects != null)
            collidableObjects.remove(o);
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
        String vertex = GraphUtils.getVertexName((int)obj.getX(), (int)obj.getY());
        zeroIntelligenceGraph.removeVertex(vertex);
        normalIntelligenceGraph.removeVertex(vertex);
    }
}
