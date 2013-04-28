package com.rootsecks.SimpleWorld.WorldManager.WorldGenerator;

import java.util.Random;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;

public class WorldGenerator {


	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	HeightMapGenerator heightMapGenerator;
	
	
	
	
	////World Generating Variables
	int caveMaxHeight;
	int caveMinHeight;
	int caveDirtSparctiy;
	
	int biomeMaxSizeX;
	int biomeCounter;
	int currentBiome;
	

	public WorldGenerator(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		heightMapGenerator = new HeightMapGenerator(simpleConfManager);
		
		biomeCounter = Integer.parseInt(simpleConfManager.getConfItem("biomeCounter"));
		biomeMaxSizeX = Integer.parseInt(simpleConfManager.getConfItem("biomeMaxSizeX"));
		caveMaxHeight = Integer.parseInt(simpleConfManager.getConfItem("caveMaxHeight"));
		caveMinHeight = Integer.parseInt(simpleConfManager.getConfItem("caveMinHeight"));
		caveDirtSparctiy = Integer.parseInt(simpleConfManager.getConfItem("caveDirtSparctiy"));
		
		currentBiome = new Random().nextInt(2);
	}
	
	
	
	
	public Byte[][] generateSurfaceChunk(Chunk chunk) {
		
		
		Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
		
		if (biomeCounter > biomeMaxSizeX) {
			
			currentBiome = new Random().nextInt(BlockTypes.totalBiomes);
			biomeCounter = 0;
		}
	
		chunkData = heightMapGenerator.getChunkHeightMap(chunk, currentBiome);
		
		biomeCounter ++;
		
		return chunkData;
		
	}
	
	
	public Byte[][] generateCaveChunk(Chunk chunk) {
		
		
		Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
		
		chunkData = heightMapGenerator.getChunkCaveMap(chunk, new Random().nextInt(caveMaxHeight) + 1, new Random().nextInt(caveMinHeight) + 1, new Random().nextInt(caveDirtSparctiy) + 1);

		return chunkData;
		
	}
	

}
