package game.graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import game.math.Vector3f;

public class TextLabel extends GuiComponent{
	private String text;
	private Color color;
	private Font font;
	public TextLabel(String text){
		this(text,new Color(0,0,0),14);
	}
	public TextLabel(String text, Color color){
		this(text,color,14);
	}
	public TextLabel(String text, Color color, int size){
		this.text = text;
		this.color = color;
		this.font = new Font("Consolas",0,size);
	}
	public void setText(String text){
		this.text = text;
	}
	@Override 
	public void draw(Graphics2D g){
		Color old = g.getColor();
		Font oldFont = g.getFont();
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, 0, 0);
		g.setColor(old);
		g.setFont(oldFont);
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
