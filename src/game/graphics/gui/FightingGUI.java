package game.graphics.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.graphics.ColorRect;
import game.math.Vector3f;
import game.resource.ResourceManager;

public class FightingGUI extends Panel{
	
	private Panel optionDialog = new Panel(new Vector3f(0,400,0),new Vector3f(640,80,0),ResourceManager.getImage("res/gui/f_option.png"));
	private GraphicW wrapper = new GraphicW(new Vector3f(30,425,0));
	private ColorRect hpBar = new ColorRect(new Vector3f(0,0,0), new Vector3f(64,12,0), new Color(255,0,0));
	private ColorRect hpBarBG = new ColorRect(new Vector3f(0,0,0), new Vector3f(64,12,0), new Color(100,100,100));
	
	
	public FightingGUI() {
		super(new Vector3f(0,0,0), new Vector3f(640,480,0), ResourceManager.getImage("res/gui/guibg_1.png"));
		add(optionDialog);
		wrapper.addDrawable(hpBarBG);
		wrapper.addDrawable(hpBar);
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
