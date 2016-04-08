package drawable;

public class Entity {
	protected Vector position;
	/* vars needed:
	 * sprite
	 * size?
	 * trigger: what can this entity do when "activated"
	 * 
	 */
	
	// other init vars needed: filepath to sprite, file containing options to import

	public void setPosition(int x, int y){
		position.setPosition(x, y);
	}
}
