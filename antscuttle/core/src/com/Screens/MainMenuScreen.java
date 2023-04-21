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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

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
import java.util.HashMap;

public class MainMenuScreen extends ScreenAdapter {
    /* Buttons */
    private Button exitButton;
    private ButtonStyle exitStyle;
    private Button newGameButton;
    private ButtonStyle newStyle;
    private Button loadGameButton;
    private ButtonStyle loadStyle;
    private Button saveGameButton;
    private ButtonStyle saveStyle;
    private Button settingsButton;
    private ButtonStyle settingsStyle;
    private Button continueButton;
    private ButtonStyle continueStyle;
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
    public String fileName = "";
    public boolean isInitialMainScreen;
    int x;
    
    Preferences prefs; 
    private File saveFolder;
    

    AntScuttleGame game;

    public MainMenuScreen(AntScuttleGame game, GameData gameData, boolean isInitialMainScreen) {
        /* Grab the menu size (1280x720) */
        MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
        MAIN_MENU_WIDTH = Gdx.graphics.getWidth();
        
        int firstColumnX = (2*MAIN_MENU_WIDTH / 5) - (200 / 2);
        int secondColumnX = (3* MAIN_MENU_WIDTH / 5) - (200 / 2);
        int settingsX = MAIN_MENU_WIDTH - 200 - 20;
        int logoX = MAIN_MENU_WIDTH/2 - logo.getWidth()/2;
        int logoY = MAIN_MENU_HEIGHT * 4/6;
        
        CONTINUE_BUTTON_LOC = new Point((firstColumnX + secondColumnX)/2, 350);
        NEW_GAME_BUTTON_LOC = new Point(firstColumnX, 210);
        LOAD_BUTTON_LOC = new Point(firstColumnX, 70);
        SAVE_GAME_BUTTON_LOC = new Point(secondColumnX, 210);
        EXIT_BUTTON_LOC = new Point(secondColumnX, 70);
        SETTINGS_BUTTON_LOC = new Point(settingsX, 20);
        LOGO_LOC = new Point(logoX, logoY);

        this.isInitialMainScreen = isInitialMainScreen;
        this.game = game;
        this.gameData = gameData;
        this.stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        skin.add("saveActive", new Texture("buttons/main-menu/Save-Game-Active.png"));
        skin.add("saveInactive", new Texture("buttons/main-menu/Save-Game.png"));
        skin.add("exitActive", new Texture("buttons/main-menu/Exit-Active.png"));
        skin.add("exitInactive", new Texture("buttons/main-menu/Exit.png"));
        skin.add("newActive", new Texture("buttons/main-menu/New-Game-Active.png"));
        skin.add("newInactive", new Texture("buttons/main-menu/New-Game.png"));
        skin.add("loadActive", new Texture("buttons/main-menu/Load-Game-Active.png"));
        skin.add("loadInactive", new Texture("buttons/main-menu/Load-Game.png"));
        skin.add("settingsActive", new Texture("buttons/cog2.png"));
        skin.add("settingsInactive", new Texture("buttons/cog1.png"));
        skin.add("continueActive", new Texture("buttons/main-menu/Continue-Active.png"));
        skin.add("continueInactive", new Texture("buttons/main-menu/Continue-Unavailable.png"));
        saveStyle = new ButtonStyle();
        saveStyle.checked = new TextureRegionDrawable(skin.getRegion("saveInactive"));
        saveStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("saveActive"));
        saveGameButton = new Button(saveStyle);
        saveGameButton.setBounds(SAVE_GAME_BUTTON_LOC.x, SAVE_GAME_BUTTON_LOC.y, 200, 100);
        saveGameButton.setChecked(true);
        exitStyle = new ButtonStyle();
        exitStyle.checked = new TextureRegionDrawable(skin.getRegion("exitInactive"));
        exitStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("exitActive"));
        newStyle = new ButtonStyle();
        newStyle.checked = new TextureRegionDrawable(skin.getRegion("newInactive"));
        newStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("newActive"));
        loadStyle = new ButtonStyle();
        loadStyle.checked = new TextureRegionDrawable(skin.getRegion("loadInactive"));
        loadStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("loadActive"));
        settingsStyle = new ButtonStyle();
        settingsStyle.checked = new TextureRegionDrawable(skin.getRegion("settingsInactive"));
        settingsStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("settingsActive"));
        continueStyle = new ButtonStyle();
        continueStyle.checked = new TextureRegionDrawable(skin.getRegion("continueInactive"));
        if(gameData != null)
            continueStyle.checkedOver = new TextureRegionDrawable(skin.getRegion("continueActive"));
        exitButton = new Button(exitStyle);
        exitButton.setBounds(EXIT_BUTTON_LOC.x, EXIT_BUTTON_LOC.y, 200, 100);
        exitButton.setChecked(true);
        newGameButton = new Button(newStyle);
        newGameButton.setBounds(NEW_GAME_BUTTON_LOC.x, NEW_GAME_BUTTON_LOC.y, 200, 100);
        newGameButton.setChecked(true);
        loadGameButton = new Button(loadStyle);
        loadGameButton.setBounds(LOAD_BUTTON_LOC.x, LOAD_BUTTON_LOC.y, 200, 100);
        loadGameButton.setChecked(true);
        settingsButton = new Button(settingsStyle);
        settingsButton.setBounds(SETTINGS_BUTTON_LOC.x, SETTINGS_BUTTON_LOC.y, 70, 70);
        settingsButton.setChecked(true);
        continueButton = new Button(continueStyle);
        continueButton.setBounds(CONTINUE_BUTTON_LOC.x, CONTINUE_BUTTON_LOC.y, 200, 100);
        continueButton.setChecked(true);
        
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(saveGameButton);
        stage.addActor(continueButton);
        stage.addActor(loadGameButton);
        stage.addActor(exitButton);
        stage.addActor(settingsButton);
        stage.addActor(newGameButton);

        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows"))
            saveFolder = new File(System.getenv("APPDATA") + "\\" + "AntScuttle");
        else
            saveFolder = new File(System.getProperty("user.dir")+"/AntScuttle");
        
        // Create a list of file names from a folder
        
        if (!saveFolder.exists()){
            saveFolder.mkdirs();
        }
        
        

        saveGameButton.addListener(new ClickListener() {
            
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                //Create new dialog for save name
                Dialog dialog = new Dialog("Enter Save Name", skin);
                final TextField inputField = new TextField("", skin);
                TextButton saveButton = new TextButton("Save", skin);
                saveButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        fileName = inputField.getText() + ".txt";
                        FileOutputStream fos;
                        // Serialize the SaveData object to a file using Kryo
                        if(gameData != null){ 
                            try {
                                fos = new FileOutputStream(saveFolder+"/" + fileName);
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
                dialog.getContentTable().add(inputField);
                dialog.button(saveButton);
                dialog.setBounds(stage.getWidth() / 2 - dialog.getWidth() / 2, stage.getHeight() / 2 - dialog.getHeight() / 2, 200, 100);
                dialog.setZIndex(100);
                stage.addActor(dialog);
        

                long id = game.sfx.play(game.VOLUME);
                saveGameButton.setChecked(true);
                

            }
        });
        
        continueButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(gameData != null){
                    long id = game.sfx.play(game.VOLUME);
                    game.setScreen(new NewGameScreen(game, gameData)); 
                } 
                continueButton.setChecked(true);
                
            }

        });

        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                long id = game.sfx.play(game.VOLUME);
                game.setScreen(new NewGameScreen(game, new GameData()));
                newGameButton.setChecked(true);
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                long id = game.sfx.play(game.VOLUME);
                Gdx.app.exit();
                exitButton.setChecked(true);
            }
            
        });

        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                long id = game.sfx.play(game.VOLUME);
                // Create a list of file names from a folder
                Map<String,String> fileNameToFullPath = new HashMap<>();
                for(File file: saveFolder.listFiles()){
                    String full = file.getPath();
                    String s = file.getName();
                    // Remove extension
                    s = s.substring(0,s.lastIndexOf("."));
                    // Remove long path
                    if(s.lastIndexOf("/") >= 0)
                        s = s.substring(s.lastIndexOf("/"));
                    if(s.lastIndexOf("\\") >= 0)
                        s = s.substring(s.lastIndexOf("\\"));
                    fileNameToFullPath.put(s, full);
                }

                if(fileNameToFullPath.isEmpty()){
                    Dialog dialog = new Dialog("Load Error", skin);
                    dialog.text("You don't have any saved games");
                    dialog.button("OK");
                    dialog.show(stage);                    
                }else{
                    // Create a new dialog
                    Dialog loadDialog = new Dialog("Saved Games", skin);

                    // Create a select box for the dropdown
                    SelectBox<String> selectBox = new SelectBox<String>(skin);
                    TextButton okButton = new TextButton("OK", skin);
                    okButton.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y){
                            String fileName = selectBox.getSelected();
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(fileNameToFullPath.get(fileName));
                                ObjectInputStream ois = new ObjectInputStream(fis);
                                Object save = ois.readObject();
                                if(save instanceof GameData){
                                    gameData = (GameData) save;
                                }
                                
                            }catch (FileNotFoundException fnfe) {
                                Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, fnfe);
                            } catch (IOException ioe){
                                Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ioe);
                            } catch (ClassNotFoundException cnfe){
                                Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, cnfe);
                            }
                        }
                    });
                        
                    // Add the file names to the select box
                    selectBox.setItems(fileNameToFullPath.keySet().toArray(new String[0]));
                    loadDialog.button(okButton);
                    // Add the select box to the dialog
                    loadDialog.getContentTable().add(selectBox);
                    loadDialog.setBounds(stage.getWidth() / 2 - loadDialog.getWidth() / 2, stage.getHeight() / 2 - loadDialog.getHeight() / 2, 200, 100);
                    // Show the dialog
                    stage.addActor(loadDialog);
                }
                loadGameButton.setChecked(true);
            }
        });
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();
        drawLogo();
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