package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class ExitButton extends Button {

    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    // public SpriteBatch batch;
    
    private final Texture INACTIVE = new Texture("buttons/Exit-Button.png");
    private final Texture ACTIVE = new Texture("buttons/Exit-Button-Active.png");
   
    @Override
    public int getWidth() {
        return WIDTH;
    }
    @Override
    public int getHeight() {
        return HEIGHT;
    }
    @Override
    public Texture active() {
        return ACTIVE;
    }
    @Override
    public Texture inactive() {
        return INACTIVE;
    }		
    @Override
    public String getButtonType() {
        return ButtonType.exit.toString();
    }
}