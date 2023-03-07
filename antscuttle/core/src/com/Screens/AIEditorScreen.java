package com.Screens;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.AttackButton;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.MoveButton;
import com.antscuttle.game.Buttons.PauseButton;
import com.antscuttle.game.Buttons.RootButton;
import com.antscuttle.game.Buttons.StartButton;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;
import com.antscuttle.game.Util.GameData;

public class AIEditorScreen extends ScreenAdapter{
	public static final float SPEED = 100;
    private static final int START_BUTTON_Y = 200;
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
    DragAndDrop dnd;
    Stage stage;
    // Create an array to hold the individual frames


    AntScuttleGame game;
    GameData gameData;

    public AIEditorScreen(AntScuttleGame game, GameData gameData){
        this.game = game;
        this.gameData = gameData;
        this.dnd = new DragAndDrop();
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

		final Skin skin = new Skin();
        skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("move", new Texture("buttons/Move.png"));
		skin.add("attack", new Texture("buttons/Attack.png"));
        skin.add("root", new Texture("buttons/Root.png"));

        

		final Image moveImage = new Image(skin, "move");
		moveImage.setBounds(gameView.getWorldWidth() * 10/12, gameView.getWorldHeight() * 8/12, 150, 100);
		stage.addActor(moveImage);

		final Image attackImage = new Image(skin, "attack");
		attackImage.setBounds(gameView.getWorldWidth() * 10/12, gameView.getWorldHeight() * 6/12, 150, 100);
		stage.addActor(attackImage);

		final Image validTargetImage = new Image(skin, "root");
		validTargetImage.setBounds(gameView.getWorldWidth() * 3/12, gameView.getWorldHeight() * 9/12, 150, 100);
		stage.addActor(validTargetImage);

		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(moveImage) {
			@Override
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("move");

				payload.setDragActor(getActor());

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				return payload;
			}
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target){
                if(target == null)
                    moveImage.setBounds(gameView.getWorldWidth() * 10/12, gameView.getWorldHeight() * 8/12, 150, 100);
            }
		});
        dragAndDrop.addSource(new Source(attackImage) {
			@Override
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setObject("attack");

				payload.setDragActor(getActor());

				Label validLabel = new Label("Some payload!", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				return payload;
			}
            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target){
                if(target == null)
                    attackImage.setBounds(gameView.getWorldWidth() * 10/12, gameView.getWorldHeight() * 6/12, 150, 100);
            }
		});
		dragAndDrop.addTarget(new Target(validTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
                if (payload.getObject().equals("move")) {
                    moveImage.setBounds(gameView.getWorldWidth() * 1/12, gameView.getWorldHeight() * 7/12, 150, 100);
                } else if (payload.getObject().equals("attack")) {
                    attackImage.setBounds(gameView.getWorldWidth() * 5/12, gameView.getWorldHeight() * 7/12, 150, 100);

                }
                
			}
		});
        
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        menuBatch.begin();
        menuBatch.draw(menuImg, gameX + (gameView.getWorldWidth() * 2/3), gameY, gameView.getWorldWidth() * 1/3,gameView.getWorldHeight());
        menuBatch.end();

        gameBatch.begin();
        gameBatch.draw(img, gameX, gameY, gameView.getWorldWidth()*2/3,gameView.getWorldHeight());
        gameBatch.end();

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
        // TODO Auto-generated method stub
        
    }
    
}
