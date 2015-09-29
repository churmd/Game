package level;

import java.util.ArrayList;

import entities.Entity;
import graphics.Screen;
import tiles.Tile;

public abstract class Level {
	
	protected int width, height;
	protected int[] tiles;
	ArrayList<Entity> entityList = new ArrayList<Entity>();
	 
	/**
	 * Takes in a width and height used to set the tile map array (not the pixel map)
	 * @param width The width of the tile map
	 * @param height The height of the tile map
	 */
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		this.tiles = new int[width*height];
		this.generateLevel();
	}
	
	/**
	 * Loads a level from an image file
	 * @param path The path to the image file
	 */
	public Level(String path){
	}

	/**
	 * Generates the level and stores it in the tile array 
	 */
	abstract void generateLevel(); 
	
	/**
	 * Updates all of the entities in the level
	 */
	public void update(){
		for(int i = 0; i < entityList.size(); i++){
			entityList.get(i).update();
		}
	}

/**
 * Renders the level onto the screen and then all of the entities on the level
 * @param xOffset The x pixel offset of the camera
 * @param yOffset The y pixel offset of the camera
 * @param screen The screen that the image is be rendered on
 */
	public void render(int xOffset, int yOffset, Screen screen){
		screen.setOffset(xOffset, yOffset);
		int x0 = xOffset >> 5;
		int x1 = ((xOffset + screen.width) >> 5) + 1;
		int y0 = yOffset >> 5;
		int y1 = ((yOffset + screen.height) >>5) + 1;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entityList.size(); i++){
			entityList.get(i).render(screen);
		}
	}
	
	/**
	 * Returns the tile at the given coordinates for the tile map not the pixel map
	 * @param x The x coord
	 * @param y The y coord
	 * @return The tile at the givem coordinates
	 */
	abstract Tile getTile(int x, int y);
	
	/**
	 * Adds an entity to the level
	 * @param entity The entity to be added
	 */
	public void addEntity(Entity entity){
		entityList.add(entity);
	}
}
