package com.rootsecks.SimpleWorld;

import com.badlogic.gdx.math.Vector2;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class CollisionManager {
	
	
	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	BlockTypes blockTypes;

	public CollisionManager(SimpleConfManager simpleConfManager) {
		this.simpleConfManager = simpleConfManager;
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		this.blockTypes = new BlockTypes();
		
	}

	public void playerCollision(Player player, WorldManager worldManager, float delta) {
		
		Vector2 playerProjection = new Vector2(player.getPlayerPosition());
		Vector2 playerAcc = new Vector2(player.getPlayerAcceleration());
		playerAcc.x = 0;
		playerProjection.add(playerAcc.tmp().mul(delta));
		
		Chunk collidingChunk = worldManager.getWorld().getChunkAt(playerProjection.x, playerProjection.y);
		
		if (collidingChunk != null) {
			
			for (int x = 0; x < chunkSizeX; x ++ ) {
				
				for ( int y = 0; y < chunkSizeY; y ++ ) {

					if (playerProjection.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && playerProjection.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
						
						if ((playerProjection.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjection.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
							|| (playerProjection.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjection.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
							
				
							Byte[][] chunkData = collidingChunk.getChunkData();

							if (blockTypes.isVisible(chunkData[x][y])) {

								if (player.getVelocityY() < player.getFallDamageThreshold()) {
									
									player.setHealth(player.getHealth() - Math.round((Math.abs(player.getVelocityY()) / player.getFallDamageMultiplier())));
									
								}
								
								player.setVelocityY(0);
								player.setOnFloor(true);
								
								

							}
							
						}

					} else if (playerProjection.x + 16 >= (collidingChunk.getChunkX() + (x * blockSizeX)) && playerProjection.x + 16 <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
	
						if ((playerProjection.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjection.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
								|| (playerProjection.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjection.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
															

							Byte[][] chunkData = collidingChunk.getChunkData();
						
	
							if (blockTypes.isVisible(chunkData[x][y])) {

								if (player.getVelocityY() < player.getFallDamageThreshold()) {
									
									player.setHealth(player.getHealth() - Math.round((Math.abs(player.getVelocityY()) / player.getFallDamageMultiplier())));
									
								}
								
								
								player.setVelocityY(0);
								player.setOnFloor(true);

							}
							
						}
						
						
						
						
					}
					
				}
				
			}
			
				
		
		Vector2 playerProjectionY = new Vector2(player.getPlayerPosition());
		Vector2 playerAccY = new Vector2(player.getPlayerAcceleration());
		playerAccY.y = 0;
		playerProjectionY.add(playerAccY.tmp().mul(delta));
		
		for (int x = 0; x < chunkSizeX; x ++ ) {
			
			for ( int y = 0; y < chunkSizeY; y ++ ) {
				

				if (playerProjectionY.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && playerProjectionY.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
					
					if ((playerProjectionY.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjectionY.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
						|| (playerProjectionY.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjectionY.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
						

						Byte[][] chunkData = collidingChunk.getChunkData();
	
						if (blockTypes.isVisible(chunkData[x][y])) {
							
							player.setVelocityX(0);
							
						}
						
					}

				} else if (playerProjectionY.x + 16 >= (collidingChunk.getChunkX() + (x * blockSizeX)) && playerProjectionY.x + 16 <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
					
					if ((playerProjectionY.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjectionY.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
							|| (playerProjectionY.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && playerProjectionY.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
													

						Byte[][] chunkData = collidingChunk.getChunkData();

						if (blockTypes.isVisible(chunkData[x][y])) {

							player.setVelocityX(0);

						}
						
					}
				}
				
			}

		
		}
		
	}
		
		
	}
	
	
	

}
