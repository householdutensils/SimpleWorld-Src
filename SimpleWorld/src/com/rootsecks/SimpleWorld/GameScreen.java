package com.rootsecks.SimpleWorld;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.NPCManager.NPCManager;
import com.rootsecks.SimpleWorld.SimpleUI.MouseHandler;
import com.rootsecks.SimpleWorld.SimpleUI.SimpleUI;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;
import com.rootsecks.SimpleWorld.WorldRenderer.WorldRenderer;

public class GameScreen implements Screen {
	
	private static final Logger logger = Logger.getLogger(GameScreen.class.getName());
	
	//ConfigManager
	SimpleConfManager simpleConfManager;
	
	//World Manager
	WorldManager worldManager;
	
	//Player Object
	Player player;
	
	//SimpleWorld Class
	SimpleWorld simpleWorld;
	
	//WorldRenderer, handles the renderring of pretty much everything
	WorldRenderer worldRenderer;
	MouseHandler mouseHandler;
	
	
	PlayerController playerController;
	
	Boolean worldLoaded;
	
	SimpleUI simpleUI;
	
	Boolean ePressed = false;
	Boolean qPressed = false;
	Boolean pPressed = false;
	
	Boolean debugMode;
	
	float currentTime;
	int totalTimeCylce;

	
	NPCManager npcManager;

	public GameScreen(SimpleWorld simpleWorld, SimpleConfManager simpleConfManager) {
				
		
		logger.log(Level.INFO, "GameScreen Consutrctor Called");

		worldLoaded = false;
		
		
		this.simpleConfManager = simpleConfManager;
		this.simpleWorld = simpleWorld;
		
		debugMode = Boolean.parseBoolean(simpleConfManager.getConfItem("debugMode"));
		
		
		//Create the world manager
		this.worldManager = new WorldManager(simpleConfManager);
		
		
		player = new Player(simpleConfManager, 64, worldManager.getChunkSizeY() * worldManager.getBlockSizeY() + 64);

		//Load the world
		worldManager.loadWorld(player.getPlayerPosX(),player.getPlayerPosY());
		worldLoaded = true;
		
		//Instance the world renderer
		worldRenderer = new WorldRenderer(simpleConfManager);
		
		
		playerController = new PlayerController(simpleConfManager);
		
		mouseHandler = new MouseHandler(simpleConfManager);
		
		simpleUI = new SimpleUI(simpleConfManager);
		
		currentTime = 0;
		totalTimeCylce = Integer.parseInt(simpleConfManager.getConfItem("totalTimeCylce"));
		
		npcManager = new NPCManager(simpleConfManager);
		
		
			
	}

	@Override
	public void render(float delta) {
				
		if (worldLoaded) {

			currentTime += delta;
			
			if (currentTime >= totalTimeCylce) {
				
				currentTime = 0;
				
				player.setDaysSurvived(player.getDaysSurvived() + 1);
			
			}
			

			///Update Phase
			handleInput();
			playerController.update(delta, player, worldManager, mouseHandler);
			
			npcManager.update(delta, worldManager, player, currentTime, totalTimeCylce);
			
			worldManager.update(player.getPlayerPosX(), player.getPlayerPosY());
			
			if (player.getHealth() <= 0) {
				
				player.clearInventory();
				
				player.setPlayerPosY(513);
				player.setPlayerPosX(new Random().nextInt(10000));
				
				player.setHealth(player.getMaxHealth());
				
			}
			
			//Render the world
			worldRenderer.render(worldManager, player, currentTime, totalTimeCylce, npcManager);
			simpleUI.render(player, currentTime, totalTimeCylce, npcManager);
			mouseHandler.render(player);
			
		}
				
	}

	@Override
	public void resize(int width, int height) {
		
		player.getCamera().initCamera();
		
		
	}

	@Override
	public void show() {
		
		logger.log(Level.INFO, "GameScreen Show Called");

		
	}
	

    private void handleInput() {

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        	playerController.leftPressed(true);
        } else {
			playerController.leftPressed(false);   	
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
        	playerController.rightPressed(true);
        } else {
			playerController.rightPressed(false);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			playerController.jumpPressed(true);
        } else {
			playerController.jumpPressed(false); 	
        }
        	
    	if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
        		
    		playerController.setMouseLeftPressed(true);
    		mouseHandler.setMouseLeftPressed(true);
        		
    	} else {
        		
    		playerController.setMouseLeftPressed(false);
    		mouseHandler.setMouseLeftPressed(false);
    		
    	}
    	
    	if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
    		
    		playerController.setMouseRightPressed(true);
    		mouseHandler.setMouseRightPressed(true);
        		
    	} else {
        		
    		playerController.setMouseRightPressed(false);
    		mouseHandler.setMouseRightPressed(false);
    		
    	}
    	
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.E) && !ePressed) {
    		
    		ePressed = true;
    		player.increaseSelectedBlock();
    	
    	} else if (!Gdx.input.isKeyPressed(Input.Keys.E) && ePressed) {
    		
    		ePressed = false;
    		
    	}
    	
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.Q) && !qPressed) {
    		
    		qPressed = true;
    		
    		player.decreaseSelectedBlock();
    		
    	} else if (!Gdx.input.isKeyPressed(Input.Keys.Q) && qPressed) {
    		
    		qPressed = false;
    		
    	}
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.P) && !pPressed) {
    		
    		pPressed = true;
    		
    		if (debugMode) {
    	
    			simpleConfManager.setDebugMode(false);
    			debugMode = false;
    			
    		} else {

    			simpleConfManager.setDebugMode(true);
    			debugMode = true;

    		}
    		
    	} else if (!Gdx.input.isKeyPressed(Input.Keys.P) && pPressed) {
    		
    		pPressed = false;
    	}
    	
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.F1)) {
    		
    		simpleUI.setShowHelp(true);
    		
    	} else {
    		
    		simpleUI.setShowHelp(false);

    	}
    	
    	
    	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
    		
    		logger.log(Level.INFO, "Escape Key Hit, Exiting");
    		worldManager.saveForExit(player);  
    		player.saveForExit();
    		
    		Gdx.app.exit();	
    	
    	
    	}
    	
    	
    	
        	
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
