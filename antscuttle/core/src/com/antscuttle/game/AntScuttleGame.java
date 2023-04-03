package com.antscuttle.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Screens.MainMenuScreen;
import com.antscuttle.game.AI.AI;
import com.antscuttle.game.Util.GameData;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AntScuttleGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	public Sound sfx;
	public float VOLUME = 1f;
	public float FONT_SCALE = 0.75f;
	public Music music;

	public boolean musicActive = true;
	public boolean sfxActive = true;
	
	public Skin skin;
	
	@Override
	public void create () {
		String fileName = "gamedata.txt";
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object test = ois.readObject();
			if(test instanceof GameData){
				GameData testData = (GameData) test;
				LinkedList<AI> ais = testData.getAllAIs();
				System.out.println("AI Names:");
				for(AI ai: ais){
					System.out.println("\t" + ai.getName());
				}
				batch = new SpriteBatch();
				font = new BitmapFont(Gdx.files.internal("fonts/myFont.fnt"));
				font.getData().setScale(FONT_SCALE);
				font.setColor(250/255f,223/255f,99/255f,1);
						
				music = Gdx.audio.newMusic(Gdx.files.internal("sounds/space.mp3"));
				sfx = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonPress.wav"));
				skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
		
				music.setVolume(1f);
				music.setLooping(true);
				music.play();
						
				setScreen(new MainMenuScreen(this, testData));
			}else{
				System.out.println("Still not working");
			}
		} catch (FileNotFoundException fnfe) {
			batch = new SpriteBatch();
			font = new BitmapFont(Gdx.files.internal("fonts/myFont.fnt"));
			font.getData().setScale(FONT_SCALE);
			font.setColor(250/255f,223/255f,99/255f,1);
					
			music = Gdx.audio.newMusic(Gdx.files.internal("sounds/space.mp3"));
			sfx = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonPress.wav"));
			skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
	
			music.setVolume(1f);
			music.setLooping(true);
			music.play();
					
			setScreen(new MainMenuScreen(this, null));
		} catch (IOException ioe){
			Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, ioe);
		} catch (ClassNotFoundException cnfe){
			Logger.getLogger(GameData.class.getName()).log(Level.SEVERE, null, cnfe);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
		font.dispose();
		sfx.dispose();
	}
}
