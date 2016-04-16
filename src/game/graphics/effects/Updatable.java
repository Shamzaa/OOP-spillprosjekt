package game.graphics.effects;

public interface Updatable {
	public void update(long dtime);
	public void render();
	public void destroy();
	public void kill();
	public boolean isAlive();
}
