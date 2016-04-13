package game.world;



import java.awt.Color;

import game.Game;
import game.graphics.Sprite;
import game.graphics.ColorRect;
import game.entity.Fighter;
import game.math.Vector3f;

public class BattleScene{
	private Sprite background;
	
	private Fighter player;
	private Fighter hostile;
	
	// positions where the sprites will be placed on a battle-scene
	/*private Vector3f playerPos = new Vector3f(60, super.getHeight()/2, 4);
	private Vector3f hostilePos = new Vector3f(super.getWidth() - 60, super.getHeight()/2, 4);
	
	
	private ColorRect menuBackground = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 2), new Vector3f(super.getWidth()/2 - 5, 75, 1), new Color(0,0,0));
	private ColorRect menuBorder = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 1), new Vector3f(super.getWidth()/2, 80, 1), new Color(255,255,255));
*/
	
	public BattleScene(Sprite background, Fighter hostile, Fighter player) {
		//super(0, 0);
		this.background = background;
		this.player = player;
		this.hostile = hostile;
		
	}
	
	public void update(long dtime){
	
	}
	
	public void render(){
		background.setDepth(1000);
		Game.getCanvas().addToQueue(background);
		
	}
	
	public void action(){
		player.attack(hostile);
		
		if(hostile.isAlive()){
			
			hostile.attack(player);
			
			if(player.isAlive()){
				return;
			}
			
			// death screen, load/restart
			
		}else{
			// victory screen, return to world map
		}
		
	}

}
