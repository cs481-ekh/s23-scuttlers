package com.Screens;

import com.badlogic.gdx.Screen;
import com.antscuttle.game.AntScuttle;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.ExitButton;
import com.antscuttle.game.Buttons.LoadGameButton;
import com.antscuttle.game.Buttons.NewGameButton;
import com.antscuttle.game.Buttons.SaveGameButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    SpriteBatch batch;
    private AntScuttle game;
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

    public MainMenuScreen(AntScuttle game) {
        this.game = game;
        /* init buttons */
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
    public void render(float delta) {

        ScreenUtils.clear(0, 0.5f, 1, 1);
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

            if (button.getButtonType() == "exit" && Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
            if (button.getButtonType() == "newgame" && Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new NewGameScreen(game));
            }
        } else {
            game.batch.draw(button.active(), x, y, w, h);
        }
    }

    @Override
    public void dispose() {
        if(game.batch != null){
            game.batch.dispose();
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }
}
