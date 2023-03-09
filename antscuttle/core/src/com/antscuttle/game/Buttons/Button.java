package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.antscuttle.game.AntScuttleGame;


public abstract class Button{

    enum ButtonType {exit, newgame, savegame, loadgame, settings, ant, ai, main, start, level, music, sfx, back, pause, newai, root, move, attack, add, items, item}

    public Button() {
        super();      
    }

    public void playButtonPressSound(AntScuttleGame game) {
        long id = game.sfx.play(game.VOLUME);
    }

    /**
     * Get the width of the button
     * @return width
     */
    public abstract int getWidth();

    /**
     * Get the height of the button
     * @return height
     */
    public abstract int getHeight();

    /**
     * Get the active texture of the button
     * @return active button
     */
    public abstract Texture active();

     /**
     * Get the inactive texture of the button
     * @return inactive button
     */
    public abstract Texture inactive();

    public abstract String getButtonType();

}
