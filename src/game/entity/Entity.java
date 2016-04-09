package game.entity;

import game.math.Vector3f;
import game.graphics.*;

public class Entity {
	protected Vector3f position;
	protected Vector3f walkDir = new Vector3f(0,0,0);
	protected Vector3f facing = new Vector3f(0,0,0);
	
	protected Sprite sprite;
	private boolean dead = false;
	/* vars needed:
	 * sprite
	 * size?
	 * trigger: what can this entity do when "activated"
	 * 
	 */
	
	// other init vars needed: filepath to sprite, file containing options to import
	public void update(long dtime){
		
	}
	public void render(){
		
	}
	public void setPosition(Vector3f position){
		this.position = position;
	}
	public Vector3f getPosition(){
		return position;
	}
	
	
	public boolean isAlive(){
		return dead;
	}
	
	protected void kill(){
		dead = true;
	}
}
