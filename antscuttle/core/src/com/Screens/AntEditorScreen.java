package com.Screens;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Buttons.AIButton;
import com.antscuttle.game.Buttons.AddButton;
import com.antscuttle.game.Buttons.AntButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.ItemButton;
import com.antscuttle.game.Buttons.ItemsButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AntEditorScreen extends ScreenAdapter {
    AntScuttleGame game;
    GameData gameData;
    SpriteBatch antBatch;
    
    Button settingsButton;
    Button backButton;

    Button AIButton;
    Button antButton;
    Button itemsButton;
    Button addButton;
    ItemButton itemButton;

    int x;

    private static int ANT_EDITOR_HEIGHT;
    private static int ANT_EDITOR_WIDTH;
    
    GlyphLayout bounds;
    
    Ant human;
    Ant zombie;
    
    float stateTime = 0;
    float i;
    
    LinkedList<Weapon> weapons;
    LinkedList<Armor> armors;
    LinkedList<Ant> ants;
    LinkedList<AI> ais;

    Stage stage;
    final Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
    private Viewport gameView;
    private Camera camera;

    public AntEditorScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;

        settingsButton = new SettingsButton();
        backButton = new BackButton();
        AIButton = new AIButton();
        antButton = new AntButton();
        itemsButton = new ItemsButton();
        addButton = new AddButton();
        itemButton = new ItemButton();

        antBatch = new SpriteBatch();

        ANT_EDITOR_HEIGHT = Gdx.graphics.getHeight();
        ANT_EDITOR_WIDTH = Gdx.graphics.getWidth();

        bounds = new GlyphLayout();
        
        weapons = gameData.getUnlockedWeapons();
        armors = gameData.getUnlockedArmors();
        ants = gameData.getAllAnts();
        ais = gameData.getAllAIs();

    
        i  = game.font.getCapHeight()+10;
        gameData.currPane = GameData.panes.ant;        
    }
    
    
    @Override
    public void show() {
        camera = new OrthographicCamera();
        gameView = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.setCamera(camera);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin.add("item", new Texture("buttons/ant-editor/Item.png"));
        skin.add("item-active", new Texture("buttons/ant-editor/Item-Active.png"));

        skin.add("items", new Texture("buttons/ant-editor/Items.png"));
        skin.add("items-active", new Texture("buttons/ant-editor/Items-Active.png"));
        final Image itemsBtn = new Image(skin, "items");
        itemsBtn.setBounds(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth(), ANT_EDITOR_HEIGHT/1.25f, 150, 75);
        stage.addActor(itemsBtn);
      
        itemsBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                itemsBtn.setDrawable(skin, "items-active");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                itemsBtn.setDrawable(skin, "items");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { 
                Button.playButtonPressSound(game);
                gameData.currPane = GameData.panes.items;
                return true;
            }
        });


        skin.add("ant", new Texture("buttons/Ant.png"));
        skin.add("ant-active", new Texture("buttons/Ant-Active.png"));
        final Image antBtn = new Image(skin, "ant");
        antBtn.setBounds(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth()*2, ANT_EDITOR_HEIGHT/1.25f, 150, 75);
        stage.addActor(antBtn);
        antBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                antBtn.setDrawable(skin, "ant-active");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                antBtn.setDrawable(skin, "ant");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { 
                Button.playButtonPressSound(game);
                gameData.currPane = GameData.panes.ant;
                return true;
            }
        });

        skin.add("ai", new Texture("buttons/AI.png"));
        skin.add("ai-active", new Texture("buttons/AI-Active.png"));
        final Image aiBtn = new Image(skin, "ai");
        aiBtn.setBounds(ANT_EDITOR_WIDTH/1.25f, ANT_EDITOR_HEIGHT/1.25f, 150, 75);
        stage.addActor(aiBtn);
        aiBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                aiBtn.setDrawable(skin, "ai-active");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                aiBtn.setDrawable(skin, "ai");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) { 
                Button.playButtonPressSound(game);
                gameData.currPane = GameData.panes.ai;
                return true;
            }
        });

        skin.add("add", new Texture("buttons/ant-editor/Add.png"));
        skin.add("add-active", new Texture("buttons/ant-editor/Add-Active.png"));

        final Image addButton = new Image(skin, "add");
        addButton.setBounds(ANT_EDITOR_WIDTH/1.25f - antButton.getWidth()*2, 30, 64, 64);
        stage.addActor(addButton);
        addButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                addButton.setDrawable(skin, "add-active");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                addButton.setDrawable(skin, "add");
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Button.playButtonPressSound(game);
                final TextField inputField = new TextField("", skin);

                new Dialog("Enter Ant Name", skin) {
                    {
                        getContentTable().add(inputField);
                        button("add", true);
                        button("X", false);
                    }

                    @Override
                    protected void result(Object obj) {
                        if (obj.equals(false)) return;
                        String input = inputField.getText();
                              
                        // boolean okInput = false;
                        // while (!okInput) {
                            if (input.length() == 0) {
                                // this.hide();
                                new Dialog("Please enter a name", skin) {
                                    {
                                        getContentTable().add(inputField);
                                        button("X", false);
                                    }
                                };
                            }
                            if (input.length() > 10) {
                                input = input.substring(0, 9);
                            }

                        //     okInput = true;
                        // }

                        final String str = input;
                        new Dialog("Choose Ant Type", skin) {
                            {
                                for (Class<? extends Ant> ant: gameData.getAllAntTypes()) {
                                    button(ant.getSimpleName(), ant.getName());
                                }
                            }

                            @Override
                            protected void result(Object obj) {
                                
                                try {
                                    ClassFactory cFactory = new ClassFactory();
                                    @SuppressWarnings("unchecked") Ant ant = cFactory.newAntInstance((Class<? extends Ant>) Class.forName(obj.toString()), str);
                                    gameData.addAnt(ant);
                                    gameData.setCurrentAnt(ant);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.show(stage);
                    }
                }.show(stage).toFront();

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        /* Set background */
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();
        game.batch.begin();

        /* Back Button */
        Button.draw(game, this, gameData, 20, ANT_EDITOR_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* The view for whichever button is clicked */
        drawCurrentPane();
        
        /* Animation of the chosen Ant */
        if (ants.size() > 0) {
            stateTime += Gdx.graphics.getDeltaTime();
            antBatch.begin();
            Texture[] frames = gameData.getCurrentAnt().getAntPreviewAnimation();
            Animation<Texture> animation = new Animation<>(0.6f, frames);
            antBatch.draw(frames[animation.getKeyFrameIndex(stateTime)], 10, ANT_EDITOR_HEIGHT/2 + 130);
            if(animation.isAnimationFinished(stateTime)){
                stateTime = 0;
            }
            antBatch.end();
            
            /* Chosen Ant Stats */
            Ant ant = gameData.getCurrentAnt();
            String str = (ant.getAI() != null) ? ant.getAI().getName() : "None";

            game.font.draw(game.batch, "AI: " + str, 20, ANT_EDITOR_HEIGHT/1.25f + bounds.height);        
            str = (ant.getMeleeWeapon() != null) ? ant.getMeleeWeapon().getName() : "None";
            game.font.draw(game.batch, "Melee: " + str, 10, ANT_EDITOR_HEIGHT/2 + 50);
            str = (ant.getArmor() != null) ? ant.getArmor().getName() : "None";
            game.font.draw(game.batch, "Armor: " + str, 10, ANT_EDITOR_HEIGHT/2 + 70);
            str = (ant.getRangedWeapon() != null) ? ant.getRangedWeapon().getName() : "None";
            game.font.draw(game.batch, "Ranged: " + str, 10, ANT_EDITOR_HEIGHT/2 + 90);
            
            game.font.draw(game.batch, "Name:" + ant.getName(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height);
            game.font.draw(game.batch, "HP:" + ant.getHealth(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 20);
            game.font.draw(game.batch, "DMG:" + ant.getBaseDamage(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 40);
            game.font.draw(game.batch, "DEF:" + ant.getBaseDefense(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 60);
            game.font.draw(game.batch, "SPD:" + ant.getSpeed(), 10, ANT_EDITOR_HEIGHT/2 + bounds.height - 80);
            
        }

        /* Settings Button */
        x = ANT_EDITOR_WIDTH - settingsButton.getWidth() - 20;
        Button.draw(game, this, gameData, x, 20, settingsButton, 1);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void drawCurrentPane() {
        i = game.font.getCapHeight()+10;
        int j=0;

         for (Actor act: stage.getActors()) {
            if (act.getName() == "item") {
                act.remove();
            }
        }
        switch(gameData.currPane) {
            case ai:
                if (ais.isEmpty()) {
                    game.font.draw(game.batch, "No Created AIs!", ANT_EDITOR_WIDTH/1.25f-antButton.getWidth(), ANT_EDITOR_HEIGHT/1.35f);
                } else {
                    game.font.draw(game.batch, "AIs: ", ANT_EDITOR_WIDTH/2.05f, ANT_EDITOR_HEIGHT/1.35f);
                    for (AI a: ais) {
                        // Button.drawGeneric(game, gameData, ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f, itemButton, null, null, null, ai);
                        final AI ai = a;
                        final Image itemButton = new Image(skin, "item");
                        itemButton.toBack();
                        itemButton.setBounds(ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f, 100, 100);
                            game.font.draw(game.batch, a.getName(), ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f+itemButton.getHeight()+20);
                            stage.addActor(itemButton);
                
                            itemButton.addListener(new InputListener() {
                                @Override
                                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                    itemButton.setDrawable(skin, "item-active");
                                }
                
                                @Override
                                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                    itemButton.setDrawable(skin, "item");
                                }
                
                                @Override
                                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                    Button.playButtonPressSound(game);
                                    gameData.getCurrentAnt().equipAI(ai);
                                    return true;
                                }
                            });
                        // game.font.draw(game.batch, ai.getName(), ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f);
                        i += game.font.getCapHeight()+10;
                        j += itemButton.getWidth()+20;
                    }
                }
                break;
            case ant:
                if (ants.isEmpty()) {
                    game.font.draw(game.batch, "No Created Ants!", ANT_EDITOR_WIDTH/1.25f-antButton.getWidth(), ANT_EDITOR_HEIGHT/1.35f);
                } else {

                    float x = ANT_EDITOR_WIDTH/2.05f+j;
                    float y = ANT_EDITOR_HEIGHT/2.05f;
                    int numAnt = 1;
                    for (Ant a: ants) {
                        final Ant ant = a;
                        final Image itemButton = new Image(skin, "item");
                        itemButton.setName("item");
                        if (numAnt%5==0) {
                            j = 0;
                            x = ANT_EDITOR_WIDTH/2.05f+j;
                            y = ANT_EDITOR_HEIGHT/2.05f-i;
                        }
                        itemButton.setBounds(x, y, 100, 100);
                        game.font.draw(game.batch, a.getName(), x, y+itemButton.getHeight()+20);
                        stage.addActor(itemButton);
            
                        itemButton.addListener(new InputListener() {
                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                itemButton.setDrawable(skin, "item-active");
                            }
            
                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                itemButton.setDrawable(skin, "item");
                            }
            
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                Button.playButtonPressSound(game);
                                gameData.setCurrentAnt(ant);
                                return true;
                            }
                        });
            
                        j += itemButton.getWidth()+50;
                        x = ANT_EDITOR_WIDTH/2.05f+j;
                        i += game.font.getCapHeight()+30;
                        numAnt++;
                    }
                    
                }
                break;
            case items:
                if (gameData.getCurrentAnt() == null) {
                    game.font.draw(game.batch, "No Selected Ant!", ANT_EDITOR_WIDTH/1.25f-antButton.getWidth(), ANT_EDITOR_HEIGHT/1.35f);
                } else {
                    game.font.draw(game.batch, "Weapons: ", ANT_EDITOR_WIDTH/2.05f, ANT_EDITOR_HEIGHT/1.35f);
                    for (Weapon weap: weapons) {
                        // Button.drawGeneric(game, gameData, ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f, itemButton, null, weap, null, null);
                        final Weapon weapon = weap;
                        final Image itemButton = new Image(skin, "item");
                        itemButton.setName("item");
                        itemButton.setBounds(ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f, 100, 100);
    
                        game.font.draw(game.batch, weap.getName(), ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/2.05f+itemButton.getHeight()+20);
                        stage.addActor(itemButton);
            
                        itemButton.addListener(new InputListener() {
                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                itemButton.setDrawable(skin, "item-active");
                            }
            
                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                itemButton.setDrawable(skin, "item");
                            }
            
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                Button.playButtonPressSound(game);
                                System.out.println("hi");
                                if (gameData.getCurrentAnt() instanceof Zombie) {
                                    new Dialog("Zombies cannot equip weapons!", skin) {
                                        {
                                            button("Ok");
                                        }
                                    }.show(stage);
                                    return true;
                                }

                                if (weapon instanceof MeleeWeapon) {
                                    MeleeWeapon mWeap = (MeleeWeapon) weapon;
                                    gameData.getCurrentAnt().equipMeleeWeapon(mWeap);
                                } else if (weapon instanceof RangedWeapon) {
                                    RangedWeapon rWeap = (RangedWeapon) weapon;
                                    gameData.getCurrentAnt().equipRangedWeapon(rWeap);
                                }
                                return true;
                            }
                        });

                        i += game.font.getCapHeight()+10;
                        j += itemButton.getWidth()+20;
                    }
                    game.font.draw(game.batch, "Armors: ", ANT_EDITOR_WIDTH/1.25f, ANT_EDITOR_HEIGHT/1.35f);
                    i = game.font.getCapHeight()+10;
                    j = 0;
                    for (Armor armr: armors) {
                        Button.drawGeneric(game, gameData, ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/3.35f, itemButton, null, null, armr, null);
    
                        game.font.draw(game.batch, armr.getName(), ANT_EDITOR_WIDTH/2.05f+j, ANT_EDITOR_HEIGHT/3.35f-i);
                        i += game.font.getCapHeight()+10;
                    }
                }
                break;
        }
    }

    @Override
    public String toString() {
        return "AntEditorScreen";
    }
}
