package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.media.jfxmedia.events.PlayerTimeListener;

import game.entity.Player;
import game.graphics.Camera;
import game.graphics.Sprite;
import game.graphics.gui.Button;
import game.graphics.gui.FightingGUI;
import game.graphics.gui.HoverArea;
import game.graphics.gui.Panel;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.screen.GameCanvas;
import game.screen.Screen;
import game.tile.Ground;
import game.tile.Tile;
import game.tile.Wall;
import game.world.Level;

public class Game implements KeyListener, MouseListener, ActionListener{
	private int worldWidth;
	private ArrayList<Level> levels = new ArrayList<Level>();
	private Level currentLevel;
	private Screen gameScreen;
	private static Game game = null;
	private Button testButton;
	private Button testButton2;
	private Panel testPanel;
	private Player playerTest;
	private HoverArea testHover;
	private Panel currentGUI = new FightingGUI();
	
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
		game.playerTest = new Player(new Vector3f(64,32,1),playerMeta);
		
		JSONArray levelRefs = gameMeta.getJSONArray("levels");
		for(Object i : levelRefs){
			String levelName = (String)(i);
			JSONObject data = new JSONObject(ResourceManager.getFileContent(levelName));
			game.levels.add(new Level(data));
		}
		game.currentLevel = game.levels.get(0);
		game.currentLevel.addEntity(game.playerTest);
		
		
	}
	public static void run(){
		//JSONObject obj = new JSONObject(ResourceManager.getFileContent("res/levels/example.json"));
		//Level testLoadFromJson = new Level(obj);
		//game.currentLevel = testLoadFromJson;
		game.ltime = System.currentTimeMillis();
		//BufferedImage img = ResourceManager.getImage("res/gui/buttonTest.png");
		//game.testButton = new Button("This is a test",new Sprite(img,new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0)),true);
		//game.testButton.setPosition(new Vector3f(640,0,0));
		//game.testButton.setCenter(new Vector3f(1,0,0));
		//game.testButton2 = new Button("Another one!",new Sprite(img,new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0)));
		//game.testButton2.setPosition(new Vector3f(640,32,0));
		//game.testButton2.setCenter(new Vector3f(1,0,0));
		//game.testButton2.hide();
		//game.testPanel = new Panel(new Vector3f(0,0,0),new Vector3f(640,480,0),ResourceManager.getImage("res/gui/guibg_1.png"));
		//game.testHover = new HoverArea(new Vector3f(640,0,0),new Sprite(img,new Vector3f(0,0,0),new Vector3f(0,1,2),new Vector3f(0,0,0),new Vector3f(32,32,0)));
		//game.testHover.setCenter(new Vector3f(1,0,0));
		
		//game.testPanel.add(game.testButton2);
		//game.testPanel.add(game.testButton);
		//game.testPanel.add(game.testHover);
		//Game.getCanvas().addMouseListener(game.testPanel);
		//Game.getCanvas().addMouseMotionListener(game.testPanel);
		
		/*game.testButton.addListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("WOW : " + arg0.getActionCommand());
				if(arg0.getActionCommand() == "DOWN"){
					game.testButton2.show();
				}else{
					game.testButton2.hide();
				}
			}
			
		});*/
		/*game.testHover.addListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(!game.testButton2.mouseHover()){
					game.testButton2.hide();	
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				game.testButton2.show();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});*/
		/*
		game.testButton2.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("WOW, dayum son!\nWhat have you done1!1!");
				
			}
		});
		*/
		/*
		game.testButton2.addListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				game.testButton2.hide();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				game.testButton2.show();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
		//game.currentLevel.addEntity(game.playerTest);
		//game.playerTest.enter(game.currentLevel);
	
		
		Timer upTimer = new Timer(10,game);
		upTimer.start();

	}
	public static Level getCurrentLevel(){
		return game.currentLevel;
	}
	public static void setLevel(Level lvl){
		game.currentLevel.leave();
		lvl.enter();
		game.currentLevel = lvl;
		
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
		((FightingGUI)(game.currentGUI)).setHpValue(game.currentLevel.getPlayer().getHealthP());
		game.gameScreen.getCanvas().render();
		if(timeAcc > 1000){
			System.out.println("FPS: " + frames);
			timeAcc = 0;
			frames = 0;
		}
	}

}
