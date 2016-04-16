package game.graphics.effects;

import java.awt.Color;

import org.json.JSONObject;

import game.Game;
import game.entity.Entity;
import game.graphics.Sprite;
import game.graphics.gui.TextLabel;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.world.Level;

public class DamageEffect implements Updatable{
	
	private Sprite bgSprite;
	private TextLabel damageText;
	private final int milliLife = 2000;
	private int timeAcc = 0;
	private Vector3f position;
	private boolean dead = false;
	
	public DamageEffect(Vector3f pos,int damage) {
		this.position = pos;
		bgSprite = new Sprite(ResourceManager.getImage("res/dmgEffect.png"),new Vector3f(0,0,0));
		damageText = new TextLabel(String.valueOf(damage), new Color(255,60,0), 12);
		damageText.setDimension(bgSprite.getDimension().sub(new Vector3f(3-String.valueOf(damage).length(),0,0).scale(10)));
		
	}
	@Override 
	public void update(long dtime){
		timeAcc += dtime;
		this.position = this.position.sub(new Vector3f(0,64.0f*dtime/1000.0f,0));
		if(timeAcc >= milliLife){
			kill();
		}
	}
	@Override
	public void render(){
		bgSprite.setDepth(-100);
		bgSprite.setPosition(position);
		bgSprite.setCenter(new Vector3f(0.5f,0.5f,0));
		damageText.setDepth(-150);
		damageText.setPosition(position);
		
		damageText.setCenter(new Vector3f(0.22f,-0.18f,0));
		Game.getCanvas().addToQueue(bgSprite);
		Game.getCanvas().addToQueue(damageText);
	}

	@Override
	public void destroy() {
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
