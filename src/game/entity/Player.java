package game.entity;
import game.math.Vector3f;
import game.graphics.*;

public class Player extends Entity{
	Player(float x, float y, float z){
		position = new Vector3f(x, y, z);
		// TODO: current sprite, other info loaded from file?
		sprite = new Sprite();
	}
	
	public void move(int x, int y){
		// will get an x and y value to move, depending on buttons pushed in main program
		// only w: x = 0, y = 1;
		// a + d: x = 0, y = 0;
		// summed up: a sum for each x and y depending on which buttons are down
		
		/*
		 	TODO: calculate how much Player will move for delta time
		 	and return x and y values for .setPosition
		 */
		
		position.setX(x);
		position.setY(y);
		// calculate z?
		
		if(x > 0){
			// turn sprite to right
		}else if(x < 0){
			// turn sprite to left
		}else{
			if(y > 0){
				// turn sprite down
			}else if(y < 0){
				// turn sprite up
			}
			// don't change sprite
		}
	}
}
