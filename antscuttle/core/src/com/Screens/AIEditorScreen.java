package com.Screens;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.AttackBlock;
import com.antscuttle.game.AI.implementations.InteractBlock;
import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.AI.options.AttackOptions;
import com.antscuttle.game.AI.options.MoveOptions;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;

public class AIEditorScreen extends ScreenAdapter{
	public static final float SPEED = 100;
    public static final float MENU_MOVE_X = Gdx.graphics.getWidth() * 9/12;
    public static final float MENU_MOVE_Y = Gdx.graphics.getHeight() * 8/12;
    public static final float MENU_ATTACK_X = Gdx.graphics.getWidth() * 9/12;
    public static final float MENU_ATTACK_Y = Gdx.graphics.getHeight() * 6/12;
    public static final float MENU_INTERACT_X = Gdx.graphics.getWidth() * 9/12;
    public static final float MENU_INTERACT_Y = Gdx.graphics.getHeight() * 4/12;
    public static final float TREE_ROOT_X = Gdx.graphics.getWidth() *1/3;
    public static final float TREE_ROOT_Y = Gdx.graphics.getHeight() -60;
    public float targetNodeX;
    public float targetNodeY;
    public int dropped;
    public String aiName;
    public Actor target;
    public HashMap<Node, Vector2> nodeMap;
    public Vector2 parentPosition;
    public Vector2 currentPosition;
    public boolean isLeftNode = true;
    // Define active and inactive colors
    final Color INACTIVE = new Color(1f, 1f, 1f, 1f); // fully opaque white
    final Color ACTIVE = new Color(1f, 1f, 1f, 0.5f); // semi-transparent white

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
    DragAndDrop dragAndDrop;
    final Skin skin;
    public final Image moveImage;
    public final Image attackImage;
    public final Image rootImage;
    public final Image saveImage;

