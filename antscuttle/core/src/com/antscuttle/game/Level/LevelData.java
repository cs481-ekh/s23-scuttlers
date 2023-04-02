
package com.antscuttle.game.Level;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.LevelObject.LevelObject;
import com.badlogic.gdx.ai.pfa.Graph;
import java.util.Set;

/**
 *
 * @author antho
 */
public class LevelData {
    protected Set<LevelObject> attackableObjects;
    protected Set<LevelObject> interactableObjects;
    protected Set<LevelObject> collidableObjects;
    protected Set<Ant> enemies;
    protected Graph zeroIntelligenceGraph;
    protected Graph normalIntelligenceGraph;

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

    public Set<LevelObject> getInteractableObjects() {
        return interactableObjects;
    }

    public void setInteractableObjects(Set<LevelObject> interactableObjects) {
        this.interactableObjects = interactableObjects;
    }

    public Set<LevelObject> getCollidableObjects() {
        return collidableObjects;
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
}
