package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Armor.implementations.Chestplate;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.implementations.Glock;
import com.antscuttle.game.Weapon.implementations.SteelSword;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class AddButton extends Button {
    private final int WIDTH = 64;
    private final int HEIGHT = 64;
    
    private final Texture INACTIVE = new Texture("buttons/ant-editor/Add.png");
    private final Texture ACTIVE = new Texture("buttons/ant-editor/Add-Active.png");
   
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
  
    @Override
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        Ant newguy = new Human("terry");
        newguy.equipMeleeWeapon(new SteelSword());
        newguy.equipRangedWeapon(new Glock());
        newguy.equipArmor(new Chestplate());
        data.addAnt(newguy);
        data.setCurrentAnt(newguy);
    }
}
