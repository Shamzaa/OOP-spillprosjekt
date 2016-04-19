package game.tile;

import org.json.JSONArray;
import org.json.JSONObject;

import game.Game;
import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.world.Level;

public class Warp extends Tile{
	
	private String targetLevel;
	private Vector3f targetPosition;
	public Warp(Sprite s, String tL, Vector3f tP){
		super(s);
		targetLevel = tL;
		targetPosition = tP;
	}
	public Warp(Warp t) {
		super(t);
		solid = false;
		targetLevel = t.targetLevel;
		targetPosition = t.targetPosition;
	}
	public Warp(JSONObject data){
		super(data);
		targetLevel = data.getString("level");
		JSONArray tPos = data.getJSONArray("dest");
		targetPosition = new Vector3f((float)tPos.getDouble(0),(float)tPos.getDouble(1),(float)tPos.getDouble(2));
		
	}
	@Override
	public void enter(Entity ent) {
		Game.getCurrentLevel().moveEntityTo(ent, Game.getLevel(targetLevel));
		ent.setPosition(targetPosition.mul(new Vector3f(Tile.SIZE,Tile.SIZE,1)));
	}

	@Override
	public void leave(Entity ent) {
		
	}

}
