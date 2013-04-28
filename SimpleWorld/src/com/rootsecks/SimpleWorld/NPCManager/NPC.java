package com.rootsecks.SimpleWorld.NPCManager;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;

public class NPC {
	
	
	SimpleConfManager simpleConfManager;
	
	//ALL of this needs to be moved into the conf manager
	Vector2 npcPosition = new Vector2();
	
	float npcSpeed = 1.5f;
	float jumpSpeed = 128f;
	float npcGravity = 1f;
	Vector2 npcAcceleration = new Vector2();
	float maxJumpAcceleration = 3f;
	float maxPlayerAcceleration = 5f;
	float fallDamageThreshold = -200f;
	
	int getFallDamageMultiplier = 10;
	
	int npcHealth;
	int npcMaxHealth;
	
	Boolean onFloor;
	Boolean isJumping;
	
	int npcDamage;

	public NPC(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		isJumping = false;
		onFloor = false;
				
		npcMaxHealth = Integer.parseInt(simpleConfManager.getConfItem("npcMaxHealth"));
		
		npcMaxHealth = new Random().nextInt(npcMaxHealth - npcMaxHealth/2) + (npcMaxHealth/2);
		
		npcHealth = npcMaxHealth;
		
		npcDamage = Integer.parseInt(simpleConfManager.getConfItem("npcDamage"));
		
		npcDamage = new Random().nextInt(npcDamage) + 1;
		
			
	}
	
	
	public void update(float delta) {
		
		npcPosition.add(npcAcceleration.tmp().mul(delta));

		
	}

	public float getNPCPosX() {
		return npcPosition.x;
	}

	public float getNPCPosY() {
		return npcPosition.y;
	}
	
	public void setNPCPosX(float npcPosX) {
		this.npcPosition.x = npcPosX;
	}

	public void setNPCPosY(float npcPosY) {
		this.npcPosition.y = npcPosY;
	}
	
	public Vector2 getNpcPosition() {
		return npcPosition;
	}

	public void setNpcPosition(Vector2 npcPosition) {
		this.npcPosition = npcPosition;
	}

	public float getNpcSpeed() {
		return npcSpeed;
	}

	public void setNpcSpeed(float npcSpeed) {
		this.npcSpeed = npcSpeed;
	}

	public float getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public float getNpcGravity() {
		return npcGravity;
	}

	public void setNpcGravity(float npcGravity) {
		this.npcGravity = npcGravity;
	}

	public Vector2 getNpcAcceleration() {
		return npcAcceleration;
	}

	public void setNpcAcceleration(Vector2 npcAcceleration) {
		this.npcAcceleration = npcAcceleration;
	}

	public float getMaxJumpAcceleration() {
		return maxJumpAcceleration;
	}

	public void setMaxJumpAcceleration(float maxJumpAcceleration) {
		this.maxJumpAcceleration = maxJumpAcceleration;
	}

	public float getMaxPlayerAcceleration() {
		return maxPlayerAcceleration;
	}

	public void setMaxPlayerAcceleration(float maxPlayerAcceleration) {
		this.maxPlayerAcceleration = maxPlayerAcceleration;
	}

	public float getFallDamageThreshold() {
		return fallDamageThreshold;
	}

	public void setFallDamageThreshold(float fallDamageThreshold) {
		this.fallDamageThreshold = fallDamageThreshold;
	}

	public int getGetFallDamageMultiplier() {
		return getFallDamageMultiplier;
	}

	public void setGetFallDamageMultiplier(int getFallDamageMultiplier) {
		this.getFallDamageMultiplier = getFallDamageMultiplier;
	}

	public int getNpcHealth() {
		return npcHealth;
	}

	public void setNpcHealth(int npcHealth) {
		this.npcHealth = npcHealth;
	}

	public int getNpcMaxHealth() {
		return npcMaxHealth;
	}

	public void setNpcMaxHealth(int npcMaxHealth) {
		this.npcMaxHealth = npcMaxHealth;
	}

	public Boolean getOnFloor() {
		return onFloor;
	}

	public void setOnFloor(Boolean onFloor) {
		this.onFloor = onFloor;
	}

	public void setVelocityY(float f) {
		
		this.npcAcceleration.y = f;
		
	}
	
	public void setVelocityX(float f) {
		
		this.npcAcceleration.x = f;
		
	}

	public float getVelocityY() {
		
		return this.npcAcceleration.y;
	}
	
	public float getVelocityX() {
		
		return this.npcAcceleration.x;
	}

	public boolean isJumping() {

		return isJumping;
		
	}
	
	
	public Boolean getIsJumping() {
		return isJumping;
	}


	public void setIsJumping(Boolean isJumping) {
		this.isJumping = isJumping;
	}


	public int getNPCDamage() {
		return npcDamage;
	}
	
}
