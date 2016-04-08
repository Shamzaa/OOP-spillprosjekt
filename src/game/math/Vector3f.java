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
	public Vector3f(Vector3f v,boolean d2){
		this(v.x,v.y,d2 ? 0 : v.z);
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
	
	public Vector3f sub(Vector3f vec){
		return new Vector3f(x-vec.x,y-vec.y,z-vec.z);
	}
	
	public Vector3f mul(Vector3f vec){
		return new Vector3f(x * vec.x,y * vec.y,z * vec.z);
	}
	
	public Vector3f div(Vector3f vec){
		return new Vector3f(x/vec.x,y/vec.y,z/vec.z);
	}
	
	public float dot(Vector3f vec){
		//Returns dot product
		Vector3f tmp = mul(vec);
		return tmp.x + tmp.y + tmp.z;
	}
	
	public float getAngle(){
		return getAngleBetween(new Vector3f(1,0,0));
	}
	public float getAngleBetween(Vector3f vec){
		float dotProduct = dot(vec);
		return dotProduct/(getLength()*vec.getLength());
		
	}
	public Vector3f scale(float size){
		return new Vector3f(x*size, y*size, z*size);
	}
	
	public Vector3f normalize(){
		float length = getLength();
		return new Vector3f(x/length, y/length, z/length);
	}
	
	public float getLength2D(){
		Vector3f fVec = new Vector3f(this,true);
		return fVec.getLength();
	}
	
	public float getLength(){
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));	
	}
}
