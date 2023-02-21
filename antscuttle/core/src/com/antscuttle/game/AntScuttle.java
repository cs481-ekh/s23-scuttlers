package com.antscuttle.game;

import com.Screens.MainMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AntScuttle extends Game {
	public SpriteBatch batch;

	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}

}
