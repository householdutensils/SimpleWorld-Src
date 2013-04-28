package com.rootsecks.SimpleWorld.WorldRenderer;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rootsecks.SimpleWorld.Player;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.Configuration.TextureManager;
import com.rootsecks.SimpleWorld.NPCManager.NPCManager;
import com.rootsecks.SimpleWorld.WorldManager.BlockTypes;
import com.rootsecks.SimpleWorld.WorldManager.WorldManager;

public class WorldRenderer {
	
	private static final Logger logger = Logger.getLogger(WorldRenderer.class.getName());
	
	SimpleConfManager simpleConfManager;
	
	Sprite playerSprite;
	Sprite npcSprite;
	SpriteBatch charSpriteBatch;
	
	SpriteBatch bgSpriteBatch;
	Sprite bgSkySprite;
	Sprite bgUnderSprite;
	Sprite bgGroundSprite;
	
	
	TextureManager textureManager;
	
	
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	public WorldRenderer(SimpleConfManager simpleConfManager) {
		
		logger.log(Level.INFO, "World Renderer");
		
		this.simpleConfManager = simpleConfManager;
		
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		textureManager = simpleConfManager.getTextureManager();
		
		playerSprite = new Sprite(textureManager.getPlayerTexture());
		npcSprite = new Sprite(textureManager.getBadGuyTexture());
		charSpriteBatch = new SpriteBatch();
		
		bgSpriteBatch = new SpriteBatch();
		bgSkySprite = new Sprite(textureManager.getBG(BlockTypes.bgSky));
		bgGroundSprite = new Sprite(textureManager.getBG(BlockTypes.bgGround));
		
	}
	
	
	
	
	public void render(WorldManager worldManager, Player player, float currentTime, int totalTimeCylce, NPCManager npcManager) {
		
		GL10 gl = Gdx.graphics.getGL10();
		
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		bgSpriteBatch.begin();
		bgSkySprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		bgGroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		if (currentTime > (totalTimeCylce/2)) {
			
			logger.log(Level.INFO, "What a horrible night to have a curse");
			bgSkySprite.setTexture(textureManager.getBG(BlockTypes.bgSkyDark));
			
		} else {
			
			logger.log(Level.INFO, "Dawn approaches");
			bgSkySprite.setTexture(textureManager.getBG(BlockTypes.bgSky));
			
		}
		
		if (player.getPlayerPosY() > Gdx.graphics.getHeight()) {
			
			
			bgSkySprite.setX(0);
			bgGroundSprite.setX(0);
			bgSkySprite.setY(0);
			bgSkySprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			
		} else {

			bgSkySprite.setX(0);
			bgGroundSprite.setX(0);
			bgSkySprite.setY((-256) - player.getCamera().getPositionY() + Gdx.graphics.getHeight());
			bgSkySprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() * 2);
	
		}
		
		bgGroundSprite.draw(bgSpriteBatch);
		bgSkySprite.draw(bgSpriteBatch);
		bgSpriteBatch.end();	
	
		gl.glEnable(GL10.GL_TEXTURE_2D);

		//logger.log(Level.INFO, "Rendering Camera at: " + player.getCamera().getPositionX() + ", " + player.getCamera().getPositionY());
		player.getCamera().render(gl);
		
		textureManager.getTerrainTexture().bind();
		
		for ( int num = 0; num < worldManager.getWorld().getActiveChunkList().size(); num ++ ) {
			
			worldManager.getWorld().getActiveChunkList().get(num).getMesh().render(GL10.GL_TRIANGLES);
		
		}
		
		//logger.log(Level.INFO, "Rendering Player at: " + player.getPlayerPosX() + ", " + player.getPlayerPosY());

		playerSprite.setPosition(player.getPlayerPosX(), player.getPlayerPosY());
	
		charSpriteBatch.setProjectionMatrix(player.getCamera().getProjectionMatrix());
		
		charSpriteBatch.begin();
		
		

		// this is only one possible drawing out of many
		playerSprite.draw(charSpriteBatch);
		
		for (int num = 0; num < npcManager.getNpcList().size(); num ++ ) {
			
			if (currentTime >= (totalTimeCylce/2)) {
				
				npcSprite.setTexture(textureManager.getBadGuyTexture());
				
			} else {
				
				if (npcManager.getNpcList().get(num).getNPCPosY() > 0) {

					npcSprite.setTexture(textureManager.getBadGuyFireTexture());
					
				} else {
					
					npcSprite.setTexture(textureManager.getBadGuyTexture());
					
				}
			}
			
			npcSprite.setPosition(npcManager.getNpcList().get(num).getNPCPosX(), npcManager.getNpcList().get(num).getNPCPosY());
			npcSprite.draw(charSpriteBatch);
		}

		charSpriteBatch.end();

				
	}
	
	

}
