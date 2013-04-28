package com.rootsecks.SimpleWorld.NPCManager;

import java.util.Random;
import com.badlogic.gdx.math.Vector2;
import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;
import com.rootsecks.SimpleWorld.WorldManager.MeshFactory;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class NPCCollisionManager {
	

	SimpleConfManager simpleConfManager;
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	BlockTypes blockTypes;

	int chanceToBreak;
	
	public NPCCollisionManager(SimpleConfManager simpleConfManager) {
		this.simpleConfManager = simpleConfManager;
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		this.blockTypes = new BlockTypes();
		
		chanceToBreak = Integer.parseInt(simpleConfManager.getConfItem("chanceToBreak"));
		
	}

	public void npcCollision(NPC npc, WorldManager worldManager, float delta, Player player) {
		
	
		Vector2 npcProjection = new Vector2(npc.getNpcPosition());
		Vector2 npcAcc = new Vector2(npc.getNpcAcceleration());
		npcAcc.x = 0;
		npcProjection.add(npcAcc.tmp().mul(delta));
		
		
		if ((npcProjection.x > player.getPlayerPosX() && npcProjection.x < player.getPlayerPosX() + 16)
				|| (npcProjection.x + 16 > player.getPlayerPosX() && npcProjection.x + 16 <  player.getPlayerPosX() + 16)) {
			
			if ((npcProjection.y > player.getPlayerPosY() && npcProjection.y < player.getPlayerPosY() + 32)
					|| (npcProjection.y + 32 > player.getPlayerPosY() && npcProjection.y + 32 <  player.getPlayerPosY() + 32)) {
				
				
				
					player.setHealth(player.getHealth() - npc.getNPCDamage());
				
				
			}
			
			
		}
		
		
		
		
		
		Chunk collidingChunk = worldManager.getWorld().getChunkAt(npcProjection.x, npcProjection.y);
		
		if (collidingChunk != null) {
		
			for (int x = 0; x < chunkSizeX; x ++ ) {
				
				for ( int y = 0; y < chunkSizeY; y ++ ) {
					
					if (npcProjection.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && npcProjection.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
						
						if ((npcProjection.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjection.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
							|| (npcProjection.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjection.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
							
							
							Byte[][] chunkData = collidingChunk.getChunkData();
			
							if (blockTypes.isVisible(chunkData[x][y])) {

								if (npc.getVelocityY() < npc.getFallDamageThreshold()) {
									
									npc.setNpcHealth(npc.getNpcHealth() - Math.round((Math.abs(npc.getVelocityY()) / npc.getGetFallDamageMultiplier())));
									
								}
								
								if ((chunkData[x][y] == BlockTypes.blockGrass)
									|| (chunkData[x][y] == BlockTypes.blockDirt)
									|| (chunkData[x][y] == BlockTypes.blockSnow)
									|| (chunkData[x][y] == BlockTypes.blockSand)) {
									
									
									if (new Random().nextInt(chanceToBreak) == 0) {
										
										collidingChunk.getChunkData()[x][y] = BlockTypes.blockAir;
										
										MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
										meshFactory.createChunkMesh();
										
									}
									
								}
								
								npc.setVelocityY(0);
								npc.setOnFloor(true);
								
								

							}
							
						}

					} else if (npcProjection.x + 16 >= (collidingChunk.getChunkX() + (x * blockSizeX)) && npcProjection.x + 16 <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
	
						if ((npcProjection.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjection.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
								|| (npcProjection.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjection.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
															
							
							Byte[][] chunkData = collidingChunk.getChunkData();
						
					
							if (blockTypes.isVisible(chunkData[x][y])) {

								if (npc.getVelocityY() < npc.getFallDamageThreshold()) {
									
									npc.setNpcHealth(npc.getNpcHealth() - Math.round((Math.abs(npc.getVelocityY()) / npc.getGetFallDamageMultiplier())));
									
								}
								
								if ((chunkData[x][y] == BlockTypes.blockGrass)
										|| (chunkData[x][y] == BlockTypes.blockDirt)
										|| (chunkData[x][y] == BlockTypes.blockSnow)
										|| (chunkData[x][y] == BlockTypes.blockSand)) {
										
										
										if (new Random().nextInt(chanceToBreak) == 0) {
											
											collidingChunk.getChunkData()[x][y] = BlockTypes.blockAir;
											
											MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
											meshFactory.createChunkMesh();
											
										}
										
									}
								npc.setVelocityY(0);
								npc.setOnFloor(true);

							}
							
						}

					}
					
				}
				
			}
			
				
		
		Vector2 npcProjectionY = new Vector2(npc.getNpcPosition());
		Vector2 npcAccY = new Vector2(npc.getNpcAcceleration());
		npcAccY.y = 0;
		npcProjectionY.add(npcAccY.tmp().mul(delta));
		
		for (int x = 0; x < chunkSizeX; x ++ ) {
			
			for ( int y = 0; y < chunkSizeY; y ++ ) {
				
		
				if (npcProjectionY.x >= (collidingChunk.getChunkX() + (x * blockSizeX)) && npcProjectionY.x <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
					
					if ((npcProjectionY.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjectionY.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
						|| (npcProjectionY.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjectionY.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
						
						
						Byte[][] chunkData = collidingChunk.getChunkData();
					
				
						if (blockTypes.isVisible(chunkData[x][y])) {
							
							if ((chunkData[x][y] == BlockTypes.blockGrass)
									|| (chunkData[x][y] == BlockTypes.blockDirt)
									|| (chunkData[x][y] == BlockTypes.blockSnow)
									|| (chunkData[x][y] == BlockTypes.blockSand)) {
									
									
									if (new Random().nextInt(chanceToBreak) == 0) {
										
										collidingChunk.getChunkData()[x][y] = BlockTypes.blockAir;
										
										MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
										meshFactory.createChunkMesh();
										
									}
									
								}
							
							npc.setVelocityX(0);
							npc.setIsJumping(true);
						}
						
					}

				} else if (npcProjectionY.x + 16 >= (collidingChunk.getChunkX() + (x * blockSizeX)) && npcProjectionY.x + 16 <= (collidingChunk.getChunkX() + (x * blockSizeX) + blockSizeX)) {
					
					if ((npcProjectionY.y >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjectionY.y <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))
							|| (npcProjectionY.y + 32 >= (collidingChunk.getChunkY() + (y * blockSizeY)) && npcProjectionY.y + 32 <= (collidingChunk.getChunkY() + (y * blockSizeY) + blockSizeY))) {
													
						
						Byte[][] chunkData = collidingChunk.getChunkData();
			
						if (blockTypes.isVisible(chunkData[x][y])) {
							
							if ((chunkData[x][y] == BlockTypes.blockGrass)
									|| (chunkData[x][y] == BlockTypes.blockDirt)
									|| (chunkData[x][y] == BlockTypes.blockSnow)
									|| (chunkData[x][y] == BlockTypes.blockSand)) {
									
									
									if (new Random().nextInt(chanceToBreak) == 0) {
										
										collidingChunk.getChunkData()[x][y] = BlockTypes.blockAir;
										
										MeshFactory meshFactory = new MeshFactory(simpleConfManager, collidingChunk);
										meshFactory.createChunkMesh();
										
									}
									
								}

							npc.setVelocityX(0);
							
							npc.setIsJumping(true);

						}
						
					}
				}
				
			}

		
		}
		
	}
		
		
	}
	
	
	

}
