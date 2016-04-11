package game.mechanics;

import java.util.HashMap;

public class Item {
	private String name;
	private boolean stackable;
	private String itemId;

	// some sort of pointer to a file for information
	private static HashMap<String,Item> itemMap = new HashMap<String,Item>();
	public Item(String name,String itemId){
		if(!itemMap.containsKey(itemId)){
			this.name = name;
			this.itemId = itemId;
			itemMap.put(itemId, this);
		}else{
			throw new IllegalArgumentException("Duplicate key!\nTwo items can't share itemId");
		}
	}
	public Item(Item i){
		//Creates a new "unregistered"/unique item
		//Can not be stacked at all
		this.name = i.name;
		this.itemId = i.itemId;
		this.stackable = false;
	}
	public Item(String itemId){
		this(itemMap.get(itemId));
	}
	public static Item getItemById(String itemId){
		if(itemMap.containsKey(itemId)){
			return itemMap.get(itemId);
		}		
		throw new IllegalArgumentException("No item with id " + itemId + " exists!");
	}
	public String getName(){
		return name;
	}

	public boolean isStackable(){
		return stackable;
	}
}
