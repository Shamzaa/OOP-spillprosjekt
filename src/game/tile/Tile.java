package game.tile;

import game.Game;
import game.graphics.Sprite;

public class Tile {
	public static final int SIZE = 32;
	Sprite sprite;
	boolean solid = false;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	public Tile(Tile t){
		this.sprite = new Sprite(t.sprite);
	}
	public void update(long dtime){
		sprite.update(dtime);
	}
	public void render(){
		Game.getCanvas().addToQueue(sprite);
	}

	public Sprite getSprite(){
		return sprite;
	}
	
}
