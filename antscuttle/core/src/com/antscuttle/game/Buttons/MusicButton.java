package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MusicButton extends ScuttleButton {
    private final int WIDTH = 64;
    private final int HEIGHT = 64;
    
    private final Texture UNMUTE_INACTIVE = new Texture("buttons/settings/Unmute.png");
    private final Texture UNMUTE_ACTIVE = new Texture("buttons/settings/Unmute-Active.png");
    private final Texture MUTE_INACTIVE = new Texture("buttons/settings/Mute.png");
    private final Texture MUTE_ACTIVE = new Texture("buttons/settings/Mute-Active.png");

    boolean soundIsActive = true;

    AntScuttleGame game;

    public MusicButton (AntScuttleGame game) {
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
        return (game.musicActive) ? UNMUTE_ACTIVE : MUTE_ACTIVE;
    }

    @Override
    public Texture inactive() { 
        return (game.musicActive) ? UNMUTE_INACTIVE : MUTE_INACTIVE;
    }


    @Override
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        this.playButtonPressSound(game);
        if (game.musicActive) {
            game.music.pause();
            game.musicActive = false;
        } else {
            game.music.play();
            game.musicActive = true;
        }
    }

}
