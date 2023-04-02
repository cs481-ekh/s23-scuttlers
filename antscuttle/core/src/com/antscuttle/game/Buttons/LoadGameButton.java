package com.antscuttle.game.Buttons;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class LoadGameButton extends ScuttleButton {

    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    
    private Texture INACTIVE = new Texture("buttons/main-menu/Load-Game.png");
    private Texture ACTIVE = new Texture("buttons/main-menu/Load-Game-Active.png");
    
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
