
package com.antscuttle.game.Buttons;

import com.Screens.NewGameScreen;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class ContinueButton extends Button {
    private final int WIDTH = 200;
    private final int HEIGHT = 100;
    private GameData gameData;
    private final Texture INACTIVE = new Texture("buttons/main-menu/Continue.png");
    private final Texture ACTIVE = new Texture("buttons/main-menu/Continue-Active.png");
    private final Texture UNAVAILABLE = new Texture("buttons/main-menu/Continue-Unavailable.png");
       
    public ContinueButton(GameData gameData){
        this.gameData = gameData;
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
        return (gameData == null) ? UNAVAILABLE : ACTIVE;
    }
    @Override
    public Texture inactive() {
        return (gameData == null) ? UNAVAILABLE : INACTIVE;
    }		
   
    @Override
    public void click(AntScuttleGame game, Screen screen, GameData data) {
        if(gameData != data)
            System.out.println("GAME DATA IS WRONG IN CONTINUE BUTTON");
        if(data != null){
            this.playButtonPressSound(game);
            game.setScreen(new NewGameScreen(game, data));
        } else {
            System.out.println("data is NULL!!!");
        }
    }
}
