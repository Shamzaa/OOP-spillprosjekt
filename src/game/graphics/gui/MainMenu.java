package game.graphics.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import game.Game;
import game.graphics.ColorRect;
import game.graphics.Sprite;
import game.math.Vector3f;
import game.resource.ResourceManager;

public class MainMenu extends Panel{
	
	private TextLabel gameTitle = new TextLabel("Best Game Ever!",new Color(0,0,0),32);
	private Button playButton = new Button("Play",new Sprite(ResourceManager.getImage("res/gui/guibutton.png"),new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0) ));
	private Button exitButton = new Button("Exit",new Sprite(ResourceManager.getImage("res/gui/guibutton.png"),new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0) ));
	
	public MainMenu() {
		super(new Vector3f(0,0,0), new Vector3f(640,480,0), ResourceManager.getImage("res/gui/MainMenuTemp.png"));
		
		playButton.setPosition(new Vector3f(640,280,0).scale(0.5f).add(new Vector3f(0,64,0)).sub(playButton.getDimension().scale(0.5f)));
		exitButton.setPosition(new Vector3f(640,280,0).scale(0.5f).add(new Vector3f(0,128,0)).sub(playButton.getDimension().scale(0.5f)));
		
		playButton.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "CLICK"){
					Game.startGame();
				}
			}
		});
		
		exitButton.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "CLICK"){
					Game.exitGame();
				}
			}
		});
		gameTitle.setPosition(new Vector3f(640,280,0).scale(0.5f));
		gameTitle.setDimension(new Vector3f(270,30,0));
		gameTitle.setCenter(new Vector3f(0.5f,0.5f,0));
		add(playButton);
		add(exitButton);
		add(gameTitle);
	}
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	
}
