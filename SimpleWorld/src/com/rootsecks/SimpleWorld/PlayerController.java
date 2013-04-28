package com.rootsecks.SimpleWorld;

import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.SimpleUI.MouseHandler;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class PlayerController {
	
	SimpleConfManager simpleConfManager;
	
	
	//Keys
	Boolean leftPressed = false;
	Boolean rightPressed = false;
	Boolean jumpPressed = false;
	
	//
	int mouseX;
	int mouseY;
	Boolean mouseLeftPressed = false;
	Boolean mouseRightPressed = false;
	
	CollisionManager collisionManager;
	
	PickPlaceController pickPlaceController;
	
	public PlayerController(SimpleConfManager simpleConfManager) {
		
		
		this.simpleConfManager = simpleConfManager;
		
		this.collisionManager = new CollisionManager(simpleConfManager);
		
		this.pickPlaceController = new PickPlaceController(simpleConfManager);
		
	}
	
	
	
	
	public void update(float delta, Player player, WorldManager worldManager, MouseHandler mouseHandler) {
		
		if (leftPressed) {
			
			player.setVelocityX(player.getVelocityX() - player.getSpeed());
			
		} else {
			
			if (player.getVelocityX() < 0) {
				
				player.setVelocityX(player.getVelocityX() + player.getSpeed());
				
			}
		
		}
		
		if (rightPressed) {
			
			player.setVelocityX(player.getVelocityX() + player.getSpeed());
			
		} else {
			
			if (player.getVelocityX() >  0) {
				
				player.setVelocityX(player.getVelocityX() - player.getSpeed());
				
			}
			
		}
		
		if (jumpPressed && player.getVelocityY() == 0 && player.getOnFloor()) {
			
			player.setVelocityY(player.getVelocityY() + player.getJumpSpeed());
			
			player.setOnFloor(false);
			
		} else {
			
			player.setVelocityY(player.getVelocityY() - player.playerGravity());
			
		}
		
		
		if (mouseLeftPressed) {
			
			pickPlaceController.pickDestory(worldManager, mouseHandler, player);
			
		} else if (mouseRightPressed) {
			
			pickPlaceController.pickPlace(worldManager, mouseHandler, player);
			
		}
		
		
		collisionManager.playerCollision(player, worldManager, delta);
		
		player.update(delta);
		
		
	}
	
	
	
	
	
	public void leftPressed(Boolean pressed) {
		
		this.leftPressed = pressed;
		
	}
	
	public void rightPressed(Boolean pressed) {
		
		this.rightPressed = pressed;
		
	}
	
	
	public void jumpPressed(Boolean pressed) {
		
		this.jumpPressed = pressed;
		
	}
	
	
	public void setMouseLeftPressed(boolean mouseLeftPressed) {

		this.mouseLeftPressed = mouseLeftPressed;
		
	}	
	
	public void setMouseRightPressed(boolean mouseRightPressed) {
		
		this.mouseRightPressed = mouseRightPressed;
		
	}
	

}
