package com.Screens;
import java.util.LinkedList;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.AttackBlock;
import com.antscuttle.game.AI.implementations.InteractBlock;
import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.TextInputListener;

public class AIEditorScreen extends ScreenAdapter{
	public static final float SPEED = 100;
    public static final float MENU_MOVE_X = Gdx.graphics.getWidth() * 9/12;
    public static final float MENU_MOVE_Y = Gdx.graphics.getHeight() * 8/12;
    public static final float TREE_POS_1X = Gdx.graphics.getWidth() * 1/12;
    public static final float TREE_POS_1Y = Gdx.graphics.getHeight() * 7/12;
    public static final float MENU_ATTACK_X = Gdx.graphics.getWidth() * 9/12;
    public static final float MENU_ATTACK_Y = Gdx.graphics.getHeight() * 6/12;
    public static final float TREE_POS_2X = Gdx.graphics.getWidth() * 5/12;
    public static final float TREE_POS_2Y = Gdx.graphics.getHeight() * 7/12;
    public static final float TREE_ROOT_X = Gdx.graphics.getWidth() * 3/12;
    public static final float TREE_ROOT_Y = Gdx.graphics.getHeight() * 9/12;

    private static int MAIN_MENU_HEIGHT= Gdx.graphics.getHeight();;
    SpriteBatch gameBatch;
    SpriteBatch menuBatch;
    Texture menuImg;
    Texture img;
    float gameX;
    float gameY;
    float startX;
    private Viewport gameView;
    private Camera camera;
    float stateTime = 0;
    Stage stage;
    private Button backButton;
    private Node rNode;
    public AI ai;
    public final RootBlock rBlock;
    public MoveBlock mBlock;
    public AttackBlock aBlock;
    public final InteractBlock iBlock;
    public LinkedList<DecisionBlock> moveList;
    AntScuttleGame game;
    GameData gameData;

    public AIEditorScreen(AntScuttleGame game, GameData gameData){
        backButton = new BackButton();
        this.moveList = new LinkedList<>();
        this.game = game;
        this.gameData = gameData;
        this.rBlock = new RootBlock();
        this.mBlock = new MoveBlock(null, 0);
        this.aBlock = new AttackBlock(null, null);
        this.iBlock = new InteractBlock(null);
        rNode = new Node(rBlock);
        System.out.println(gameData.getAllAIs());
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        gameView = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.setCamera(camera);
        gameBatch = new SpriteBatch();
        menuBatch = new SpriteBatch();
        Pixmap treeMap = new Pixmap((int)gameView.getWorldWidth() * 1/3, (int)gameView.getWorldHeight(),Format.RGBA8888);
        Color treeColor = new Color(0, 38/255f, 66/255f, 1);
        treeMap.setColor(treeColor);
        treeMap.fill();
        Pixmap playMap = new Pixmap((int)gameView.getWorldWidth() * 2/3, (int)gameView.getWorldHeight(),Format.RGBA8888);
        Color playColor = Color.BLACK;
        playMap.setColor(playColor);
        playMap.fill();
        menuImg = new Texture(treeMap);
        img = new Texture(playMap);

        stage = new Stage();
		Gdx.input.setInputProcessor(stage);


        

		final Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        // Register the style with the skin
		skin.add("move", new Texture("buttons/Move.png"));
		skin.add("attack", new Texture("buttons/Attack.png"));
        skin.add("root", new Texture("buttons/Root.png"));
        skin.add("save", new Texture("buttons/Save-AI.png"));

        final Image saveImage = new Image(skin, "save");
        saveImage.setBounds((Gdx.graphics.getWidth() * 2/3)-150, 0, 150, 100);
        stage.addActor(saveImage);

        // Define active and inactive colors
        final Color inactiveColor = new Color(1f, 1f, 1f, 1f); // fully opaque white
        final Color activeColor = new Color(1f, 1f, 1f, 0.5f); // semi-transparent white
        
        final TextInputListener listener = new TextInputListener() {
            @Override
            public void input(String text) {
                // The text argument is the user input from the dialog box
                ai = new AI(rNode, text);
                gameData.addAI(ai);
                game.setScreen(new NewGameScreen(game, gameData));
            }

            @Override
            public void canceled() {
                // The user has canceled the input dialog
                Dialog dialog = new Dialog("Input Canceled", skin);
                dialog.text("You have canceled the input dialog.");
                dialog.button("OK");
                dialog.show(stage);
            }
        };
        // Add input listener to the saveImage actor
        saveImage.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Highlight the saveImage actor on mouse enter
                saveImage.setColor(activeColor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Unhighlight the saveImage actor on mouse exit
                saveImage.setColor(inactiveColor);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                long id = game.sfx.play(game.VOLUME);
                if(!moveList.isEmpty()){
                    for(DecisionBlock db:  moveList){
                        rNode.addChild(new Node(db));
                    }
                    Dialog dialog = new Dialog("Enter AI name", skin);
                    final TextField inputField = new TextField("", skin);
                    TextButton saveButton = new TextButton("Save", skin);
                    saveButton.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                            String aiName = inputField.getText();
                            ai = new AI(rNode, aiName);
                            gameData.addAI(ai);
                            game.setScreen(new NewGameScreen(game, gameData));
                            return true;
                        }
                    });
                    dialog.getContentTable().add(inputField);
                    dialog.button(saveButton);
                    dialog.show(stage);
                   
