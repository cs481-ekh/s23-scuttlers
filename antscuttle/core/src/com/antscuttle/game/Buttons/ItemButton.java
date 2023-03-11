package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Weapon;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ItemButton extends Button {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;
    
    private final Texture INACTIVE = new Texture("buttons/ant-editor/Item.png");
    private final Texture ACTIVE = new Texture("buttons/ant-editor/Item-Active.png");

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
    public void click(AntScuttleGame game, Screen screen, GameData data) {}

    // maybe pass editObject so the user can pass in what they want to update?
    public void click(AntScuttleGame game, GameData data, Ant ant, Weapon weap, Armor armor, AI ai) {
        this.playButtonPressSound(game);

        switch (data.currPane) {
            case ai: data.getCurrentAnt().equipAI(ai); break;
            case ant: data.setCurrentAnt(ant); break;

            case items:
                if (armor != null) {
                    data.getCurrentAnt().equipArmor(armor);
                } else if (armor == null && ant == null) {
                    if (weap instanceof MeleeWeapon) {
                        MeleeWeapon mWeap = (MeleeWeapon) weap;
                        data.getCurrentAnt().equipMeleeWeapon(mWeap);
                    } else if (weap instanceof RangedWeapon) {
                        RangedWeapon rWeap = (RangedWeapon) weap;
                        data.getCurrentAnt().equipRangedWeapon(rWeap);
                    }

                }

                break;
        }
        
    }
}
