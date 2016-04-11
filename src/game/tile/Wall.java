package game.tile;

import game.graphics.Sprite;

public class Wall extends Tile{
	
	public Wall(Sprite sprite) {
		super(sprite);
		solid = true;
	}
	public Wall(Tile tile){
		super(tile);
	}

}
