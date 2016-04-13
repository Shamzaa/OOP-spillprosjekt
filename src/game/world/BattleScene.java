package game.world;



import java.awt.Color;
import java.awt.image.BufferedImage;

import game.Game;
import game.graphics.Sprite;
import game.graphics.ColorRect;
import game.entity.Fighter;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.graphics.gui.*;

public class BattleScene{
	
	
	private Sprite background;
	
	private Fighter player;
	private Fighter hostile;
	
	private Button attack;
	private Button fireball;
	
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
		
		background.setDepth(100000);
		
		BufferedImage img = ResourceManager.getImage("res/gui/buttonTest.png");
		attack = new Button("Attack",new Sprite(img,new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0)));
		fireball = new Button("Explosion",new Sprite(img,new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0)));
	
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
