package com.rootsecks.SimpleWorld.WorldManager;

import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.WorldGenerator.WorldGenerator;

public class ChunkGenerator {
	
	
	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	WorldGenerator worldGenerator;
	
	public ChunkGenerator(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		worldGenerator = new WorldGenerator(simpleConfManager);
		

	}
	
	
	
	
	
	public void generateChunk(Chunk chunk) {
		
		ChunkLoader chunkLoader = new ChunkLoader(simpleConfManager);
		
		
		Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
		
		if (!chunkLoader.doesFileExist(chunk)) {
			
			if (chunk.getChunkY() < 0) {

				
				chunkData = worldGenerator.generateCaveChunk(chunk);
				
							
			} else if (chunk.getChunkY() == 0) {
				
					chunkData = worldGenerator.generateSurfaceChunk(chunk);
							
			} else {
				
				for ( int x = 0; x < chunkSizeX; x ++ ) {
					
					for ( int y = 0; y < chunkSizeY; y ++ ) {
								
						chunkData[x][y] = BlockTypes.blockAir;
							
					}
				}
				
			
			}
	
		} else {
			
			chunkData = chunkLoader.loadChunk(chunk);
			
		}
		
		chunk.setChunkData(chunkData);
		

	}
	
	
	

}
