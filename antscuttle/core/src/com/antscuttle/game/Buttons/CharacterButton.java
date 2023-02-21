package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public class CharacterButton extends Button{

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
        return ButtonType.character.toString();
    }
}
