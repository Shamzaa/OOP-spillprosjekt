package game.screen;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.graphics.Drawable;
import game.graphics.DrawableCompare;
import game.math.Vector3f;

public class GameCanvas extends JPanel{
	ArrayList<Drawable> drawQueue = new ArrayList<Drawable>(255); 
	Vector3f cameraPos = new Vector3f(0,0,0);
	float rotation = 0;
	BufferedImage imgTest;
	
	public GameCanvas(){
		try {
			imgTest = ImageIO.read(new File("res/testImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setCameraPos(Vector3f pos){
		cameraPos = pos;
	}
	public void addToQueue(Drawable d){
		drawQueue.add(d);
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawQueue.sort(new DrawableCompare());
		cameraPos.add(new Vector3f(1,0,0));
		g2.translate(cameraPos.getX(),cameraPos.getY());
		g2.translate(128, 128);
		rotation += Math.PI * 0.0005;
		g2.rotate(rotation);
		g2.scale(2, 2);
		g2.translate(-64, -64);
		g.drawImage(imgTest, 0, 0,128,128,null);
		AffineTransform tmpTransform;
		for(Drawable i : drawQueue){
			tmpTransform = g2.getTransform(); //<-- 'push' transform
			g2.getTransform();
			g2.translate(i.getPosition().getX(),i.getPosition().getY());
			g2.scale(i.getScale().getX(), i.getScale().getY());
			g2.rotate(i.getRotation());
			g2.translate(-i.getCenter().getX(), -i.getCenter().getY());
			i.draw(g2);
			g2.setTransform(tmpTransform); //<-- 'pop' transform
		}
		drawQueue.clear();
	}
}
