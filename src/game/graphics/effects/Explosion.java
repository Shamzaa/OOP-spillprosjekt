package game.graphics.effects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.world.Level;

public class Explosion extends Entity{
	
	private int particles = 50;
	// speed and gravity are based on pixels and seconds
	private int gravity = 426;
	private ArrayList<Particle> particleContainer;
	
	public Explosion(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
		
		BufferedImage img = ResourceManager.getImage("res/particle.png");
		Vector3f particle1Pos = new Vector3f(1, 1, 1);
		// TODO: make more particles
		Vector3f centerParticles = new Vector3f(5, 5, 0);
		Vector3f particleDimension = new Vector3f(9, 9, 0);
		for(int x = 0; x < particles; x++){
			particleContainer.add(new Particle(img, position, particle1Pos, centerParticles, particleDimension));
		}
		
		// TODO Auto-generated constructor stub
		

	}



	
	public void update(long dtime){
		for(Particle particle: particleContainer){
			// delta x, y
			float dx = particle.getSpeedX()*dtime/1000;
			float dy = (float) (particle.getSpeedY()*dtime/1000 + 0.5*gravity*Math.pow(dtime/1000, 2));
			
			particle.setPosition(particle.getPosition().add(new Vector3f(dx, dy, 0)));
			particle.setSpeed(particle.getSpeedX(), particle.getSpeedY() + gravity*dtime/1000); // direction is changed in .setSpeed
			
		}
	}
	
	
	@Override
	public void enter(Level lvl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave(Level lvl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touch(Entity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
