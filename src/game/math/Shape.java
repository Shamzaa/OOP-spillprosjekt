package game.math;

import game.graphics.Drawable;

public abstract class Shape implements Drawable{
	private Vector3f position;
	private float depth = 0;
	protected final boolean DO_DRAW = true;
	public Shape(Vector3f position){
		this.position = position;
	}
	public final Vector3f getPosition(){
		return position;
	}
	public void setPosition(Vector3f pos){
		this.position = pos;
	}
	public abstract boolean overlaps(Shape shape);
	public boolean withinRadius(Shape shape){
		Vector3f p1 = getPosition();
		Vector3f p2 = shape.getPosition();
		Vector3f AB = p1.add(getCenter()).sub(p2.add(shape.getCenter()));
		return AB.getLength() < shape.getRadius() + getRadius();
	}
	public abstract float getRadius();
	public abstract Vector3f getShapeCenter();
	public abstract String toString();
	
	@Override
	public float getDepth() {
		return depth;
	}
	public void setDepth(float depth){
		this.depth = depth;
	}
}
