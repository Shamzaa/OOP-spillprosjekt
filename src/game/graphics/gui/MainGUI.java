package game.graphics.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.ColorRect;
import game.math.Vector3f;
import game.resource.ResourceManager;

public class MainGUI extends Panel{
	
	private Panel optionDialog = new Panel(new Vector3f(0,400,0),new Vector3f(640,80,0),ResourceManager.getImage("res/gui/f_option.png"));
	private GraphicW wrapper = new GraphicW(new Vector3f(30,425,0));
	private ColorRect hpBar = new ColorRect(new Vector3f(0,0,0), new Vector3f(128,6,0), new Color(255,0,0));
	private ColorRect hpBarBG = new ColorRect(new Vector3f(0,0,0), new Vector3f(128,6,0), new Color(100,100,100));
	private ColorRect apBar = new ColorRect(new Vector3f(0,19,0), new Vector3f(128,6,0), new Color(0,0,255));
	private ColorRect apBarBG = new ColorRect(new Vector3f(0,19,0), new Vector3f(128,6,0), new Color(100,100,100));
	
	private TextLabel hpLabel = new TextLabel("Health:",new Color(0,0,0),12);
	private TextLabel apLabel = new TextLabel("Mana:",new Color(0,0,0),12);
	
	public MainGUI() {
		super(new Vector3f(0,0,0), new Vector3f(640,480,0), ResourceManager.getImage("res/gui/guibg_1.png"));
		//add(optionDialog);
		hpLabel.setPosition(new Vector3f(0,-1,0));
		wrapper.addDrawable(hpBarBG);
		wrapper.addDrawable(hpBar);
		wrapper.add(hpLabel);
		wrapper.addDrawable(apBarBG);
		wrapper.addDrawable(apBar);
		wrapper.add(apLabel);
		apLabel.setPosition(new Vector3f(0,18,0));
		add(wrapper);
	}
	@Override
	public void draw(Graphics2D g){
		super.draw(g);
	}
	public Panel getOptionDiag(){
		return optionDialog;
	}
	public void setHpValue(float p){
		hpBar.setScale(new Vector3f(p,1,0));
	}
}
