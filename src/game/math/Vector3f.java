package game.math;

public class Vector3f {
	float x,y,z;
	public Vector3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float x){
		this.x = x;
	}
	public void setZ(float x){
		this.x = x;
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
	public void add(Vector3f vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
	}
}
