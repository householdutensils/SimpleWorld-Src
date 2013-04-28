package com.rootsecks.SimpleWorld.WorldManager.WorldGenerator;

import java.util.Random;
	
public class NoiseGenerator {
	
	int noiseSeed;
	
	public NoiseGenerator(int seed) {
		
		Random seedRand = new Random(seed);
		noiseSeed = seedRand.nextInt();
	}

	public float getNoise(float x, float y) {
		float xN = (x + y * 57);
		int n = ((int)xN << 13) ^ ((int)xN);
		return (1.0f - ((n * (n * n * 15731 * noiseSeed + 789221 * noiseSeed) + 1376312589 * noiseSeed) & 0x7fffffff) / 1073741824.0f);
	}
	
	public float smoothNoise2D(float x, float y) {		
		float c = getNoise(x - 1, y - 1) + getNoise(x - 1, y + 1) + getNoise(x + 1, y - 1) + getNoise(x + 1, y + 1);
		float s = getNoise(x, y - 1) + getNoise(x, y + 1) + getNoise(x - 1, y) + getNoise(x + 1, y);
		float ce = getNoise(x, y);
		return (ce / 4.0f) + (s / 8.0f) + (c / 16.0f);		
	}
	

}