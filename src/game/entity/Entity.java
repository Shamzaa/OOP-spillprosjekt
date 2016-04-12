package game.entity;

import game.math.Rectangle;
import game.math.Shape;
import game.math.Vector3f;
import game.mechanics.Inventory;
import game.tile.Tile;
import game.world.Level;

import game.Game;
import game.graphics.*;

public abstract class Entity {
	protected Vector3f position;
	protected Vector3f walkDir = new Vector3f(0,0,0);
	protected Vector3f facing = new Vector3f(0,0,0);
	protected double walkSpeed = 256;
	protected Sprite[] sprites;
	protected Inventory inventory = new Inventory();
	protected Sprite currentSprite;
	protected Shape shape;
	private boolean dead = false;
	
	/* vars needed:
	 * sprite
	 * size?
	 * trigger: what can this entity do when "activated"
	 * 
	 */
	
	// other init vars needed: filepath to sprite, file containing options to import
	public Entity(Vector3f position, Sprite[] sprites){
		this.position = position;
		this.sprites = sprites;
		currentSprite = sprites[0];
		shape = new Rectangle(position,currentSprite.getDimension().mul(new Vector3f(1,0.25f,0)));
	}
	
	public void update(long dtime){
		if(dead){
			Game.getCurrentLevel().destroyEntity(this);
		}
		if(walkDir.getLength() > 0){
			
			face(walkDir);
			currentSprite.update(dtime);
			Vector3f deltaPos = walkDir.normalize().scale((float) (walkSpeed*dtime/1000));
			Vector3f newPos = position.add(deltaPos);
			setPosition(newPos);
		}else{
			currentSprite.setCurrentFrame(0);
		}
	}
	public void face(Vector3f dir){
		facing = dir;
		if(facing.getY() < 0){
			currentSprite = sprites[3];
		}else if(facing.getY() > 0){
			currentSprite = sprites[2];
		}else if(facing.getX() > 0){
			currentSprite = sprites[0];
		}else if(facing.getX() < 0){
			currentSprite = sprites[1];
		}
	}
	public void render(){
		if(currentSprite != null){
			currentSprite.setPosition(position);
			currentSprite.setDepth(calcDepth());
			shape.setDepth(calcDepth() - 1.0f);
			
			Game.getCanvas().addToQueue(currentSprite);
			Game.getCanvas().addToQueue(shape);
		}
	}
	protected float calcDepth(){
		return -position.getY() - (currentSprite.getDimension().getY() - currentSprite.getOffset().getY()) - position.getZ()*Tile.SIZE;
	}
	public void setPosition(Vector3f position){
		Vector3f sPos = (position.sub(sprites[0].getOffset()));
		sPos = sPos.add(sprites[0].getDimension().sub(shape.getDimension()).mul(new Vector3f(0,1,0)));
		shape.setPosition(sPos);
		this.position = position;
	}
	public Vector3f getPosition(){
		return position;
	}
	public abstract void enter(Level lvl);
	public abstract void leave(Level lvl);
	public abstract void touch(Entity ent);
	
	//Called when the entity is destroyed
	public abstract void destory();
	
	public boolean isAlive(){
		return !dead;
	}
	
	public void kill(){
		dead = true;
	}
}
