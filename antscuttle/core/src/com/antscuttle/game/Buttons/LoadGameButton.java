package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class LoadGameButton extends Button {

    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    
    private Texture INACTIVE = new Texture("buttons/Load-Game-Button.png");
    private Texture ACTIVE = new Texture("buttons/Load-Game-Button-Active.png");
    
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
        return ButtonType.loadgame.toString();
    }		
}
