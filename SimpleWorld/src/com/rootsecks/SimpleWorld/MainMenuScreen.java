package com.rootsecks.SimpleWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;

public class MainMenuScreen implements Screen {
	
	//Config Manager
	SimpleConfManager simpleConfManager;

	SimpleWorld simpleWorld;
	
	SpriteBatch menuSpriteBatch;
	BitmapFont menuFont;
	
	public MainMenuScreen(SimpleWorld simpleWorld, SimpleConfManager simpleConfManager) {

		this.simpleConfManager = simpleConfManager;
		
		this.simpleWorld = simpleWorld;
		
		menuSpriteBatch = new SpriteBatch();
		menuFont = new BitmapFont();
		
	}

	@Override
	public void render(float delta) {
		
		
		
		menuSpriteBatch.begin();
		
		menuFont.setScale(4, 4);
		menuFont.draw(menuSpriteBatch, "SimpleWorld", Gdx.graphics.getWidth() - menuFont.getBounds("SimpleWorld").width, Gdx.graphics.getHeight() - 150);
		
		menuFont.setScale(2, 2);
		menuFont.draw(menuSpriteBatch, "Click to Play", 100, 200);
		
		
		menuSpriteBatch.end();
		
		
		
	
		if (Gdx.input.justTouched()) {
			
			simpleWorld.setScreen(simpleWorld.getGameScreen());
		
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
