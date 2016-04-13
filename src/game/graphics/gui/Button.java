package game.graphics.gui;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.graphics.Sprite;
import game.math.Vector3f;

public class Button extends GuiComponent{
	private Sprite sprite;
	private TextLabel label = new TextLabel("");
	private boolean toggle = false;
	private boolean down = false;
	private boolean on = false;
	
	public Button(String text,Sprite sprite){
		label.setText(text);
		this.sprite = sprite;
		add(label);
		dimension = new Vector3f(sprite.getDimension());
		//label.position = new Vector3f(dimension.scale(0.5f));
		label.dimension = dimension;
		label.center = new Vector3f(-0.25f,-0.6f,0);
	}
	public Button(String text, Sprite sprite, boolean toggle){
		this(text,sprite);
		this.toggle = toggle;
	}
	public void draw(Graphics2D g){
		sprite.setCurrentFrame(down ? 1 : 0);
		sprite.draw(g);
		label.center = new Vector3f(-0.25f,sprite.getCurrentFrame() == 0 ? -0.6f : -0.7f,0);
		super.draw(g);
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		if(toggle){
			on = !on;
			down = on;
		}
		
		ActionEvent actionEvent = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,toggle ? (on ? "DOWN" : "UP") : "CLICK");
		for(ActionListener i : actionListeners){
			i.actionPerformed(actionEvent);
		}
		super.mouseClicked(event);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1){
			down = true;
		}
		super.mousePressed(event);
	
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1){
			down = on;
		}
		sprite.setCurrentFrame(0);
	}
	@Override
	public void mouseEntered(MouseEvent evt){
		for(MouseListener l : mouseListeners){
			l.mouseEntered(evt);
		}
	}
	@Override
	public void mouseExited(MouseEvent evt){
		for(MouseListener l : mouseListeners){
			l.mouseExited(evt);
		}
	}

}
