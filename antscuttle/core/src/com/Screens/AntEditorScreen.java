package com.Screens;

import java.util.LinkedList;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.AntDecorator;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Armor.Armor;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.Buttons.ItemButton;
import com.antscuttle.game.Buttons.SettingsButton;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.GameData;
import com.antscuttle.game.Weapon.MeleeWeapon;
import com.antscuttle.game.Weapon.RangedWeapon;
import com.antscuttle.game.Weapon.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Array;

public class AntEditorScreen extends ScreenAdapter {
    AntScuttleGame game;
    GameData gameData;
    SpriteBatch antBatch;
    
    ScuttleButton settingsButton;
    ScuttleButton backButton;
    ScuttleButton addButton;
    ItemButton itemButton;

    private static int ANT_EDITOR_HEIGHT;
    private static int ANT_EDITOR_WIDTH;

    GlyphLayout bounds;

    float stateTime = 0;

    LinkedList<Weapon> weapons;
    LinkedList<Armor> armors;
    LinkedList<Ant> ants;
    LinkedList<AI> ais;

    Stage stage;
    final Skin skin;

    // scroll pane
    protected Table scrollTable;
    protected Table selectionContainer;
    protected ScrollPane scrollPane;
    protected Array<Image> scrollButtons;

    public AntEditorScreen(AntScuttleGame game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;
        skin = game.skin;

        settingsButton = new SettingsButton();
        backButton = new BackButton();

        antBatch = new SpriteBatch();

        ANT_EDITOR_HEIGHT = Gdx.graphics.getHeight();
        ANT_EDITOR_WIDTH = Gdx.graphics.getWidth();

        bounds = new GlyphLayout();

        weapons = gameData.getUnlockedWeapons();
        armors = gameData.getUnlockedArmors();
        ants = gameData.getAllAnts();
        ais = gameData.getAllAIs();

        // i = game.font.getCapHeight() + 10;
        gameData.currPane = GameData.panes.ant;
    }

