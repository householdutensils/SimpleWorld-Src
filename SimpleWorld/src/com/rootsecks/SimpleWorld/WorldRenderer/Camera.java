package com.rootsecks.SimpleWorld.WorldRenderer;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public class Camera {
	
	private static final Logger logger = Logger.getLogger(Camera.class.getName());

	
	private int screenWidth;
	private int screenHeight;
	
	//Variable to hold the camera
	private OrthographicCamera gameCamera;
	

	public Camera() {
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		initCamera();
		
	}
	
	public OrthographicCamera getOrthCam() {
		
		return gameCamera;
		
	}
	
	
	public void initCamera() {
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		gameCamera = new OrthographicCamera(screenWidth, screenHeight);            
		gameCamera.position.set(screenWidth / 2, screenHeight / 2, 0);
        
        Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);	
        
        		
	}
	
	
	public Matrix4 getProjectionMatrix() {
		
		return gameCamera.combined;
		
	}
	
	public void update() {
		
		
		
	}
	
	
	
	public void render(GL10 gl) {

		gl.glViewport(0, 0, screenWidth, screenHeight);
		gameCamera.update();
		gameCamera.apply(gl);
		
		
	}
	

	public void translate(float f, float g, int tranZ) {
		
		logger.log(Level.INFO, "Translating Camera to: " + f + ", " + g + ", " + tranZ);
		
		gameCamera.translate(f, g, tranZ);
	
		
	}

	public void setPositionX(float posX) {
		
		gameCamera.position.x = posX;
				
	}
	
	
	public void setPositionY(float posY) {
		
		gameCamera.position.y = posY;
				
	}
	
	public float getPositionX() {
		
		return gameCamera.position.x;
				
	}
	
	
	public float getPositionY() {
		
		return gameCamera.position.y;
				
	}
	

}
