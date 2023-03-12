package com.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.ContinueButton;
import com.antscuttle.game.Buttons.ExitButton;
import com.antscuttle.game.Buttons.LoadGameButton;
import com.antscuttle.game.Buttons.NewGameButton;
import com.antscuttle.game.Buttons.SaveGameButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
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
    private Button continueButton;
    private final Texture logo = new Texture("antscuttle.png");
    private GameData gameData;
    /* y-axis for buttons */
    private static Point CONTINUE_BUTTON_LOC;
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

    public MainMenuScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;
        exitButton = new ExitButton();
        newGameButton = new NewGameButton();
        loadGameButton = new LoadGameButton();
        saveGameButton = new SaveGameButton();
        settingsButton = new SettingsButton();
        continueButton = new ContinueButton(gameData);

        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();
        
        int firstColumnX = (2*MAIN_MENU_WIDTH / 5) - (newGameButton.getWidth() / 2);
        int secondColumnX = (3* MAIN_MENU_WIDTH / 5) - (newGameButton.getWidth() / 2);
        int settingsX = MAIN_MENU_WIDTH - settingsButton.getWidth() - 20;
        int logoX = MAIN_MENU_WIDTH/2 - logo.getWidth()/2;
        int logoY = MAIN_MENU_HEIGHT * 4/6;
        
        CONTINUE_BUTTON_LOC = new Point((firstColumnX + secondColumnX)/2, 350);
        NEW_GAME_BUTTON_LOC = new Point(firstColumnX, 210);
        LOAD_BUTTON_LOC = new Point(firstColumnX, 70);
        SAVE_GAME_BUTTON_LOC = new Point(secondColumnX, 210);
        EXIT_BUTTON_LOC = new Point(secondColumnX, 70);
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
        
        /* Continue Button */
        Button.draw(game, this, gameData, CONTINUE_BUTTON_LOC.x, CONTINUE_BUTTON_LOC.y, continueButton, 1);
        /* New Game Button */
        Button.draw(game, this, gameData, NEW_GAME_BUTTON_LOC.x, NEW_GAME_BUTTON_LOC.y, newGameButton, 1);
      
        /* Load Game Button */
        Button.draw(game, this, gameData, LOAD_BUTTON_LOC.x, LOAD_BUTTON_LOC.y, loadGameButton, 1);
    
        /* Save Game Button */
        Button.draw(game, this, gameData, SAVE_GAME_BUTTON_LOC.x, SAVE_GAME_BUTTON_LOC.y, saveGameButton, 1);

        /* Exit Button */
        Button.draw(game, this, gameData, EXIT_BUTTON_LOC.x, EXIT_BUTTON_LOC.y, exitButton, 1);

        /* Settings Button */
        Button.draw(game, this, gameData, SETTINGS_BUTTON_LOC.x, SETTINGS_BUTTON_LOC.y, settingsButton, 1);

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