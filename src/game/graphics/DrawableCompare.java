package game.graphics;

import java.util.Comparator;

public class DrawableCompare implements Comparator<Drawable>{

	@Override
	public int compare(Drawable o1, Drawable o2) {
		return (o1.getDepth() - o2.getDepth()) < 0 ? 0 : -1;
	}

}
	