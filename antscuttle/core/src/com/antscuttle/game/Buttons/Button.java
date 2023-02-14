package com.antscuttle.game.Buttons;

import com.badlogic.gdx.graphics.Texture;

public abstract class Button {

    enum ButtonType {exit, navigation, action}

    public Button() {
        super();
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
