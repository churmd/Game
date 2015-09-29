/**
 * Used to render all of the components on screen and store the screens display as a pixel accurate int array (does not scale the image up)
 */

package graphics;

import tiles.Tile;

public class Screen {

	public int width;
	public int height;
	private int[] pixels;
	private int xOffset, yOffset;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	/**
	 * Returns the image to be put on screen as an int array 
	 * @return The image to be put on screen as an int array
	 */
	public int[] getPixels(){
		return pixels;
	}
	
	/**
	 * Sets all of the pixels in the image int array to be 0
	 */
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	/**
	 * Sets the x and y pixel offset of the level on screen
	 * @param xOffset The amount of x pixels the screen is offset
	 * @param yOffset The amount of y pixels the screen is offset
	 */
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * Renders a single tile to the pixels array
	 * @param xPos The x position of the tile in pixels
	 * @param yPos The y position of the tile in pixels
	 * @param tile The tile being rendered
	 */
	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;
		for(int y = 0; y< tile.sprite.size; y++){
			int ya = yPos + y;
			for(int x = 0; x < tile.sprite.size; x++){
				int xa = xPos + x;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = tile.sprite.spritePixels[x + y * tile.sprite.size];
			}
		}
	}

}
