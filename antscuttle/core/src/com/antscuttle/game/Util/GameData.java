
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
    
    /* Save info, user-created Ants and AIs */
    private LinkedList<Ant> userAnts;
    private LinkedList<AI> userAIs;
    
    /* Class info */
    private LinkedList<Class<? extends Level>> allLevels;
    private Set<Armor> allArmors;
    private Set<Weapon> allWeapons;
    private LinkedList<Class<? extends DecisionBlock>> allDBs;
    private LinkedList<Class<? extends Ant>> allAntTypes;
    
    /* Unlocked */
    private LinkedList<Armor> unlockedArmors;
    private LinkedList<Weapon> unlockedWeapons;
    private LinkedList<Class<? extends Level>> unlockedLevels;
    
    /* Locked : Weapons and Armor are in the same list */
    private LinkedList<Object> lockedItems;
    private LinkedList<Class<? extends Level>> lockedLevels;

    /* Constructor for new games */
    public GameData() {
        this.currentAnt = null;
        this.currentLevel = null;
        this.unlockedArmors = new LinkedList<>();
        this.unlockedWeapons = new LinkedList<>();
        this.unlockedLevels = new LinkedList<>();
        this.lockedItems = new LinkedList<>();
        this.lockedLevels = new LinkedList<>();
        
        /* Need to read available implemented objs */
        ClassDetector cd = new ClassDetector();
        this.allAntTypes = cd.findAntTypes();
        this.allLevels = cd.findLevels();
        this.allArmors = cd.findArmors();
        this.allWeapons = cd.findWeapons();
        this.allDBs = cd.findBlocks();
        
        /* add available items to locked list */
        for(Armor a : allArmors)
            lockedItems.add(a);
        for(Weapon w : allWeapons)
            lockedItems.add(w);
        for(Class<? extends Level> level : allLevels)
            lockedLevels.add(level);
    
        /* unlock first level */
        unlockNewLevel();
        
    }

    /* Getters */
    public LinkedList<Ant> getAllAnts() {
        return userAnts;
    }

    public LinkedList<Class<? extends Ant>> getAllAntTypes() {
        return allAntTypes;
    }

    public LinkedList<AI> getAllAIs() {
        return userAIs;
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

    public LinkedList<Class<? extends DecisionBlock>> getAllDBs() {
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
    
    public boolean deleteAnt(Ant ant){
        return userAnts.remove(ant);
    }
    
    public void addAnt(Ant ant){
        userAnts.add(ant);
    }
    
    public boolean deleteAI(AI ai){
        return userAIs.remove(ai);
    }
    
    public void addAI(AI ai){
        userAIs.add(ai);
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

    @Override
    public String toString() {
        return "Hi\n";
    }
}
