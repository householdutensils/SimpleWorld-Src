package com.rootsecks.SimpleWorld.Configuration;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;

public class TextureManager {
	
	
	public final static int blockAir = 255;
	public final static int blockGrass = 1;
	public final static int blockDirt = 2;
	
	Texture terrainTexture;
	Texture playerTexture;
	Texture mouseTexture;
	Texture mouseTextureClick;
	Texture mouseTextureOut;
	Texture uiPanelTexture;
	Texture healthBarTexture;
	Texture[] bgTextures;
	Texture badGuyTexture;
	Texture badGuyFireTexture;
	Texture helpTexture;
	
	public TextureManager() {
		
	
		FileHandle imageFileHandle = Gdx.files.internal("data/terrain.png"); 
		terrainTexture = new Texture(imageFileHandle);
		
		playerTexture = new Texture(Gdx.files.internal("data/player.png"));
		
		mouseTexture = new Texture(Gdx.files.internal("data/mouseCursor.png"));
		
		mouseTextureClick = new Texture(Gdx.files.internal("data/mouseCursorClick.png"));
		
		mouseTextureOut = new Texture(Gdx.files.internal("data/mouseCursorOut.png"));
		
		uiPanelTexture = new Texture(Gdx.files.internal("data/UIPanel.png"));
		
		healthBarTexture = new Texture(Gdx.files.internal("data/healthBar.png")); 
		
		bgTextures = new Texture[BlockTypes.totalBGs];
		
		bgTextures[BlockTypes.bgSky] = new Texture(Gdx.files.internal("data/SkyBG.png")); 
		
		bgTextures[BlockTypes.bgGround] = new Texture(Gdx.files.internal("data/GroundBG.png"));
		
		bgTextures[BlockTypes.bgSkyDark] = new Texture(Gdx.files.internal("data/SkyBGDark.png"));
		
		badGuyTexture = new Texture(Gdx.files.internal("data/badGuy.png"));  
		
		badGuyFireTexture = new Texture(Gdx.files.internal("data/badGuyFire.png"));  
		
		helpTexture = new Texture(Gdx.files.internal("data/helpSprite.png"));  
		
	}
	
	public Texture getHelpTexture() {
		return helpTexture;
	}

	public Texture getMouseTextureClick() {
		
		return mouseTextureClick;
		
	}
	
	public Texture getMouseTextureOut() {
		
		return mouseTextureOut;
		
	}
	
	public Texture getMouseTexture() {
		
		return mouseTexture;
		
	}
	
	public Texture getBadGuyFireTexture() {
		return badGuyFireTexture;
	}
	
	
	public Texture getTerrainTexture() {
		return terrainTexture;
	}
	
	public Texture getPlayerTexture() {
		return playerTexture;
	}


	public ArrayList<Float> getTerrainTextureCoords(Byte blockID) {
		
		ArrayList<Float> textureCoord = new ArrayList<Float>();
		
		float textureIncrement = 1f/16;
		float texX = textureIncrement * (int) blockID;
		float texY = 0;
		
		textureCoord.add(texX + textureIncrement);
	    textureCoord.add(texY + textureIncrement);
	    
	    textureCoord.add(texX);
	    textureCoord.add(texY + textureIncrement);
	    
        textureCoord.add(texX + textureIncrement);
        textureCoord.add(texY);
        
        textureCoord.add(texX);
        textureCoord.add(texY);
		
		
		return textureCoord;
	}

	public Texture getUIPanelTexture() {
		
		return uiPanelTexture;
	}

	
	public Texture getHealthBarTexture() {
		
		return healthBarTexture;
		
	}
	
	public Texture getBG(int bgIndex) {

		return bgTextures[bgIndex];
		
	}
	
	public Texture getBadGuyTexture() {
		return badGuyTexture;
	}

	public void setBadGuyTexture(Texture badGuyTexture) {
		this.badGuyTexture = badGuyTexture;
	}

}
