package com.rootsecks.SimpleWorld;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.badlogic.gdx.math.Vector2;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;
import com.rootsecks.SimpleWorld.WorldRenderer.Camera;

public class Player {
	
	private static final Logger logger = Logger.getLogger(Player.class.getName());
	
	//COnfigmanager
	SimpleConfManager simpleConfManager;

	//ALL of this needs to be moved into the conf manager
	Vector2 playerPosition = new Vector2();
	Vector2 playerAcceleration = new Vector2();
	
	float maxJumpAcceleration;
	float maxPlayerAcceleration;
	float playerSpeed;
	float jumpSpeed;
	float playerGravity;
	float fallDamageThreshold;
	int getFallDamageMultiplier;
	
	int playerHealth;
	int playerMaxHealth;
	
	Boolean onFloor;
	
	
	String worldPrefix;
	String chunkFileName;

	//Camera
	Camera camera;
	
	int currentlySelectedBlockID;
	
	int[] blockInventory;
		
	
	int daysSurvived;
	
	
	public Player(SimpleConfManager simpleConfManager, int playerPosX, int playerPosY) {
		
		this.simpleConfManager = simpleConfManager;
		
		worldPrefix = simpleConfManager.getConfItem("worldPrefix");

		maxJumpAcceleration = Float.parseFloat(simpleConfManager.getConfItem("maxJumpAcceleration"));
		maxPlayerAcceleration = Float.parseFloat(simpleConfManager.getConfItem("maxPlayerAcceleration"));
		playerSpeed = Float.parseFloat(simpleConfManager.getConfItem("playerSpeed"));
		jumpSpeed = Float.parseFloat(simpleConfManager.getConfItem("jumpSpeed"));
		playerGravity = Float.parseFloat(simpleConfManager.getConfItem("playerGravity"));
		fallDamageThreshold = Float.parseFloat(simpleConfManager.getConfItem("fallDamageThreshold"));
		getFallDamageMultiplier = Integer.parseInt(simpleConfManager.getConfItem("getFallDamageMultiplier"));
		playerMaxHealth = Integer.parseInt(simpleConfManager.getConfItem("playerMaxHealth"));
	
        chunkFileName = worldPrefix	 + "Player";

        blockInventory = new int[BlockTypes.totalBlocks + 1];
        
		this.playerPosition.x = playerPosX;
		this.playerPosition.y = playerPosY;
		
		camera = new Camera();
		
		camera.setPositionX(playerPosX);
		camera.setPositionY(playerPosY);
		
		onFloor = false;
		
		playerHealth = playerMaxHealth;
			
		currentlySelectedBlockID = 0;
		
		daysSurvived = 0;
				
		
		loadPlayer();
		
		
	}
	
	
	
	



	public int getDaysSurvived() {
		return daysSurvived;
	}







	public void setDaysSurvived(int daysSurvived) {
		this.daysSurvived = daysSurvived;
	}


	public Boolean getOnFloor() {
		return onFloor;
	}



	public void setOnFloor(Boolean onFloor) {
		this.onFloor = onFloor;
	}



	public void update(float delta) {

		
		playerPosition.add(playerAcceleration.tmp().mul(delta));
		 
		getCamera().setPositionX(getPlayerPosX());
		getCamera().setPositionY(getPlayerPosY());
		
	}
	
	
	
	public Vector2 getPlayerPosition() {
		
		return playerPosition;
		
	}
	
	public Vector2 getPlayerAcceleration() {
		
		return playerAcceleration;
		
	}




	public float getPlayerPosX() {
		return playerPosition.x;
	}


	public void setPlayerPosX(float playerPosX) {
		this.playerPosition.x = playerPosX;
	}


	public float getPlayerPosY() {
		return playerPosition.y;
	}


	public void setPlayerPosY(float playerPosY) {
		this.playerPosition.y = playerPosY;
	}
	
	public Camera getCamera() {
		return camera;
	}


	public float getVelocityX() {
		return playerAcceleration.x;
	}
	
	
	public float getVelocityY() {
		return playerAcceleration.y;
	}

	
	public void setVelocityX(float velocity) {
		playerAcceleration.x = velocity;
	}
	
	
	public void setVelocityY(float velocity) {
		playerAcceleration.y = velocity;
	}




	public float getSpeed() {
	
		return playerSpeed;
	}



	public float getJumpSpeed() {
		return jumpSpeed;
	}



	public float playerGravity() {
		// TODO Auto-generated method stub
		return playerGravity;
	}

	public float getMaxJumpAcceleration() {
		return maxJumpAcceleration;
	}



	public float getMaxPlayerAcceleration() {
		return maxPlayerAcceleration;
	}


