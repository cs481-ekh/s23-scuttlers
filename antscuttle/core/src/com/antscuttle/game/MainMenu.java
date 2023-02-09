package com.antscuttle.game;


import com.badlogic.gdx.Screen;
import com.antscuttle.game.Buttons.ExitButton;
import com.antscuttle.game.Buttons.NewGameButton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenu extends ApplicationAdapter implements Screen {
    SpriteBatch batch;

    ExitButton exitButton;
	NewGameButton newGameButton;

    private static final int EXIT_BUTTON_Y = 50;
    private static final int NEW_GAME_BUTTON_Y = 300;

    private static final int MAIN_MENU_HEIGHT = Gdx.graphics.getHeight();
    private static final int MAIN_MENU_WIDTH = Gdx.graphics.getWidth();

    int x;

    @Override
    public void create () {
        batch = new SpriteBatch();
        exitButton = new ExitButton();
        newGameButton = new NewGameButton();
    }
	
    
    @Override
    public void render() {
        ScreenUtils.clear(0, 0.5f, 1, 1);
        batch.begin();



        x = (MAIN_MENU_WIDTH / 2) - (NewGameButton.WIDTH / 2);

        if (Gdx.input.getX() <  x + NewGameButton.WIDTH && 
            Gdx.input.getX() > x && 
            MAIN_MENU_HEIGHT - Gdx.input.getY() < NEW_GAME_BUTTON_Y + NewGameButton.HEIGHT && 
            MAIN_MENU_HEIGHT - Gdx.input.getY() > NEW_GAME_BUTTON_Y) {
           
            batch.draw(newGameButton.inactive, x, NEW_GAME_BUTTON_Y, NewGameButton.WIDTH, NewGameButton.HEIGHT);
            // if(Gdx.input.isTouched()){
            //     Gdx.app.exit();
            // }
        }
        else{
            batch.draw(newGameButton.active, x, NEW_GAME_BUTTON_Y, NewGameButton.WIDTH, NewGameButton.HEIGHT);
        }
        
        //EXIT BUTTON
        x = (MAIN_MENU_WIDTH / 2) - (ExitButton.WIDTH / 2);

        if (Gdx.input.getX() <  x + ExitButton.WIDTH && 
            Gdx.input.getX() > x && 
            MAIN_MENU_HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + ExitButton.HEIGHT && 
            MAIN_MENU_HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y) {
           
            batch.draw(exitButton.inactive, x, EXIT_BUTTON_Y, ExitButton.WIDTH, ExitButton.HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else{
            batch.draw(exitButton.active, x, EXIT_BUTTON_Y, ExitButton.WIDTH, ExitButton.HEIGHT);
        }

        // //PLAY BUTTON
        // x = ZombieTest.WIDTH/2-PLAY_BUTTON_WIDTH/2;
        // if(Gdx.input.getX() <  x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && ZombieTest.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && ZombieTest.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
        //     game.batch.draw(playActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        //     if(Gdx.input.isTouched()){
        //         this.dispose();
        //         game.setScreen(new MainGameScreen(game));
        //     }
        
        // }
        // else{
            //     game.batch.draw(playInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            // }
            
        batch.end();
            
        }

        @Override
        public void dispose () {
            batch.dispose();
            // exitButton.dispose();
        }


        @Override
        public void show() {
            // TODO Auto-generated method stub
            
        }


        @Override
        public void render(float delta) {
            // TODO Auto-generated method stub
            
        }


        @Override
        public void hide() {
            // TODO Auto-generated method stub
            
        }
}
