package game.graphics;

import java.awt.Graphics2D;

import game.math.Vector3f;

public interface Drawable {
	public void draw(Graphics2D g);
	public float getDepth();
	public Vector3f getPosition();
	public float getRotation();
	public Vector3f getCenter();
	public Vector3f getScale();
	public Vector3f getDimension();
}
