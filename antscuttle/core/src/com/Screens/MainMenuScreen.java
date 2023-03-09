package com.Screens;

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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import java.awt.Point;

public class MainMenuScreen extends ScreenAdapter {
    /* Buttons */
    private Button exitButton;
    private Button newGameButton;
    private Button loadGameButton;
    private Button saveGameButton;
    private Button settingsButton;
    private final Texture logo = new Texture("antscuttle.png");

    /* y-axis for buttons */
    private static Point EXIT_BUTTON_LOC;
    private static Point LOAD_BUTTON_LOC;
    private static Point SAVE_GAME_BUTTON_LOC;
    private static Point NEW_GAME_BUTTON_LOC;
    private static Point SETTINGS_BUTTON_LOC;
    private static Point LOGO_LOC;

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
        
        int firstColumnX = (2*MAIN_MENU_WIDTH / 5) - (newGameButton.getWidth() / 2);
        int secondColumnX = (3* MAIN_MENU_WIDTH / 5) - (newGameButton.getWidth() / 2);
        int settingsX = MAIN_MENU_WIDTH - settingsButton.getWidth() - 20;
        int logoX = MAIN_MENU_WIDTH/2 - logo.getWidth()/2;
        int logoY = MAIN_MENU_HEIGHT * 3/5;
        
        NEW_GAME_BUTTON_LOC = new Point(firstColumnX, 235);
        LOAD_BUTTON_LOC = new Point(firstColumnX, 90);
        SAVE_GAME_BUTTON_LOC = new Point(secondColumnX, 235);
        EXIT_BUTTON_LOC = new Point(secondColumnX, 90);
        SETTINGS_BUTTON_LOC = new Point(settingsX, 20);
        LOGO_LOC = new Point(logoX, logoY);
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

        drawLogo();
        /* New Game Button */
        Button.draw(game, this, null, NEW_GAME_BUTTON_LOC.x, NEW_GAME_BUTTON_LOC.y, newGameButton, 1);
      
        /* Load Game Button */
        Button.draw(game, this, null, LOAD_BUTTON_LOC.x, LOAD_BUTTON_LOC.y, loadGameButton, 1);
    
        /* Save Game Button */
        Button.draw(game, this, null, SAVE_GAME_BUTTON_LOC.x, SAVE_GAME_BUTTON_LOC.y, saveGameButton, 1);

        /* Exit Button */
        Button.draw(game, this, null, EXIT_BUTTON_LOC.x, EXIT_BUTTON_LOC.y, exitButton, 1);

        /* Settings Button */
        Button.draw(game, this, null, SETTINGS_BUTTON_LOC.x, SETTINGS_BUTTON_LOC.y, settingsButton, 1);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawLogo(){
        game.batch.draw(logo, LOGO_LOC.x, LOGO_LOC.y);
    }
    
    @Override 
    public String toString() {
        return "MainMenuScreen";
    }
}