package com.rootsecks.SimpleWorld.WorldManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;

public class ChunkLoader {
	
	SimpleConfManager simpleConfManager;
	
	String worldPrefix;
	
	int chunkSizeX;
	int chunkSizeY;
	
	public ChunkLoader(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		worldPrefix = simpleConfManager.getConfItem("worldPrefix");
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		
	}
	
	
	
	
	public Byte[][] loadChunk(Chunk chunk) {
		
        String chunkFileName = worldPrefix	 + Integer.toString(chunk.getChunkX()) + "." + Integer.toString(chunk.getChunkY());
		  
        Byte[][] chunkData = new Byte[chunkSizeX][chunkSizeY];
   
        File chunkFile = new File(chunkFileName);
        
        FileReader fr = null;
        try {
            fr = new FileReader(chunkFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
        }
         BufferedReader br = new BufferedReader(fr);

             for (int x = 0; x < chunkSizeX; x ++ ) {
                 for ( int y = 0; y < chunkSizeY; y ++ ) {

	                 try {
	                     chunkData[x][y] = Byte.parseByte(br.readLine());
	                 } catch (IOException ex) {
	                     Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
	                 }
	           
                 }

             }

        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        return chunkData;
		
		
		
		
	}
	
	
	
	
	public Boolean doesFileExist(Chunk chunk) {
		
        String chunkFileName = worldPrefix	 + Integer.toString(chunk.getChunkX()) + "." + Integer.toString(chunk.getChunkY());
		
        File chunkFile = new File(chunkFileName);
        
        return chunkFile.exists();
		
	}
	
	

}
