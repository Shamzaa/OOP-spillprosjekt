package game.graphics.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.graphics.Sprite;
import game.math.Vector3f;

public class HoverArea extends GuiComponent{
	private Sprite spr;
	public HoverArea(Vector3f pos,Sprite spr){
		dimension = spr.getDimension();
		position = pos;
		this.spr = spr;
	}
	public void draw(Graphics2D g){
		spr.setCenter(center);
		spr.setPosition(position);
		spr.setCurrentFrame(mouseHover() ? 1 : 0);
		spr.draw(g);
		super.draw(g);
	}
	@Override
	public void mouseEntered(MouseEvent evt){
		for(MouseListener l : mouseListeners){
			l.mouseEntered(evt);
		}
		super.mouseEntered(evt);
	}
	@Override
	public void mouseExited(MouseEvent evt){
		for(MouseListener l : mouseListeners){
			l.mouseExited(evt);
		}
		super.mouseExited(evt);
	}
	
	
	
	
}
