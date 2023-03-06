package com.Screens;

import java.util.LinkedList;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.Ant.AnimationDirection;
import com.antscuttle.game.Ant.Ant.AnimationType;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class AntEditorScreen extends ScreenAdapter {
    AntScuttleGame game;
    GameData gameData;
    SpriteBatch antBatch;
    
    Button settingsButton;
    Button backButton;
    Button AIButton;

    int x;

    private static int ANT_EDITOR_HEIGHT;
    private static int ANT_EDITOR_WIDTH;

    private static final int SETTINGS_BUTTON_Y = 10;

    String title = "Ant Editor";
    GlyphLayout bounds;

    Ant human;
    Ant zombie;

    float stateTime = 0;

    public AntEditorScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;
        settingsButton = new SettingsButton();
        backButton = new BackButton();
        AIButton = new AIButton();

        antBatch = new SpriteBatch();

        ANT_EDITOR_HEIGHT = Gdx.graphics.getHeight();
        ANT_EDITOR_WIDTH = Gdx.graphics.getWidth();

        bounds = new GlyphLayout();
        bounds.setText(game.font, title);

        human = new Human("jerry");
        zombie = new Zombie("timmy");
        gameData.setCurrentAnt(human);
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
        drawButton(20, ANT_EDITOR_HEIGHT - backButton.getHeight() - 20, backButton);

        drawButton(ANT_EDITOR_WIDTH/2, ANT_EDITOR_HEIGHT/2, AIButton);

        LinkedList<Class<? extends Ant>> list = gameData.getAllAntTypes();
        stateTime += Gdx.graphics.getDeltaTime();
    
        antBatch.begin();
        // antBatch.draw(gameData.getCurrentAnt().getAnimation(AnimationType.Move, AnimationDirection.Up), 0, 0);
        Texture[] frames = gameData.getCurrentAnt().getAntPreviewAnimation();
        Animation<Texture> animation = new Animation<>(0.3f, frames);
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
        x = ANT_EDITOR_WIDTH - settingsButton.getWidth() - 10;
        drawButton(x, SETTINGS_BUTTON_Y, settingsButton);

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
    private void drawButton(int x, int y, Button button) {
        int w = button.getWidth();
        int h = button.getHeight();

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
        } else {
            game.batch.draw(button.inactive(), x, y, w, h);
            // Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    }   
}
