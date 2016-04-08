package game.graphics;

import java.awt.Graphics2D;

import game.math.Vector3f;

public interface Drawable {
	public void draw(Graphics2D g);
	public void setDepth(float depth);
	public float getDepth();
	public void setPosition(Vector3f pos);
	public Vector3f getPosition();
	public float getRotation();
	public void setRotation(float r);
	public Vector3f getCenter();
	public void setCenter(Vector3f center);
}
