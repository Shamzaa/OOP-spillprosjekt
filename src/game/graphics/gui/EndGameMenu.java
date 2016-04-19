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
import game.sound.AudioChannel;
import game.sound.AudioManager;

public class EndGameMenu extends Panel{
	
	private TextLabel gameTitle = new TextLabel("Game Over!",new Color(0,0,0),32);
	//private Button playButton = new Button("Play",new Sprite(ResourceManager.getImage("res/gui/guibutton.png"),new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0) ));
	//private Button exitButton = new Button("Exit",new Sprite(ResourceManager.getImage("res/gui/guibutton.png"),new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0) ));
	private Button okButton = new Button("->",new Sprite(ResourceManager.getImage("res/gui/guibutton.png"),new Vector3f(0,0,0),new Vector3f(0,0,2),new Vector3f(0,0,0),new Vector3f(128,32,0) ));
	private AudioChannel mainMenuMusic = AudioManager.getMixer().addChannel("res/music/gameover.wav", "res/music/gameover.wav");
	public EndGameMenu() {
		super(new Vector3f(0,0,0), new Vector3f(640,480,0), ResourceManager.getImage("res/gui/MainMenuTemp.png"));
		mainMenuMusic.setRVolume(0);
		AudioManager.playBG(mainMenuMusic);
		okButton.setPosition(new Vector3f(640,280,0).scale(0.5f).add(new Vector3f(0,128,0)).sub(okButton.getDimension().scale(0.5f)));
		
		okButton.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "CLICK"){
					Game.restartGame();
				}
			}
		});
		
		
		gameTitle.setPosition(new Vector3f(640,280,0).scale(0.5f));
		gameTitle.setDimension(new Vector3f(270,30,0));
		gameTitle.setCenter(new Vector3f(0.5f,0.5f,0));
		add(okButton);
		add(gameTitle);
	}
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	
}
