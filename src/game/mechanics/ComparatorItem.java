package game.mechanics;

import java.util.Comparator;

public class ComparatorItem implements Comparator<Item> {
	
	@Override
	public int compare(Item one, Item two){
		return one.getName().compareTo(two.getName());
	}
}