    public AIEditorScreen(AntScuttleGame game, GameData gameData){
        stage = new Stage();
        backButton = new BackButton();
        this.moveList = new LinkedList<>();
        this.game = game;
        this.gameData = gameData;
        this.rBlock = new RootBlock();
        this.mBlock = new MoveBlock(new MoveOptions(null));
        this.aBlock = new AttackBlock(new AttackOptions(null, null));
        this.iBlock = new InteractBlock(null);
        System.out.println(gameData.getAllAIs());
        dragAndDrop = new DragAndDrop();
        skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        skin.add("move", new Texture("buttons/ai-editor/Move.png"));
		skin.add("attack", new Texture("buttons/ai-editor/Attack.png"));
        skin.add("root", new Texture("buttons/ai-editor/Root.png"));
        skin.add("save", new Texture("buttons/ai-editor/Save-AI.png"));
        saveImage = new Image(skin, "save");
        saveImage.setBounds((Gdx.graphics.getWidth() * 2/3)-150, 0, 100, 50);
        moveImage = new Image(skin, "move");
		moveImage.setBounds(MENU_MOVE_X, MENU_MOVE_Y, 100, 50);
		attackImage = new Image(skin, "attack");
		attackImage.setBounds(MENU_ATTACK_X, MENU_ATTACK_Y, 100, 50);
		rootImage = new Image(skin, "root");
		rootImage.setBounds(TREE_ROOT_X, TREE_ROOT_Y, 100, 50);
        this.targetNodeX = TREE_ROOT_X -150;
        this.targetNodeY = TREE_ROOT_Y -150;
        this.dropped = 0;
        rNode = new Node(rBlock, rootImage);
        nodeMap = new HashMap<>();
        parentPosition = new Vector2(TREE_ROOT_X, TREE_ROOT_Y);
        nodeMap.put(rNode,parentPosition);
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
		Gdx.input.setInputProcessor(stage);
        addNewSource(moveImage);
        addNewSource(attackImage);
        addNewTarget(rootImage);      
        stage.addActor(saveImage);

        //Create new dialog for AI name
        Dialog dialog = new Dialog("Enter AI name", skin);
        final TextField inputField = new TextField("", skin);
        TextButton saveButton = new TextButton("Save", skin);
        //Add listener so that on AI name save, the correct data is added to gameData, and the user moves back to the new game screen
        saveButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                String aiName = inputField.getText();
                ai = new AI(rNode, aiName);
                return true;
            }
        });
        dialog.getContentTable().add(inputField);
        dialog.button(saveButton);
        dialog.show(stage);

        // Add input listener to the saveImage actor
        saveImage.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                // Highlight the saveImage actor on mouse enter
                saveImage.setColor(INACTIVE);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                // Unhighlight the saveImage actor on mouse exit
                saveImage.setColor(ACTIVE);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                long id = game.sfx.play(game.VOLUME);
                if(rNode.hasChildren()){
                    gameData.addAI(ai);
                    game.setScreen(new NewGameScreen(game, gameData));
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
    }

    private void addNewTarget(Image image){
        final Image newImage = image;
        stage.addActor(newImage);
		dragAndDrop.addTarget(new Target(newImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
                parentPosition = getActor().localToStageCoordinates(new Vector2(0,0));
                getNextPosition(parentPosition);
                // iterate over the HashMap to find node to add to
                Node parentNode = null;
                for (Map.Entry<Node, Vector2> entry : nodeMap.entrySet()) {
                    Node key = entry.getKey();
                    Vector2 value = entry.getValue();
                    System.out.println(key + " is at position x= " + value.x +", y= " + value.y);
                    if(value.equals(parentPosition)){
                        parentNode = key;
                        break;
                    }
                }
                if (payload.getObject().toString() == "move") {
                    Node temp = new Node(mBlock, moveImage);
                    payload.getDragActor().setPosition(currentPosition.x, currentPosition.y);
                    if(isLeftNode){
                        parentNode.addChildAt(0,temp);
                    }else{
                        parentNode.addChildAt(1,temp);
                    }
                    nodeMap.put(temp, currentPosition);
                  //  getNextPosition(parentPosition);
                    addNewSource(moveImage);
                } else if (payload.getObject().toString() == "attack") {
                    payload.getDragActor().setPosition(currentPosition.x, currentPosition.y);
                    Node temp = new Node(aBlock, attackImage);
                    if(isLeftNode){
                        parentNode.addChildAt(0,temp);
                    }else{
                        parentNode.addChildAt(1,temp);
                    }
                    nodeMap.put(temp, currentPosition);
                    // getNextPosition(parentPosition);
                    addNewSource(attackImage);
                }else{

                }
                
                //update the ai
                ai = new AI(rNode, aiName);
       
			}});
    }

    public Vector2 getNextPosition(Vector2 vector){ 
        //If vector isn't in map, set vector to that parent
        boolean vectorExists = false;
        for (Map.Entry<Node, Vector2> entry : nodeMap.entrySet()) {
            Node key = entry.getKey();
            Vector2 value = entry.getValue();
            System.out.println(key + " is at position x= " + value.x +", y= " + value.y);
            if(value.equals(vector)){
                vectorExists = true;
                break;
            }
        }
        //Child/parent variables
        Vector2 leftChild;   
        Vector2 rightChild;
        Vector2 parentFromLeftChild;
        Vector2 parentFromRightChild;
        //Next position variables
        float nextX;
        float nextY;
        int level = 1;
        for(float i = vector.y; i < TREE_ROOT_Y; i += 175){
            level++;
        }
        if(level == 1){
            //Child positions
            leftChild = new Vector2(vector.x - 200,vector.y -175);
            rightChild = new Vector2(vector.x + 200,vector.y -175);    
            //Parent position
            parentFromLeftChild = new Vector2(vector.x + 200,vector.y +175);
            parentFromRightChild = new Vector2(vector.x - 200,vector.y +175);
        }else if (level == 2){
            //Child positions
             leftChild = new Vector2(vector.x - 100,vector.y -175);
             rightChild = new Vector2(vector.x + 100,vector.y -175);    
            //Parent position
             parentFromLeftChild = new Vector2(vector.x + 100,vector.y +175);
             parentFromRightChild = new Vector2(vector.x - 100,vector.y +175);
        }else if(level == 3 ){
            //Child positions
             leftChild = new Vector2(vector.x - 50,vector.y -175);
             rightChild = new Vector2(vector.x + 50,vector.y -175);    
            //Parent position
             parentFromLeftChild = new Vector2(vector.x + 50,vector.y +175);
             parentFromRightChild = new Vector2(vector.x - 50,vector.y +175);
        }else{
            // Show a dialog box to inform the user that they can only create that many nodes
            Dialog dialog = new Dialog("Error", skin);
            dialog.text("You have reached the maximum number of nodes.");
            dialog.button("OK");
            dialog.show(stage);
            return null;
        }

        if(!vectorExists){
            if(nodeMap.containsValue(parentFromLeftChild)){
                vector = parentFromLeftChild;
            }else{
                vector = parentFromRightChild;
            }
        }

        if(nodeMap.containsValue(leftChild) && nodeMap.containsValue(rightChild)){
            Dialog dialog = new Dialog("Error", skin);
            dialog.text("This node is full, please proceed to the next available node.");
            dialog.button("OK");
            dialog.show(stage);
            return currentPosition;
        }

        if(!nodeMap.containsValue(leftChild)){
            nextX = leftChild.x;
            nextY = leftChild.y;
            isLeftNode = true;
        }else{
            nextX = rightChild.x;
            nextY = rightChild.y;
            isLeftNode = false;
        }
        

        //Set the current position
        currentPosition = new Vector2(nextX, nextY);        
        //TODO Shrink nodes as more get added (after node addition/deletion works)

        //Return Coordinates
        return currentPosition;
    }
    

    private void addNewSource(Image image) {
        final SelectBox<String> selectBox;
        final String imageName = image.getDrawable().toString();
        final Image newImage = new Image(skin, imageName);
        if(imageName.equals("move")){
            newImage.setBounds(MENU_MOVE_X, MENU_MOVE_Y, 100, 50);
            stage.addActor(newImage);
            selectBox = new SelectBox<String>(skin);
            selectBox.setName("Target");
            selectBox.setItems(mBlock.getAllOptionOnes());
            selectBox.setWidth(100f);
            selectBox.setHeight(50f);
            stage.addActor(selectBox);
            selectBox.setVisible(true);
            selectBox.setPosition(MENU_MOVE_X, MENU_MOVE_Y);
            selectBox.toFront();

            selectBox.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    String selected = selectBox.getSelected();
                    System.out.println(selected);
                    mBlock = new MoveBlock(new MoveOptions(selected));
                   // moveList.add(mBlock);
                    selectBox.setVisible(false);
                }
            });
        }else{
            newImage.setBounds(MENU_ATTACK_X, MENU_ATTACK_Y, 100, 50);
            stage.addActor(newImage);
            selectBox = new SelectBox<String>(skin);
            selectBox.setItems(aBlock.getAllOptionOnes());
            selectBox.setWidth(100f);
            selectBox.setHeight(50f);
            stage.addActor(selectBox);
                
            selectBox.setVisible(true);
            selectBox.setPosition(MENU_ATTACK_X, MENU_ATTACK_Y);
            selectBox.toFront();

            selectBox.addListener(new ChangeListener() {
                final SelectBox<String> attackBox = new SelectBox<String>(skin);
                public void changed(ChangeEvent event, Actor actor) {
                    final String selected = selectBox.getSelected();
                    selectBox.setVisible(false);
                    attackBox.setItems(aBlock.getAllOptionTwos());
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
                            selectBox.remove();
                            String attackString = attackBox.getSelected();
                            aBlock = new AttackBlock(new AttackOptions(selected,attackString));
                            // moveList.add(aBlock);
                            attackBox.setVisible(false);
                        }
                    });
                }
            }); 
        }
       
        dragAndDrop.addSource(new Source(newImage) {
            boolean newMove;
            boolean newAttack;
            boolean newInteraction;
            Vector2 coordinates = newImage.localToStageCoordinates(new Vector2(0,0));

            @Override
            public Payload dragStart (InputEvent event, float x, float y, int pointer) {
                if(selectBox.isVisible()){
                    Dialog dialog = new Dialog("Select", skin);
                    dialog.text("Please select an option first.");
                    dialog.button("OK");
                    dialog.show(stage);
                }
                Payload payload = new Payload();
                payload.setObject(newImage.getDrawable());

                payload.setDragActor(getActor());
                coordinates = new Vector2(getActor().getX(), getActor().getY());
                Label validLabel = new Label("", skin);
                validLabel.setColor(0, 1, 0, 1);
                payload.setValidDragActor(validLabel);
                newMove = ((coordinates.x == MENU_MOVE_X) && (coordinates.y == MENU_MOVE_Y)) ? true : false;
                newAttack = ((coordinates.x == MENU_ATTACK_X) && (coordinates.y == MENU_ATTACK_Y)) ? true : false;
                return payload;
            }
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
                if (target == null) {
                    if (newMove || newAttack) {
                        newImage.remove();
                        addNewSource(newImage);
                        return;
                    }
                    newImage.remove();
                    Node nodeToRemove = null;
                    for (Map.Entry<Node, Vector2> entry : nodeMap.entrySet()) {
                        Node key = entry.getKey();
                        Vector2 value = entry.getValue();
                        if (value.equals(coordinates)) {
                            nodeToRemove = key;
                            break;
                        }
                    }
                    if (nodeToRemove != null) {
                        LinkedList<Node> children = nodeToRemove.getChildren();
                        removeChildrenRecursive(children);
                        nodeMap.remove(nodeToRemove);
                        rNode.removeChild(nodeToRemove);
                        ai = new AI(rNode, aiName);
                    }
                } else {
                    try {
                        addNewTarget(newImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
            private void removeChildrenRecursive(LinkedList<Node> children) {
                LinkedList<Node> kids = new LinkedList<>();
                for(Node node:children){
                    kids.add(node);
                }
                for (Node node : kids) {
                    Actor actor = stage.hit(nodeMap.get(node).x, nodeMap.get(node).y, true);
                    nodeMap.remove(node);
                    rNode.removeChild(node);
                    actor.remove();
                    removeChildrenRecursive(node.getChildren());
                }
            }
            
        });
    }

    @Override
    public void render(float delta) {        
        /* Back Button */
        
        menuBatch.begin();
        menuBatch.draw(menuImg, gameX + (gameView.getWorldWidth() * 2/3), gameY, gameView.getWorldWidth() * 1/3,gameView.getWorldHeight());
        menuBatch.end();
        
        gameBatch.begin();
        gameBatch.draw(img, gameX, gameY, gameView.getWorldWidth()*2/3,gameView.getWorldHeight());
        gameBatch.end();

        game.batch.begin();
        Button.draw(game, this, gameData, 20, MAIN_MENU_HEIGHT - backButton.getHeight() - 20, backButton, 1);
        game.batch.end();
        
        // a stage has its own batch so don't put it within batch.begin() and batch.end()
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); //you are likely missing THIS LINE :D
        stage.draw();
        
    }
    @Override
    public void resize(int width, int height) {
        gameView.update(width, height);        
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
    @Override
    public String toString() {
        return "AIEditorScreen";
    }
}
