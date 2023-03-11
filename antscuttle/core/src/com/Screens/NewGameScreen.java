package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.MainButton;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Buttons.AntButton;
import com.antscuttle.game.Buttons.SelectionButtonNext;
import com.antscuttle.game.Buttons.SelectionButtonPrev;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import java.awt.Point;

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
    private Button prevLevelButton;
    private Button nextLevelButton;
    


    /* y-axis for buttons */
    private static final int AI_BUILDER_BUTTON_Y = 125;
    private static final int CHAR_BUTTON_Y = 280;
    private static final int LEVEL_DISPLAY_Y = 440;
    private static final int START_BUTTON_Y = 480;
    private static final int SETTINGS_BUTTON_Y = 20;

    private static int MAIN_MENU_HEIGHT;
    private static int MAIN_MENU_WIDTH;
    
    private static Point PREV_LVL_BTN_LOC;
    private static Point NEXT_LVL_BTN_LOC;
    private static GlyphLayout levelGlyph;

    int x;

    public NewGameScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;
        
        /* init buttons */
        // mainButton = new MainButton();
        backButton = new BackButton();
        startButton = new StartButton();
        aiButton = new AIButton();
        charButton = new AntButton();
        settingsButton = new SettingsButton();
        prevLevelButton = new SelectionButtonPrev();
        nextLevelButton = new SelectionButtonNext();
        PREV_LVL_BTN_LOC = new Point();
        NEXT_LVL_BTN_LOC = new Point();

        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();
        
        // TEMPORARY TO SHOW LEVEL SELECTION WORKS
        gameData.unlockNewLevel();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 38/255f, 66/255f, 1);

        game.batch.begin();
        x = (MAIN_MENU_WIDTH / 2) - (startButton.getWidth() / 2);
        /* Back Button */
        Button.draw(game, this, gameData, 20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* Start Game Button */
        Button.draw(game, this, gameData, x, START_BUTTON_Y, startButton, 1);
        
        /* Level selection */
        if(gameData.getCurrentLevel() == null){
            System.err.println("Error loading levels");
            System.exit(1);
        }
        String levelName = gameData.getCurrentLevel().getName();
        levelGlyph = new GlyphLayout(game.font, levelName);
        
        int levelX = x + (int)(startButton.getWidth()-levelGlyph.width)/2;
        PREV_LVL_BTN_LOC.x = MAIN_MENU_WIDTH/2 - (int)levelGlyph.width - prevLevelButton.getWidth()/2;
        PREV_LVL_BTN_LOC.y = LEVEL_DISPLAY_Y - (int)levelGlyph.height/2-prevLevelButton.getHeight()/2;
        NEXT_LVL_BTN_LOC.x = MAIN_MENU_WIDTH/2 + (int)levelGlyph.width-nextLevelButton.getWidth()/2;
        NEXT_LVL_BTN_LOC.y = PREV_LVL_BTN_LOC.y;
        
        game.font.draw(game.batch, levelName, levelX, LEVEL_DISPLAY_Y);
        Button.draw(game, this, gameData, PREV_LVL_BTN_LOC.x, PREV_LVL_BTN_LOC.y, prevLevelButton, 1);
        Button.draw(game, this, gameData, NEXT_LVL_BTN_LOC.x, NEXT_LVL_BTN_LOC.y, nextLevelButton, 1);
        
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
