package com.rootsecks.SimpleWorld.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.rootsecks.SimpleWorld.World.Chunk;




public class SimpleConfManager {
	
	private static final Logger logger = Logger.getLogger(SimpleConfManager.class.getName());

	
	private static final String confFileName = "SimpleWorld.conf";
	
	
	//Configuration Map. Holds strings that relate to conf items that can be requested by the rest of the application
	HashMap<String, String> confMap;
	
	TextureManager textureManager;
	
	public SimpleConfManager() {
		
		confMap = new HashMap<String, String>();

		loadConfigItems();
		
		textureManager = new TextureManager();
		
	}
	
	
	private void loadConfigItems() {

	
        File chunkFile = new File(confFileName);
        
        if (chunkFile.exists()) {
        
	        FileReader fr = null;
	        try {
	            fr = new FileReader(chunkFile);
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
	        }
	         BufferedReader br = new BufferedReader(fr);
	         	
	         	String line = null;
	         	
        		try {
							line = br.readLine();
						} catch (IOException e) {
					
							e.printStackTrace();
						}

				while (line != null) {

					if (!line.startsWith("#")) {
						
						String[] readLine = line.split("=");	
						confMap.put(readLine[0], readLine[1]);
						logger.log(Level.INFO, "Config Item Loaded: " + readLine[0] + "=" + readLine[1]);
						
					} else {
						
						logger.log(Level.INFO, "Comment Found: " + line);
				
					}

					
					
					try {
						line = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
	         	}

	        try {
	            br.close();
	        } catch (IOException ex) {
	            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        
        }

		
	}


	public void setDebugMode(Boolean debugMode) {
		
		confMap.remove("debugMode");
		confMap.put("debugMode", Boolean.toString(debugMode));
		
	}
	
	//Return the item in the map with the requested index
	public String getConfItem(String confIndex) {
		
		return confMap.get(confIndex);
		
	}
	
	public TextureManager getTextureManager() {
		return textureManager;
	}


	public void setTextureManager(TextureManager textureManager) {
		this.textureManager = textureManager;
	}



	
}
