package game.graphics.gui;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import game.Game;
import game.graphics.Drawable;
import game.math.Vector3f;

public abstract class GuiComponent implements Drawable, MouseListener, MouseMotionListener{
	private float depth = 0;
	private float rotation;
	
	private ArrayList<GuiComponent> children = new ArrayList<GuiComponent>();
	private MouseEvent prevMoveEvent = null;
	protected ArrayList<MouseListener> mouseListeners = new ArrayList<MouseListener>();
	protected ArrayList<MouseMotionListener> mouseMotionListeners = new ArrayList<MouseMotionListener>();
	private boolean show = true;
	protected ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
	protected GuiComponent parent;
	protected Vector3f position = new Vector3f(0,0,0);
	protected Vector3f center = new Vector3f(0,0,0);
	protected Vector3f dimension = new Vector3f(0,0,0);
	protected Vector3f scale = new Vector3f(1,1,0);
	private boolean hover = false;
	
	public void draw(Graphics2D g2){
		//Copy pasta
		AffineTransform tmpTransform;
		for(GuiComponent i : children){
			if(i.isVisible()){
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
	@Override
	public float getDepth() {
		return depth;
	}
	public boolean mouseHover(){
		return hover;
	}
	public void add(GuiComponent component){
		component.parent = this;
		children.add(component);
	}
	public void hide(){
		show = false;
	}
	public void show(){
		show = true;
	}
	public boolean isVisible(){
		return show;
	}
	public void addListener(ActionListener listener){
		actionListeners.add(listener);
	}
	public void addListener(MouseListener  listener){
		mouseListeners.add(listener);
	}
	public void addListener(MouseMotionListener listener){
		mouseMotionListeners.add(listener);
	}
	public void removeListener(ActionListener listener){
		actionListeners.remove(listener);
	}
	public void removeListener(MouseListener  listener){
		mouseListeners.remove(listener);
	}
	public void removeListener(MouseMotionListener listener){
		mouseMotionListeners.remove(listener);
	}
	public void remove(GuiComponent component){
		component.parent = null;
		children.remove(component);
	}
	public void setPosition(Vector3f pos){
		position = pos;
	}
	public void setDimension(Vector3f dimension){
		this.dimension = dimension;
	}
	public void setDepth(float depth){
		this.depth = depth;
	}
	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public Vector3f getCenter() {
		return center;
	}
	public void setCenter(Vector3f center){
		this.center = center;
	}
	@Override
	public Vector3f getScale() {
		return scale;
	}

	@Override
	public Vector3f getDimension() {
		return dimension;
	}
	private boolean doPerformeEvent(MouseEvent event, GuiComponent child){
		Vector3f tright = child.getPosition().sub(child.getDimension().mul(child.getCenter()));
		Vector3f bleft = tright.add(child.getDimension());
		//System.out.println(event.getX() + " : " + event.getY());
		//System.out.println((event.getX() >= tright.getX()) + " : " + (event.getX() < bleft.getX()) + " : " + (event.getY() >= tright.getY()) + " : " + (event.getY() < bleft.getY()));
		return child.isVisible() && event.getX() >= tright.getX() && event.getX() < bleft.getX() &&
				   event.getY() >= tright.getY() && event.getY() < bleft.getY();
			
	}
	public MouseEvent getLocalEvent(MouseEvent event, GuiComponent child){
		Vector3f eventPosition = new Vector3f(event.getX(),event.getY(),0);
		Vector3f tright = child.getPosition().sub(child.getDimension().mul(child.getCenter()));
		Vector3f localPos = eventPosition.sub(tright);
		return new MouseEvent(
				(Component) event.getSource(),event.getID(),event.getWhen(),event.getModifiers(),
				(int)localPos.getX(),(int)localPos.getX(),event.getClickCount(),
				event.isPopupTrigger(),event.getButton());		
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		for(GuiComponent i : children){
			if(doPerformeEvent(event, i)){
				i.mouseClicked(getLocalEvent(event, i));
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent event) {
		hover = true;
	}
	@Override
	public void mouseExited(MouseEvent event) {
		hover = false;
	}
	@Override
	public void mousePressed(MouseEvent event) {
		for(GuiComponent i : children){
			if(doPerformeEvent(event, i)){
				i.mousePressed(getLocalEvent(event, i));
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		for(GuiComponent i : children){
			i.mouseReleased(getLocalEvent(event, i)); 
		}
	}
	@Override
	public void mouseDragged(MouseEvent event) {
		for(GuiComponent i : children){
			if(doPerformeEvent(event, i)){
				i.mouseDragged(getLocalEvent(event, i));
			}
		}	
	}
	@Override
	public void mouseMoved(MouseEvent event) {
		for(GuiComponent i : children){
			if(prevMoveEvent != null){
				if(!doPerformeEvent(prevMoveEvent, i) && doPerformeEvent(event, i)){
					i.mouseEntered(new MouseEvent((Component) event.getSource(),MouseEvent.MOUSE_ENTERED,event.getWhen(),event.getModifiers(),event.getX(),event.getY(),event.getClickCount(),event.isPopupTrigger()));	
				}else if(doPerformeEvent(prevMoveEvent, i) && !doPerformeEvent(event, i)){
					i.mouseExited(new MouseEvent((Component) event.getSource(),MouseEvent.MOUSE_EXITED,event.getWhen(),event.getModifiers(),event.getX(),event.getY(),event.getClickCount(),event.isPopupTrigger()));	
				}
			}
			if(doPerformeEvent(event, i)){
				i.mouseMoved(getLocalEvent(event, i));
			}
		}
		prevMoveEvent = event;
	}
	
}
