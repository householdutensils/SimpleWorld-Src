package com.rootsecks.SimpleWorld.WorldManager;

public class BlockTypes {
	
	
	public final static byte totalBlocks = 5;
	public final static byte blockAir = (byte) 255;
	public final static byte blockGrass = 0;
	public final static byte blockDirt = 1;
	public final static byte blockStone = 2;
	public final static byte blockSand = 4;
	public final static byte blockSnow = 5;
	
	public final static int totalBGs = 3;
	public final static int bgSky = 0;
	public final static int bgGround = 1;
	public final static int bgSkyDark = 2;
	
	public final static int totalBiomes = 2;
	public final static int biomePlains = 0;
	public final static int biomeDesert = 1;
	public final static int biomeMountains = 2;
	public final static int biomeTundra = 3;
	
	public Boolean isVisible(byte blockID) {
		
		
		Boolean isVisible = false;
		
		switch(blockID) {
		case blockAir:
			isVisible = false;
			break;
		case blockDirt:
			isVisible = true;
			break;
		case blockGrass:
			isVisible = true;
			break;
		default:
			isVisible = true;
			break;		
		}		
		
		return isVisible;
		
		
	}
	
	
	
	

}
