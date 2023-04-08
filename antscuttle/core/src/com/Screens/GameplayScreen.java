package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.Buttons.PauseButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Level.Level;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

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

	float charY;
	float charX;
    float gameX;
    float gameY;
    float startX;

    private Viewport gameView;
    private OrthographicCamera camera;
    float stateTime = 0;

    AntScuttleGame game;
    GameData gameData;
    final Skin skin;
    Stage stage;
    
    private TiledMapRenderer renderer;
    private TiledMap map;
    private Level level;
    private AssetManager assetManager;

    private boolean gameStarted;
    
    public GameplayScreen(AntScuttleGame game, GameData gameData){
        this.game = game;
        this.gameData = gameData;
        start = new StartButton();
        back = new BackButton();
        pause = new PauseButton();
        skin = game.skin;
        this.gameStarted = false;
        assetManager = new AssetManager();
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

                //LinkedList<Node> childs = gameData.getCurrentAnt().getAI().getRoot().getChildren();
                //childs.removeFirst().getBlock().execute(gameData, null);
                
                
                gameStarted = true;
                level.startLevel();
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
            levelBatch.end();
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