                    // inputField.addListener(new ChangeListener() {
                    //     @Override
                    //     public void changed(ChangeEvent event, Actor actor) {
                    //         inputText[0] = inputField.getText(); // update the current text variable
                    //     }
                    // });
                    // dialog.getContentTable().add(inputField).pad(10);
                    // dialog.button("Save", new Runnable() {
                    //     @Override
                    //     public void run() {
                    //         String aiName = inputText[0]; // get the current value of inputText
                    //         ai = new AI(rNode, aiName);
                    //         gameData.addAI(ai);
                    //         game.setScreen(new NewGameScreen(game, gameData));
                    //     }
                    // }).button("Cancel", false).key(Input.Keys.ENTER, true).key(Input.Keys.ESCAPE, false);                    
                    // dialog.show(stage);
                    
                    
                   // Gdx.input.getTextInput(listener, "Enter AI Name", "placeholder", "hi",Input.OnscreenKeyboardType.Password);
                    
                } else {
                    // Show a dialog box to inform the user that they need to create an AI before saving.
                    Dialog dialog = new Dialog("Create AI", skin);
                    dialog.text("Please create an AI before saving.");
                    dialog.button("OK");
                    dialog.show(stage);
                }
                return true;
            }
        });

		final Image moveImage = new Image(skin, "move");
		moveImage.setBounds(MENU_MOVE_X, MENU_MOVE_Y, 150, 100);
		stage.addActor(moveImage);


		final Image attackImage = new Image(skin, "attack");
		attackImage.setBounds(MENU_ATTACK_X, MENU_ATTACK_Y, 150, 100);
		stage.addActor(attackImage);

		final Image rootImage = new Image(skin, "root");
		rootImage.setBounds(TREE_ROOT_X, TREE_ROOT_Y, 150, 100);
		stage.addActor(rootImage);

		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(moveImage) {
			@Override
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("move");

				payload.setDragActor(getActor());

				Label validLabel = new Label("", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				return payload;
			}
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target){
                if(target == null){
                    moveImage.setBounds(MENU_MOVE_X, MENU_MOVE_Y, 150, 100);
                    if(moveList.contains(mBlock))
                        moveList.remove(mBlock);
                }
                else{
                    Vector2 moveImagePos = moveImage.localToStageCoordinates(new Vector2(0, 0));
                    if((moveImagePos.x == TREE_POS_1X || moveImagePos.x == TREE_POS_2X)  && (moveImagePos.y == TREE_POS_1Y || moveImagePos.y == TREE_POS_2Y)){
                        final SelectBox<String> selectBox;
                        // Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
                        selectBox = new SelectBox<String>(skin);
                        selectBox.setName("Direction");
                        selectBox.setItems("Left", "Right", "Up", "Down");
                        selectBox.setWidth(100f);
                        selectBox.setHeight(50f);
                        stage.addActor(selectBox);
                        selectBox.setVisible(true);
        
                        float moveImageX = moveImagePos.x;
                        float moveImageY = moveImagePos.y;
                        selectBox.setPosition(moveImageX, moveImageY);
                        selectBox.toFront();
            
                        selectBox.addListener(new ChangeListener() {
                            public void changed(ChangeEvent event, Actor actor) {
                                String selected = selectBox.getSelected();
                                System.out.println(selected);
                                mBlock = new MoveBlock(MoveBlock.MoveDirection.valueOf(selected.toUpperCase()), 10);
                                moveList.add(mBlock);
                                selectBox.setVisible(false);
                            }
                        });
                    }
                }    
            }
		});
        dragAndDrop.addSource(new Source(attackImage) {
			@Override
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("attack");

				payload.setDragActor(getActor());

				Label validLabel = new Label("", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				return payload;
			}
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target){
                if(target == null){
                    attackImage.setBounds(MENU_ATTACK_X, MENU_ATTACK_Y, 150, 100);
                    if(moveList.contains(aBlock))
                        moveList.remove(aBlock);
                }
                else{
                    Vector2 atckImagePos = attackImage.localToStageCoordinates(new Vector2(0, 0));
                    if((atckImagePos.x == TREE_POS_1X || atckImagePos.x == TREE_POS_2X)  && (atckImagePos.y == TREE_POS_1Y || atckImagePos.y == TREE_POS_2Y)){
                        final SelectBox<String> targetBox = new SelectBox<String>(skin);
                        // Skin skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
                        targetBox.setItems("Obstacle", "Ant");
                        targetBox.setWidth(100f);
                        targetBox.setHeight(50f);
                        stage.addActor(targetBox);
                            
                        targetBox.setVisible(true);
                        float atckImageX = atckImagePos.x;
                        float atckImageY = atckImagePos.y;
                        targetBox.setPosition(atckImageX, atckImageY);
                        targetBox.toFront();
    
                        targetBox.addListener(new ChangeListener() {
                            final SelectBox<String> attackBox = new SelectBox<String>(skin);
                            public void changed(ChangeEvent event, Actor actor) {
                                final String selected = targetBox.getSelected();
                                targetBox.setVisible(false);
                                attackBox.setItems("Melee", "Ranged");
                                attackBox.setWidth(100f);
                                attackBox.setHeight(50f);
                                stage.addActor(attackBox);
                                
                                attackBox.setVisible(true);
                                Vector2 atckImagePos = attackImage.localToStageCoordinates(new Vector2(0, 0));
                                float atckImageX = atckImagePos.x;
                                float atckImageY = atckImagePos.y;
                                attackBox.setPosition(atckImageX, atckImageY);
                                attackBox.toFront();
                                attackBox.addListener(new ChangeListener(){
                                    public void changed(ChangeEvent event, Actor actor){
                                        targetBox.remove();
                                        String attackString = attackBox.getSelected();
                                        aBlock = new AttackBlock(AttackBlock.ObjectType.valueOf(selected.toUpperCase()),AttackBlock.AttackType.valueOf(attackString.toUpperCase()));
                                        moveList.add(aBlock);
                                        attackBox.setVisible(false);
                                    }
                                });
                            }
                        }); 
                    }        
                }
            }
		});
		dragAndDrop.addTarget(new Target(rootImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
                Vector2 atckImagePos = attackImage.localToStageCoordinates(new Vector2(0, 0));
                Vector2 moveImagePos = moveImage.localToStageCoordinates(new Vector2(0, 0));

                if (payload.getObject().equals("move")) {
                    if(atckImagePos.x == MENU_ATTACK_X && atckImagePos.y == MENU_ATTACK_Y)
                        moveImage.setBounds(TREE_POS_1X, TREE_POS_1Y, 150, 100);
                    else
                        moveImage.setBounds(TREE_POS_2X, TREE_POS_2Y, 150, 100);
                } else if (payload.getObject().equals("attack")) {
                    if(moveImagePos.x == MENU_MOVE_X && moveImagePos.y == MENU_MOVE_Y)
                        attackImage.setBounds(TREE_POS_1X, TREE_POS_1Y, 150, 100);
                    else
                        attackImage.setBounds(TREE_POS_2X, TREE_POS_2Y, 150, 100);
                }
                
			}
		});
        
        // TODO Auto-generated method stub
        
    }
    /**
     * Draw the Button
     * @param x
     * @param y
     * @param button type of button
     */
    private void drawButton(int x, int y, Button button) {
        game.batch.begin();
        int w = button.getWidth();
        int h = button.getHeight();

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            MAIN_MENU_HEIGHT - Gdx.input.getY() < y + h && MAIN_MENU_HEIGHT - Gdx.input.getY() > y) {

            game.batch.draw(button.active(), x, y, w, h);

            if (button.getButtonType() == "back" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new NewGameScreen(game, gameData));
            }
        }else {
            game.batch.draw(button.inactive(), x, y, w, h);
        }
        game.batch.end();
    }
    @Override
    public void render(float delta) {
        

        
        /* Back Button */
        drawButton(20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton);

        menuBatch.begin();
        menuBatch.draw(menuImg, gameX + (gameView.getWorldWidth() * 2/3), gameY, gameView.getWorldWidth() * 1/3,gameView.getWorldHeight());
        menuBatch.end();

        gameBatch.begin();
        gameBatch.draw(img, gameX, gameY, gameView.getWorldWidth()*2/3,gameView.getWorldHeight());
        gameBatch.end();
        drawButton(20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton);
        // a stage has its own batch so don't put it within batch.begin() and batch.end()
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); //you are likely missing THIS LINE :D
        stage.draw();
        
    }
    @Override
    public void resize(int width, int height) {
        gameView.update(width, height);
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void dispose() {
        stage.dispose();
        // TODO Auto-generated method stub
        
    }
    
}
