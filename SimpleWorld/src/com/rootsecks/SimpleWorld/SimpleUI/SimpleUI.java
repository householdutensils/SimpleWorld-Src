package com.rootsecks.SimpleWorld.SimpleUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.Configuration.TextureManager;
import com.rootsecks.SimpleWorld.NPCManager.NPCManager;

public class SimpleUI {
	
	SimpleConfManager simpleConfManager;
	
	SpriteBatch uiSpriteBatch;
	BitmapFont uiFont;
	Sprite selectedBlockSprite;
	Sprite uiPanel;
	Sprite healthBar;
	Sprite helpScreen;
	
	TextureManager textureManager;
	TextureRegion[][] terrainTextures;
	Texture uiPanelTexture;
	Texture healthBarTexture;
	
	Boolean debugMode;
	
	Boolean showHelp;
	
	public SimpleUI(SimpleConfManager simpleConfManager) {
		
		this.simpleConfManager = simpleConfManager;
		
		debugMode = Boolean.parseBoolean(simpleConfManager.getConfItem("debugMode"));
		

		textureManager = simpleConfManager.getTextureManager();
	
		terrainTextures = TextureRegion.split(textureManager.getTerrainTexture(), 16, 16);
		
		uiPanelTexture = textureManager.getUIPanelTexture();
		healthBarTexture = textureManager.getHealthBarTexture();
		
		uiSpriteBatch  = new SpriteBatch();
		uiFont = new BitmapFont();
		selectedBlockSprite = new Sprite();
		uiPanel = new Sprite(uiPanelTexture);
		healthBar = new Sprite(healthBarTexture);
		helpScreen = new Sprite(textureManager.getHelpTexture());
		
		
		
		showHelp = false;
		
	}


	public void render(Player player, float currentTime, int totalTimeCylce, NPCManager npcManager) {
		debugMode = Boolean.parseBoolean(simpleConfManager.getConfItem("debugMode"));

			
		String blockTotals = Integer.toString(player.getBlockInventory()[player.getCurrentlySelectedBlockID()]);
				
		//Location
		int panelX = (Gdx.graphics.getWidth()) - (uiPanelTexture.getWidth()*2);
		int panelY = (50);
		uiPanel.setX(panelX);
		uiPanel.setY(panelY);
		selectedBlockSprite.setX(panelX + 8);
		selectedBlockSprite.setY(panelY + 8);
		healthBar.setX(Gdx.graphics.getWidth() - 150);
		healthBar.setY(Gdx.graphics.getHeight() - 50);

		//Scale
		selectedBlockSprite.setSize(32, 32);
		selectedBlockSprite.setScale(1, 1);
		uiPanel.setSize(48, 48);
	
		healthBar.setSize(player.getHealth(), 16);
		
		selectedBlockSprite.setRegion(terrainTextures[0][player.getCurrentlySelectedBlockID()]);

		uiSpriteBatch.begin();

		uiPanel.draw(uiSpriteBatch);
		selectedBlockSprite.draw(uiSpriteBatch);
		healthBar.draw(uiSpriteBatch);
		uiFont.draw(uiSpriteBatch, "HP:", healthBar.getX() - uiFont.getBounds("HP: ").width - 5, healthBar.getY() + uiFont.getBounds("HP:").height);
		uiFont.draw(uiSpriteBatch, "Days Survived: " + player.getDaysSurvived(), healthBar.getX() - uiFont.getBounds("HP: ").width - 5, healthBar.getY() + uiFont.getBounds("HP:").height * 3);
		uiFont.draw(uiSpriteBatch, blockTotals, panelX + (uiPanel.getWidth()/2) - (uiFont.getBounds(blockTotals).width/2), panelY);
		
		
		if (getShowHelp()) {
			
			helpScreen.setX(Gdx.graphics.getWidth()/2 - (textureManager.getHelpTexture().getWidth()/2));
			helpScreen.setY(Gdx.graphics.getHeight()/2 - (textureManager.getHelpTexture().getHeight()/2));
			
			helpScreen.draw(uiSpriteBatch);
		}
		
		
		if (debugMode) {
			
			
			uiFont.draw(uiSpriteBatch, "Debug Mode Active", 10, Gdx.graphics.getHeight() - 10);
			uiFont.draw(uiSpriteBatch, "Player Location: " + player.getPlayerPosX() + ", " + player.getPlayerPosY(), 10, Gdx.graphics.getHeight() - 25);
			uiFont.draw(uiSpriteBatch, "Player Accelleration: " + player.getVelocityX() + ", " + player.getVelocityY(), 10, Gdx.graphics.getHeight() - 40);
			uiFont.draw(uiSpriteBatch, "Player Health: " + player.getHealth() + "/" + player.getMaxHealth(), 10, Gdx.graphics.getHeight() - 55);
			uiFont.draw(uiSpriteBatch, "CurrentTime: " + currentTime, 10, Gdx.graphics.getHeight() - 70);
			uiFont.draw(uiSpriteBatch, "Total NPCs: " + npcManager.getNpcList().size(), 10, Gdx.graphics.getHeight() - 85);

			
		}
		
	
		
	
		uiSpriteBatch.end();
		
		
	}
	
	

	public Boolean getShowHelp() {
		return showHelp;
	}

	public void setShowHelp(Boolean showHelp) {
		this.showHelp = showHelp;
	}
	

}
