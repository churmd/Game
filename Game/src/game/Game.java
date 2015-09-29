/**
 * This the main running class of the game
 */

package game;

import graphics.Screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import level.Level;
import level.RandomLevel;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// window properties
	public static int width = 400;
	public static int height = (width / 16) * 9;
	public static int scale = 2;
	public String title = "Test";

	// variables used in Game
	private boolean running = false;

	// objects used in Game
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private JFrame frame;
	private Thread thread;
	private Level level;

	/**
	 * The constructor for this class
	 */
	public Game() {
		Dimension dimSize = new Dimension(width * scale, height * scale);
		this.setPreferredSize(dimSize);
		
		screen = new Screen(width, height);

		frame = new JFrame();
		
		level = new RandomLevel(30, 30);
	}

	/**
	 * Initialises the thread and starts it running
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}

	/**
	 * Stops the thread from running
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * This is the method called when the thread starts running
	 */
	public void run() {
		final double updateLimiter = 1000000000.0 / 60.0;
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int fps = 0;
		int ups = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / updateLimiter;
			lastTime = now;
			if (delta >= 1) {
				this.update();
				delta--;
				ups++;
			}

			this.render();
			fps++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "   |   UPS:" + ups + "   FPS:" + fps);
				ups = 0;
				fps = 0;
			}
		}

	}

	/**
	 * when called this updates all of the entities on the current level
	 */
	private void update() {
		level.update();
	}

	/**
	 * When called this renders all of the items, entities and level within the
	 * players view
	 */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		//int xCamOffset = player.x - width/2;
		//int yCamOffset = player.y - height/2;
		
		level.render(0, 0, screen);
		//player.render(screen);
		
		int[] screenPixels = screen.getPixels();
		for(int i=0; i<imagePixels.length; i++){
			imagePixels[i] = screenPixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(),getHeight(),null);
		//if(Mouse.getButton() != -1) g.fillRect(Mouse.getX(), Mouse.getY(), 16, 16);
		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {

		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();

	}
}
