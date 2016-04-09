package game.mechanics;

public class MeleeWeapon extends Weapon implements Wearable{
	private static Slots[] slots = {Slots.RightHand,Slots.LeftHand};
	public MeleeWeapon(String name, String itemId, int damage) {
		super(name, itemId, damage);
	}

	@Override
	public Slots[] getPossibleSlots() {
		return slots;
	}
	

}
