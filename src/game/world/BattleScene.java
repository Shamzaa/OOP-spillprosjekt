package game.world;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.graphics.Sprite;
import game.graphics.ColorRect;
import game.entity.Entity;
import game.entity.Fighter;
import game.entity.Player;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.tile.Ground;
import game.tile.Tile;
import game.tile.Wall;
import game.graphics.gui.*;

public class BattleScene extends Level{
	
	
	private Sprite background;
	
	private Fighter player;
	private Fighter hostile;
	private Level prev;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Vector3f pLPos;
	private Vector3f hLPos;
	
	// positions where the sprites will be placed on a battle-scene
	private Vector3f playerPos = new Vector3f(100,480 - 480/4, 1);
	private Vector3f hostilePos = new Vector3f(640-100,480 - 480/3, 1);
	
	/*
	private ColorRect menuBackground = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 2), new Vector3f(super.getWidth()/2 - 5, 75, 1), new Color(0,0,0));
	private ColorRect menuBorder = new ColorRect(new Vector3f(super.getWidth()/2, super.getHeight() - 40, 1), new Vector3f(super.getWidth()/2, 80, 1), new Color(255,255,255));
*/
	
	public BattleScene(Sprite background, Fighter player, Fighter hostile, Level prev) {
		super(0, 0);
		this.background = background;
		this.player = player;
		this.hostile = hostile;
		entities.add(player);
		entities.add(hostile);
		hLPos = player.getPosition();
		pLPos = player.getPosition();
		player.setPosition(playerPos);
		hostile.setPosition(hostilePos);
		
		prev.moveEntityTo(player, this);
		prev.moveEntityTo(hostile, this);
		
		player.setFighting(true);
		hostile.setFighting(true);
		//background.setDepth(100000);
		getCamera().setPosition(new Vector3f(640/2,480/2,0));
	
	}
	
	public void update(long dtime){
		super.update(dtime);
		if(!hostile.isAlive()){
			//PLAYER WIN
		}
		if(!player.isAlive()){
			//GAME OVER
		}
	}
	public void enter(){
		super.enter();
		//Set gui to FightingGUI
		((Player) player).disableControls();
	}
	public void leave(){
		//Set gui to normal
		super.leave();
		((Player) player).enableControls();
	}
	public void render(){
		background.setDepth(1000);
		Game.getCanvas().addToQueue(background);
		//getCamera().setTarget(null);
		//getCamera().setPosition(new Vector3f(0,0,0));
		for(Entity i : entities){
			i.render();
		}
		player.render();
		hostile.render();
		
	}
	
	public void action(){
		
	}

}
