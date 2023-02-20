package com.antscuttle.game;

import com.antscuttle.game.Buttons.Button;
import com.antscuttle.game.Buttons.BackButton;
import com.antscuttle.game.Buttons.MusicButton;
import com.antscuttle.game.Buttons.SFXButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;


public class SettingsMenuScreen extends ScreenAdapter {
    AntScuttleGame game;
    Screen previousScreen;
    
    private static int SETTINGS_MENU_HEIGHT;
    private static int SETTINGS_MENU_WIDTH;

    Button backButton;
    MusicButton musicButton;
    SFXButton sfxButton;

    int x;
    String musicTxt = "Music";
    String sfxTxt = "SFX";
    GlyphLayout bounds;



    public SettingsMenuScreen (AntScuttleGame game, Screen prevScreen) {
        this.game = game;
        this.previousScreen = prevScreen;

        SETTINGS_MENU_HEIGHT = Gdx.graphics.getHeight();
        SETTINGS_MENU_WIDTH = Gdx.graphics.getWidth();

        backButton = new BackButton();
        musicButton = new MusicButton();
        sfxButton = new SFXButton();

        /* Grabs the dimensions of the given string with the given font */
        bounds = new GlyphLayout();
        bounds.setText(game.font, musicTxt);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 38/255f, 66/255f, 1);
        game.batch.begin();

        /* Back button */
        drawButton(20, SETTINGS_MENU_HEIGHT - backButton.getHeight() - 20, backButton);

        /* Sound buttons */
        x = (SETTINGS_MENU_WIDTH/2) - 80;
        game.font.getData().setScale(0.75f);
        game.font.draw(game.batch, musicTxt, x, SETTINGS_MENU_HEIGHT/2+musicButton.getHeight() + bounds.height+10);
        drawButton(x, SETTINGS_MENU_HEIGHT/2, musicButton);

        x += 160;
        game.font.draw(game.batch, sfxTxt, x, SETTINGS_MENU_HEIGHT/2+sfxButton.getHeight() + bounds.height+10);
        drawButton(x, SETTINGS_MENU_HEIGHT/2, sfxButton);

       
        game.batch.end();
    }

    private void drawButton(int x, int y, Button button) {
        int w = button.getWidth();
        int h = button.getHeight();

        /* if the cursor is inbounds of the button */
        if (Gdx.input.getX() < x + w && Gdx.input.getX() > x &&
            SETTINGS_MENU_HEIGHT - Gdx.input.getY() < y + h && SETTINGS_MENU_HEIGHT - Gdx.input.getY() > y) {

            game.batch.draw(button.inactive(), x, y, w, h);

            if (button.getButtonType() == "navigation" && Gdx.input.isTouched()) {
                button.playButtonPressSound(game);
                game.setScreen(previousScreen);
            } else if (Gdx.input.isTouched() && button.getButtonType() == "music") {
                button.playButtonPressSound(game);
                musicButton.toggleMusic(game);
                
            } else if (Gdx.input.isTouched() && button.getButtonType() == "sfx") {
                button.playButtonPressSound(game);
                sfxButton.toggleSFX(game);
            }

        } else {
            game.batch.draw(button.active(), x, y, w, h);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}
