package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class PauseButton extends Button {

    private final int WIDTH = 50;
    private final int HEIGHT = 75;
    
    private final Texture INACTIVE = new Texture("buttons/pause.png");
    private final Texture ACTIVE = new Texture("buttons/pause-active.png");

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
        return ButtonType.pause.toString();
    }
    
}
