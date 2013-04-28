package com.rootsecks.SimpleWorld;

import com.badlogic.gdx.math.Vector3;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.SimpleUI.MouseHandler;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;
import com.rootsecks.SimpleWorld.WorldManager.MeshFactory;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class PickPlaceController {

	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	BlockTypes blockTypes;
	
	public PickPlaceController(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		this.blockTypes = new BlockTypes();
		
		
	}

	public void pickDestory(WorldManager worldManager, MouseHandler mouseHandler, Player player) {
		
		if (mouseHandler.getWithinPickZone()) {
				
				Vector3 adjustedMouse = new Vector3(mouseHandler.getMouseX(), mouseHandler.getUnAdjustedY(), 0);
				
				player.getCamera().getOrthCam().unproject(adjustedMouse);
				
				Chunk collidingChunk = worldManager.getWorld().getChunkAt(adjustedMouse.x, adjustedMouse.y);
				
				if (collidingChunk != null) {
					
					for (int x = 0; x < chunkSizeX; x ++ ) {
						
						for ( int y = 0; y < chunkSizeY; y ++ ) {
		
							if (adjustedMouse.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && adjustedMouse.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
								
								if (adjustedMouse.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && adjustedMouse.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY)) {
									
									if (blockTypes.isVisible(collidingChunk.getChunkData()[x][y])) {
		
										if (player.getBlockInventory()[collidingChunk.getChunkData()[x][y]] < 150) {
												
											player.getBlockInventory()[collidingChunk.getChunkData()[x][y]] ++;
		
											
										}
										
										collidingChunk.getChunkData()[x][y] = BlockTypes.blockAir;
										
										MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
										meshFactory.createChunkMesh();
										
		
									}
									
								}
		
							}
							
						}
						
					}
					
				}
				
		}
		
		
	}

	public void pickPlace(WorldManager worldManager, MouseHandler mouseHandler, Player player) {
		
		if (mouseHandler.getWithinPickZone()) {
				
				Vector3 adjustedMouse = new Vector3(mouseHandler.getMouseX(), mouseHandler.getUnAdjustedY(), 0);
				
				player.getCamera().getOrthCam().unproject(adjustedMouse);
				
				Chunk collidingChunk = worldManager.getWorld().getChunkAt(adjustedMouse.x, adjustedMouse.y);
				
				if (collidingChunk != null) {
					
					for (int x = 0; x < chunkSizeX; x ++ ) {
						
						for ( int y = 0; y < chunkSizeY; y ++ ) {
		
							if (adjustedMouse.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && adjustedMouse.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
								
								if (adjustedMouse.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && adjustedMouse.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY)) {
									
									if (!blockTypes.isVisible(collidingChunk.getChunkData()[x][y])) {
										
										if (player.getBlockInventory()[player.getCurrentlySelectedBlockID()] > 0) {
											
											player.getBlockInventory()[player.getCurrentlySelectedBlockID()] --;
											
											collidingChunk.getChunkData()[x][y] = (byte) player.getCurrentlySelectedBlockID();
											
											MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
											meshFactory.createChunkMesh();
											
										}
					
		
									}
									
								}
		
							}
							
						}
						
					}
					
				}

		}
	}
	
	
	
	
	
	

}
