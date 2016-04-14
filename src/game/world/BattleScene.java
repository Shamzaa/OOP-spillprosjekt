package game.world;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.graphics.Sprite;
import game.graphics.ColorRect;
import game.entity.Entity;
import game.entity.Fighter;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.graphics.gui.*;

public class BattleScene{
	
	
	private Sprite background;
	
	private Fighter player;
	private Fighter hostile;
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	
	// positions where the sprites will be placed on a battle-scene
	/*private Vector3f playerPos = new Vector3f(60, super.getHeight()/2, 4);
	private Vector3f hostilePos = new Vector3f(super.getWidth() - 60, super.getHeight()/2, 4);
	
	
	private ColorRect menuBackground = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 2), new Vector3f(super.getWidth()/2 - 5, 75, 1), new Color(0,0,0));
	private ColorRect menuBorder = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 1), new Vector3f(super.getWidth()/2, 80, 1), new Color(255,255,255));
*/
	
	public BattleScene(Sprite background, Fighter player, Fighter hostile) {
		//super(0, 0);
		this.background = background;
		this.player = player;
		this.hostile = hostile;
		entities.add(player);
		entities.add(hostile);
		background.setDepth(100000);
		
		BufferedImage img = ResourceManager.getImage("res/gui/buttonTest.png");
	
	}
	
	public void update(long dtime){
		for(Entity i : entities){
			i.update(dtime);
		}		
	}
	
	public void render(){
		background.setDepth(1000);
		Game.getCanvas().addToQueue(background);
		for(Entity i : entities){
			i.render();
		}
		player.render();
		hostile.render();
		
	}
	
	public void action(){
		
	}

}
