package com.Screens;

import java.util.LinkedList;
import java.util.Set;

import com.Screens.Panes.AntNameInputListener;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Armor.implementations.Chestplate;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.AddButton;
import com.antscuttle.game.Buttons.AntButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.ItemsButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.Weapon;
import com.antscuttle.game.Weapon.implementations.Glock;
import com.antscuttle.game.Weapon.implementations.SteelSword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AntEditorScreen extends ScreenAdapter {
    AntScuttleGame game;
    GameData gameData;
    SpriteBatch antBatch;
    
    Button settingsButton;
    Button backButton;

    Button AIButton;
    Button antButton;
    Button itemsButton;
    Button addButton;

    int x;

    private static int ANT_EDITOR_HEIGHT;
    private static int ANT_EDITOR_WIDTH;

    private static final int SETTINGS_BUTTON_Y = 20;
    
    GlyphLayout bounds;
    
    Ant human;
    Ant zombie;
    
    float stateTime = 0;
    
    AntNameInputListener listener;

    Set<Weapon> weapons;
    Set<Armor> armors;
    LinkedList<Ant> ants;

    public AntEditorScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;

        settingsButton = new SettingsButton();
        backButton = new BackButton();
        AIButton = new AIButton();
        antButton = new AntButton();
        itemsButton = new ItemsButton();
        addButton = new AddButton();

        antBatch = new SpriteBatch();

        ANT_EDITOR_HEIGHT = Gdx.graphics.getHeight();
        ANT_EDITOR_WIDTH = Gdx.graphics.getWidth();

        bounds = new GlyphLayout();

        weapons = gameData.getAllWeapons();
        armors = gameData.getAllArmors();
        ants = gameData.getAllAnts();

        human = new Human("Jerry");
        zombie = new Zombie("Timmy");
        gameData.setCurrentAnt(human);

        // listener = new AntNameInputListener();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() { });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();

        /* Back Button */
        drawButton(20, ANT_EDITOR_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* Button View */
        drawButton(ANT_EDITOR_WIDTH/1.25f, ANT_EDITOR_HEIGHT/1.25f, AIButton, 0.75f);
        drawButton(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth()*2, ANT_EDITOR_HEIGHT/1.25f, antButton, 0.75f);
        drawButton(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth(), ANT_EDITOR_HEIGHT/1.25f, itemsButton, 0.75f);

        /* Add Ant Button */
       drawButton(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth()*2, 30, addButton, 1);


        

        float i = game.font.getCapHeight()+10;
        game.font.draw(game.batch, "Weapons: ", ANT_EDITOR_WIDTH/1.25f, ANT_EDITOR_HEIGHT/2);
        for (Weapon w: weapons) {
            game.font.draw(game.batch, w.getName(), ANT_EDITOR_WIDTH/1.25f, ANT_EDITOR_HEIGHT/2-i);
            i += game.font.getCapHeight()+10;
        }
        i = game.font.getCapHeight()+10;
        game.font.draw(game.batch, "Armors: ", ANT_EDITOR_WIDTH/2, ANT_EDITOR_HEIGHT/2);
        for (Armor armr: armors) {
            game.font.draw(game.batch, armr.getName(), ANT_EDITOR_WIDTH/2, ANT_EDITOR_HEIGHT/2-i);
            i += game.font.getCapHeight()+10;
        }

        if (ants.isEmpty()) {
            game.font.draw(game.batch, "No Created Ants!", ANT_EDITOR_WIDTH/1.25f-antButton.getWidth(), ANT_EDITOR_HEIGHT/1.25f-30);
        } else {
            i = game.font.getCapHeight()+10;
            for (Ant a: ants) {
                game.font.draw(game.batch, a.getName(), ANT_EDITOR_WIDTH/1.25f-100, ANT_EDITOR_HEIGHT/2-i);
                i += game.font.getCapHeight()+10;
            }
        }
        

        stateTime += Gdx.graphics.getDeltaTime();
    
        antBatch.begin();
        Texture[] frames = gameData.getCurrentAnt().getAntPreviewAnimation();
        Animation<Texture> animation = new Animation<>(0.6f, frames);
        antBatch.draw(frames[animation.getKeyFrameIndex(stateTime)], 10, ANT_EDITOR_HEIGHT/2 + 130);
        if(animation.isAnimationFinished(stateTime)){
            stateTime = 0;
        }
        antBatch.end();

        /* Chosen Ant Stats */
        Ant ant = gameData.getCurrentAnt();
        game.font.draw(game.batch, "AI:" + ant.getAI().toString(), 20, ANT_EDITOR_HEIGHT/1.25f + bounds.height);
        game.font.draw(game.batch, "Melee:" + ant.getMeleeWeapon(), 10, ANT_EDITOR_HEIGHT/2 + 50);
        game.font.draw(game.batch, "Armor:" + ant.getArmor(), 10, ANT_EDITOR_HEIGHT/2 + 70);
        game.font.draw(game.batch, "Ranged:" + ant.getRangedWeapon(), 10, ANT_EDITOR_HEIGHT/2 + 90);

        game.font.draw(game.batch, "Name:" + ant.getName(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height);
        game.font.draw(game.batch, "HP:" + ant.getHealth(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 20);
        game.font.draw(game.batch, "DMG:" + ant.getBaseDamage(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 40);
        game.font.draw(game.batch, "DEF:" + ant.getBaseDefense(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 60);
        game.font.draw(game.batch, "SPD:" + ant.getSpeed(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 80);


        /* Settings Button */
        x = ANT_EDITOR_WIDTH - settingsButton.getWidth() - 20;
        drawButton(x, SETTINGS_BUTTON_Y, settingsButton, 1);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Draw the Button
     * @param x
     * @param y
     * @param button type of button
     */
    private void drawButton(float x, float y, Button button, float scale) {
        float w = button.getWidth()*scale;
        float h = button.getHeight()*scale;

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            ANT_EDITOR_HEIGHT - Gdx.input.getY() < y + h && ANT_EDITOR_HEIGHT - Gdx.input.getY() > y) {

            // Gdx.graphics.setSystemCursor(SystemCursor.Hand);
            game.batch.draw(button.active(), x, y, w, h);

            if (button.getButtonType() == "settings" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new SettingsMenuScreen(game, this));
            }
            if (button.getButtonType() == "back" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new NewGameScreen(game, gameData));
            }
            if (button.getButtonType() == "ai" && Gdx.input.justTouched()) {
                if (gameData.getCurrentAnt().equals(human)) {
                    gameData.setCurrentAnt(zombie);
                } else {
                    gameData.setCurrentAnt(human);
                }
            }
            if (button.getButtonType() == "add" && Gdx.input.justTouched()) {
                Ant newguy = new Human("terry");
                newguy.equipMeleeWeapon(new SteelSword());
                newguy.equipRangedWeapon(new Glock());
                newguy.equipArmor(new Chestplate());
                gameData.addAnt(newguy);
                gameData.setCurrentAnt(newguy);
            }
        } else {
            game.batch.draw(button.inactive(), x, y, w, h);
        }
    }   

    private void drawCurrentPane() {
        Gdx.input.getTextInput(listener, "title", "add me cmon", "hint");
    }
}
