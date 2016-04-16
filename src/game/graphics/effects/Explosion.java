package game.graphics.effects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.entity.Entity;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.world.Level;

public class Explosion implements Updatable{
	
	private int particles = 500;
	// speed and gravity are based on pixels and seconds
	private int gravity = 1000;
	private boolean dead = false;
	private final long lifeTime = 2000;
	private long accTime = 0;
	private ArrayList<Particle> particleContainer = new ArrayList<Particle>(particles);
	
	public Explosion(Vector3f position) {
		this(position, new Vector3f(1,0.45f,0));
	}
	public Explosion(Vector3f position,Vector3f distrebution) {
		this(position,distrebution,-100);
	
	}
	public Explosion(Vector3f position,Vector3f distrebution,float initSpeed) {
		
		BufferedImage img = ResourceManager.getImage("res/particle.png");
		Vector3f particle1Pos = new Vector3f(0, 0, 1);
		// TODO: make more particles
		Vector3f centerParticles = new Vector3f(5, 5, 0);
		Vector3f particleDimension = new Vector3f(9, 9, 0);
		for(int x = 0; x < particles; x++){
			particleContainer.add(new Particle(img, position,distrebution,initSpeed, particle1Pos, centerParticles, particleDimension));
		}
	
	}


	
	public void update(long dtime){
		accTime+=dtime;
		if(accTime >= lifeTime){
			kill();
		}
		for(Particle particle: particleContainer){
			
			// delta x, y
			float dx = particle.getSpeedX()*dtime/1000.0f;
			float dy = (float) (particle.getSpeedY()*dtime/1000.0f + 0.5*gravity*Math.pow(dtime/1000, 2));
			//float dx = 32*dtime;
			//float dy = 0;
			particle.setPosition(particle.getPosition().add(new Vector3f(dx, dy, 0)));
			particle.setSpeed(particle.getSpeedX(), particle.getSpeedY() + gravity*dtime/1000); // direction is changed in .setSpeed
			
		}
	}
	
	
	


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		for(Particle particle : particleContainer){
			Game.getCanvas().addToQueue(particle);
		}
	}




	@Override
	public void kill() {
		dead = true;
	}



	@Override
	public boolean isAlive() {
		return !dead;
	}
	
	
	

}
