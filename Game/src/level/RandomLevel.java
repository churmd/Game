package level;

import java.util.Random;

import tiles.Tile;

public class RandomLevel extends Level {
	
	private Random generator;

	public RandomLevel(int width, int height) {
		super(width, height);
	}

	@Override
	void generateLevel() {
		this.generator = new Random();
		for(int i = 0; i < tiles.length; i++){
			tiles[i] = generator.nextInt(2);
		}
		
	}

	@Override
	Tile getTile(int x, int y) {
		int t = tiles[x + y * width];
		if(t == 0) {
			return Tile.voidTile;
		} else {
			return Tile.brickPathTile;
		}
	}

}
