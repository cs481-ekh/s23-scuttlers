package com.Screens;

import com.antscuttle.game.AntScuttleGame;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.SettingsButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;

public class AntEditorScreen extends ScreenAdapter {
    AntScuttleGame game;

    Button settingsButton;
    Button backButton;

    int x;

    private static int ANT_EDITOR_HEIGHT;
    private static int ANT_EDITOR_WIDTH;

    private static final int SETTINGS_BUTTON_Y = 10;

    String title = "Ant Editor";
    GlyphLayout bounds;

    public AntEditorScreen(AntScuttleGame game) {
        this.game = game;
        settingsButton = new SettingsButton();
        backButton = new BackButton();

        ANT_EDITOR_HEIGHT = Gdx.graphics.getHeight();
        ANT_EDITOR_WIDTH = Gdx.graphics.getWidth();

        bounds = new GlyphLayout();
        bounds.setText(game.font, title);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() { });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();

        /* Back Button */
        drawButton(20, ANT_EDITOR_HEIGHT - backButton.getHeight() - 20, backButton);

        game.font.draw(game.batch, title, ANT_EDITOR_WIDTH/2, ANT_EDITOR_HEIGHT/2 + bounds.height+10);


        /* Settings Button */
        x = ANT_EDITOR_WIDTH - settingsButton.getWidth() - 10;
        drawButton(x, SETTINGS_BUTTON_Y, settingsButton);

        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Draw the Button
     * @param x
     * @param y
     * @param button type of button
     */
    private void drawButton(int x, int y, Button button) {
        int w = button.getWidth();
        int h = button.getHeight();

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            ANT_EDITOR_HEIGHT - Gdx.input.getY() < y + h && ANT_EDITOR_HEIGHT - Gdx.input.getY() > y) {

            // Gdx.graphics.setSystemCursor(SystemCursor.Hand);
            game.batch.draw(button.inactive(), x, y, w, h);

            if (button.getButtonType() == "settings" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new SettingsMenuScreen(game, this));
            }
            if (button.getButtonType() == "back" && Gdx.input.justTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(new NewGameScreen(game));
            }
        } else {
            game.batch.draw(button.active(), x, y, w, h);
            // Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    }   
}
