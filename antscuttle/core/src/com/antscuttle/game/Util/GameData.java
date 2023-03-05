
package com.antscuttle.game.Util;

import com.antscuttle.game.AI.*;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Weapon.Weapon;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author antho
 */
public class GameData {
    /* Current selections */
    private Ant currentAnt;
    private Level currentLevel;
    
    /* Save info */
    private LinkedList<Ant> allAnts;
    private LinkedList<AI> allAIs;
    
    /* Class info */
    private LinkedList<Class<? extends Level>> allLevels;
    private Set<Armor> allArmors;
    private Set<Weapon> allWeapons;
    private LinkedList<DecisionBlock> allDBs;
    
    /* Unlocked */
    private LinkedList<Armor> unlockedArmors;
    private LinkedList<Weapon> unlockedWeapons;
    private LinkedList<Class<? extends Level>> unlockedLevels;
    
    /* Locked */
    private LinkedList<Object> lockedItems;
    private LinkedList<Class<? extends Level>> lockedLevels;

    /* Constructor for new games */
    public GameData() {
        this.currentAnt = null;
        this.currentLevel = null;
        this.unlockedArmors = new LinkedList<>();
        this.unlockedWeapons = new LinkedList<>();
        this.unlockedLevels = new LinkedList<>();
        
        /* Need to read and assign classes from disk */
        
        /*this.availableAnts = availableAnts;
        this.allAIs = findAIs();
        this.allLevels = findLevels();
        this.allArmors = findArmors();
        this.allWeapons = findWeapons();
        
        add available items to locked list
        */
    }

    /* Getters */
    public LinkedList<Ant> getAllAnts() {
        return allAnts;
    }

    public LinkedList<AI> getAllAIs() {
        return allAIs;
    }

    public LinkedList<Class<? extends Level>> getAllLevels() {
        return allLevels;
    }

    public Set<Armor> getAllArmors() {
        return allArmors;
    }

    public Set<Weapon> getAllWeapons() {
        return allWeapons;
    }

    public LinkedList<DecisionBlock> getAllDBs() {
        return allDBs;
    }

    public LinkedList<Armor> getUnlockedArmors() {
        return unlockedArmors;
    }

    public LinkedList<Weapon> getUnlockedWeapons() {
        return unlockedWeapons;
    }

    public LinkedList<Class<? extends Level>> getUnlockedLevels() {
        return unlockedLevels;
    }

    public Ant getCurrentAnt() {
        return currentAnt;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    /* Setters */
    public void setCurrentAnt(Ant currentAnt) {
        this.currentAnt = currentAnt;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    /* Unlockables */
    /**
     * unlockRandomItem
     * 
     * @return true if successful, else false
     */
    public boolean unlockRandomItem(){
        if(lockedItems.isEmpty())
            return false;
        int rand = new Random().nextInt(lockedItems.size()) - 1;
        Object unlockedItem = lockedItems.get(rand);
        lockedItems.remove(unlockedItem);
        if( unlockedItem instanceof Weapon )
            unlockedWeapons.add((Weapon)unlockedItem);
        else if( unlockedItem instanceof Armor )
            unlockedArmors.add((Armor)unlockedItem);
        else return false;
        return true;
    }
    /** unlockNewLevel unlocks a new level and assigns it to currentLevel
     * 
     * @return true if successful, else false 
     */
    public boolean unlockNewLevel(){
        if(lockedLevels.isEmpty())
            return false;
        Class<? extends Level> unlockedLevel = lockedLevels.poll();
        unlockedLevels.add(unlockedLevel);
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Level> cons = (Constructor<? extends Level>) unlockedLevel.getConstructors()[0];
            Level level = cons.newInstance();
            currentLevel = level;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex){
            System.err.println("Error loading new level: " + unlockedLevel.getName());
            return false;
        }
        return true;
    }
}