	public void saveForExit() {
		
		HashMap<String, String> playerParameters = new HashMap<String, String>();
	
		playerParameters.put("playerPositionX", Float.toString(playerPosition.x));
		playerParameters.put("playerPositionY", Float.toString(playerPosition.y));
		playerParameters.put("playerAccelerationX", Float.toString(playerAcceleration.x));
		playerParameters.put("playerAccelerationY", Float.toString(playerAcceleration.y));
		playerParameters.put("onFloor", Boolean.toString(onFloor));      
		playerParameters.put("playerHealth", Integer.toString(playerHealth)); 
		playerParameters.put("playerMaxHealth", Integer.toString(playerMaxHealth)); 
		playerParameters.put("daysSurvived", Integer.toString(daysSurvived));     
		
        File deleteChunkFile = new File(chunkFileName);

        deleteChunkFile.delete();

        File chunkFile = new File(chunkFileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(chunkFile);
        } catch (IOException ex) {
            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(fw);
                		
		for ( String playerParametersKey : playerParameters.keySet() ) {
			
                		
        	
                    try {

                        bw.write(playerParametersKey + "=" + playerParameters.get(playerParametersKey));

                        bw.newLine();

                    } catch (IOException ex) {

                        Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
                    }

        		
        		
        	}
		
		
        	try {
				bw.write("--BlockInventory--");
				bw.newLine();
			} catch (IOException e) {
		
				e.printStackTrace();
			}

        	
        	for (int num = 0; num < blockInventory.length; num ++ ) {
        		
        		try {
					bw.write(num + "=" + blockInventory[num]);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        	}
        	
        	try {
				bw.write("==BlockInventory==");
				bw.newLine();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
     

        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
        }

		
		
		
	}

	
	
	
	private void loadPlayer() {
		
		
		HashMap<String, String> playerParameters = new HashMap<String, String>();

		
	        File chunkFile = new File(chunkFileName);
	        
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
	        		
	        		Boolean currentlyReadingBlocks = false;
	        		
					while (line != null) {
						
						logger.log(Level.INFO, "Read Line: " + line);
		         		
						if (!currentlyReadingBlocks) {
							
							if (line.equals("--BlockInventory--")) {
							
								currentlyReadingBlocks = true;
					         		
							} else {
								
								String[] readLine = line.split("=");	
				         		playerParameters.put(readLine[0], readLine[1]);
								
								
							}
							
						} else {
							
							if (line.equals("==BlockInventory==")) {
								
								currentlyReadingBlocks = false;

				         		
							} else {
								
								String[] readLine = line.split("=");	
				         		blockInventory[Integer.parseInt(readLine[0])] = Integer.parseInt(readLine[1]);
								
							}
				
						}
						

		         		
		         		try {
							line = br.readLine();
						} catch (IOException e) {
					
							e.printStackTrace();
						}
		         		
		         	}
	
		        try {
		            br.close();
		        } catch (IOException ex) {
		            Logger.getLogger(Chunk.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        
		        
		        
		        
		        for ( String playerParametersKey : playerParameters.keySet() ) {
		        	
		        	
		        	logger.log(Level.INFO, "Key: " + playerParametersKey + " and value: " + playerParameters.get(playerParametersKey));
		        	
		        	
		        }
		        
		        
		        this.setPlayerPosX(Float.parseFloat(playerParameters.get("playerPositionX")));
		        this.setPlayerPosY(Float.parseFloat(playerParameters.get("playerPositionY")));
		        playerAcceleration = new Vector2(Float.parseFloat(playerParameters.get("playerAccelerationX")), Float.parseFloat(playerParameters.get("playerAccelerationY")));
		        onFloor = Boolean.parseBoolean(playerParameters.get("onFloor"));
		        playerHealth = Integer.parseInt(playerParameters.get("playerHealth"));
		        playerMaxHealth = Integer.parseInt(playerParameters.get("playerMaxHealth"));
		        daysSurvived = Integer.parseInt(playerParameters.get("daysSurvived"));
			
        }
			
	}


	public int getHealth() {
		
		return playerHealth;
	}

	public int getMaxHealth() {
		return playerMaxHealth;
	}


	public int getCurrentlySelectedBlockID() {

		return currentlySelectedBlockID;
	}

	public void increaseSelectedBlock() {
	
		if (currentlySelectedBlockID < BlockTypes.totalBlocks - 1) {
			
			currentlySelectedBlockID ++;
			
		}
		
	}
	

	public void decreaseSelectedBlock() {
		
		if (currentlySelectedBlockID > 0) {
			
			currentlySelectedBlockID --;
			
		}
	}
				
	public int[] getBlockInventory() {
		return blockInventory;
	}

	public float getFallDamageThreshold() {
		return fallDamageThreshold;
	}


	public void setHealth(int i) {

		this.playerHealth = i;
		
	}

	public float getFallDamageMultiplier() {
		
		return getFallDamageMultiplier;
		
	}







	public void clearInventory() {

		
    	for (int num = 0; num < blockInventory.length; num ++ ) {
    		
    		blockInventory[num] = 0;
    		
    	}
		
	}



}
