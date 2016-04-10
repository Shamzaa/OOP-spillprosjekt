package game.entity;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.world.Level;

public class NPC extends Entity{
	
	
	public NPC(Vector3f position, Sprite[] sprites){
		super(position,sprites);
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
	
	// TODO: patrol method?, trigger dialogue object(observatør-observert-teknikken?)
}
