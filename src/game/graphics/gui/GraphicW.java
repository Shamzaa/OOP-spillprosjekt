package game.graphics.gui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import game.graphics.Drawable;
import game.math.Vector3f;

public class GraphicW extends GuiComponent{
	
	public ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	public GraphicW(Vector3f position){
		this.setPosition(position);
	}
	public void addDrawable(Drawable d){
		drawables.add(d);
	}
	@Override
	public void draw(Graphics2D g2){
		super.draw(g2);
		AffineTransform tmpTransform;
		for(Drawable i : drawables){
			tmpTransform = g2.getTransform(); //<-- 'push' transform
			g2.translate(i.getPosition().getX(),i.getPosition().getY());
			g2.scale(i.getScale().getX(), i.getScale().getY());
			g2.rotate(i.getRotation());
			g2.translate(-i.getCenter().getX()*i.getDimension().getX(), -i.getCenter().getY()*i.getDimension().getY());
			i.draw(g2);
			g2.setTransform(tmpTransform); //<-- 'pop' transform
		}
		
	}

}
