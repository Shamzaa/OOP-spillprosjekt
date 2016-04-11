package game.entity;

import java.awt.Color;

import game.Game;
import game.graphics.ColorRect;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.mechanics.Inventory;

public abstract class Fighter extends Entity{
	
	// % base health
	private int baseHealth = 1000;
	// % based damage reduction from hits
	private double baseResistance = 0.35;
	
	private int baseMaxHealth = 1000;
	private int healthPoints = 500;
	private double resistance = 0.35;
	private Inventory inventory;
	private boolean fighting = false;
	private ColorRect healthBarBG;
	private ColorRect healthBar;
	public Fighter(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
		Vector3f hpDim = new Vector3f(sprites[0].getDimension().getX()+6,3,0);
		healthBar = new ColorRect(position,hpDim,new Color(255,0,0));
		healthBarBG = new ColorRect(position,hpDim,new Color(100,100,100));
		
	}
	public void hit(double damage){
		healthPoints -= resistance * damage;
		if(healthPoints <= 0){
			kill();
		}
	}
	@Override
	public void render(){
		super.render();
		if(fighting){
			Vector3f pos = position.sub(currentSprite.getOffset().add(new Vector3f(0,6,0)));
			healthBar.setDepth(calcDepth());
			healthBar.setPosition(pos);
			healthBar.setScale(new Vector3f((float)healthPoints/baseMaxHealth,1,0));
			healthBarBG.setDepth(calcDepth()+1f);
			healthBarBG.setPosition(pos);
			Game.getCanvas().addToQueue(healthBarBG);
			Game.getCanvas().addToQueue(healthBar);
			
			
		}
	}
	public int getHealth(){
		return healthPoints;
	}
	public int getMaxHealth(){
		return baseMaxHealth;
	}
	public boolean isFighting(){
		return fighting;
	}
	public void attack(Fighter enemy){
		// TODO: add item/spell in attack parameter?
		
	}

	
}
