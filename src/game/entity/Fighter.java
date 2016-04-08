package game.entity;

import game.mechanics.Inventory;

public class Fighter extends Entity{
	private double healthPoints;
	// private double manaPoints;
	private Inventory inventory;
	// % based damage reduction from hits
	private double resistance = 0.35;
	
	public void hit(double damage){
		healthPoints -= resistance * damage;
		if(healthPoints <= 0){
			die();
		}
	}
	
	public void attack(Fighter enemy){
		// TODO: add item/spell in attack parameter?
		
	}
	
	public boolean isAlive(){
		return healthPoints > 0;
	}
	
	private void die(){
		// TODO: DIE
	}
	
}
