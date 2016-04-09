package game;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import game.graphics.Sprite;
import game.math.Vector3f;
import game.resource.ResourceManager;
import game.screen.GameCanvas;
import game.screen.Screen;
import game.tile.Tile;
import game.world.Level;

public class Game {
	static int worldWidth;
	//Level location in the world
	static private Level[] levelMap;
	static private ArrayList<Level> levels;
	static private Level currentLevel;
	static private Screen gameScreen;
	
	
	public static void init(String configFile){
		byte[] buffer = ResourceManager.getFileBuffer(configFile);
		JSONObject cfg = new JSONObject(new String(buffer));
		init(cfg);
	}
	public static void init(JSONObject gameMeta){
		currentLevel = new Level(32,32);
		gameScreen = new Screen();	
	}
	public static void run(){
		long dtime;
		long ctime;
		long ltime = System.currentTimeMillis();
		long timeAcc = 0;
		long frames = 0;
		BufferedImage imageTest = ResourceManager.getImage("res/testImage.png");
		Sprite spriteTest = new Sprite(imageTest,new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(32,32,0));
		spriteTest.setFPS(2);
		Tile testTile = new Tile(spriteTest);
		for(int i=0; i<42;i++){
			currentLevel.setTileAt(new Tile(testTile), i);
		}
		while(true){
			ctime = System.currentTimeMillis();
			dtime = ctime - ltime;
			if(dtime > 12 && gameScreen.getCanvas().isReady()){
				ltime = ctime;
				timeAcc += dtime;
				frames++;
				currentLevel.update(dtime);
				currentLevel.render();
				gameScreen.getCanvas().repaint();
				if(timeAcc > 1000){
					System.out.println("FPS: " + frames);
					timeAcc = 0;
					frames = 0;
				}
			}
		}
	}
	public static Level getCurrentLevel(){
		return currentLevel;
	}
	public static void setLevel(Level lvl){
		currentLevel.leave();
		lvl.enter();
		currentLevel = lvl;
		
	}
	public static GameCanvas getCanvas(){
		return gameScreen.getCanvas();
	}
	
	
	
	
	public static void main(String[] args){
		System.setProperty("sun.java2d.opengl", "true");
		Game.init(new JSONObject());
		Game.run();
	}
}
