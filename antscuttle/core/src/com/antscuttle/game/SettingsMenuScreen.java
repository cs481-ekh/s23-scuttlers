package com.antscuttle.game;

import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.SettingsButton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class SettingsMenuScreen extends ScreenAdapter {
    AntScuttleGame game;
    Screen prevScreen;
    
    private static int SETTINGS_MENU_HEIGHT;
    private static int SETTINGS_MENU_WIDTH;

    Button backButton;

    public SettingsMenuScreen (AntScuttleGame game, Screen prevScreen) {
        this.game = game;
        this.prevScreen = prevScreen;

        SETTINGS_MENU_HEIGHT = Gdx.graphics.getHeight();
        SETTINGS_MENU_WIDTH = Gdx.graphics.getWidth();
        backButton = new BackButton();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();

        drawButton(20, SETTINGS_MENU_HEIGHT - backButton.getHeight() - 20, backButton);
       
        game.batch.end();
    }

    private void drawButton(int x, int y, Button button) {
        int w = button.getWidth();
        int h = button.getHeight();

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            SETTINGS_MENU_HEIGHT - Gdx.input.getY() < y + h && SETTINGS_MENU_HEIGHT - Gdx.input.getY() > y) {

            game.batch.draw(button.inactive(), x, y, w, h);

            if (button.getButtonType() == "navigation" && Gdx.input.isTouched()) {
                game.setScreen(prevScreen);
            }
        } else {
            game.batch.draw(button.active(), x, y, w, h);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }



   
    
}
