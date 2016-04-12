package game.mechanics;

import java.util.Comparator;

public class ComparatorStack implements Comparator<Stack> {
	
	@Override
	public int compare(Stack one, Stack two){
		return one.getItem().getName().compareTo(two.getItem().getName());
	}
}
