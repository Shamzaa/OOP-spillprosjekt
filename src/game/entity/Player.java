package game.entity;
import game.math.Vector3f;
import game.world.Level;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import org.json.JSONObject;

import game.Game;
import game.graphics.*;

public class Player extends Fighter implements KeyListener{

	private boolean controlsEnabled = true;

	@SuppressWarnings("serial")
	private HashMap<Integer,Boolean> keyMap = new HashMap<Integer,Boolean>(){
		{
			put(KeyEvent.VK_W,false);
			put(KeyEvent.VK_S,false);
			put(KeyEvent.VK_D,false);
			put(KeyEvent.VK_A,false);
			put(KeyEvent.VK_ENTER,false);
		};
	};
	
	// other information sugested for init: HP, *MP*, inventory from json, 
	public Player(Vector3f position, Sprite[] sprites) {
		super(position, sprites);
	}
	public Player(Vector3f position, JSONObject data){
		super(position,data);
	}
	
	public void disableControls(){
		controlsEnabled = false;
	}
	public void enableControls(){
		controlsEnabled = true;
	}

	@Override
	public void update(long dtime){
		if(controlsEnabled){
			walkDir = new Vector3f(
					(keyMap.get(KeyEvent.VK_D) ? 1 : 0)+
					(keyMap.get(KeyEvent.VK_A) ? -1 : 0),
					(keyMap.get(KeyEvent.VK_W) ? -1 : 0)+
					(keyMap.get(KeyEvent.VK_S) ? 1 : 0),0);
		}else{
			walkDir = new Vector3f(0,0,0);
		}
		super.update(dtime);
	}
	
	@Override
	public void enter(Level lvl) {
		Game.setLevel(lvl);
		lvl.getCamera().setTarget(this);
		lvl.addListener(this);
	}

	@Override
	public void leave(Level lvl) {
		lvl.removeListener(this);
	}

	@Override
	public void destory() {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode())){
			keyMap.put(e.getKeyCode(), true);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode())){
			keyMap.put(e.getKeyCode(), false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void touch(Entity ent) {
		
	}
}
