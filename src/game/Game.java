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

import javax.swing.Timer;

import org.json.JSONObject;

import game.entity.Player;
import game.graphics.Camera;
import game.graphics.Sprite;
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
	//Level location in the world
	private Level[] levelMap;
	private ArrayList<Level> levels;
	private Level currentLevel;
	private Screen gameScreen;
	private Vector3f camDir = new Vector3f(0,0,0);
	private boolean VK_A,VK_S,VK_D,VK_W,VK_Q,VK_E,VK_pluss,VK_minus;
	private int rot = 0;
	private static Game game = null;
	private BufferedImage imageTest = ResourceManager.getImage("res/testImage.png");
	private BufferedImage imageTest2 = ResourceManager.getImage("res/maintest.png");
	private Sprite spriteTest = new Sprite(imageTest,new Vector3f(0,0,0),new Vector3f(0,0,1),new Vector3f(0,0,0),new Vector3f(32,32,0));
	private Sprite spriteTest2 = new Sprite(imageTest,new Vector3f(0,0,0),new Vector3f(1,0,1),new Vector3f(0,0,0),new Vector3f(32,32,0));
	
	private Sprite spritePlayer0 = new Sprite(imageTest2,new Vector3f(0,0,0),new Vector3f(0,0,3),new Vector3f(0.5f,0.5f,0),new Vector3f(26,34,0));
	private Sprite spritePlayer1 = new Sprite(imageTest2,new Vector3f(0,0,0),new Vector3f(0,1,3),new Vector3f(0.5f,0.5f,0),new Vector3f(26,34,0));
	private Sprite spritePlayer2 = new Sprite(imageTest2,new Vector3f(0,0,0),new Vector3f(0,2,3),new Vector3f(0.5f,0.5f,0),new Vector3f(26,34,0));
	private Sprite spritePlayer3 = new Sprite(imageTest2,new Vector3f(0,0,0),new Vector3f(0,3,3),new Vector3f(0.5f,0.5f,0),new Vector3f(26,34,0));
	private Player playerTest = new Player(new Vector3f(64,32,1),new Sprite[]{
			spritePlayer0,
			spritePlayer1,
			spritePlayer2,
			spritePlayer3
	});
	
	
	private long ctime,dtime,ltime,timeAcc,frames;
	
	public Game(JSONObject obj){
		Game.game = this;
		init(obj);
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
		//game.gameScreen.getCanvas().setRequestFocusEnabled(true);
	}
	public static void run(){
		JSONObject obj = new JSONObject(ResourceManager.getFileContent("res/levels/example.json"));
		Level testLoadFromJson = new Level(obj);
		game.currentLevel = testLoadFromJson;
		game.ltime = System.currentTimeMillis();
		//game.spriteTest.setFPS(2);
		/*Tile testTile = new Ground(game.spriteTest);
		Tile testTile2 = new Wall(game.spriteTest2);
		for(int i=0; i<32*32;i++){
			game.currentLevel.setTileAt(new Ground(testTile), i);
		}
		for(int i=0; i<32;i++){
			game.currentLevel.setTileAt(new Wall(testTile2), new Vector3f(i,0,1));
		}*/
		game.currentLevel.addEntity(game.playerTest);
		game.playerTest.enter(game.currentLevel);
	
		
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
		new Game(new JSONObject());
		
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
		game.currentLevel.render();
		game.gameScreen.getCanvas().render();
		if(timeAcc > 1000){
			System.out.println("FPS: " + frames);
			timeAcc = 0;
			frames = 0;
		}
	}

}
