package game.mechanics;

public interface Wearable {
	public final int slotCount = 5;
	public enum ItemSlot{
		
		LeftHand(0),
		RightHand(1),
		Head(2),
		Body(3),
		Legs(4);
		private int slot;
		ItemSlot(int index){
			slot = index;
		}
		public int slot(){
			return slot;
		}
		
	}
	public ItemSlot[] getPossibleSlots();
	
}
