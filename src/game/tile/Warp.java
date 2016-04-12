package game.tile;

import game.Game;
import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.world.Level;

public class Warp extends Tile{
	
	private Level targetLevel;
	private Vector3f targetPosition;
	
	public Warp(Sprite s, Level tL, Vector3f tP){
		super(s);
		targetLevel = tL;
		targetPosition = tP;
	}
	public Warp(Warp t) {
		super(t);
		solid = false;
		targetLevel = t.targetLevel;
		targetPosition = t.targetPosition;
	}

	@Override
	public void enter(Entity ent) {
		Game.getCurrentLevel().moveEntityTo(ent, targetLevel);
		ent.setPosition(targetPosition);
	}

	@Override
	public void leave(Entity ent) {
		// TODO Auto-generated method stub
		
	}

}
