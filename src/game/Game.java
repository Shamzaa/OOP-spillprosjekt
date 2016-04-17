package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.HashMap;


import javax.swing.Timer;

import org.json.JSONArray;
import org.json.JSONObject;


import game.entity.Fighter;
import game.entity.Hostile;
import game.entity.Player;
import game.graphics.Camera;
import game.graphics.Sprite;
import game.graphics.gui.Button;
import game.graphics.gui.FightGUI;
import game.graphics.gui.MainGUI;
import game.graphics.gui.HoverArea;
import game.graphics.gui.Panel;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.screen.GameCanvas;
import game.screen.Screen;
import game.sound.AudioChannel;
import game.sound.AudioMixer;
import game.tile.Ground;
import game.tile.Tile;
import game.tile.Wall;
import game.world.Level;

public class Game implements KeyListener, MouseListener, ActionListener, FocusListener{
	private int worldWidth;
	private ArrayList<Level> levels = new ArrayList<Level>();
	private HashMap<String,Level> levelMap = new HashMap<String,Level>();
	private HashMap<String,JSONObject> entityProtoMap = new HashMap<String,JSONObject>();
	private Level currentLevel;
	private Screen gameScreen;
	private static Game game = null;
	private Button testButton;
	private Button testButton2;
	private Panel testPanel;
	private Player player;
	private HoverArea testHover;
	private Panel currentGUI = new MainGUI();
	private Timer upTimer;
	private long ctime,dtime,ltime,timeAcc,frames;

	
	public Game(String configFile){
		Game.game = this;
		init(configFile);
		run();
	}

	public static void init(String configFile){
		byte[] buffer = ResourceManager.getFileBuffer(configFile);
		JSONObject cfg = new JSONObject(new String(buffer));
		init(cfg);
	}
	public static void init(JSONObject gameMeta){
		game.gameScreen = new Screen();
		game.gameScreen.addKeyListener(game);
		game.gameScreen.addMouseListener(game);
		game.gameScreen.getCanvas().setRequestFocusEnabled(true);
		
		JSONObject playerMeta = gameMeta.getJSONObject("player");
		JSONArray a = playerMeta.getJSONArray("position");
		game.player = new Player(new Vector3f(a.getInt(0)*32,a.getInt(1)*32,a.getInt(2)),playerMeta);
		
		JSONArray levelRefs = gameMeta.getJSONArray("levels");
		for(Object i : levelRefs){
			String levelName = (String)(i);
			JSONObject data = new JSONObject(ResourceManager.getFileContent(levelName));
			Level lvl = new Level(data); 
			game.levels.add(lvl);
			String[] nameRef = levelName.split("/");
			System.out.println(nameRef[nameRef.length-1]);
			game.levelMap.put(nameRef[nameRef.length-1],lvl);
		}
		game.currentLevel = game.levels.get(0);
		game.currentLevel.addEntity(game.player);
		Fighter f = new Hostile(new Vector3f(256,256,1),playerMeta);
		game.currentLevel.addEntity(f);
		game.gameScreen.addFocusListener(game);

	}
	public static Player getPlayer(){
		return game.player;
	}
	public static void run(){

		game.ltime = System.currentTimeMillis();
		
		game.upTimer = new Timer(10,game);
		game.upTimer.start();

	}
	public static Level getCurrentLevel(){
		return game.currentLevel;
	}
	public static Level getLevel(String name){
		return game.levelMap.get(name);
	}
	public static void setLevel(Level lvl){
		game.currentLevel.leave();
		lvl.enter();
		game.currentLevel = lvl;
		
	}
	public static Panel getCurrentGUI(){
		return game.currentGUI;
	}
	public static void setCurrentGUI(Panel gui){
		if(game.currentGUI != null){
			getCanvas().removeMouseListener(game.currentGUI);
		}
		game.currentGUI = gui;
		getCanvas().addMouseListener(gui);
	}
	public static GameCanvas getCanvas(){
		return game.gameScreen.getCanvas();
	}
	
	
	
	
	public static void main(String[] args){
		System.setProperty("sun.java2d.opengl", "true");
		new Game("res/game.json");
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent key) {
		if(Game.getCurrentLevel() != null){
			Game.getCurrentLevel().keyPressed(key);
		}
	}
	@Override
	public void keyReleased(KeyEvent key) {
		if(Game.getCurrentLevel() != null){
			Game.getCurrentLevel().keyReleased(key);
		}	
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ctime = System.currentTimeMillis();
		dtime = ctime - ltime;
		ltime = ctime;
		timeAcc += dtime;
		frames++;
		
		game.currentLevel.update(dtime);
		Game.getCanvas().addToDirectQueue(game.currentGUI);
		game.currentLevel.render();
		//Use interface, or keep ugly code?
		//That is the question!
		if(game.currentLevel.getPlayer() != null){
			if(game.currentGUI instanceof MainGUI){
				((MainGUI)(game.currentGUI)).setHpValue(game.currentLevel.getPlayer().getHealthP());	
			}else if(game.currentGUI instanceof FightGUI){
				((FightGUI)(game.currentGUI)).setHpValue(game.currentLevel.getPlayer().getHealthP());		
			}
		}
		game.gameScreen.getCanvas().render();
		if(timeAcc > 1000){
			System.out.println("FPS: " + frames);
			timeAcc = 0;
			frames = 0;
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		game.ltime = System.currentTimeMillis();
		game.upTimer.start();
	}
	@Override
	public void focusLost(FocusEvent e) {
		//Prevents memory growing when game is unfocused
		//caused game to bug out
		game.upTimer.stop();	
	}

}
