package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.Buttons.NewAIButton;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class AISelectScreen extends ScreenAdapter{
    AntScuttleGame game;
    GameData gameData;
    
    /* Buttons */
    private ScuttleButton startButton;
   // private Button levelButton;
    private ScuttleButton charButton;
    private ScuttleButton aiButton;
    private ScuttleButton newAiButton;
    // private Button mainButton;
    private ScuttleButton settingsButton;
    private ScuttleButton backButton;
    


    /* y-axis for buttons */
    private static final int AI_BUILDER_BUTTON_Y = 155;
    private static final int CHAR_BUTTON_Y = 300;
    private static final int START_BUTTON_Y = 445;
    private static final int SETTINGS_BUTTON_Y = 10;

    private static int MAIN_MENU_HEIGHT;
    private static int MAIN_MENU_WIDTH;

    int x;

    public AISelectScreen(AntScuttleGame game, GameData gamedata) {
        this.game = game;
        this.gameData = gamedata;
        
        /* init buttons */
        // mainButton = new MainButton();
        backButton = new BackButton();
        aiButton = new AIButton();
        newAiButton = new NewAIButton();
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
        drawButton(20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton);

        /* Start Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (newAiButton.getWidth() / 2);
        drawButton(x, START_BUTTON_Y, newAiButton);

      
        /* AI Editor Button */
        x = (MAIN_MENU_WIDTH / 2) - (aiButton.getWidth() / 2);
        drawButton(x, AI_BUILDER_BUTTON_Y, aiButton);

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
    private void drawButton(int x, int y, ScuttleButton button) {
        int w = button.getWidth();
        int h = button.getHeight();

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            MAIN_MENU_HEIGHT - Gdx.input.getY() < y + h && MAIN_MENU_HEIGHT - Gdx.input.getY() > y) {

            game.batch.draw(button.active(), x, y, w, h);

            
        } else {
            game.batch.draw(button.inactive(), x, y, w, h);
        }
    }

    @Override
    public void dispose() {
        if(this.game.batch != null){
            this.game.batch.dispose();
        }
    }

    
}
