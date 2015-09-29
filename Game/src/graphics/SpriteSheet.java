package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public static SpriteSheet brickTiles = new SpriteSheet(
			"/spriteSheets/tiles/BrickPaths.png", 256);

	private String path;
	private int size;
	public int[] sheetPixles;

	/**
	 * SpriteSheet Constructor
	 * 
	 * @param path
	 *            The file path the sprite sheet image
	 * @param size
	 *            The pixel size of the image (must be square)
	 */
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.size = size;
		sheetPixles = new int[size * size];
		this.loadSheet();
	}

	/**
	 * Attempts to load the sprite sheet using the given path and set the
	 * sheetPixels array equal to its pixel info
	 */
	private void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class
					.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, sheetPixles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the size of the sprite sheet
	 * 
	 * @return the size of the sprite sheet
	 */
	public int getSize() {
		return size;
	}

}
