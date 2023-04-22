package com.Screens;

import com.antscuttle.game.AI.AI;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.AI.Node;
import com.antscuttle.game.AI.implementations.RootBlock;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.Buttons.PauseButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Level.Level;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.implementations.Door;
import com.antscuttle.game.LevelObject.implementations.PressurePlate;
import com.antscuttle.game.LevelObject.implementations.Projectile;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class GameplayScreen extends ScreenAdapter{
    public static final float SPEED = 100;
    private static final int START_BUTTON_Y = 200;

    SpriteBatch gameBatch;
    SpriteBatch characterBatch;
    SpriteBatch menuBatch;
    SpriteBatch titleBatch;
    SpriteBatch buttonBatch;
    SpriteBatch levelBatch;
    ScuttleButton back;
    ScuttleButton pause;
    ScuttleButton start;

    Texture titleImg;
    Texture menuImg;
    Texture img;

    float gameX;
    float gameY;
    float startX;

    private Viewport gameView;
    private OrthographicCamera camera;
    float stateTime = 0;

    AntScuttleGame game;
    GameData gameData;
    LevelData levelData;
    final Skin skin;
    Stage stage;
    
    private TiledMapRenderer renderer;
    private TiledMap map;
    private Level level;
    private Ant player;

    private boolean gameStarted;
    private Iterator blockIterator;
    private DecisionBlock currentBlock;
    private Map<Ant,DecisionBlock> enemyDBMap;
    private Map<Ant,Iterator> enemyIterMap;

    public GameplayScreen(AntScuttleGame game, GameData gameData){
        this.game = game;
        this.gameData = gameData;
        start = new StartButton();
        back = new BackButton();
        pause = new PauseButton();
        skin = game.skin;
        this.gameStarted = false;
        this.player = gameData.getCurrentAnt();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        
        
        gameView = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.setCamera(camera);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        
        
        gameBatch = new SpriteBatch();
        characterBatch = new SpriteBatch();
        titleBatch = new SpriteBatch();
        buttonBatch = new SpriteBatch();
        menuBatch = new SpriteBatch();
        levelBatch = new SpriteBatch();

        Pixmap treeMap = new Pixmap((int)gameView.getWorldWidth()-864, (int)gameView.getWorldHeight(),Format.RGBA8888);
        Color treeColor = new Color(0, 38/255f, 66/255f, 1);
        treeMap.setColor(treeColor);
        treeMap.fill();

        Pixmap playMap = new Pixmap(864, (int)gameView.getWorldHeight(),Format.RGBA8888);
        Color playColor = Color.BLACK;
        
        playMap.setColor(playColor);
        playMap.fill();

        camera.setToOrtho(false, Gdx.graphics.getWidth(), playMap.getHeight());
        camera.zoom = .5f;
        camera.translate(-Gdx.graphics.getWidth()/4, -Gdx.graphics.getHeight()/4);
	    camera.update();
        
        menuImg = new Texture(treeMap);
        titleImg = new Texture("antscuttle.png");
        img = new Texture(playMap);
        
        skin.add("start", new Texture("buttons/Start.png"));
        skin.add("start-active", new Texture("buttons/Start-Active.png"));
        final Image startBtn = new Image(skin, "start");
        startBtn.setBounds(gameX + (gameView.getWorldWidth() * 3/12), START_BUTTON_Y, 200, 100);
        stage.addActor(startBtn);

        ClassFactory cf = new ClassFactory();
        level = cf.newLevelInstance(gameData.getCurrentLevel().getClass());
        map = new TmxMapLoader().load(level.getTiledMap());
        renderer = new OrthogonalTiledMapRenderer(map,levelBatch);
        
        levelData = level.getLevelData();
        
        
        startBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                startBtn.setDrawable(skin, "start-active");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                startBtn.setDrawable(skin, "start");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScuttleButton.playButtonPressSound(game);
                if(player != null && player.getAI() != null){
                    Point startLoc = level.getPlayerStartLoc();
                    player.setPos(startLoc.x*32, startLoc.y*32);
                    gameStarted = true;
                    level.startLevel();
                    startBtn.setVisible(false);
                    initAI();
                    initEnemyAIs();
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        stateTime += Gdx.graphics.getDeltaTime();
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        
        
        renderer.setView(camera);
        renderer.render();
        
        
        menuBatch.begin();
        menuBatch.draw(menuImg, gameX + (864), gameY, gameView.getWorldWidth()-864,gameView.getWorldHeight());
        menuBatch.end();
        // Check for game over  
        if(levelData.isGameFinished()){
            player.reset();
            if(levelData.isGameWon()){
                Object unlockedItem =gameData.unlockRandomItem();
                Level unlockedLevel = gameData.unlockNewLevel();
                winGame(unlockedItem, unlockedLevel);
                //Display game won + items unlocked dialog, go to NewGameScreen
            } else {
                //Display game lost dialog, go to NewGameScreen
                endGame();
            }
        }
        if(!gameStarted){
            gameBatch.begin();
            gameBatch.draw(img, gameX, gameY, 864,gameView.getWorldHeight());
            gameBatch.end();
        
            titleBatch.begin();
            titleBatch.draw(titleImg, gameX + (gameView.getWorldWidth() * 1/12), gameY + (gameView.getWorldHeight() * 6/12), gameView.getWorldWidth() * 1/2, gameView.getWorldHeight()/3);
            titleBatch.end();
        }
        if(gameStarted){

            levelBatch.begin();
            level.render(levelBatch);
            for(LevelObject obj: levelData.getAllObjects()){
                obj.update(delta);
            }
            levelBatch.end();
            // Do block stuff
            doBlocks();
            
            characterBatch.begin();
            if(player.getHealth() < 1){
                levelData.setGameFinished(true);
                levelData.setGameWon(false);
            }
            player.update(delta);
            player.render(characterBatch);
            Set<Ant> enemies = levelData.getEnemies();
            for(Ant enemy: enemies){
                enemy.render(characterBatch);
                enemy.update(delta);
            }
            checkForCollisions(characterBatch);
            characterBatch.end();
            
            removeEnemies();
            
        }
        game.batch.begin();

        /* Back Button */
        ScuttleButton.draw(game, this, gameData, 20, gameView.getWorldHeight() - back.getHeight() - 20, back, 1);
        if(!gameStarted){
            /* Start Game Button */
            startX = gameX + (gameView.getWorldWidth() * 3/12);
            ScuttleButton.draw(game, this, gameData, startX, START_BUTTON_Y, start, 1);

        } else {
            ScuttleButton.draw(game, this, gameData, 20+pause.getWidth()/2, 20+pause.getWidth()/2, pause, 1);
        }
        game.batch.end();
        stage.draw();
        
    }

    private void removeEnemies(){
        Set<Ant> enemies = levelData.getEnemies();
        Set<Ant> enemiesToRemove = new HashSet<>();
        for(Ant enemy: enemies){
            if(enemy.getHealth()<1){
                enemiesToRemove.add(enemy);
            }
        }
        for(Ant enemy:enemiesToRemove){
            levelData.removeEnemy(enemy);
        }
    }
    public void endGame(){
        //game.setScreen(new MainMenuScreen(game, gameData, true));
        Dialog dialog = new Dialog("Game Over", skin);
        dialog.text("You lost. Press OK to go back to the Main Menu");
        dialog.button("OK").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenuScreen(game, gameData, true));
            }        
        });
        dialog.setBounds((stage.getWidth() / 2 - dialog.getWidth() / 2)-350, stage.getHeight() / 2 - dialog.getHeight() / 2, 400, 200);
        stage.addActor(dialog);  

    }
    public void winGame(Object unlockedItem, Object unlockedLevel){
        String unlockedItemString;
        String unlockedLevelString;
        if(!unlockedItem.toString().equals("false"))
            unlockedItemString = "\n\nItem: " + unlockedItem.toString();
        else    
            unlockedItemString = "\n\nItem: Sorry, there are no more items available";
        if(unlockedLevel != null)
            unlockedLevelString = "\n\nLevel: " + unlockedLevel.toString();
        else
            unlockedLevelString = "\n\nLevel: Sorry, there are no more levels available";
        //game.setScreen(new MainMenuScreen(game, gameData, true));
        Dialog dialog = new Dialog("\t\t\t\t\tYou Win!", skin);
        dialog.text("\tPress OK to go back to the Main Menu and\n\t\t\tcollect your rewards!" + unlockedItemString + unlockedLevelString);

        dialog.button("OK").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenuScreen(game, gameData, true));
            }        
        });
        dialog.setBounds((stage.getWidth() / 2 - dialog.getWidth() / 2)-350, stage.getHeight() / 2 - dialog.getHeight() / 2, 400, 200);
        stage.addActor(dialog);  

    }
    private void doBlocks(){
        if (currentBlock.isFinished()) {
            
            // Reset the finished status and move on
            currentBlock = (DecisionBlock)blockIterator.next();
        }
        // If at the end of the tree, restart the tree.
        if (currentBlock instanceof RootBlock)
            initAI();
        currentBlock.execute(gameData, levelData, player);
        
        
        Set<Ant> enemies = levelData.getEnemies();
        for(Ant enemy: enemies){
            Iterator iter = enemyIterMap.get(enemy);
            DecisionBlock db = enemyDBMap.get(enemy);
            if(db.isFinished()){
                db = (DecisionBlock)iter.next();
                enemyDBMap.put(enemy,db);
            }
            if(db instanceof RootBlock){
                enemy.getAI().resetAI();
                iter = enemy.getAI().iterator();
                enemyIterMap.put(enemy, iter);
                db = (DecisionBlock)iter.next();
                enemyDBMap.put(enemy, db);
            }
            db.execute(gameData, levelData, enemy);
        }
    }

    private void initAI(){
        player.getAI().resetAI();
        blockIterator = player.getAI().iterator();
        currentBlock = (DecisionBlock)blockIterator.next();
    }
    private void initEnemyAIs(){
        enemyDBMap = new HashMap<>();
        enemyIterMap = new HashMap<>();
        Set<Ant> enemies = levelData.getEnemies();
        for(Ant enemy: enemies){
            Iterator iter = enemy.getAI().iterator();
            enemyIterMap.put(enemy, iter);
            enemyDBMap.put(enemy, (DecisionBlock)iter.next());
        }
    }
    public void checkForCollisions(SpriteBatch batch){
        Set<LevelObject> ends = levelData.getEndSpaces();
        Set<LevelObject> hazards = levelData.getHazardousObjects();
        Set<Projectile> projectiles = levelData.getProjectiles();
        for(LevelObject o: ends){
            if(o.collides(player)){
                levelData.setGameFinished(true);
                levelData.setGameWon(true);
            }
        }
        for(LevelObject o: hazards){
            if(o.collides(player))
                ((InteractableLevelObject)o).interact(player, levelData);
        }
        for(Projectile p: projectiles){
            
            if(p.collides(player)){
                p.attack(player, levelData);
                p.setToDispose();
            }
            for(LevelObject o: levelData.getCollidableObjects()){
                if(p.collides(o)){
                   if(o instanceof InteractableLevelObject)
                       p.attack(o, levelData);
                   p.setToDispose();
                }
            }
            Set<Ant> enemies = levelData.getEnemies();
            for(Ant ant: enemies){
                if(p.collides(ant)){
                    p.attack(ant, levelData);
                    p.setToDispose();
                    
                }
                
            }
        }
        Set<Projectile> done = new HashSet<>();
        for(Projectile p: levelData.getProjectiles()){
            if(p.shouldDispose())
                done.add(p);
        }
        for(Projectile p: done){
            
            p.dispose(levelData);
        }
        
    }
    @Override
    public void resize(int width, int height) {
        gameView.update(width, height);
    }

    @Override 
    public String toString() {
        return "GameplayScreen";
    }
    
}
