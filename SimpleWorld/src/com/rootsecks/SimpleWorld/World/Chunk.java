package com.rootsecks.SimpleWorld.World;

import com.badlogic.gdx.graphics.Mesh;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;

public class Chunk {
	
	//Config manager
	SimpleConfManager simpleConfManager;
	
	
	
	//Byte array that holds the chunks blocks
	Byte[][] chunkData;
	
	//Chunk Coords in the world
	int chunkX;
	int chunkY;
	
	//Size of the chunks
	int chunkSizeX;
	int chunkSizeY;
	
	Mesh chunkMesh;
	
	int chunkBG;
	
	public Chunk(int chunkX, int chunkY, SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		
		this.chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		this.chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		
		//Create the array for the blocks;
		chunkData = new Byte[chunkSizeX][chunkSizeY];
	
	}
	
	
	public int getChunkBG() {
		
		return chunkBG;
		
	}
	
	public void setChunkBG(int chunkBG) {
		
		this.chunkBG = chunkBG;
		
	}
	
	
	
	///Getters and Setters

	public Byte[][] getChunkData() {
		return chunkData;
	}

	public void setChunkData(Byte[][] chunkData) {
		this.chunkData = chunkData;
	}

	public int getChunkX() {
		return chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}





	public void setMesh(Mesh chunkMesh) {
		
		this.chunkMesh =chunkMesh;
		
	}
	
	public Mesh getMesh() {
		
		return chunkMesh;
		
	}
	
	
	
	
	
	

}
