package game.math;

import java.lang.Math;
public class Vector3f {
	float x,y,z;
	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3f(Vector3f v){
		this(v.x,v.y,v.z);
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public void setZ(float z){
		this.z = z;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getZ(){
		return z;
	}
	public Vector3f add(Vector3f vec) {
		return new Vector3f(x + vec.x, y + vec.y, z + vec.z);
	}
	public Vector3f scale(float size){
		return new Vector3f(x*size, y*size, z*size);
	}
	
	public Vector3f normalize(){
		float length = getLength2D();
		return new Vector3f(x/length, y/length, z/length);
	}
	
	public float getLength2D(){
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
