package game.screen;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import game.graphics.Camera;
import game.graphics.Drawable;
import game.graphics.DrawableCompare;
import game.math.Vector3f;


public class GameCanvas extends JPanel{
	private ArrayList<Drawable> drawQueue = new ArrayList<Drawable>(255); 
	private ArrayList<Drawable> directDrawQueue = new ArrayList<Drawable>(255); 
	private Camera camera = new Camera(new Vector3f(getWidth()/2,getHeight()/2,0));
	private AffineTransform original;

	public GameCanvas(){
		super();
		setBackground(new Color(0,0,0));
	}

	public void setCamera(Camera cam){
		camera = cam;
	}
	public Camera getCamera(){
		return camera;
	}
	public void addToQueue(Drawable d){
		drawQueue.add(d);
	}
	public void addToDirectQueue(Drawable d){
		directDrawQueue.add(d);
	}
	public void render(){
		ready = false;
		repaint();
	}
	public AffineTransform getDefault(){
		return original;
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		original = g2.getTransform();
		camera.setScreenSpace(new Vector3f(this.getWidth(),this.getHeight(),0));
		drawQueue.sort(new DrawableCompare());
		g2.setTransform(camera.transform(g2.getTransform()));
		AffineTransform tmpTransform;
		for(Drawable i : drawQueue){
			tmpTransform = g2.getTransform(); //<-- 'push' transform
			g2.translate(i.getPosition().getX(),i.getPosition().getY());
			g2.scale(i.getScale().getX(), i.getScale().getY());
			g2.rotate(i.getRotation());
			g2.translate(-i.getCenter().getX()*i.getDimension().getX(), -i.getCenter().getY()*i.getDimension().getY());
			i.draw(g2);
			g2.setTransform(tmpTransform); //<-- 'pop' transform
		}
		g2.setTransform(original);
		for(Drawable i : directDrawQueue){
			tmpTransform = g2.getTransform(); //<-- 'push' transform
			g2.translate(i.getPosition().getX(),i.getPosition().getY());
			g2.scale(i.getScale().getX(), i.getScale().getY());
			g2.rotate(i.getRotation());
			g2.translate(-i.getCenter().getX()*i.getDimension().getX(), -i.getCenter().getY()*i.getDimension().getY());
			i.draw(g2);
			g2.setTransform(tmpTransform); //<-- 'pop' transform	
		}
		drawQueue.clear();
		directDrawQueue.clear();
		ready = true;
	}
}
