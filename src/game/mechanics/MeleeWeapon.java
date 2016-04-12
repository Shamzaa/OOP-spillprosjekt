package game.mechanics;

public class MeleeWeapon extends Weapon implements Wearable{
	private static ItemSlot[] slots = {ItemSlot.RightHand,ItemSlot.LeftHand};
	public MeleeWeapon(String name, String itemId, int damage) {
		super(name, itemId, damage);
	}

	@Override
	public ItemSlot[] getPossibleSlots() {
		return slots;
	}
	

}
