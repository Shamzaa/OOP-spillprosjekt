package game.mechanics;

import java.util.ArrayList;
import java.util.Collections;

public class Inventory {
	private ArrayList<Item> inventoryList = new ArrayList<Item>();
	
	public void addItem(Item item){
		inventoryList.add(item);
		// sort
		
	}
	
	public void removeItem(Item item){
		if(inventoryList.contains(item)){
			inventoryList.remove(item);
		}else{
			throw new IllegalArgumentException("Prøver å remove et Item som ikke finnes");
		}
	}
	private void sort(){
		// sorterer Inventory alfabetist bastert på Item sitt navn
		Collections.sort(inventoryList, new ComparatorItem());
	}
}
