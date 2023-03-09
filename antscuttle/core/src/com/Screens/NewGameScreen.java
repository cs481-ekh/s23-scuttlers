package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.MainButton;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Buttons.AntButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class NewGameScreen extends ScreenAdapter{
    AntScuttleGame game;
    GameData gameData;
    
    /* Buttons */
    private Button startButton;
   // private Button levelButton;
    private Button charButton;
    private Button aiButton;
    // private Button mainButton;
    private Button settingsButton;
    private Button backButton;
    


    /* y-axis for buttons */
    private static final int AI_BUILDER_BUTTON_Y = 155;
    private static final int CHAR_BUTTON_Y = 300;
    private static final int START_BUTTON_Y = 445;
    private static final int SETTINGS_BUTTON_Y = 20;

    private static int MAIN_MENU_HEIGHT;
    private static int MAIN_MENU_WIDTH;

    int x;

    public NewGameScreen(AntScuttleGame game, GameData gamedata) {
        this.game = game;
        this.gameData = gamedata;
        
        /* init buttons */
        // mainButton = new MainButton();
        backButton = new BackButton();
        startButton = new StartButton();
        aiButton = new AIButton();
        charButton = new AntButton();
        settingsButton = new SettingsButton();

        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 38/255f, 66/255f, 1);

        game.batch.begin();

        /* Back Button */
        Button.draw(game, this, gameData, 20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* Start Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (startButton.getWidth() / 2);
        Button.draw(game, this, gameData, x, START_BUTTON_Y, startButton, 1);
      
        /* AI Editor Button */
        x = (MAIN_MENU_WIDTH / 2) - (aiButton.getWidth() / 2);
        Button.draw(game, this, gameData, x, AI_BUILDER_BUTTON_Y, aiButton, 1);
    
        /* Save Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (charButton.getWidth() / 2);
        Button.draw(game, this, gameData, x, CHAR_BUTTON_Y, charButton, 1);

        /* Settings Button */
        x = MAIN_MENU_WIDTH - settingsButton.getWidth() - 20;
        Button.draw(game, this, gameData, x, SETTINGS_BUTTON_Y, settingsButton, 1);

        game.batch.end();
    }


    @Override
    public void dispose() {
        if(this.game.batch != null){
            this.game.batch.dispose();
        }
    }

    @Override
    public String toString() {
        return "NewGameScreen";
    }
}
