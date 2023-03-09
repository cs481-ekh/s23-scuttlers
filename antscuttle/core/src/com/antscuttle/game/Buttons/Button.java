package com.antscuttle.game.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Util.GameData;


public abstract class Button{

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

    public abstract void click(AntScuttleGame game, Screen screen, GameData data);

    public static void draw(AntScuttleGame game, Screen screen, GameData data, float x, float y, Button button, float scale) {
        float w = button.getWidth()*scale;
        float h = button.getHeight()*scale;

        /* if the cursor is inbounds of the button dimensions */
        if (cursorInBounds(x, y, w, h)) {
            game.batch.draw(button.active(), x, y, w, h);

            if (Gdx.input.justTouched()) 
                button.click(game, screen, data);
            
            
        } else {
            game.batch.draw(button.inactive(), x, y, w, h);
        }
    }


    public static void drawAntButton(AntScuttleGame game, Screen screen, GameData data, float x, float y, ItemButton button, float scale, Ant ant) {
        float w = button.getWidth()*scale;
        float h = button.getHeight()*scale;

        /* if the cursor is inbounds of the button dimensions */
        if (cursorInBounds(x, y, w, h)) {
            game.batch.draw(button.active(), x, y, w, h);

            if (Gdx.input.justTouched()) 
                button.click(game, data, ant);
            
        } else {
            game.batch.draw(button.inactive(), x, y, w, h);
        }
    }

    private static boolean cursorInBounds(float x, float y, float w, float h) {
        return (Gdx.input.getX() < x + w && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY() < y + h && Gdx.graphics.getHeight() - Gdx.input.getY() > y);
    }
}
