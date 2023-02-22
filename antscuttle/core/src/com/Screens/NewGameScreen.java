package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.MainButton;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Buttons.CharacterButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class NewGameScreen extends ScreenAdapter{
    AntScuttleGame game;
    /* Buttons */
    private Button startButton;
   // private Button levelButton;
    private Button charButton;
    private Button aiButton;
    private Button mainButton;
    private Button settingsButton;



    /* y-axis for buttons */
    private static final int MAIN_BUTTON_Y = 10;
    private static final int AI_BUILDER_BUTTON_Y = 155;
    private static final int CHAR_BUTTON_Y = 300;
    private static final int START_BUTTON_Y = 445;
    private static final int SETTINGS_BUTTON_Y = 10;

    private static int MAIN_MENU_HEIGHT;
    private static int MAIN_MENU_WIDTH;

    int x;

    public NewGameScreen(AntScuttleGame game) {
        this.game = game;
        /* init buttons */
        mainButton = new MainButton();
        startButton = new StartButton();
        aiButton = new AIButton();
        charButton = new CharacterButton();
        settingsButton = new SettingsButton();

        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 38/255f, 66/255f, 1);

        game.batch.begin();

        /* New Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (startButton.getWidth() / 2);
        drawButton(x, START_BUTTON_Y, startButton);

      
        /* Load Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (aiButton.getWidth() / 2);
        drawButton(x, AI_BUILDER_BUTTON_Y, aiButton);

    
        /* Save Game Button */
        x = (MAIN_MENU_WIDTH / 2) - (charButton.getWidth() / 2);
        drawButton(x, CHAR_BUTTON_Y, charButton);

    
        /* Exit Button */
        x = (MAIN_MENU_WIDTH / 2) - (mainButton.getWidth() / 2);
        drawButton(x, MAIN_BUTTON_Y, mainButton);


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

            if (button.getButtonType() == "main" && Gdx.input.isTouched()) {
                game.setScreen(new MainMenuScreen(game));
            }
            if (button.getButtonType() == "newgame" && Gdx.input.isTouched()){
                
            }
        } else {
            game.batch.draw(button.active(), x, y, w, h);
        }
    }

    @Override
    public void dispose() {
        if(this.game.batch != null){
            this.game.batch.dispose();
        }
    }

    
}
