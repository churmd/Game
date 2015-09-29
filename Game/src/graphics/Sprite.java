package graphics;

public class Sprite {
	
	public static Sprite voidSprite = new Sprite(32, 0x472bbf);
	public static Sprite brickPath = new Sprite(SpriteSheet.brickTiles, 32, 0, 0);
	

	private SpriteSheet sheet;
	public final int size;
	private int xPos, yPos;
	public int[] spritePixels;

	/**
	 * Sprite Constructor
	 * 
	 * @param sheet
	 *            The SpriteSheet with the sprite on
	 * @param size
	 *            The pixel size of the sprite (must be square)
	 * @param x
	 *            The sprites x postion on the sheet
	 * @param y
	 *            The sprites y postion on the sheet
	 */
	public Sprite(SpriteSheet sheet, int size, int x, int y) {
		this.sheet = sheet;
		this.size = size;
		this.xPos = x * size;
		this.yPos = y * size;
		this.spritePixels = new int[size * size];
		this.load();
	}

	private void load() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				spritePixels[x + y * size] = sheet.sheetPixles[(this.xPos + x)
						+ (this.yPos + y) * sheet.getSize()];
			}
		}
	}

	/**
	 * A sprite constructor designed to make a single solid colour tile sprite
	 * @param size The size of the sprite (must be square)
	 * @param colour The colour the sprite will be 
	 */
	public Sprite(int size, int colour) {
		this.size = size;
		spritePixels = new int[size * size];
		setTileColour(colour);
	}

	/**
	 * Used to set the colour of a sprite
	 * @param colour The desired colour of the sprite
	 */
	private void setTileColour(int colour) {
		for (int i = 0; i < spritePixels.length; i++) {
			spritePixels[i] = colour;
		}
	}

}
