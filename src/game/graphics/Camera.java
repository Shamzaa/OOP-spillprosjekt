package game.graphics;

import java.awt.geom.AffineTransform;



import game.entity.Entity;
import game.math.Vector3f;
import javafx.beans.property.SetProperty;


public class Camera {
	private double rotation = 0;
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f scale = new Vector3f(1f,1f,0);
	private Vector3f center = new Vector3f(0.5f,0.5f,0f);
	private Vector3f screenSpace = new Vector3f(0,0,0);
	private Entity target = null;
	public Camera(final Vector3f position){
		this.position = new Vector3f(position);
	}
	public void setScreenSpace(final Vector3f screenSpace){
		this.screenSpace = screenSpace;
	}
	public void setPosition(final Vector3f position){
		this.position = new Vector3f(position);
	}
	public void setScale(final Vector3f scale){
		this.scale = new Vector3f(scale);
	}
	public void setRotation(double rotation){
		this.rotation = rotation;
	}
	public void setTarget(final Entity target){
		this.target = target;
	}
	public final Vector3f getPosition(){
		return position;
	}
	public double getRotation(){
		return rotation;
	}
	public final Vector3f getScale(){
		return scale;
	}
	public final Vector3f getCenter(){
		return center;
	}
	public AffineTransform transform(AffineTransform transform){
		AffineTransform t = (AffineTransform) transform.clone();
		if(target != null){
			setPosition(target.getPosition());
		}
		float offsetX = center.getX()*screenSpace.getX();
		float offsetY = center.getY()*screenSpace.getY();
		t.translate(-position.getX() + offsetX, -position.getY() + offsetY);
		t.scale(scale.getX(),scale.getY());
		t.rotate(rotation);
		//t.translate(-offsetX,-offsetY);
		/*
		 *     context.translate(this.pos.x+context.canvas.width/2,this.pos.y+context.canvas.height/2);
        context.scale(this.scale.x,this.scale.y);
        context.rotate(this.rot.z);
        context.translate(-context.canvas.width/2,-context.canvas.height/2);
    
		 * */
		return t;
	}
}
