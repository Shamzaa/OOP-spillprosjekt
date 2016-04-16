package game.entity;

import org.json.JSONObject;

import game.Game;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.world.Level;

public class Hostile extends Fighter{
	
	
	
	
	public Hostile(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
	}
	public Hostile(Vector3f position, JSONObject data){
		super(position,data);
	}
	@Override
	public void enter(Level lvl) {
	}

	@Override
	public void leave(Level lvl) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void touch(Entity ent) {
		if(ent instanceof Player){
			//((Fighter) ent).fighting = true;
			//this.fighting = true;
			Game.getCurrentLevel().startBattle((Fighter)ent, this);
		}
	}

}
