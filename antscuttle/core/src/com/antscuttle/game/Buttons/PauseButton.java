package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class PauseButton extends ScuttleButton {

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
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        this.playButtonPressSound(game);
    }
    
}
