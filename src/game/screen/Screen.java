package game.screen;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;



public class Screen extends JFrame{
	private static final long serialVersionUID = 1L;
	GameCanvas canvas;
	Dimension screenSize = new Dimension(640,480);
	public Screen(){
		super("Best game evar");
		setLayout(new FlowLayout());
		setMinimumSize(screenSize);
		canvas = new GameCanvas();
		canvas.setPreferredSize(screenSize);
		canvas.setMinimumSize(screenSize);
		canvas.setVisible(true);
		add(canvas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		setVisible(true);
		pack();
		
		
	}
	public GameCanvas getCanvas(){
		return canvas;
	}

	
}
