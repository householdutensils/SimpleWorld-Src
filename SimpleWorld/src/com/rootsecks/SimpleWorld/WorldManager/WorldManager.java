package com.rootsecks.SimpleWorld.WorldManager;

import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.FancyMath;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.World.World;

public class WorldManager {
	
	
	//Config Manager
	SimpleConfManager simpleConfManager;
	
	//World Object to hold the data 
	World world;
	
	//////WORLD DIMENSIONAL INFO
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	int loadDistanceX;
	int loadDistanceY;
	
	ChunkGenerator chunkGenerator;
	
	public WorldManager(SimpleConfManager simpleConfManager) {
		
		
		//Assign the config manager
		this.simpleConfManager = simpleConfManager;
		//Request all the config items that this class needs
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		//Once I've gotten everything working, I'll start parsing the load distance from the size of the window
		loadDistanceX = Integer.parseInt(simpleConfManager.getConfItem("loadDistanceX"));
		loadDistanceY = Integer.parseInt(simpleConfManager.getConfItem("loadDistanceY"));
	
		world = new World(simpleConfManager);
		
		chunkGenerator = new ChunkGenerator(simpleConfManager);
		
		
	}
	

	//This method takes the players spawn position, and loads in the chunks surrounding them
	public void loadWorld(float playerPosX, float playerPosY) {
		
		
	
		int totalChunkSizeX = chunkSizeX * blockSizeX;
		int totalChunkSizeY = chunkSizeY * blockSizeY;
		FancyMath fancyMath = new FancyMath();
		int loadStartX = fancyMath.roundToMultiple(Math.round(playerPosX), totalChunkSizeX);
		int loadStartY = fancyMath.roundToMultiple(Math.round(playerPosY), totalChunkSizeY);
		
		loadStartX -= (loadDistanceX/2) * totalChunkSizeX;
		loadStartY -= (loadDistanceY/2) * totalChunkSizeY;
		
		//We iterate through the load distances and init the chunks into the chunk array
		for (int x = 0; x < loadDistanceX; x ++ ) {
			
			for ( int y = 0; y < loadDistanceY; y ++ ) {
				
				//Adjust the chunkX var by the loading variables we got above
                int chunkX = loadStartX + (x * (chunkSizeX * blockSizeX));
                int chunkY = loadStartY + (y * (chunkSizeY * blockSizeY));
                
                //Add the chunk to the load list
                world.getLoadChunkList().add(new Chunk(chunkX, chunkY, simpleConfManager));
				
			}
			
		}
		
		
		//Load those mother fuckers
		loadChunks();
		
		
	}
	
	

	private void loadChunks() {
		

		
		for ( int num = 0; num < world.getLoadChunkList().size(); num ++ ) {
			
			
			chunkGenerator.generateChunk(world.getLoadChunkList().get(num));
			MeshFactory meshFactory = new MeshFactory(simpleConfManager, world.getLoadChunkList().get(num));
			meshFactory.createChunkMesh();
			world.getActiveChunkList().add(world.getLoadChunkList().get(num));	
			
		}
		
		
		world.getLoadChunkList().clear();
				
		
	}
	
	
	public void unloadChunks() {
		
		for (int num = 0; num < world.getUnloadChunkList().size(); num ++ ) {
			
			world.getActiveChunkList().remove(world.getUnloadChunkList().get(num));

			ChunkUnloader chunkUnloader = new ChunkUnloader(simpleConfManager);
			chunkUnloader.unloadChunk(world.getUnloadChunkList().get(num));
			
			world.getUnloadChunkList().remove(world.getUnloadChunkList().get(num));
			
		}
		
	}
	
	
	
	public void update(float playerX, float playerY) {
		
		for (int num = 0; num < world.getActiveChunkList().size(); num ++ ) {
			
			if (isOutsideLoadRange(world.getActiveChunkList().get(num), playerX, playerY)) {
				
				world.getUnloadChunkList().add(world.getActiveChunkList().get(num));
				
			}
			
		}
		
			
		int totalChunkSizeX = chunkSizeX * blockSizeX;
		int totalChunkSizeY = chunkSizeY * blockSizeY;
		FancyMath fancyMath = new FancyMath();
		int loadStartX = fancyMath.roundToMultiple(Math.round(playerX), totalChunkSizeX);
		int loadStartY = fancyMath.roundToMultiple(Math.round(playerY), totalChunkSizeY);
		
		loadStartX -= (loadDistanceX/2) * totalChunkSizeX;
		loadStartY -= (loadDistanceY/2) * totalChunkSizeY;
		
		//We iterate through the load distances and init the chunks into the chunk array
		for (int x = 0; x < loadDistanceX; x ++ ) {
			
			for ( int y = 0; y < loadDistanceY; y ++ ) {
				
				//Adjust the chunkX var by the loading variables we got above
                int chunkX = loadStartX + (x * (chunkSizeX * blockSizeX));
                int chunkY = loadStartY + (y * (chunkSizeY * blockSizeY));
                
                //Add the chunk to the load list
        		
        		if (!world.chunkExists(chunkX, chunkY)) {
        			
	        		//logger.log(Level.INFO, "Generating Chunk At: " + chunkX + ", " + chunkY);

        			world.getLoadChunkList().add(new Chunk(chunkX, chunkY, simpleConfManager));		
        			
        			
        		}
				
			}
			
		}
		
		
		loadChunks();
		
		
		unloadChunks();
		
	}

	
	public World getWorld() {
		return world;
	}


	
	public int getChunkSizeX() {
		return chunkSizeX;
	}


	public int getChunkSizeY() {
		return chunkSizeY;
	}


	public int getBlockSizeX() {
		return blockSizeX;
	}


	public int getBlockSizeY() {
		return blockSizeY;
	}

	
	private boolean isOutsideLoadRange(Chunk chunk, float playerX, float playerY) {

		Boolean isOutside = false;
		
		if (chunk.getChunkX()  < playerX - ((loadDistanceX/2) * (blockSizeX * chunkSizeX)) || chunk.getChunkX() > playerX + ((loadDistanceX/2) * (blockSizeX * chunkSizeX))) {
			
			isOutside = true;
		
		}
		
		if (chunk.getChunkY()  < playerY - ((loadDistanceY/2) * (blockSizeY * chunkSizeY)) || chunk.getChunkY() > playerY + ((loadDistanceY/2) * (blockSizeY * chunkSizeY))) {
			
			isOutside = true;
		
		}
			
		return isOutside;
	}


	public void saveForExit(Player player) {
		
		
		world.getLoadChunkList().clear();
		
		for (int num = 0; num < world.getActiveChunkList().size(); num ++ ) {
			
			world.getUnloadChunkList().add(world.getActiveChunkList().get(num));
			
		}
		
		
		unloadChunks();
		
		
	}

	

}
