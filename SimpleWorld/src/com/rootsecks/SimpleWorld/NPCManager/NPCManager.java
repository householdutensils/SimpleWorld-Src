package com.rootsecks.SimpleWorld.NPCManager;

import java.util.ArrayList;

import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class NPCManager {

	SimpleConfManager simpleConfManager;

	int maxNPCs;
	int genNPCChance;
	
	NPCGenerator npcGenerator;
	
	ArrayList<NPC> npcList;
	
	NPCCollisionManager npcCollisionManager;
	
	public NPCManager(SimpleConfManager simpleConfManager) {	
		
		this.simpleConfManager = simpleConfManager;
				
		maxNPCs = Integer.parseInt(simpleConfManager.getConfItem("maxNPCs"));
			
		npcList = new ArrayList<NPC>();
		
		npcGenerator = new NPCGenerator(simpleConfManager);
		
		npcCollisionManager = new NPCCollisionManager(simpleConfManager);
		
	}
	
	
	
	public ArrayList<NPC> getNpcList() {
		return npcList;
	}



	public void setNpcList(ArrayList<NPC> npcList) {
		this.npcList = npcList;
	}



	public void update(float delta, WorldManager worldManager, Player player, float currentTime, int totalTimeCylce) {
	
		
		for (int num = 0; num < npcList.size(); num ++ ) {
			
			
			if (npcList.get(num).getNpcHealth() <= 0) {
				
				npcList.remove(num);
				
			}

			
		}
		
		
		if (currentTime > (totalTimeCylce/2)) {
			
			if (npcList.size() < maxNPCs) {
			
				npcList.add(npcGenerator.spawnNPC(worldManager, player));
			
			}
		
		} else {
			
			for (int num = 0; num < npcList.size(); num ++ ) {
				
				if (npcList.get(num).getNPCPosY() > 0) {
					npcList.get(num).setNpcHealth(npcList.get(num).getNpcHealth() - 1 );
				}
				
			}
			
			
		}

		
		
		
		
		
		for (int num = 0; num < npcList.size(); num ++ ) {
			
			
			
			
			
			
			if (npcList.get(num).getNPCPosX() > player.getPlayerPosX()) {
				
				npcList.get(num).setVelocityX(npcList.get(num).getVelocityX() - npcList.get(num).getNpcSpeed());
				
			}  else {
				
				if (npcList.get(num).getVelocityX() < 0) {
					
					npcList.get(num).setVelocityX(npcList.get(num).getVelocityX() + npcList.get(num).getNpcSpeed());
					
				}
			
			} 
			
			
			if (npcList.get(num).getNPCPosX() < player.getPlayerPosX()) {
				
				npcList.get(num).setVelocityX(npcList.get(num).getVelocityX() + npcList.get(num).getNpcSpeed());
				
			} else {
				
				if (npcList.get(num).getVelocityX() >  0) {
					
					npcList.get(num).setVelocityX(npcList.get(num).getVelocityX() - npcList.get(num).getNpcSpeed());
					
				}
				
			}
			
			
			
			
			
			
			
			
			if (npcList.get(num).isJumping() && npcList.get(num).getVelocityY() == 0 && npcList.get(num).getOnFloor()) {
				
				npcList.get(num).setVelocityY(npcList.get(num).getVelocityY() + npcList.get(num).getJumpSpeed());
				
				npcList.get(num).setOnFloor(false);
				
			} else {
				
				npcList.get(num).setVelocityY(npcList.get(num).getVelocityY() - npcList.get(num).getNpcGravity());
				
			}
			
			
			npcCollisionManager.npcCollision(npcList.get(num), worldManager, delta, player);
			
			npcList.get(num).update(delta);
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
}
