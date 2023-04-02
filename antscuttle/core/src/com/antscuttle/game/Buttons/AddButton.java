package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Armor.implementations.Chestplate;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.implementations.Glock;
import com.antscuttle.game.Weapon.implementations.SteelSword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class AddButton extends ScuttleButton {
    private final int WIDTH = 64;
    private final int HEIGHT = 64;
    
    private final Texture INACTIVE = new Texture("buttons/ant-editor/Add.png");
    private final Texture ACTIVE = new Texture("buttons/ant-editor/Add-Active.png");

    final Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

    Dialog dialog = new Dialog("Enter AI name", skin);
    final TextField inputField = new TextField("", skin);
    TextButton saveButton = new TextButton("Save", skin);
    Stage stage = new Stage();
    
   
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
        dialog.add(inputField, saveButton);
        this.playButtonPressSound(game);
        // final TextField inputField = new TextField("", skin);
        String aiName = inputField.getText();
        dialog.text("An AI has been created and is ready to be saved.");
        dialog.button("OK");
        dialog.show(stage);

        Ant newguy = new Human(aiName);
        // ask for user input
        // newguy.equipMeleeWeapon(new SteelSword());
        // newguy.equipRangedWeapon(new Glock());
        // newguy.equipArmor(new Chestplate());
        data.addAnt(newguy);
        data.setCurrentAnt(newguy);

    }
}
