package com.antscuttle.game;

import com.Screens.MainMenuScreen;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.implementations.MoveBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.implementations.Human;
import com.antscuttle.game.Ant.implementations.Zombie;
import com.antscuttle.game.Util.ClassFactory;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.lang.reflect.Constructor;
import java.util.LinkedList;

public class AntScuttleGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	public Sound sfx;
	public float VOLUME = 1f;
	public float FONT_SCALE = 0.75f;
	public Music music;

	public boolean musicActive = true;
	public boolean sfxActive = true;
	
	@Override
	public void create () {
                GameData data = new GameData();
                LinkedList<Class<? extends Ant>> antTypes = data.getAntTypes();
                ClassFactory cf = new ClassFactory();
                for(Class a : antTypes){
                    Ant ant = cf.newAntInstance(a, "Name");
                    System.out.println("Ant name: " + ant.getName() + 
                            "Ant type: " + ant.getClass().getName());
                }
                
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/myFont.fnt"));
		font.getData().setScale(FONT_SCALE);

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
