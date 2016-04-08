package game.mechanics;

public class Item {
	private String name;
	// some sort of pointer to a file for information
	
	public Item(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
