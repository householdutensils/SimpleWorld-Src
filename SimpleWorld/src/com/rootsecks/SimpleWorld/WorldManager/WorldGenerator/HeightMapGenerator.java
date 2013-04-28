package com.rootsecks.SimpleWorld.WorldManager.WorldGenerator;

import java.util.Random;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;

public class HeightMapGenerator {
	
	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	NoiseGenerator noiseGenerator;
	
	public HeightMapGenerator(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		this.noiseGenerator = new NoiseGenerator(new Random().nextInt());
		
	}
	
	
	
	
	
	public Byte[][] getChunkHeightMap(Chunk chunk, int biomeID) {
		
		Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
		
		
		for ( int x = 0; x < chunkSizeX; x ++ ) {
			
			
			double noiseHeight = noiseGenerator.smoothNoise2D(x + chunk.getChunkX() , x + chunk.getChunkX());
			
            int yHeight = (int) (noiseHeight * ((chunkSizeY) / 2) + ((chunkSizeY)) / 2); 
												
			for ( int y = 0; y < chunkSizeY; y ++ ) {
					
				switch (biomeID) {
				
					case BlockTypes.biomePlains:
						if (y == yHeight) {
												
							chunkData[x][y] = BlockTypes.blockGrass;
							
						} else if (y < yHeight) {
							
							chunkData[x][y] = BlockTypes.blockDirt;
							
						} else {
							
							chunkData[x][y] = BlockTypes.blockAir;
							
						}
						break;
						
					case BlockTypes.biomeDesert:
						if (y == yHeight) {
												
							chunkData[x][y] = BlockTypes.blockSand;
							
						} else if (y < yHeight) {
							
							chunkData[x][y] = BlockTypes.blockSand;
							
						} else {
							
							chunkData[x][y] = BlockTypes.blockAir;
							
						}
						break;
						
					case BlockTypes.biomeTundra:
						if (y == yHeight) {
												
							chunkData[x][y] = BlockTypes.blockSnow;
							
						} else if (y < yHeight) {
							
							chunkData[x][y] = BlockTypes.blockSnow;
							
						} else {
							
							chunkData[x][y] = BlockTypes.blockAir;
							
						}
						break;
						
					default:
						if (y == yHeight) {
							
							chunkData[x][y] = BlockTypes.blockGrass;
							
						} else if (y < yHeight) {
							
							chunkData[x][y] = BlockTypes.blockDirt;
							
						} else {
							
							chunkData[x][y] = BlockTypes.blockAir;
							
						}
						break;
					
					}
					

					
			}
			
			
		}
		
		
		
		return chunkData;
		
		
	}
	
	
	public Byte[][] getChunkCaveMap(Chunk chunk, int caveSparcity, int caveMin, int dirtSparcity) {
		
		Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
		
		for ( int x = 0; x < chunkSizeX; x ++ ) {
			
					
			double noiseHeight = noiseGenerator.smoothNoise2D(x + chunk.getChunkX() , x + chunk.getChunkX());

			
            int yHeight = (int) (noiseHeight * ((chunkSizeY) / 2) + ((chunkSizeY)) / 2); 
			
            Random randNum = new Random();
									
			for ( int y = 0; y < chunkSizeY; y ++ ) {
				
				int caveHeight = randNum.nextInt(caveSparcity) + caveMin;
				
				if (y < yHeight) {
					
					if (new Random().nextInt(dirtSparcity) == 0) {
						
						chunkData[x][y] = BlockTypes.blockDirt;
						
					} else {
						
						chunkData[x][y] = BlockTypes.blockStone;
						
					}
					
				} else if (y < yHeight + caveHeight && y > yHeight) {
					
					chunkData[x][y] = BlockTypes.blockAir;
					
				} else {
					
					if (new Random().nextInt(dirtSparcity) == 0) {
						
						chunkData[x][y] = BlockTypes.blockDirt;
						
					} else {
						
						chunkData[x][y] = BlockTypes.blockStone;
						
					}
					
				}
					
			}
			
			
		}
		
		
		
		return chunkData;
		
		
	}
	

}
