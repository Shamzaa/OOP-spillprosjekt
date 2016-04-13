package game.graphics.effects;
import java.awt.image.BufferedImage;

import game.graphics.Sprite;
import game.math.Vector3f;
import java.lang.Math;

public class Particle extends Sprite{
	private Vector3f direction;
	
	private float speed = 800;
	
	public Particle(BufferedImage img, Vector3f pos, Vector3f srcPos, Vector3f center, Vector3f dimension) {
		super(img, pos, srcPos, center, dimension);
		// TODO Auto-generated constructor stub
		
		
		// random angle where the particle will go:
		float angle = (float) Math.random()*360;
		float radian = (float) Math.toRadians(angle);
		direction = new Vector3f((float) Math.cos(radian)*1, (float) Math.sin(radian)*1, 0);
		
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public void setSpeed(float x, float y){
		speed = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		float nX = (float) Math.acos(x/speed);
		float nY = (float) Math.asin(y/speed);
		direction.setX(nX);
		direction.setY(nY);
	}
	
	public float getSpeed(){
		return speed;
	}
	
	public float getSpeedX(){
		return direction.getX()*speed;
	}
	
	public float getSpeedY(){
		return direction.getY()*speed;
	}
	
	public void setDirection(Vector3f direction){
		this.direction = direction;
	}
	
	public Vector3f getDirection(){
		return direction;
	}
	


	
	

}
