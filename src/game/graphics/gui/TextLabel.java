package game.graphics.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.math.Vector3f;

public class TextLabel extends GuiComponent{
	private String text;
	private Color color;
	public TextLabel(String text){
		this(text,new Color(0,0,0));
	}
	public TextLabel(String text, Color color){
		this.text = text;
		this.color = color;
		
	}
	public void setText(String text){
		this.text = text;
	}
	@Override 
	public void draw(Graphics2D g){
		Color old = g.getColor();
		g.setColor(color);
		g.drawString(text, 0, 0);
		g.setColor(old);
		super.draw(g);
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
