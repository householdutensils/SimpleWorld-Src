package com.rootsecks.SimpleWorld.World;

import java.util.ArrayList;

import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;

public class World {

	//ConfigManager 
	SimpleConfManager simpleConfManager;
	
	//List to hold the currently active Chunks
	ArrayList<Chunk> activeChunkList;
	//List to hold the chunks that need to be loaded
	ArrayList<Chunk> loadChunkList;
	//List to hold the chunks that need to be unloaded
	ArrayList<Chunk> unloadChunkList;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	public World(SimpleConfManager simpleConfManager) {
	
		this.simpleConfManager = simpleConfManager;
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
				
		//Initalize the ArrayLists
		activeChunkList = new ArrayList<Chunk>();
		loadChunkList = new ArrayList<Chunk>();
		unloadChunkList = new ArrayList<Chunk>();
		
		
		
				
	}

	public ArrayList<Chunk> getActiveChunkList() {
		return activeChunkList;
	}

	public ArrayList<Chunk> getLoadChunkList() {
		return loadChunkList;
	}

	public ArrayList<Chunk> getUnloadChunkList() {
		return unloadChunkList;
	}

	
	
	
	public boolean chunkExists(int chunkX, int chunkY) {
		
		for ( int num = 0; num < activeChunkList.size(); num ++ ) {
			
			if (chunkX == activeChunkList.get(num).getChunkX()) {
				if (chunkY == activeChunkList.get(num).getChunkY()) {

					return true;
					
				}				
			}
			
			
		}
		
		
		return false;
	}

	public Chunk getChunkAt(float playerX, float playerY) {
		
		for ( int num = 0; num < activeChunkList.size(); num ++ ) {
			
			if (playerX >= activeChunkList.get(num).getChunkX() && playerX <= activeChunkList.get(num).getChunkX() + (chunkSizeX * blockSizeX)) {
				
				if (playerY >= activeChunkList.get(num).getChunkY() && playerY <= activeChunkList.get(num).getChunkY() + (chunkSizeY * blockSizeY)) {
					
					return activeChunkList.get(num);
					
				}
				
			}
	
		}
		
		
		return null;
		
	}
	
	
	
}
