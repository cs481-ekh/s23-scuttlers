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
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
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
		// Create a list of file names from a folder
		FileHandle folder = Gdx.files.internal("saves");
		LinkedList<String> fileNames = new LinkedList<>();
		for(FileHandle file: folder.list()){
                    if(file.toString().endsWith(".txt"))
			fileNames.add(file.toString());
		}

		String fileName = fileNames.isEmpty() ? "": fileNames.get(0);
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object save = ois.readObject();
			if(save instanceof GameData){
				GameData saveData = (GameData) save;
				LinkedList<AI> ais = saveData.getAllAIs();
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
						
				setScreen(new MainMenuScreen(this, saveData, true));
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
					
			setScreen(new MainMenuScreen(this, null, true));
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
