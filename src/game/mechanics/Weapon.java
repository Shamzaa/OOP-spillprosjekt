package game.mechanics;

public class Weapon extends Item{
	private int damage;
	public Weapon(String name, String itemId,int damage) {
		super(name, itemId);
		this.damage = damage;
	}
	public Weapon(Weapon wep){
		super(wep);
		this.damage = wep.damage;
	}
	public int getDamage(){
		return damage;
	}

}
