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
			inventoryList.add(new Stack(stack));
			stack.setQty(0);
		}
	}
	public Stack getStack(int index){
		return inventoryList.get(index);
	}
	public void moveStack(int index,int qty,Inventory other){
		Stack s = getStack(index);
		if(qty > 0 && qty <= s.getQty()){
			other.addStack(new Stack(s.getItem(),qty));
			s.setQty(s.getQty() - qty);
			if(s.getQty() <= 0){
				removeStack(s);
			}
		}
	}
	public void moveStack(int index,Inventory other){
		moveStack(index,getStack(index).getQty(),other);
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
		Collections.sort(inventoryList, new ComparatorStack());
	}
}
