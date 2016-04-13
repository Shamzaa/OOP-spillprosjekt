package game.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.json.JSONArray;
import org.json.JSONObject;

import game.math.Vector3f;
import game.resource.ResourceManager;

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
	private int fps = 12;
	private long timeAcc = 0;
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
	public Sprite(Sprite spr){
		//Copy sprite
		this(spr.image,spr.position,spr.srcPos,spr.center,spr.dimension);
		this.fps = spr.fps;
	}
	public Sprite(JSONObject obj){
		this.image = ResourceManager.getImage(obj.getString("image"));
		JSONArray pos = obj.getJSONArray("index");
		this.srcPos = pos != null ? new Vector3f(pos.getInt(0),pos.getInt(1),pos.getInt(2)) : srcPos;
	}
	public void setFPS(int fps){
		this.fps = fps;
	}
	public int getFPS(){
		return fps;
	}
	public void setCurrentFrame(int frame){
		this.cFrame = frame%(int)srcPos.getZ();
	}
	public int getCurrentFrame(){
		return this.cFrame;
	}
	public void draw(Graphics2D g){
		int xx = (int)(image.getWidth()/dimension.getX());
		
		Vector3f src = new Vector3f(((srcPos.getX() + cFrame%xx)*dimension.getX()),
									((srcPos.getY() + (int)(cFrame/xx)*xx)*dimension.getY()),0);
		g.drawImage(image, 0, 0, 
				(int)dimension.getX(),
				(int)dimension.getY(),
				(int)src.getX(),
				(int)src.getY(), 
				(int)(src.getX() + dimension.getX()),
				(int)(src.getY() + dimension.getY()), null);
	}
	private void inc(){
		cFrame = (cFrame + 1)%(int)srcPos.getZ();
	}
	public void update(long dtime){
		timeAcc += dtime;
		if(timeAcc > 1000/fps){
			inc();
			timeAcc -= 1000/fps;
		}
	}
	public void setDepth(float depth){
		this.depth = depth;
	}
	@Override
	public float getDepth(){
		return depth;
	}
	public Vector3f getOffset(){
		return dimension.mul(center);
	}
	public void setPosition(Vector3f position){
		this.position = position;
	}
	
	public Vector3f getPosition(){
		return position;
	}
	@Override
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	@Override
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
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	@Override
	public Vector3f getDimension() {
		// TODO Auto-generated method stub
		return dimension;
	}
	
	// validation methods:
	
}
