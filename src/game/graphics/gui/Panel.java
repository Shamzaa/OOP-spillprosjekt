package game.graphics.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.Sprite;
import game.math.Vector3f;

public class Panel extends GuiComponent{
	private BufferedImage bg;
	private Sprite sprite;
	public Panel(Vector3f position,Vector3f dimension,BufferedImage bg){
		this.position = position;
		this.dimension = dimension;
		this.bg = bg;
	}
	public void draw(Graphics2D g){
		if(bg!=null){
			g.drawImage(bg,0,0, null);
		}
		super.draw(g);
	}
	
}
