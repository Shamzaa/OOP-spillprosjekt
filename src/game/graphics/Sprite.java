package game.graphics;

import java.awt.Graphics2D;

import game.math.Vector3f;

public class Sprite implements Drawable{
	private float depth;
	private float rotation = 0;
	
	private Vector3f position;
	private Vector3f center;
	
	public void draw(Graphics2D g){
		
	}
	
	public void setDepth(float depth){
		// position.getZ as parameter
	}
	
	public float getDepth(){
		return depth;
	}
	
	public void setPosition(Vector3f position){
		this.position = position;
	}
	
	public Vector3f getPosition(){
		return position;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public Vector3f getCenter(){
		return center;
	}
	
	public void setCenter(Vector3f center){
		this.center = center;
	}
}
