package game.tile;

import org.json.JSONObject;

import game.entity.Entity;
import game.graphics.Sprite;

public class Wall extends Tile{
	
	public Wall(Sprite sprite) {
		super(sprite);
		solid = true;
	}
	public Wall(Tile tile){
		super(tile);
	}
	public Wall(JSONObject obj){
		super(obj);
	}
	@Override
	public void enter(Entity ent) {
		
	}
	@Override
	public void leave(Entity ent) {
		
	}

}
