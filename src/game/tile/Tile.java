package game.tile;




import org.json.JSONObject;

import game.Game;
import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Rectangle;
import game.math.Shape;
import game.math.Vector3f;

public abstract class Tile {
	public static final int SIZE = 32;
	private Sprite sprite;
	private Vector3f position; 
	private Shape shape = new Rectangle(new Vector3f(0,0,0),new Vector3f(SIZE,SIZE,0));
	private float depthOffset = 0;
	protected boolean solid = false;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	public Tile(Tile t){
		this.sprite = new Sprite(t.sprite);
	}
	public Tile(JSONObject data){
		if(data.has("sprite")){
			this.sprite = new Sprite(data.getJSONObject("sprite"));
		}
		if(data.has("height")){
			this.shape.setDimension(new Vector3f(SIZE,(float)(SIZE*data.getDouble("height")),0));
		}
		if(data.has("depthOffset")){
			this.depthOffset = (float)data.getDouble("depthOffset");
		}
	}
	public boolean isSolid(){
		return solid;
	}
	public void update(long dtime){
		if(sprite != null)
			sprite.update(dtime);
	}
	public void setPosition(Vector3f position){
		this.position = position;
	}
	public Vector3f getPosition(){
		return this.position;
	}
	public void render(){
		if(sprite != null){
			sprite.setPosition(position);
			sprite.setDepth(-(position.getY()+Tile.SIZE+position.getZ()) + depthOffset*Tile.SIZE);
			shape.setPosition(position.add(new Vector3f(0,Tile.SIZE-shape.getDimension().getY(),0)));
			shape.setDepth(sprite.getDepth()-1);

		
		Game.getCanvas().addToQueue(sprite);
		}
		Game.getCanvas().addToQueue(shape);
	}
	public abstract void enter(Entity ent);
	public abstract void leave(Entity ent);
	
	public Shape getShape(){
		return shape;
	}
	public Sprite getSprite(){
		return sprite;
	}
	
}
