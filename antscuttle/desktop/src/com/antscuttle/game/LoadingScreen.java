package com.antscuttle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingScreen {
    
    public static void load() { 
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.disableAudio(true);
		config.setDecorated(false);
		config.setResizable(false);
		config.setTransparentFramebuffer(true);
		new Lwjgl3Application(new ApplicationAdapter() {
			private SpriteBatch batch;
			private Texture texture;
			private float time;

            private Sprite sprite;
            private TextureRegion tRegion;
			@Override
			public void create() {
				// load your PNG
				texture = new Texture("sdp-logo-3.png");
				batch = new SpriteBatch();

                tRegion = new TextureRegion(texture, 0, 0, 1024, 577);
                sprite = new Sprite(tRegion);
                sprite.setSize(1f, 1.25f * sprite.getHeight() / sprite.getWidth());
			}
			@Override
			public void render() {
				
				time += Gdx.graphics.getDeltaTime();
				
				// render your PNG
				Gdx.gl.glClearColor(0, 0, 0, 0);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				batch.getProjectionMatrix().setToOrtho2D(0, 0, 1, 1);
				batch.begin();
				
                sprite.draw(batch);
				batch.end();
				
				if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || time > 4f){ 
					Gdx.app.exit();
				}
			}
			@Override
			public void dispose() {
				texture.dispose();
				batch.dispose();
			}
		}, config);
    }
}
