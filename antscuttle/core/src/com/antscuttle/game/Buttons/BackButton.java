package com.antscuttle.game.Buttons;

import com.Screens.MainMenuScreen;
import com.Screens.NewGameScreen;
import com.Screens.SettingsMenuScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class BackButton extends ScuttleButton {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;
    
    private final Texture INACTIVE = new Texture("buttons/Back-Arrow.png");
    private final Texture ACTIVE = new Texture("buttons/Back-Arrow-Active.png");
    
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
  

    public void click(AntScuttleGame game, Screen screen) {
        this.playButtonPressSound(game);
        
    }
	@Override
	public void click(AntScuttleGame game, Screen screen, GameData data) {
        // check which screen so we can go back
        this.playButtonPressSound(game);
        switch (screen.toString()) {
            case "SettingsMenuScreen": game.setScreen(SettingsMenuScreen.previousScreen); break;
            case "NewGameScreen": game.setScreen(new MainMenuScreen(game, data)); break;

            case "GameplayScreen": 
            case "AIEditorScreen":
            case "AntEditorScreen": game.setScreen(new NewGameScreen(game, data)); break;
            default:
                System.out.println("ya messed up");
        }
		
	}
}
