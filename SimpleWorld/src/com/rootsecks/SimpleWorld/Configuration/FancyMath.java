package com.rootsecks.SimpleWorld.Configuration;

public class FancyMath {
	
	

	public int roundToMultiple(int numToRound, int multiple) {
		
		int temp = numToRound % multiple;
		
	    if (temp < (multiple/2 + 1)) {
	    	
	         return numToRound - temp;
	         
	    } else {
	    	
	         return numToRound + multiple - temp;
	         
		}
	    
	
	}
	

}
