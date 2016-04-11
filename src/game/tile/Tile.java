package game.tile;




import game.Game;
import game.graphics.Sprite;
import game.math.Rectangle;
import game.math.Shape;
import game.math.Vector3f;

public abstract class Tile {
	public static final int SIZE = 32;
	private Sprite sprite;
	private Shape shape = new Rectangle(new Vector3f(0,0,0),new Vector3f(SIZE,SIZE,0));
	protected boolean solid = false;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	public Tile(Tile t){
		this.sprite = new Sprite(t.sprite);
	}
	public boolean isSolid(){
		return solid;
	}
	public void update(long dtime){
		sprite.update(dtime);
	}
	public void render(){
		Game.getCanvas().addToQueue(shape);
		Game.getCanvas().addToQueue(sprite);
	}
	public Shape getShape(){
		return shape;
	}
	public Sprite getSprite(){
		return sprite;
	}
	
}
