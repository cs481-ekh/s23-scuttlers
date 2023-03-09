package com.antscuttle.game.Buttons;

import com.Screens.SettingsMenuScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

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
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        this.playButtonPressSound(game);
        game.setScreen(new SettingsMenuScreen(game, screen));
    }

}
