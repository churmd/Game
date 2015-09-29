package entities;

import level.Level;
import graphics.Screen;

public abstract class Entity {
	
	public int x,y;
	private boolean removed = false;
	protected Level level;
	
	public Entity(){
	}
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
	}
	
	public void render(Screen screen){
	}
	
	public boolean getRemoved(){
		return removed;
	}
	
	public void setRemoved(){
		this.removed = true;
	}

	public void init(Level level){
		this.level = level;
		level.addEntity(this);
	}
}
