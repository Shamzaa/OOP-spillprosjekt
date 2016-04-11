package game.mechanics;

import java.util.ArrayList;
import java.util.Collections;

public class Inventory {
	private ArrayList<Stack> inventoryList = new ArrayList<Stack>();
	
	
	public void addStack(Stack stack){
		boolean merged = false;
		for(Stack s : inventoryList){
			if(s.merge(stack)){
				merged = true;
				break;
			}
		}
		if(!merged){
			inventoryList.add(stack);
		}
	}
	public Stack getStack(int index){
		return inventoryList.get(index);
	}
	
	public void removeStack(Stack stack){
		if(inventoryList.contains(stack)){
			inventoryList.remove(stack);
		}else{
			throw new IllegalArgumentException("Prøver å 'remove' et 'Item' som ikke finnes");
		}
	}
	private void sort(){
		// sorterer Inventory alfabetist bastert på Item sitt navn
		//Collections.sort(inventoryList, new ComparatorItem());
	}
}
