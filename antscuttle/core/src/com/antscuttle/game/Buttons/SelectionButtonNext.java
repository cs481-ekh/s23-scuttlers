/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.antscuttle.game.Buttons;

import com.Screens.MainMenuScreen;
import com.Screens.NewGameScreen;
import com.Screens.SettingsMenuScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import java.util.LinkedList;

/**
 *
 * @author antho
 */
public class SelectionButtonNext extends ScuttleButton{
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    
    private final Texture INACTIVE = new Texture("buttons/Next-Arrow-Gray.png");
    private final Texture ACTIVE = new Texture("buttons/Next-Arrow-Gray-Active.png");
    
    @Override
    public int getWidth() {
        return WIDTH;
    }
    @Override
    public int getHeight() {
        return HEIGHT;
    }
    @Override
    public Texture active() {
        return ACTIVE;
    }
    @Override
    public Texture inactive() {
        return INACTIVE;
    }		
  

    public void click(AntScuttleGame game, Screen screen) {
        this.playButtonPressSound(game);
        
    }
    @Override
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        click(game, screen);
        LinkedList<Class<? extends Level>> unlockedLevels = data.getUnlockedLevels();
        Class<? extends Level> current = data.getCurrentLevel().getClass();
        int currIdx = unlockedLevels.indexOf(current);
        if(currIdx < unlockedLevels.size()-1){
            ClassFactory cf = new ClassFactory();
            data.setCurrentLevel(cf.newLevelInstance(unlockedLevels.get(currIdx+1)));
        }
    }
}
