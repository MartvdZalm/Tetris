package tetris.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import tetris.control.KeyHandler;

public class GameForm extends JFrame
{
	private GameArea gameArea;
	private KeyHandler keyHandler;
	
	public GameForm()
	{
		this.setResizable(false);
		this.setPreferredSize(new Dimension(400, 720));

		gameArea = new GameArea();
		keyHandler = new KeyHandler(gameArea);

		this.add(gameArea);
		this.pack();
		this.setLocationRelativeTo(null);
		this.addKeyListener(keyHandler);
		
		startGame();
	}
	
	public void startGame()
	{
		new GameThread(gameArea, this).start();
	}	
}
