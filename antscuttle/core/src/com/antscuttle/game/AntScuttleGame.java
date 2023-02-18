package com.antscuttle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AntScuttleGame extends Game {
	public SpriteBatch batch;
	protected BitmapFont font;
	
	public Sound sfx;
	public float VOLUME = 1f;
	public Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/myFont.fnt"));

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/space.mp3"));
		sfx = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonPress.wav"));

		music.setVolume(1f);
		music.setLooping(true);
		music.play();

		setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
		font.dispose();
		sfx.dispose();
	}
}
