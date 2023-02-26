package com.Screens;
import com.antscuttle.game.AntScuttleGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.ScreenAdapter;

public class GameplayScreen extends ScreenAdapter{
	public static final float SPEED = 100;

    SpriteBatch gameBatch;
    SpriteBatch characterBatch;
    SpriteBatch menuBatch;
    SpriteBatch titleBatch;
    Texture titleImg;
    Texture menuImg;
    Texture img;
	float charY;
	float charX;
    float gameX;
    float gameY;
    private Viewport gameView;
    private Camera camera;
    float stateTime = 0;
    

    // Create an array to hold the individual frames


    AntScuttleGame game;

    public GameplayScreen(AntScuttleGame game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        gameView = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.setCamera(camera);
        gameBatch = new SpriteBatch();
        characterBatch = new SpriteBatch();
        titleBatch = new SpriteBatch();
        menuBatch = new SpriteBatch();
        Pixmap pixmap = new Pixmap((int)gameView.getWorldWidth() * 1/3, (int)gameView.getWorldHeight(),Format.RGBA8888);
        Color color = new Color(0, 38/255f, 66/255f, 1);
        pixmap.setColor(color);
        pixmap.fill();
        menuImg = new Texture(pixmap);
        titleImg = new Texture("antscuttle.png");
        img = new Texture("pixilart-drawing.png");
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {

        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = new TextureRegion(new Texture("animations/zombie/zombie_walk_right.png"));
        frames[1] = new TextureRegion(new Texture("animations/zombie/zombie_walk_left.png"));
        frames[2] = new TextureRegion(new Texture("animations/zombie/zombie_walk_down.png"));
        frames[3] = new TextureRegion(new Texture("animations/zombie/zombie_walk_up.png"));

        // Create an animation from the frames with a frame rate of 0.1f
        Animation<TextureRegion> animation = new Animation<>(0.3f, frames);
        // Update the animation state
        stateTime += Gdx.graphics.getDeltaTime();
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuBatch.begin();
        menuBatch.draw(menuImg, gameX + (gameView.getWorldWidth() * 2/3), gameY, gameView.getWorldWidth() * 1/3,gameView.getWorldHeight());
        menuBatch.end();

        gameBatch.begin();
        gameBatch.draw(img, gameX, gameY, gameView.getWorldWidth()*2/3,gameView.getWorldHeight());
        gameBatch.end();

        titleBatch.begin();
        titleBatch.draw(titleImg, gameX + (gameView.getWorldWidth() * 1/12), gameY + (gameView.getWorldHeight() * 7/12), gameView.getWorldWidth() * 1/2, gameView.getWorldHeight()/3);
        titleBatch.end();

        characterBatch.begin();
        characterBatch.draw(frames[animation.getKeyFrameIndex(stateTime)], charX, charY);
        if(animation.isAnimationFinished(stateTime)){
            stateTime = 0;
        }
        characterBatch.end();

        
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
