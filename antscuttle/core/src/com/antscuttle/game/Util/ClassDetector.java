
package com.antscuttle.game.Util;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Weapon.Weapon;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * ClassDetector: finds implemented Armors, Weapons, Levels, Ants, DBs.
 *                places them in a list in the assets folder to be read at 
 *                run time.
 * @author anthony
 */
public class ClassDetector {
    
    private static final String basePackage = "com.antscuttle.game.";
    private static final String armorPackage = "Armor.implementations.";
    private static final String weaponPackage = "Weapon.implementations.";
    private static final String antPackage = "Ant.implementations.";
    private static final String dbPackage = "AI.implementations.";
    private static final String levelPackage = "Level.levels.";
    private boolean debugPrint = false;
    
    /**
     * 
     * @return all armors detected by script in implementations folder
     */
    public Set<Armor> findArmors() {
        Set<Armor> armors = new HashSet<>();
        ClassFactory cf = new ClassFactory();
        
        try{
            InputStream is = this.getClass().getResourceAsStream("/detector/Armor.txt");
            String str="";
            while(is.available() > 0){
                str += (char)is.read();
            }
            String classes[] = str.trim().split("\n");
            for(String s : classes){
                s=s.substring(0, s.length()-5);
                s = basePackage + armorPackage + s;
                @SuppressWarnings("unchecked") Class<? extends Armor> c = (Class<? extends Armor>) Class.forName(s);
                armors.add(cf.newArmorInstance(c));
            }
        } catch (IOException | ClassNotFoundException ex){
            System.err.println("Error loading armor");
        }
        if(debugPrint){
            System.out.println();
            System.out.println("Loaded Armors\n-------------");
            for(Armor a: armors)
                System.out.println(a.getName());
            System.out.println();
        }
        return armors;
    }
    /**
     * 
     * @return all weapons detected by script in implementations folder
     */
    public Set<Weapon> findWeapons() {
        Set<Weapon> weapons = new HashSet<>();
        
        ClassFactory cf = new ClassFactory();
        try{
            InputStream is = this.getClass().getResourceAsStream("/detector/Weapon.txt");
            String str="";
            while(is.available() > 0){
                str += (char)is.read();
            }
            String classes[] = str.trim().split("\n");
            for(String s : classes){
                s=s.substring(0, s.length()-5);
                s = basePackage + weaponPackage + s;
                @SuppressWarnings("unchecked") Class<? extends Weapon> c = (Class<? extends Weapon>) Class.forName(s);
                weapons.add(cf.newWeaponInstance(c));
            }
        } catch (IOException | ClassNotFoundException ex){
            System.err.println("Error loading weapon");
        }
        
        if(debugPrint){
            System.out.println();
            System.out.println("Loaded Weapons\n--------------");
            for(Weapon w:weapons)
                System.out.println(w.getName());
            System.out.println();
        }
        return weapons;
    }
    /**
     * 
     * @return all ant classes found by script in implementations folder
     */
    public LinkedList<Class<? extends Ant>> findAntTypes(){
        LinkedList<Class<? extends Ant>> antTypes = new LinkedList<>();
        
        
        try{
            InputStream is = this.getClass().getResourceAsStream("/detector/Ant.txt");
            String str="";
            while(is.available() > 0){
                str += (char)is.read();
            }
            String classes[] = str.trim().split("\n");
            for(String s : classes){
                s=s.substring(0, s.length()-5);
                s = basePackage + antPackage + s;
                @SuppressWarnings("unchecked") Class<? extends Ant> c = (Class<? extends Ant>) Class.forName(s);
                antTypes.add(c);
            }
        } catch (IOException | ClassNotFoundException ex){
            System.err.println("Error loading ant");
        }

        if(debugPrint){
            System.out.println();
            System.out.println("Loaded Ant Types\n----------------");
            for(Class c : antTypes)
                System.out.println(c.getName()
                    .substring(c.getName().lastIndexOf('.')+1));
            System.out.println();
        }
        return antTypes;
    }
    /**
     * 
     * @return all level classes found by script in levels folder
     */
    public LinkedList<Class<? extends Level>> findLevels(){
        LinkedList<Class<? extends Level>> levels = new LinkedList<>();
        
        try{
            InputStream is = this.getClass().getResourceAsStream("/detector/Level.txt");
            String str="";
            while(is.available() > 0){
                str += (char)is.read();
            }
            String classes[] = str.trim().split("\n");
            for(String s : classes){
                s=s.substring(0, s.length()-5);
                s = basePackage + levelPackage + s;
                @SuppressWarnings("unchecked") Class<? extends Level> c = (Class<? extends Level>) Class.forName(s);
                levels.add(c);
            }
        } catch (IOException | ClassNotFoundException ex){
            System.err.println("Error loading level");
        }
        if(debugPrint){
            System.out.println();
            System.out.println("Loaded Levels\n-------------");
            for(Class c : levels)
                System.out.println(c.getName()
                    .substring(c.getName().lastIndexOf('.')+1));
            System.out.println();
        }
        return levels;
    }
    /**
     * 
     * @return all DecisionBlocks found by script in implementations folder
     */
    public LinkedList<Class<? extends DecisionBlock>> findBlocks(){
        LinkedList<Class<? extends DecisionBlock>> blocks = new LinkedList<>();
        
        
        try{
            InputStream is = this.getClass().getResourceAsStream("/detector/AI.txt");
            String str="";
            while(is.available() > 0){
                str += (char)is.read();
            }
            String classes[] = str.trim().split("\n");
            for(String s : classes){
                s=s.substring(0, s.length()-5);
                s = basePackage + dbPackage + s;
                @SuppressWarnings("unchecked") Class<? extends DecisionBlock> c = (Class<? extends DecisionBlock>) Class.forName(s);
                blocks.add(c);
            }
        } catch (IOException | ClassNotFoundException ex){
            System.err.println("Error loading decision block");
        }
        if(debugPrint){
            System.out.println();
            System.out.println("Loaded Decision Blocks\n----------------------");
            for(Class c : blocks)
                System.out.println(c.getName()
                    .substring(c.getName().lastIndexOf('.')+1));
            System.out.println();
        }
        return blocks;
    }
    
    
}
