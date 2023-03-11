package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ItemButton extends Button {
    private final int WIDTH = 100;
    private final int HEIGHT = 100;
    
    private final Texture INACTIVE = new Texture("buttons/ant-editor/Item.png");
    private final Texture ACTIVE = new Texture("buttons/ant-editor/Item-Active.png");

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
    public void click(AntScuttleGame game, Screen screen, GameData data) {}

    public void click(AntScuttleGame game, GameData data, Ant ant) {
        this.playButtonPressSound(game);
        data.setCurrentAnt(ant);
    }
}
