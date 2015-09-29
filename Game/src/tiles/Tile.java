package tiles;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile brickPathTile = new BrickPath(Sprite.brickPath);
	
	public Sprite sprite;

	/**
	 * Creates a tile as part of a map of a level
	 * @param sprite The sprite the tile will be seen as on screen
	 */
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	/**
	 * Renders the tile to the screen 
	 * @param x The x position of the tile on the tile map
	 * @param y The y position of the tile on the tile map
	 * @param screen The screen the tile is being rendered to
	 */
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 5, y << 5, this);
	}
	
	/**
	 * Returns whether the tile is solid (can be passed through)
	 */
	public boolean solid(){
		return false;
	}

}
