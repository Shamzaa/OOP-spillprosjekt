package game.entity;

public class Vector {
	// {x, y}
	private Integer[] position = new Integer[3];
	
	Vector(int x, int y){
		position[0] = x;
		position[1] = y;
		
		// calculate z-value (position[2])
		// no need for validation?
	}
	

	public void setPosition(int x, int y){
		position[0] = x;
		position[1] = y;
		
		// calculate z-position
	}
	/*
	public Integer[] getPosition(){
		return position;
	}
	*/
	// or getX, getY, getZ?
	public int getX(){
		return position[0];
	}
	public int getY(){
		return position[1];
	}
	public int getZ(){
		return position[2];
	}
}
