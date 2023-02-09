package com.antscuttle.game.Buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class ExitButton extends Game {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 100;
    // public SpriteBatch batch;
    
    public Texture inactive = new Texture("buttons/Exit-Button.png");
    public Texture active = new Texture("buttons/Exit-Button-Active.png");
       
    @Override
    public void create() {
        // batch = new SpriteBatch();
        // this.setScreen(new MainMenu());
    }
    @Override
    public void render() {
        super.render();
    }		
}