package com.rootsecks.SimpleWorld.NPCManager;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class NPCGenerator {
	
	SimpleConfManager simpleConfManager;
	
	int generateRange;
	
	public NPCGenerator(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		generateRange = 513;
		
	}

	public NPC spawnNPC(WorldManager worldManager, Player player) {
		
		
		NPC npc = new NPC(simpleConfManager);
		
		int randXPos;
		
		int randX = new Random().nextInt(generateRange);
		
		if (new Random().nextBoolean()) {

			randXPos = Math.round(player.getPlayerPosX() - (Gdx.graphics.getWidth()/2) - randX);
			
		} else {

			randXPos = Math.round(player.getPlayerPosX() + (Gdx.graphics.getWidth()/2) + randX);

		}
		
		npc.setNPCPosX(randXPos);
		
		npc.setNPCPosY(513);
		
		
		return npc;
		
		
	}
	
	

}
