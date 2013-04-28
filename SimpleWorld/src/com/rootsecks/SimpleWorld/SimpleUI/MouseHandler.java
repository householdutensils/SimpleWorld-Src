package com.rootsecks.SimpleWorld.SimpleUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.Configuration.TextureManager;

public class MouseHandler {
	
	SimpleConfManager simpleConfManager;
	
	int mouseX;
	int mouseY;
	int unAdjustedX;
	int unAdjustedY;
	
	int xSpot;
	int ySpot;
	
	Sprite mouseSprite;
	SpriteBatch mouseSpriteBatch;

	
	Boolean mouseLeftPressed;
	Boolean mouseRightPressed;

	TextureManager textureManager;
	
	Boolean withinPickZone;
	
	int pickRangeX;
	int pickRangeY;
	
	public MouseHandler(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		textureManager = simpleConfManager.getTextureManager();
		
		mouseLeftPressed = false;
		
		mouseSprite = new Sprite(textureManager.getMouseTexture());
		mouseSprite.setSize(16, 16);
		mouseSprite.setPosition(0, 0);
		mouseSpriteBatch = new SpriteBatch();	
		
		xSpot = 0;
		ySpot = textureManager.getMouseTexture().getHeight();
		
		withinPickZone = false;
		
		pickRangeX = Integer.parseInt(simpleConfManager.getConfItem("pickRangeX"));
		pickRangeY = Integer.parseInt(simpleConfManager.getConfItem("pickRangeY"));
		
	}
	
	
	
	public void render(Player player) {
		
			if (getMouseX() > (Gdx.graphics.getWidth()/2) - (pickRangeX/2) && getMouseX() < (Gdx.graphics.getWidth()/2) + (pickRangeX/2)) {
				
				if (getMouseY() > (Gdx.graphics.getHeight()/2) - (pickRangeY/2) && getMouseY() < (Gdx.graphics.getHeight()/2) + (pickRangeY/2)) {
					
					setWithinPickZone(true);
					
				} else {
					
					setWithinPickZone(false);
					
				}

			} else {
				
				setWithinPickZone(false);
				
			}
		
					
			if (mouseLeftPressed) {
				mouseSprite.setTexture(textureManager.getMouseTextureClick());
			} else if (getWithinPickZone()) {
				mouseSprite.setTexture(textureManager.getMouseTexture());
			} else {			
				mouseSprite.setTexture(textureManager.getMouseTextureOut());	
			}
			
				
			Gdx.input.setCursorCatched(true);

			
			mouseSpriteBatch.begin();
			
			setMouseX(Gdx.input.getX());
			setMouseY(Gdx.input.getY());
			
			
			mouseSprite.setX(getMouseX());
			mouseSprite.setY(getMouseY());

			mouseSprite.draw(mouseSpriteBatch);

			mouseSpriteBatch.end();
			
	}
	
	
	public void setMouseX(int mouseX) {
		
		this.mouseX =  mouseX - xSpot;
		this.unAdjustedX = mouseX;
		
	}
	
	public void setMouseY(int mouseY) {

		this.mouseY = Gdx.graphics.getHeight() - mouseY - 1 - ySpot;
		this.unAdjustedY = mouseY;
	}
	
	public int getUnAdjustedX() {
		return unAdjustedX;
	}



	public int getUnAdjustedY() {
		return unAdjustedY;
	}



	public int getMouseX() {
		
		return mouseX;
		
	}
	
	public int getMouseY() {
		
		return mouseY;
		
	}

	public void setMouseLeftPressed(boolean b) {
		
		this.mouseLeftPressed = b;
		
	}

	
	public void setMouseRightPressed(boolean b) {
		
		this.mouseRightPressed = b;
		
	}
	
	public Boolean getWithinPickZone() {
		return withinPickZone;
	}



	public void setWithinPickZone(Boolean withinPickZone) {
		this.withinPickZone = withinPickZone;
	}

}
