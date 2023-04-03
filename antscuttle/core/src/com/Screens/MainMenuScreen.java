package com.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.Buttons.ContinueButton;
import com.antscuttle.game.Buttons.ExitButton;
import com.antscuttle.game.Buttons.LoadGameButton;
import com.antscuttle.game.Buttons.NewGameButton;
import com.antscuttle.game.Buttons.SaveGameButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Preferences;

public class MainMenuScreen extends ScreenAdapter {
    /* Buttons */
    private ScuttleButton exitButton;
    private ScuttleButton newGameButton;
    private ScuttleButton loadGameButton;
    private Button saveGameButton;
    private ButtonStyle buttonStyle;
    private ScuttleButton settingsButton;
    private ScuttleButton continueButton;
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
    final Color INACTIVE = new Color(1f, 1f, 1f, 1f); // fully opaque white
    final Color ACTIVE = new Color(1f, 1f, 1f, 0.5f); // semi-transparent white 
    public Image saveImage;
    public final Skin skin;
    public final Stage stage;
    int x;
    
    Preferences prefs; 

    

    AntScuttleGame game;

    public MainMenuScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;
        this.stage = new Stage();
        exitButton = new ExitButton();
        newGameButton = new NewGameButton();
        loadGameButton = new LoadGameButton();
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
        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        skin.add("saveActive", new Texture("buttons/main-menu/Save-Game-Active.png"));
        skin.add("saveInactive", new Texture("buttons/main-menu/Save-Game.png"));
        buttonStyle = new ButtonStyle();
        buttonStyle.checked = new TextureRegionDrawable(skin.getRegion("saveInactive"));
        buttonStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("saveActive"));
        saveGameButton = new Button(buttonStyle);
        saveGameButton.setBounds(SAVE_GAME_BUTTON_LOC.x, SAVE_GAME_BUTTON_LOC.y, 200, 100);
        saveGameButton.setChecked(true);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(saveGameButton);

        saveGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                long id = game.sfx.play(game.VOLUME);
                String fileName = "gamedata.txt";
                FileOutputStream fos;
                saveGameButton.setChecked(true);
                
                // Serialize the SaveData object to a file using Kryo
               if(gameData != null){ 
                    try {
                        fos = new FileOutputStream(fileName);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(gameData); 
                        oos.close();
                    } catch (FileNotFoundException fnfe) {
                        Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, fnfe);
                    } catch (IOException ioe){
                        Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ioe);
                    }
                }else{
                    System.out.println("print a dialog eventually with error message");
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();

        drawLogo();
        
        /* Continue Button */
        ScuttleButton.draw(game, this, gameData, CONTINUE_BUTTON_LOC.x, CONTINUE_BUTTON_LOC.y, continueButton, 1);
        /* New Game Button */
        ScuttleButton.draw(game, this, gameData, NEW_GAME_BUTTON_LOC.x, NEW_GAME_BUTTON_LOC.y, newGameButton, 1);
      
        /* Load Game Button */
        ScuttleButton.draw(game, this, gameData, LOAD_BUTTON_LOC.x, LOAD_BUTTON_LOC.y, loadGameButton, 1);
    
        /* Save Game Button */
        //Button.draw(game, this, gameData, SAVE_GAME_BUTTON_LOC.x, SAVE_GAME_BUTTON_LOC.y, saveGameButton, 1);

        /* Exit Button */
        ScuttleButton.draw(game, this, gameData, EXIT_BUTTON_LOC.x, EXIT_BUTTON_LOC.y, exitButton, 1);

        /* Settings Button */
        ScuttleButton.draw(game, this, gameData, SETTINGS_BUTTON_LOC.x, SETTINGS_BUTTON_LOC.y, settingsButton, 1);

        game.batch.end();
        // a stage has its own batch so don't put it within batch.begin() and batch.end()
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); //you are likely missing THIS LINE :D
        stage.draw();
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