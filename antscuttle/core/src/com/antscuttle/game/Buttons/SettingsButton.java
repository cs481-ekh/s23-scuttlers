package com.antscuttle.game.Buttons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SettingsButton extends Button {

    private final int WIDTH = 70;
    private final int HEIGHT = 70;
    
    private final Texture INACTIVE = new Texture("buttons/cog1.png");
    private final Texture ACTIVE = new Texture("buttons/cog2.png");

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
        return ButtonType.navigation.toString();
    }

}
