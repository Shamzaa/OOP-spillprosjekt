package game.tile;

import org.json.JSONObject;

import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Vector3f;

public class Elevator extends Tile{
	private Vector3f modDir = new Vector3f(0,0,1);
	public Elevator(JSONObject data) {
		super(data);
		modDir = new Vector3f(0,0,data.getInt("direction"));
	
	}
	public Elevator(Elevator other) {
		super(other);
		modDir = other.modDir;
		
	}
	public Elevator(Sprite spr,int dir) {
		super(spr);
		modDir = new Vector3f(0,0,dir);
	}
	@Override
	public void enter(Entity ent) {
		System.out.println("Pusing: " + modDir);
		ent.setPosition(ent.getPosition().add(modDir));
	}
	@Override
	public void leave(Entity ent) {
		
	}
}
