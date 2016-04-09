package game.entity;

import game.mechanics.Inventory;

public class Fighter extends Entity{
	// % base health
	private int baseHealth = 1000;
	// % based damage reduction from hits
	private double baseResistance = 0.35;
	
	private int baseMaxHealth = 1000;
	private int healthPoints = 1000;
	private double resistance = 0.35;
	private Inventory inventory;
	
	public void hit(double damage){
		healthPoints -= resistance * damage;
		if(healthPoints <= 0){
			kill();
		}
	}
	
	public void attack(Fighter enemy){
		// TODO: add item/spell in attack parameter?
		
	}

	
}
