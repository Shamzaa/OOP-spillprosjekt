package game.screen;


import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.ArrayList;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import game.graphics.Camera;
import game.graphics.Drawable;
import game.graphics.DrawableCompare;
import game.math.Vector3f;


public class GameCanvas extends JPanel{
	ArrayList<Drawable> drawQueue = new ArrayList<Drawable>(255); 
	Camera camera = new Camera(new Vector3f(getWidth()/2,getHeight()/2,0));
	private boolean ready = true;

	public boolean isReady(){
		return ready;
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
	@Override
	protected void paintComponent(Graphics g){
		ready = false;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
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
		drawQueue.clear();
		ready = true;
	}
}
