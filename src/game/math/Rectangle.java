package game.math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Locale;

import com.sun.glass.ui.Pixels.Format;

public class Rectangle extends Shape{
	

	private Vector3f dimension;
	
	public Rectangle(Vector3f position,Vector3f dimension) {
		super(position);
		this.dimension = dimension;
	}

	public boolean overlaps(Rectangle rect){
		if(withinRadius(rect)){
			Vector3f p1 = getPosition();
			Vector3f p2 = rect.getPosition();
			Vector3f e1 = p1.add(dimension);
			Vector3f e2 = p2.add(rect.dimension);
			//Vector3f AB = p1.add(getCenter()).sub(p2.add(rect.getCenter()));
			return  p1.getX() < e2.getX() && e2.getX() > p1.getX() &&
					p1.getY() < e2.getY() && e2.getY() > p1.getY();
			
		}
		return false;
	}

	@Override
	public float getRadius() {
		return getCenter().getLength();
	}

	public Vector3f getShapeCenter() {
		return dimension.scale(0.5f);
	}
	@Override
	public Vector3f getCenter() {
		return new Vector3f(0,0,0);
	}
	@Override
	public boolean overlaps(Shape shape) {
		if(shape instanceof Rectangle){
			return overlaps((Rectangle) shape);
		}
		throw new IllegalArgumentException(String.format(Locale.ENGLISH,"%s does not supported overlap with %s",this,shape));
	}

	@Override
	public String toString() {
		return String.format(Locale.ENGLISH,"Rectangle[%s,%s]",getPosition(),dimension);
	}

	@Override
	public void draw(Graphics2D g) {
		if(DO_DRAW){
			Color old = g.getColor();
			g.setColor(new Color(255,0,0));
			g.drawRect(0,0,(int) dimension.getX(),(int) dimension.getY());
			g.setColor(old);
		}
	}



	@Override
	public float getRotation() {
		return 0;
	}

	@Override
	public Vector3f getScale() {
		return new Vector3f(1,1,1);
	}

	@Override
	public Vector3f getDimension() {
		return dimension;
	}
	
}
