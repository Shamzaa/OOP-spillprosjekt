package game.tile;

import org.json.JSONObject;

import game.entity.Entity;
import game.graphics.Sprite;

public class Ground extends Tile{
	
	public Ground(Sprite sprite) {
		super(sprite);
	}
	public Ground(Tile tile){
		super(tile);
	}
	public Ground(JSONObject obj){
		super(obj);
	}
	@Override
	public void enter(Entity ent) {
		
	}
	@Override
	public void leave(Entity ent) {
		
	}

}
