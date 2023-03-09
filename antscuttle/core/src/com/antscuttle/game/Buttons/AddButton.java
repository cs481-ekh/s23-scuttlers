package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class AddButton extends Button {
    private final int WIDTH = 64;
    private final int HEIGHT = 64;
    
    private final Texture INACTIVE = new Texture("buttons/ant-editor/Add.png");
    private final Texture ACTIVE = new Texture("buttons/ant-editor/Add-Active.png");
   
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
        return ButtonType.add.toString();
    }
}
