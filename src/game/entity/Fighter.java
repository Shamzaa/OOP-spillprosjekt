package game.entity;

import java.awt.Color;

import org.json.JSONObject;

import game.Game;
import game.graphics.ColorRect;
import game.graphics.Sprite;
import game.graphics.effects.DamageEffect;
import game.graphics.effects.Explosion;
import game.math.Vector3f;
import game.mechanics.Inventory;
import game.mechanics.Wearable;
import game.mechanics.Wearable.ItemSlot;
import game.sound.AudioChannel;
import game.sound.AudioManager;
import game.world.BattleScene;

public abstract class Fighter extends Entity{
	
	// % base health
	protected int baseHealth = 1000;
	// % based damage reduction from hits
	protected double baseResistance = 0.35;
	
	protected int baseMaxHealth = 1000;
	protected int healthPoints = baseHealth;
	protected double resistance = 0.35;
	
	protected float baseDamage = 320;
	protected boolean fighting = false;
	private Fighter strikeTarget = null;
	private Vector3f startPos = null;
	
	private float attackSpeed = 600;
	private ColorRect healthBarBG;
	private ColorRect healthBar;
	private Wearable[] itemSlots = new Wearable[Wearable.slotCount];
	private AudioChannel hitSound = AudioManager.getMixer().addChannel("hit", "res/sound_fx/hit.wav");
	
	public Fighter(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
		Vector3f hpDim = new Vector3f(sprites[0].getDimension().getX()+6,3,0);
		healthBar = new ColorRect(position,hpDim,new Color(255,0,0));
		healthBarBG = new ColorRect(position,hpDim,new Color(50,50,50));
		
	}
	
	public void equip(Wearable w){
		ItemSlot[] pSlots = w.getPossibleSlots();
		boolean anyFree = false;
		for(ItemSlot i : pSlots){
			if(itemSlots[i.slot()] == null){
				itemSlots[i.slot()] = w;
				anyFree = true;
				break;
			}
		}
		if(!anyFree){
			itemSlots[pSlots[0].slot()] = w;
		}
	}
	
	public Fighter(Vector3f position, JSONObject obj){
		super(position,obj);
		Vector3f hpDim = new Vector3f(sprites[0].getDimension().getX()+6,3,0);
		healthBar = new ColorRect(position,hpDim,new Color(255,0,0));
		healthBarBG = new ColorRect(position,hpDim,new Color(50,50,50));
		
		if(obj.has("baseDamage")){
		
			baseDamage = obj.getInt("baseDamage");
		}
		if(obj.has("baseMaxHealth")){
			baseMaxHealth = obj.getInt("baseMaxHealth");
			baseHealth = baseMaxHealth;
			healthPoints = baseHealth;
		}
	}
	
	public void hit(double damage){
		double randomBlock = Math.random()*(1-resistance);
		int hpDraw = (int) ((resistance+randomBlock) * damage);
		healthPoints -= hpDraw;
		Game.getCurrentLevel().addUpdatable(new DamageEffect(position.sub(new Vector3f(0,currentSprite.getDimension().getY(),0)),(int)Math.ceil((float)hpDraw/10.0f)));
		Game.getCurrentLevel().addUpdatable(new Explosion(position.add(currentSprite.getDimension().sub(currentSprite.getOffset()))));
		hitSound.play();
		if(healthPoints <= 0){
			kill();
		}
	}

	@Override
	public void render(){
		super.render();
		if(fighting){
			Vector3f pos = position.sub(currentSprite.getOffset().add(new Vector3f(0,6,0)));
			healthBar.setDepth(calcDepth());
			healthBar.setPosition(pos);
			healthBar.setScale(new Vector3f((float)healthPoints/baseMaxHealth,1,0));
			healthBarBG.setDepth(calcDepth()+1f);
			healthBarBG.setPosition(pos);
			Game.getCanvas().addToQueue(healthBarBG);
			Game.getCanvas().addToQueue(healthBar);
		}
	}
	public int getHealth(){
		return healthPoints;
	}
	public int getMaxHealth(){
		return baseMaxHealth;
	}
	public boolean isFighting(){
		return fighting;
	}
	public float getHealthP(){
		return (float)getHealth()/getMaxHealth();
	}
	public void setFighting(boolean fig){
		this.fighting = fig;
	}
	public void setHealth(int h){
		this.healthPoints = Math.max(h,getMaxHealth());
	}
	public void update(long dtime){
		super.update(dtime);
		if(fighting){
			setSpeed(attackSpeed);
			face(new Vector3f(this instanceof Player ? 1 : -1,0,0));
			Vector3f diff = null;
			if(strikeTarget != null){
				diff = strikeTarget.position.sub(position);//position.sub(strikeTarget.position);
				setDirection(diff);
				if(diff.getLength2D() < 32){
					strikeTarget.hit(baseDamage);
					strikeTarget = null;
				}
			}else if(startPos != null){
				diff = startPos.sub(position);//position.sub(startPos);
				setDirection(diff);
				if(diff.getLength2D() < 10){
					this.position = startPos;
					startPos = null;
					setDirection(new Vector3f(0,0,0));
					((BattleScene)Game.getCurrentLevel()).nextTurn();
					
				}
			}
		}else{
			setSpeed((float)walkSpeed);
		}
		
		
		
	}
	public void attack(Fighter enemy){
		//int damage = 100;
		//setSpeed(attackSpeed);
		strikeTarget = enemy;
		startPos = position;
		//enemy.hit(damage);
		
	}
	public void battleAction(){
		if(isFighting()){
			attack(((BattleScene)Game.getCurrentLevel()).getOponent(this));
		}
	}

	
}
