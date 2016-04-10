package game.graphics;

import java.util.Comparator;

public class DrawableCompare implements Comparator<Drawable>{

	@Override
	public int compare(Drawable o1, Drawable o2) {
		return (int)Math.signum(-o1.getDepth() + o2.getDepth());
	}

}
	