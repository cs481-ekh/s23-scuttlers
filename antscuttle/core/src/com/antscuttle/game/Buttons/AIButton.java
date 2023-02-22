package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class AIButton extends Button {

    private final int WIDTH = 140;
    private final int HEIGHT = 140;
    
    private final Texture INACTIVE = new Texture("buttons/AI.png");
    private final Texture ACTIVE = new Texture("buttons/AI-Active.png");

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
        return ButtonType.aibuilder.toString();
    }
    
}