    @Override
    public void show() {
        skin.add("item", new Texture("buttons/ant-editor/Item.png"));
        skin.add("item-active", new Texture("buttons/ant-editor/Item-Active.png"));
        skin.add("items", new Texture("buttons/ant-editor/Items.png"));
        skin.add("items-active", new Texture("buttons/ant-editor/Items-Active.png"));
        skin.add("ant", new Texture("buttons/Ant.png"));
        skin.add("ant-active", new Texture("buttons/Ant-Active.png"));
        skin.add("ai", new Texture("buttons/AI.png"));
        skin.add("ai-active", new Texture("buttons/AI-Active.png"));
        skin.add("add", new Texture("buttons/ant-editor/Add.png"));
        skin.add("add-active", new Texture("buttons/ant-editor/Add-Active.png"));

        final Image itemsBtn = new Image(skin, "items");
        final Image antBtn = new Image(skin, "ant");
        final Image aiBtn = new Image(skin, "ai");
        final Image addButton = new Image(skin, "add");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        itemsBtn.setBounds(ANT_EDITOR_WIDTH / 1.25f - 200, ANT_EDITOR_HEIGHT / 1.25f, 150, 75);
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
                ScuttleButton.playButtonPressSound(game);
                stage.clear();
                stage.addActor(addButton);
                stage.addActor(itemsBtn);
                stage.addActor(antBtn);
                stage.addActor(aiBtn);
                gameData.currPane = GameData.panes.items;
                drawScrollPane();
                return true;
            }
        });

        antBtn.setBounds(ANT_EDITOR_WIDTH / 1.25f - 200 * 2, ANT_EDITOR_HEIGHT / 1.25f, 150, 75);
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
                ScuttleButton.playButtonPressSound(game);
                stage.clear();
                stage.addActor(addButton);
                stage.addActor(itemsBtn);
                stage.addActor(antBtn);
                stage.addActor(aiBtn);
                gameData.currPane = GameData.panes.ant;
                drawScrollPane();
                return true;
            }
        });

        aiBtn.setBounds(ANT_EDITOR_WIDTH / 1.25f, ANT_EDITOR_HEIGHT / 1.25f, 150, 75);
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
                ScuttleButton.playButtonPressSound(game);
                stage.clear();
                stage.addActor(addButton);
                stage.addActor(itemsBtn);
                stage.addActor(antBtn);
                stage.addActor(aiBtn);
                gameData.currPane = GameData.panes.ai;
                drawScrollPane();
                return true;
            }
        });


        addButton.setBounds(ANT_EDITOR_WIDTH / 1.25f - 200 * 2, 30, 64, 64);
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
                ScuttleButton.playButtonPressSound(game);
                
                final TextField inputField = new TextField("", skin);

                new Dialog("Enter Ant Name", skin) {
                    {
                        getContentTable().add(inputField);
                        button("Add", true);
                        button("X", false);
                    }

                    @Override
                    protected void result(Object obj) {
                        if (obj.equals(false))
                            return;
                        String input = inputField.getText();

                        if (input.equals("")) {
                            new Dialog("Name cannot be empty", skin) {
                                {
                                    button("Ok", false);
                                }
                            }.show(stage).setPosition(ANT_EDITOR_WIDTH/3.5f, ANT_EDITOR_HEIGHT/2);
                            return;
                        }

                        if (input.length() > 10) {
                            input = input.substring(0, 9);
                        }

                        final String str = input;
                        new Dialog("Choose Ant Type", skin) {
                            {
                                for (Class<? extends Ant> ant : gameData.getAllAntTypes()) {
                                    button(ant.getSimpleName(), ant.getName());
                                }
                            }

                            @Override
                            protected void result(Object obj) {
                                String antType = obj.toString();
                               new Dialog("Choose Elemental Type", skin) {
                                {
                                    for (DamageType type: DamageType.values()) {
                                        button(type.getName(), type);
                                    }
                                    button("None", false);
                                }
                                @Override
                                protected void result (Object obj) {
                                    try {
                                        ClassFactory cFactory = new ClassFactory();
                                        @SuppressWarnings("unchecked")
                                        Ant ant = cFactory.newAntInstance((Class<? extends Ant>) Class.forName(antType), str);
                                        if (!obj.equals(false)) {
                                            AntDecorator eAnt = new AntDecorator(ant,(DamageType) obj);
                                            gameData.addAnt(eAnt);
                                            gameData.setCurrentAnt(eAnt);
                                        } else {
                                            gameData.addAnt(ant);
                                            gameData.setCurrentAnt(ant);
                                        }

                                        stage.clear();
                                        stage.addActor(addButton);
                                        stage.addActor(itemsBtn);
                                        stage.addActor(antBtn);
                                        stage.addActor(aiBtn);
                                        drawScrollPane();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                               }.show(stage);
                            }
                        }.show(stage);
                    }
                }.show(stage);

                return true;
            }
        });
      
        stage.addActor(aiBtn);
        stage.addActor(addButton);
        stage.addActor(antBtn);
        stage.addActor(itemsBtn);

        drawScrollPane();
    }

    @Override
    public void render(float delta) {
        /* Set background */
        ScreenUtils.clear(0, 38 / 255f, 66 / 255f, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        game.batch.begin();

        /* Back Button */
        ScuttleButton.draw(game, this, gameData, 20, ANT_EDITOR_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* Animation of the chosen Ant */
        if (ants.size() > 0) {
            stateTime += Gdx.graphics.getDeltaTime();
            antBatch.begin();
            Texture[] frames = gameData.getCurrentAnt().getAntPreviewAnimation();
            Animation<Texture> animation = new Animation<>(0.6f, frames);
            antBatch.draw(frames[animation.getKeyFrameIndex(stateTime)], 10, ANT_EDITOR_HEIGHT / 2 + 130);
            if (animation.isAnimationFinished(stateTime)) {
                stateTime = 0;
            }
            antBatch.end();

            /* Chosen Ant Stats */
            Ant ant = gameData.getCurrentAnt();
            String str = (ant.getAI() != null) ? ant.getAI().getName() : "None";

            game.font.draw(game.batch, "AI: " + str, 20, ANT_EDITOR_HEIGHT / 1.25f + bounds.height);
            str = (ant.getMeleeWeapon() != null) ? ant.getMeleeWeapon().getName() : "None";
            game.font.draw(game.batch, "Melee: " + str, 10, ANT_EDITOR_HEIGHT / 2 + 50);
            str = (ant.getArmor() != null) ? ant.getArmor().getName() : "None";
            game.font.draw(game.batch, "Armor: " + str, 10, ANT_EDITOR_HEIGHT / 2 + 70);
            str = (ant.getRangedWeapon() != null) ? ant.getRangedWeapon().getName() : "None";
            game.font.draw(game.batch, "Ranged: " + str, 10, ANT_EDITOR_HEIGHT / 2 + 90);

            game.font.draw(game.batch, "DMG Type:" + gameData.getCurrentAnt(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height-100);

            game.font.draw(game.batch, "Name:" + ant.getName(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height);
            game.font.draw(game.batch, "HP:" + ant.getHealth(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height - 20);
            game.font.draw(game.batch, "DMG:" + ant.getBaseDamage(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height - 40);
            game.font.draw(game.batch, "DEF:" + ant.getBaseDefense(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height - 60);
            game.font.draw(game.batch, "SPD:" + ant.getSpeed(), 10, ANT_EDITOR_HEIGHT / 2 + bounds.height - 80);
        }

        /* Settings Button */
        ScuttleButton.draw(game, this, gameData, ANT_EDITOR_WIDTH - settingsButton.getWidth() - 20, 20, settingsButton, 1);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    // need to change the skin so I am drawing a different font
    protected void drawScrollPane() {
        scrollButtons = new Array<Image>();
        scrollTable = new Table();
        scrollTable.setFillParent(true);
        stage.addActor(scrollTable);

        selectionContainer = new Table();
        selectionContainer.setOrigin(0, 0);

        switch (gameData.currPane) {
            case ai:
                if (ais.isEmpty()) {
                    Label name = new Label("No Created AIs!", skin);
                    name.setFontScale(2);
                    name.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.35f);
                    stage.addActor(name);
                    return;
                } else if (gameData.getCurrentAnt() == null) {
                    Label name = new Label("No Selected Ant!", skin);
                    name.setFontScale(2);
                    name.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.35f);
                    stage.addActor(name);
                    return;
                } else {
                    drawAIs();
                }
            break;

            case ant:
                if (ants.isEmpty()) {
                    Label name = new Label("No Created Ants!", skin);
                    name.setFontScale(2);
                    name.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.35f);
                    stage.addActor(name);
                    return;
                }
                drawAnts();
              
            break;

            case items:
                if (gameData.getCurrentAnt() == null) {
                    Label name = new Label("No Selected Ant!", skin);
                    name.setFontScale(2);
                    name.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.35f);
                    stage.addActor(name);
                    return;
                } else {
                    drawItems();
                }
            break;
        }

    
        selectionContainer.setTransform(false);
        scrollPane = new ScrollPane(selectionContainer, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);
        scrollTable.add(scrollPane).size(630, 400).fill();
        scrollTable.setPosition(20, 5);
    }

    private void drawAIs() {
        int rowSize = 0;
        for (int i = 0; i < ais.size(); i++) {
            rowSize++;
            final int index = i;

            Group g = new Group();
            g.setSize(100, 100);
            g.setTransform(false);
            
            Label name = new Label(ais.get(index).getName(), skin);
            name.setFontScale(2);
            name.setPosition(5, 105);

            final Image itemBtn = new Image(skin, "item");
            scrollButtons.add(itemBtn);
            itemBtn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    itemBtn.setDrawable(skin, "item-active");
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    itemBtn.setDrawable(skin, "item");
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ScuttleButton.playButtonPressSound(game);
                    gameData.getCurrentAnt().equipAI(ais.get(index));
                    return true;
                }
            });

            itemBtn.setFillParent(true);
            g.addActor(itemBtn);
            g.addActor(name);

            if (rowSize%4==0) {
                selectionContainer.add(g).padTop(50).size(100, 100).row();
            } else {
                selectionContainer.add(g).padRight(50).padTop(50).size(100,100);
            }
        }
    }

    private void drawAnts() {
        int rowSize = 0;
        for (int i = 0; i < ants.size(); i++) {
            rowSize++;
            final int index = i;

            Group g = new Group();
            g.setSize(100, 100);
            g.setTransform(false);
            
            Label name = new Label(ants.get(index).getName(), skin);
            name.setFontScale(2);
            name.setPosition(5, 105);

            final Image itemBtn = new Image(skin, "item");
            scrollButtons.add(itemBtn);
            itemBtn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    itemBtn.setDrawable(skin, "item-active");
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    itemBtn.setDrawable(skin, "item");
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ScuttleButton.playButtonPressSound(game);
                    gameData.setCurrentAnt(ants.get(index));
                    return true;
                }
            });

            itemBtn.setFillParent(true);
            g.addActor(itemBtn);
            g.addActor(name);

            if (rowSize%4==0) {
                selectionContainer.add(g).padTop(50).size(100, 100).row();
            } else {
                selectionContainer.add(g).padRight(50).padTop(50).size(100,100);
            }
        }
    }

    private void drawItems() {
        int rowSize = 0;
                    
        Label w = new Label("Weapons", skin);
        w.setFontScale(2);
        w.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.35f);
        stage.addActor(w);
        selectionContainer.add(w).row();

        for (int i = 0; i < weapons.size(); i++) {
            rowSize++;
            final int index = i;
            Weapon weapon = weapons.get(index);

            Group g = new Group();
            g.setSize(100, 100);
            g.setTransform(false);
            
            Label name = new Label(weapon.getName(), skin);
            name.setFontScale(2);
            name.setPosition(5, 105);

            final Image itemBtn = new Image(skin, "item");
            scrollButtons.add(itemBtn);
            itemBtn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    itemBtn.setDrawable(skin, "item-active");
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    itemBtn.setDrawable(skin, "item");
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ScuttleButton.playButtonPressSound(game);
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

            itemBtn.setFillParent(true);
            g.addActor(itemBtn);
            g.addActor(name);

            // new row if the last item or fourth item in the row
            if (rowSize%4==0 || i+1==weapons.size()) {
                selectionContainer.add(g).padTop(50).size(100, 100).row();
            } else {
                selectionContainer.add(g).padRight(50).padTop(50).size(100,100);
            }
        }


        Label a = new Label("Armor", skin);
        a.setFontScale(2);
        a.setPosition(ANT_EDITOR_WIDTH/1.25f-200, ANT_EDITOR_HEIGHT/1.5f);
        stage.addActor(a);
        selectionContainer.add(a).row();
        rowSize = 0;

        for (int i = 0; i < armors.size(); i++) {
            rowSize++;
            final int index = i;
            Armor armor = armors.get(index);

            Group g = new Group();
            g.setSize(100, 100);
            g.setTransform(false);
            
            Label name = new Label(armor.getName(), skin);
            name.setFontScale(2);
            name.setPosition(5, 105);

            final Image itemBtn = new Image(skin, "item");
            scrollButtons.add(itemBtn);
            itemBtn.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    itemBtn.setDrawable(skin, "item-active");
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    itemBtn.setDrawable(skin, "item");
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ScuttleButton.playButtonPressSound(game);
                    gameData.getCurrentAnt().equipArmor(armor);
                    return true;
                }
            });

            itemBtn.setFillParent(true);
            g.addActor(itemBtn);
            g.addActor(name);

            if (rowSize%4==0) {
                selectionContainer.add(g).padTop(50).size(100, 100).row();
            } else {
                selectionContainer.add(g).padRight(50).padTop(50).size(100,100);
            }
        }
    }

    @Override
    public String toString() {
        return "AntEditorScreen";
    }
}
