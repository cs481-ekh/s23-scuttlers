
package com.antscuttle.game.Util;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Weapon.Weapon;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * ClassDetector: finds implemented Armors, Weapons, Levels, Ants, DBs
 * @author antho
 */
public class ClassDetector {
    private static final String userDir = System.getProperty("user.dir");
    private static final String basePath = userDir + "/antscuttle/core/src/com/antscuttle/game/";
    private static final String basePackage = "com.antscuttle.game.";
    
    public Set<Armor> findArmors() {
        Set<Armor> armors = new HashSet<>();
        String armorPackage = "Armor.implementations.";
        File actual = new File(basePath + "Armor/implementations");
        if(actual.exists()){
            for( File f : actual.listFiles()){
                String fileName = f.getName();
                if(!f.isDirectory() && fileName.matches("\\w+.java")){
                    try{
                        // Cut off '.java' from the name
                        fileName = fileName.substring(0, fileName.length()-5);
                        fileName = basePackage + armorPackage + fileName;
                        
                        // Get instance of the found armor
                        @SuppressWarnings("unchecked") Class<? extends Armor> c = (Class<? extends Armor>) Class.forName(fileName);
                        @SuppressWarnings("unchecked") Constructor<? extends Armor> cons = (Constructor<? extends Armor>) c.getConstructors()[0];
                        Armor armor = cons.newInstance();
                        
                        armors.add(armor);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.err.println("Error loading Armor: " + fileName);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Loaded Armors\n-------------");
        for(Armor a: armors)
            System.out.println(a.getName());
        System.out.println();
        return armors;
    }
    
    public Set<Weapon> findWeapons() {
        Set<Weapon> weapons = new HashSet<>();
        String weaponPackage = "Weapon.implementations.";
        File actual = new File(basePath + "Weapon/implementations");
        if(actual.exists()){
            for( File f : actual.listFiles()){
                String fileName = f.getName();
                if(!f.isDirectory() && fileName.matches("\\w+.java")){
                    try{
                        // Cut off '.java' from the name
                        fileName = fileName.substring(0, fileName.length()-5);
                        fileName = basePackage + weaponPackage + fileName;
                        
                        // Get instance of the found armor
                        @SuppressWarnings("unchecked") Class<? extends Weapon> c = (Class<? extends Weapon>) Class.forName(fileName);
                        @SuppressWarnings("unchecked") Constructor<? extends Weapon> cons = (Constructor<? extends Weapon>) c.getConstructors()[0];
                        Weapon weapon = cons.newInstance();
                        
                        weapons.add(weapon);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.err.println("Error loading Weapon: " + fileName);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Loaded Weapons\n--------------");
        for(Weapon w:weapons)
            System.out.println(w.getName());
        System.out.println();
        return weapons;
    }
    
    public LinkedList<Class<? extends Ant>> findAntTypes(){
        LinkedList<Class<? extends Ant>> antTypes = new LinkedList<>();
        String antPackage = "Ant.implementations.";
        File actual = new File(basePath + "Ant/implementations");
        if(actual.exists()){
            for( File f : actual.listFiles()){
                String fileName = f.getName();
                if(!f.isDirectory() && fileName.matches("\\w+.java")){
                    try{
                        // Cut off '.java' from the name
                        fileName = fileName.substring(0, fileName.length()-5);
                        fileName = basePackage + antPackage + fileName;
                        // Get instance of the found armor
                        @SuppressWarnings("unchecked") Class<? extends Ant> c = (Class<? extends Ant>) Class.forName(fileName);
                        antTypes.add(c);
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Error loading ant: " + fileName);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Loaded Ant Types\n----------------");
        for(Class c : antTypes)
            System.out.println(c.getName()
                .substring(c.getName().lastIndexOf('.')+1));
        System.out.println();
        return antTypes;
    }
    
    public LinkedList<Class<? extends Level>> findLevels(){
        LinkedList<Class<? extends Level>> levels = new LinkedList<>();
        String levelPackage = "Level.levels.";
        File actual = new File(basePath + "Level/levels");
        if(actual.exists()){
            for( File f : actual.listFiles()){
                String fileName = f.getName();
                if(!f.isDirectory() && fileName.matches("\\w+.java")){
                    try{
                        // Cut off '.java' from the name
                        fileName = fileName.substring(0, fileName.length()-5);
                        fileName = basePackage + levelPackage + fileName;
                        // Get instance of the found armor
                        @SuppressWarnings("unchecked") Class<? extends Level> c = (Class<? extends Level>) Class.forName(fileName);
                        levels.add(c);
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Error loading level: " + fileName);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Loaded Levels\n-------------");
        for(Class c : levels)
            System.out.println(c.getName()
                .substring(c.getName().lastIndexOf('.')+1));
        System.out.println();
        return levels;
    }
    
    public LinkedList<Class<? extends DecisionBlock>> findBlocks(){
        LinkedList<Class<? extends DecisionBlock>> blocks = new LinkedList<>();
        String dbPackage = "AI.implementations.";
        File actual = new File(basePath + "AI/implementations");
        if(actual.exists()){
            for( File f : actual.listFiles()){
                String fileName = f.getName();
                if(!f.isDirectory() && fileName.matches("\\w+.java")){
                    try{
                        // Cut off '.java' from the name
                        fileName = fileName.substring(0, fileName.length()-5);
                        fileName = basePackage + dbPackage + fileName;
                        // Get instance of the found armor
                        @SuppressWarnings("unchecked") Class<? extends DecisionBlock> c = (Class<? extends DecisionBlock>) Class.forName(fileName);
                        blocks.add(c);
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Error loading level: " + fileName);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("Loaded Decision Blocks\n----------------------");
        for(Class c : blocks)
            System.out.println(c.getName()
                .substring(c.getName().lastIndexOf('.')+1));
        System.out.println();
        return blocks;
    }
    
    
}
