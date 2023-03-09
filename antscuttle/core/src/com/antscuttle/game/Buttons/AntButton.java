package com.antscuttle.game.Buttons;

import com.Screens.AntEditorScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class AntButton extends Button{

    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    
    private final Texture INACTIVE = new Texture("buttons/Ant.png");
    private final Texture ACTIVE = new Texture("buttons/Ant-Active.png");

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

        switch (screen.toString()) {
            case "AntEditorScreen": data.currPane = GameData.panes.ant; break;
            case "NewGameScreen": game.setScreen(new AntEditorScreen(game, data)); break;
        }
        
    }
}
