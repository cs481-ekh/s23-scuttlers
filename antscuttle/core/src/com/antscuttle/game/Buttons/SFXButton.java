package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class SFXButton extends ScuttleButton {
    private final int WIDTH = 64;
    private final int HEIGHT = 64;
    
    private final Texture UNMUTE_INACTIVE = new Texture("buttons/settings/Unmute.png");
    private final Texture UNMUTE_ACTIVE = new Texture("buttons/settings/Unmute-Active.png");
    private final Texture MUTE_INACTIVE = new Texture("buttons/settings/Mute.png");
    private final Texture MUTE_ACTIVE = new Texture("buttons/settings/Mute-Active.png");

    boolean soundIsActive = true;

    AntScuttleGame game;

    public SFXButton (AntScuttleGame game) {
        this.game = game;
    }

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
        return (game.sfxActive) ? UNMUTE_ACTIVE : MUTE_ACTIVE;
    }

    @Override
    public Texture inactive() { 
        return (game.sfxActive) ? UNMUTE_INACTIVE : MUTE_INACTIVE;
    }

    @Override
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        this.playButtonPressSound(game);
        if (game.sfxActive) {
            game.VOLUME = 0f;
            game.sfxActive = false;
        } else {
            game.VOLUME = 1f;
            game.sfxActive = true;
        }
    }
}
