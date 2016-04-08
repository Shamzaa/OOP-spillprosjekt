package game.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.math.Vector3f;

public class Sprite implements Drawable{
	private float depth;
	private float rotation = 0;
	private Vector3f srcPos = new Vector3f(0,0,0);
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f center = new Vector3f(0,0,0);
	private int cFrame = 0;
	private BufferedImage image;
	private Vector3f scale = new Vector3f(1,1,0);
	private Vector3f dimension = new Vector3f(32,32,0); 
	public Sprite(BufferedImage img,Vector3f pos, Vector3f srcPos, Vector3f center, Vector3f dimension){
		image = img;
		position = pos;
		this.srcPos = srcPos;
		this.center = center;
		this.dimension = dimension;
	}
	public Sprite(BufferedImage img,Vector3f pos, int srcX,int srcY, int length, Vector3f center, Vector3f dimension){
		this(img,pos,new Vector3f(srcX,srcY,length),center,dimension);
	}
	public void draw(Graphics2D g){
		int xx = (int)(image.getWidth()/dimension.getX());
		g.drawImage(image, 0, 0, 
				(int)dimension.getX(),
				(int)dimension.getY(),
				(int)srcPos.getX() + cFrame%xx,
				(int)srcPos.getY() + (int)(cFrame/xx)*xx, 
				(int)dimension.getX(),
				(int)dimension.getY(), null);
	}
	private void inc(){
		cFrame = (cFrame + 1)%(int)srcPos.getZ();
	}
	public void setDepth(float depth){
		this.depth = depth;
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
	@Override
	public Vector3f getScale() {
		return scale;
	}
	@Override
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	// validation methods:
	
}
