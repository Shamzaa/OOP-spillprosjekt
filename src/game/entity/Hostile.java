package game.entity;

import game.graphics.Sprite;
import game.math.Vector3f;
import game.world.Level;

public class Hostile extends Fighter{
	
	
	
	
	public Hostile(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
	}

	@Override
	public void enter(Level lvl) {
	}

	@Override
	public void leave(Level lvl) {
	}

	@Override
	public void destory() {
	}

}
