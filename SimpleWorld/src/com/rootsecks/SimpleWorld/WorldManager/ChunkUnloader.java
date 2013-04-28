package com.rootsecks.SimpleWorld.WorldManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;

public class ChunkUnloader {
	
	SimpleConfManager simpleConfManager;
	
	String worldPrefix;
	
	int chunkSizeX;
	int chunkSizeY;
	
	public ChunkUnloader(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		worldPrefix = simpleConfManager.getConfItem("worldPrefix");
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		
	}
	
	
	
	public void unloadChunk(Chunk chunk) {
		
		Boolean breakSave = false;
		
		Byte[][] chunkData = chunk.getChunkData();
		
		int chunkX = chunk.getChunkX();
        int chunkY = chunk.getChunkY();
                        
        String chunkFileName = worldPrefix	 + Integer.toString(chunkX) + "." + Integer.toString(chunkY);

        File chunkFile = new File(chunkFileName);
        chunkFile.delete();
        
        FileWriter fw = null;
        
        try {
            fw = new FileWriter(chunkFile);
        } catch (IOException ex) {
        	breakSave = true;
            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!breakSave) {
	        BufferedWriter bw = new BufferedWriter(fw);
	
	        for (int x = 0; x < chunkSizeX; x ++ ) {
	            for ( int y = 0; y < chunkSizeY; y ++ ) {
	
	                    try {
	
	                        bw.write(Byte.toString(chunkData[x][y]));
	
	                        bw.newLine();
	
	                    } catch (IOException ex) {
	
	                        Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
	                    }
	
	            }
	        }
	
	        try {
	            bw.close();
	        } catch (IOException ex) {
	            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
	        }
        
        }
        
		
	}
	
	
	

}
