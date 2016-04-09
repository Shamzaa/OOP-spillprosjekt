package game.mechanics;

public interface Wearable {
	public enum Slots{
		LeftHand,
		RightHand,
		Head,
		Body,
		Legs,
	}
	
	public Slots[] getPossibleSlots();
	
}
