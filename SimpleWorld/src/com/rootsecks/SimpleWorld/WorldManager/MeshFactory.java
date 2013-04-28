package com.rootsecks.SimpleWorld.WorldManager;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector3;
import com.rootsecks.SimpleWorld.Configuration.SimpleConfManager;
import com.rootsecks.SimpleWorld.World.Chunk;

public class MeshFactory {
	

	SimpleConfManager simpleConfManager;

	Chunk chunk;
	
	
	//////Mesh Verticies and Junk
	ArrayList<Float> chunkPositionArray;
	ArrayList<Short> chunkIndexArray;
	ArrayList<Vector3> chunkTextureArray;
	
	
	
	///Chunk Details
	int chunkX;
	int chunkY;
	int chunkSizeX;
	int chunkSizeY;
	int blockSizeX;
	int blockSizeY;
	
	
	//Chunk Data
	Byte[][] chunkData;
	
	
	BlockTypes blockTypes;
	
	
	public MeshFactory(SimpleConfManager simpleConfManager, Chunk chunk) {
		
				
		this.simpleConfManager = simpleConfManager;
		this.chunk = chunk;
		
		
		chunkX = chunk.getChunkX();
		chunkY = chunk.getChunkY();
		
		chunkSizeX = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeX"));
		chunkSizeY = Integer.parseInt(simpleConfManager.getConfItem("chunkSizeY"));
		blockSizeX = Integer.parseInt(simpleConfManager.getConfItem("blockSizeX"));
		blockSizeY = Integer.parseInt(simpleConfManager.getConfItem("blockSizeY"));
		
		chunkData = chunk.getChunkData();
		
		
		chunkPositionArray = new ArrayList<Float>();
		chunkIndexArray = new ArrayList<Short>();
		chunkTextureArray = new ArrayList<Vector3>();
		
		
		blockTypes = new BlockTypes();
		
		
	}
	
	
	
	public void createChunkMesh() {
		
		int	indexCount = 0;
		
		for ( int x = 0; x < chunkSizeX; x ++ ) {
			
			for ( int y = 0; y < chunkSizeY; y ++ ) {
				
				if (blockTypes.isVisible(chunkData[x][y])) {
					
					
					indexCount = createBlockVerticies(x, y, indexCount);
					
				}
				
			}		
			
		}
		
		
		finalizeMesh(indexCount);
		
	}
	
	
	
	
	private void finalizeMesh(int indexCount) {
		
		
        //Convert the index arraylist to a int array
        short[] chunkIndicies = convertShortArrayListToShortArray(chunkIndexArray);
        float[] chunkVerticies = convertFloatArrayListTofloatArray(chunkPositionArray);
        
        
		Mesh chunkMesh = new Mesh(true, indexCount, chunkIndicies.length, 
								new VertexAttribute(Usage.Position, 3, "a_position"),
								new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"));

		chunkMesh.setVertices(chunkVerticies);
		chunkMesh.setIndices(chunkIndicies);
		
		
		chunk.setMesh(chunkMesh);
		
		
	}
	
	
	
	private int createBlockVerticies(int blockX, int blockY, int indexCount) {
		
		
		ArrayList<Float> textureCoord = new ArrayList<Float>();
		
		textureCoord = simpleConfManager.getTextureManager().getTerrainTextureCoords(chunkData[blockX][blockY]);
		
		//Pos Verts
        chunkPositionArray.add((float) (0 + (blockX * blockSizeX) + (chunkX)));
        chunkPositionArray.add((float) (0 + (blockY * blockSizeY) + (chunkY)));
        chunkPositionArray.add((float) 0);
        //Tex Verts
        chunkPositionArray.add(textureCoord.get(0));
        chunkPositionArray.add(textureCoord.get(1));
        
		//Pos Verts
        chunkPositionArray.add((float) ((blockSizeX + (blockX * blockSizeX) + (chunkX))));
        chunkPositionArray.add((float) (0 + (blockY * blockSizeY) + (chunkY)));
        chunkPositionArray.add((float) 0);
        //Tex Verts
        chunkPositionArray.add(textureCoord.get(2));
        chunkPositionArray.add(textureCoord.get(3));      
        
		//Pos Verts
        chunkPositionArray.add((float) ((0 + (blockX * blockSizeX) + (chunkX))));
        chunkPositionArray.add((float) (blockSizeY + (blockY * blockSizeY) + (chunkY)));
        chunkPositionArray.add((float) 0);
        //Tex Verts
        chunkPositionArray.add(textureCoord.get(4));
        chunkPositionArray.add(textureCoord.get(5));
        
		//Pos Verts
        chunkPositionArray.add((float) (blockSizeX + (blockX * blockSizeX) + (chunkX)));
        chunkPositionArray.add((float) (blockSizeY + (blockY * blockSizeY) + (chunkY)));
        chunkPositionArray.add((float) 0);	
        //Tex Verts
        chunkPositionArray.add(textureCoord.get(6));
        chunkPositionArray.add(textureCoord.get(7));
        
        chunkIndexArray.add((short) (3 + (indexCount)));
        chunkIndexArray.add((short) (2 + (indexCount)));
        chunkIndexArray.add((short) (1 + (indexCount)));
        chunkIndexArray.add((short) (2 + (indexCount)));
        chunkIndexArray.add((short) (0 + (indexCount)));
        chunkIndexArray.add((short) (1 + (indexCount)));
        
       //indexCount += 4;
		
        indexCount += 4;
        
        
		return indexCount;
		
	}
	
	
    //Convert an Integer arraylist to an int array
    private short[] convertShortArrayListToShortArray(ArrayList<Short> inArray) {
        
        //Generate an int array
        short[] outArray= new short[inArray.size()];
        
        //Loop through the arraylist and move each element into the int array
        for (int arrayIndex = 0; arrayIndex < inArray.size(); arrayIndex ++) {
            
                outArray[arrayIndex] = inArray.get(arrayIndex);
        }

        //Return the int array
        return outArray;
    }
    
        //Convert Float arraylist to a float array
    private float[] convertFloatArrayListTofloatArray(ArrayList<Float> inArray) {

        //Generate an float array
        float[] outArray= new float[inArray.size()];

        //Loop through the arraylist and move each element into the float array
        for (int arrayIndex = 0; arrayIndex < inArray.size(); arrayIndex ++) {

            outArray[arrayIndex] = inArray.get(arrayIndex);
        }

        //Return the float array
        return outArray;
            
    }
	

}
