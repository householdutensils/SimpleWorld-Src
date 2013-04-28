package com.rootsecks.SimpleWorld;

import com.badlogic.gdx.Game;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;

public class SimpleWorld extends Game {
	
	//Config Manager
	SimpleConfManager simpleConfManager;
	
	//Screens
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		
		simpleConfManager = new SimpleConfManager();
		
		mainMenuScreen = new MainMenuScreen(this, simpleConfManager);
		gameScreen = new GameScreen(this, simpleConfManager);
		setScreen(mainMenuScreen);
	
	}
	
	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public void setMainMenuScreen(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}


}
