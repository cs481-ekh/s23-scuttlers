
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
 * Structure to hold current game data
 * @author anthony
 */
public class GameData implements java.io.Serializable{
    private static final long serialVersionUID = 2022L;
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

    /* Keep track of current pane in Ant editor */
	public enum panes {ant, ai, items}
    public panes currPane;

    /* Constructor for new games */
    public GameData() {
        this.currentAnt = null;
        this.currentLevel = null;
        this.unlockedArmors = new LinkedList<>();
        this.unlockedWeapons = new LinkedList<>();
        this.unlockedLevels = new LinkedList<>();
        this.lockedItems = new LinkedList<>();
        this.lockedLevels = new LinkedList<>();
        this.userAnts = new LinkedList<>();
        this.userAIs = new LinkedList<>();
        
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
    /**
     * 
     * @return all ant classes in game
     */
    public LinkedList<Class<? extends Ant>> getAllAntTypes() {
        return allAntTypes;
    }
    /**
     * 
     * @return all user-created ai's
     */
    public LinkedList<AI> getAllAIs() {
        return userAIs;
    }
    
    /**
     * 
     * @return all levels in game
     */
    public LinkedList<Class<? extends Level>> getAllLevels() {
        return allLevels;
    }
    /**
     * 
     * @return all armors in game
     */
    public Set<Armor> getAllArmors() {
        return allArmors;
    }
    /**
     * 
     * @return all weapons in game
     */
    public Set<Weapon> getAllWeapons() {
        return allWeapons;
    }
    /**
     * 
     * @return all decision block classes
     */
    public LinkedList<Class<? extends DecisionBlock>> getAllDBs() {
        return allDBs;
    }
    /**
     * 
     * @return currently unlocked armors
     */
    public LinkedList<Armor> getUnlockedArmors() {
        return unlockedArmors;
    }
    /**
     * 
     * @return currently unlocked weapons 
     */
    public LinkedList<Weapon> getUnlockedWeapons() {
        return unlockedWeapons;
    }
    /**
     * 
     * @return levels currently unlocked
     */
    public LinkedList<Class<? extends Level>> getUnlockedLevels() {
        return unlockedLevels;
    }
    /**
     * 
     * @return current selected player ant
     */
    public Ant getCurrentAnt() {
        return currentAnt;
    }
    /**
     * 
     * @return current selected level
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /* Setters */
    public void setCurrentAnt(Ant currentAnt) {
        this.currentAnt = currentAnt;
    }
    /**
     * 
     * @param currentLevel 
     */
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }
    /**
     * 
     * @param ant
     * @return successful
     */
    public boolean deleteAnt(Ant ant){
        return userAnts.remove(ant);
    }
    /**
     * 
     * @param ant 
     */
    public void addAnt(Ant ant){
        userAnts.add(ant);
    }
    /**
     * 
     * @param ai
     * @return successful
     */
    public boolean deleteAI(AI ai){
        return userAIs.remove(ai);
    }
    /**
     * 
     * @param ai 
     */
    public void addAI(AI ai){
        userAIs.add(ai);
    }
    
    /* Unlockables */
    /**
     * unlockRandomItem
     * 
     * @return the item if successful, else null
     */
    public Object unlockRandomItem(){
        if(lockedItems.isEmpty())
            return null;
        int rand = new Random().nextInt(lockedItems.size());
        Object unlockedItem = lockedItems.get(rand);
        lockedItems.remove(unlockedItem);
        if( unlockedItem instanceof Weapon )
            unlockedWeapons.add((Weapon)unlockedItem);
        else if( unlockedItem instanceof Armor )
            unlockedArmors.add((Armor)unlockedItem);
        else return null;
        return unlockedItem;
    }
    /** unlockNewLevel unlocks a new level and assigns it to currentLevel
     * 
     * @return the level if successful, else null
     */
    public Level unlockNewLevel(){
        if(lockedLevels.isEmpty())
            return null;
        Class<? extends Level> unlockedLevel = lockedLevels.poll();
        unlockedLevels.add(unlockedLevel);
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Level> cons = (Constructor<? extends Level>) unlockedLevel.getConstructors()[0];
            Level level = cons.newInstance();
            currentLevel = level;
            return level;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException ex){
            System.err.println("Error loading new level: " + unlockedLevel.getName());
            return null;
        }
        
    }
}
