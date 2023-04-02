package com.Screens;

import com.antscuttle.game.Buttons.ScuttleButton;
import com.antscuttle.game.AntScuttleGame;
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
    public static Screen previousScreen;
    
    private static int SETTINGS_MENU_HEIGHT;
    private static int SETTINGS_MENU_WIDTH;

    ScuttleButton backButton;
    MusicButton musicButton;
    SFXButton sfxButton;

    int x;
    String musicTxt = "Music";
    String sfxTxt = "SFX";
    GlyphLayout bounds;



    public SettingsMenuScreen (AntScuttleGame game, Screen prevScreen) {
        this.game = game;
        previousScreen = prevScreen;

        SETTINGS_MENU_HEIGHT = Gdx.graphics.getHeight();
        SETTINGS_MENU_WIDTH = Gdx.graphics.getWidth();

        backButton = new BackButton();
        musicButton = new MusicButton(game);
        sfxButton = new SFXButton(game);

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
        ScuttleButton.draw(game, this, null, 20, SETTINGS_MENU_HEIGHT - backButton.getHeight() - 20, backButton, 1);

        /* Sound buttons */
        x = (SETTINGS_MENU_WIDTH/2) - 80;
        game.font.draw(game.batch, musicTxt, x, SETTINGS_MENU_HEIGHT/2+musicButton.getHeight() + bounds.height+10);
        ScuttleButton.draw(game, this, null, x, SETTINGS_MENU_HEIGHT/2, musicButton, 1);

        x += 160;
        game.font.draw(game.batch, sfxTxt, x, SETTINGS_MENU_HEIGHT/2+sfxButton.getHeight() + bounds.height+10);
        ScuttleButton.draw(game, this, null, x, SETTINGS_MENU_HEIGHT/2, sfxButton, 1);

        game.batch.end();
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public String toString() {
        return "SettingsMenuScreen";
    }
}
