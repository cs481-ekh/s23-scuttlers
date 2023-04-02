package com.antscuttle.game.Buttons;

import com.Screens.AIEditorScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class AIButton extends ScuttleButton {

    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    
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
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        this.playButtonPressSound(game);
        switch (screen.toString()){
            case "NewGameScreen": game.setScreen(new AIEditorScreen(game, data)); break;
            case "AntEditorScreen": data.currPane = GameData.panes.ai; break;
        }      
        
    }
    
}
