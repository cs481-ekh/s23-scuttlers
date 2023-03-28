/**
 * ClassFactory: 
 */
package com.antscuttle.game.Util;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.AntDecorator;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Weapon.Weapon;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 *
 * @author antho
 */
public class ClassFactory {
    /**
     * 
     * @param c the class obj for FireSword, Glock, etc
     * @return the new instance
     */
    public Weapon newWeaponInstance(Class<? extends Weapon> c){
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Weapon> cons = (Constructor<? extends Weapon>) c.getConstructors()[0];
            return cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("Error instantiating class " + c.getName());
            return null;
        }
    }
    
    /**
     * 
     * @param c The class object for Human, Zombie
     * @param name the new Ant's name
     * @return the new instance
     */
    public Ant newAntInstance(Class<? extends Ant> c, String name){
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Ant> cons = (Constructor<? extends Ant>) c.getConstructors()[0];
            return cons.newInstance(name);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("Error instantiating class " + c.getName());
            return null;
        }
    }
    /**
     * 
     * @param c
     * @param name
     * @param damageType A singular bonus damage type
     * @return 
     */
    public Ant newAntInstance(Class<? extends Ant> c, String name, DamageType damageType){
        Ant ant = newAntInstance(c, name);
        return new AntDecorator(ant, damageType);
    }
    /**
     * 
     * @param c
     * @param name
     * @param damageTypes A set of bonus damage types
     * @return 
     */
    public Ant newAntInstance(Class<? extends Ant> c, String name, Set<DamageType> damageTypes){
        Ant ant = newAntInstance(c, name);
        for(DamageType type : damageTypes)
            ant = new AntDecorator(ant, type);
        return ant;
    }
    /**
     * 
     * @param c the class obj for Chestplate
     * @return the new instance
     */
    public Armor newArmorInstance(Class<? extends Armor> c){
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Armor> cons = (Constructor<? extends Armor>) c.getConstructors()[0];
            return cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("Error instantiating class " + c.getName());
            return null;
        }
    }
    /**
     * 
     * @param c the class obj for implemented Levels: Level1, etc
     * @return the new instance
     */
    public Level newLevelInstance(Class<? extends Level> c){
        try{
            @SuppressWarnings("unchecked") Constructor<? extends Level> cons = (Constructor<? extends Level>) c.getConstructors()[0];
            return cons.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("Error instantiating class " + c.getName());
            return null;
        }
    }
    /**
     * 
     * @param c the class obj for implemented DecisionBlocks
     * @param options the BlockOptions instance
     * @return the new instance
     */
    public DecisionBlock newDBInstance(Class<? extends DecisionBlock> c, BlockOptions options){
        try{
            @SuppressWarnings("unchecked") Constructor<? extends DecisionBlock> cons = (Constructor<? extends DecisionBlock>) c.getConstructors()[0];
            return cons.newInstance(options);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println("Error instantiating class " + c.getName());
            return null;
        }
    }
    
}
