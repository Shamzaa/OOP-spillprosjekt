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
	private Vector3f target = null;
	public Camera(final Vector3f position, final Vector3f screenSpace){
		this.position = new Vector3f(position);
		this.screenSpace = new Vector3f(screenSpace);
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
	public void setTarget(final Vector3f target){
		this.target = target;
	}
	public AffineTransform transform(AffineTransform transform){
		AffineTransform t = (AffineTransform) transform.clone();
		if(target != null){
			setPosition(target);
		}
		
		t.translate(-position.getX(), -position.getY());
		t.rotate(rotation);
		t.scale(scale.getX(),scale.getY());
		t.translate(-center.getX()*screenSpace.getX(), -center.getY()*screenSpace.getY());
		return t;
	}
}
