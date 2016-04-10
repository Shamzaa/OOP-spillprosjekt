package game.entity;

import game.math.Vector3f;
import game.tile.Tile;
import game.world.Level;
import game.Game;
import game.graphics.*;

public abstract class Entity {
	protected Vector3f position;
	protected Vector3f walkDir = new Vector3f(0,0,0);
	protected Vector3f facing = new Vector3f(0,0,0);
	protected double walkSpeed = 128;
	protected Sprite[] sprites;
	protected Sprite currentSprite;
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
	}
	
	public void update(long dtime){
		if(dead){
			Game.getCurrentLevel().destroyEntity(this);
		}
		if(walkDir.getLength() > 0){
			face(walkDir);
			currentSprite.update(dtime);
			setPosition(position.add(walkDir.normalize().scale((float) (walkSpeed*dtime/1000))));
		}else{
			currentSprite.setCurrentFrame(0);
		}
	}
	public void face(Vector3f dir){
		facing = dir;
		if(facing.getY() < 0){
			currentSprite = sprites[2];
		}else if(facing.getY() > 0){
			currentSprite = sprites[3];
		}else if(facing.getX() > 0){
			currentSprite = sprites[0];
		}else if(facing.getX() < 0){
			currentSprite = sprites[1];
		}
	}
	public void render(){
		if(currentSprite != null){
			currentSprite.setPosition(position);
			currentSprite.setDepth(-position.getY() - (currentSprite.getDimension().getY() - currentSprite.getOffset().getY()) - position.getZ()*Tile.SIZE);
			Game.getCanvas().addToQueue(currentSprite);
		}
	}
	public void setPosition(Vector3f position){
		this.position = position;
	}
	public Vector3f getPosition(){
		return position;
	}
	public abstract void enter(Level lvl);
	public abstract void leave(Level lvl);
	//Called when the entity is destroyed
	public abstract void destory();
	
	public boolean isAlive(){
		return !dead;
	}
	
	public void kill(){
		dead = true;
	}
}
