package com.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.ExitButton;
import com.antscuttle.game.Buttons.LoadGameButton;
import com.antscuttle.game.Buttons.NewGameButton;
import com.antscuttle.game.Buttons.SaveGameButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen extends ScreenAdapter {
    /* Buttons */
    private Button exitButton;
    private Button newGameButton;
    private Button loadGameButton;
    private Button saveGameButton;
    private Button settingsButton;

    /* y-axis for buttons */
    private static final int EXIT_BUTTON_Y = 30;
    private static final int LOAD_BUTTON_Y = 140;
    private static final int SAVE_GAME_BUTTON_Y = 250;
    private static final int NEW_GAME_BUTTON_Y = 360;
    private static final int SETTINGS_BUTTON_Y = 10;

    private static int MAIN_MENU_HEIGHT;
    private static int MAIN_MENU_WIDTH;

    int x;

    AntScuttleGame game;

    public MainMenuScreen(AntScuttleGame game) {
        this.game = game;
        exitButton = new ExitButton();
        newGameButton = new NewGameButton();
        loadGameButton = new LoadGameButton();
        saveGameButton = new SaveGameButton();
        settingsButton = new SettingsButton();

        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();

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

        /* New Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (newGameButton.getWidth() / 2);
        drawButton(x, NEW_GAME_BUTTON_Y, newGameButton);

      
        /* Load Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (loadGameButton.getWidth() / 2);
        drawButton(x, LOAD_BUTTON_Y, loadGameButton);

    
        /* Save Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (saveGameButton.getWidth() / 2);
        drawButton(x, SAVE_GAME_BUTTON_Y, saveGameButton);

    
        /* Exit Button */
        x = (MAIN_MENU_WIDTH / 2) - (exitButton.getWidth() / 2);
        drawButton(x, EXIT_BUTTON_Y, exitButton);


        /* Settings Button */
        x = MAIN_MENU_WIDTH - settingsButton.getWidth() - 10;
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
            MAIN_MENU_HEIGHT - Gdx.input.getY() < y + h && MAIN_MENU_HEIGHT - Gdx.input.getY() > y) {

            game.batch.draw(button.inactive(), x, y, w, h);

            if (button.getButtonType() == "exit" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                Gdx.app.exit();
            }
            if (button.getButtonType() == "newgame" && Gdx.input.justTouched()){
                this.dispose();
                button.playButtonPressSound(game);
                game.setScreen(new NewGameScreen(game));
            }
            if (button.getButtonType() == "settings" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new SettingsMenuScreen(game, this));
            }
        } else {
            game.batch.draw(button.active(), x, y, w, h);
        }
    }
}