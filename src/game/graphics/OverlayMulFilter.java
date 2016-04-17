package game.graphics;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Game;
import game.math.Vector3f;

public class OverlayMulFilter implements Drawable{
	private BufferedImage filter;
	
	public OverlayMulFilter(BufferedImage filterImage){
		filter = filterImage;
	}
	@Override
	public void draw(Graphics2D g) {
		Composite old = g.getComposite();
		g.setComposite(AlphaComposite.SrcOver);
		g.drawImage(filter,0,0,null);
		g.setComposite(old);
	}

	@Override
	public float getDepth() {
		return -1000000;
	}

	@Override
	public Vector3f getPosition() {
		return Game.getCanvas().getCamera().getPosition();
	}

	@Override
	public float getRotation() {
		return 0;
	}

	@Override
	public Vector3f getCenter() {
		return Game.getCanvas().getCamera().getCenter();
	}

	@Override
	public Vector3f getScale() {
		return new Vector3f(1,1,1);
	}

	@Override
	public Vector3f getDimension() {
		return new Vector3f(640,480,0);
	}

}
