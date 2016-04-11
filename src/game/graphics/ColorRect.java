package game.graphics;

import java.awt.Color;
import java.awt.Graphics2D;

import game.math.Vector3f;

public class ColorRect implements Drawable{
	private float depth = 0;
	private float rotation = 0;
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f center = new Vector3f(0f,0f,0);
	private Vector3f scale = new Vector3f(1,1,0);
	private Vector3f dimension = new Vector3f(0,0,0); 
	private Color color = new Color(0,0,0);
	
	public ColorRect(){}
	public ColorRect(Vector3f position,Vector3f dimension,Color color){
		this.position = position;
		this.dimension = dimension;
		this.color = color;
	};
	@Override
	public void draw(Graphics2D g) {
		Color oldC = g.getColor();
		g.setColor(color);
		g.fillRect(0, 0,(int) dimension.getX(),(int) dimension.getY());
		g.setColor(oldC);
	}
	public void setColor(Vector3f color){
		setColor((int)color.getX(),(int)color.getY(),(int)color.getZ());
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setColor(int r, int g, int b){
		setColor(new Color(r,g,b));
	}
	public Color getColor(){
		return color;
	}
	public void setDepth(float depth){
		this.depth = depth;
	}
	@Override
	public float getDepth() {
		return depth;
	}
	public void setPosition(Vector3f position){
		this.position = position;
	}
	@Override
	public Vector3f getPosition() {
		return position;
	}
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	@Override
	public float getRotation() {
		return rotation;
	}
	public void setCenter(Vector3f center){
		this.center = center;
	}
	@Override
	public Vector3f getCenter() {
		return center;
	}
	public void setScale(Vector3f scale){
		this.scale = scale;
	}
	@Override
	public Vector3f getScale() {
		return scale;
	}
	public void setDimension(Vector3f dim){
		this.dimension = dim;
	}
	@Override
	public Vector3f getDimension() {
		return dimension;
	}

}
