package tetris.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.window.GameArea;

public class KeyHandler implements KeyListener
{
	private GameArea gameArea;
	
	public KeyHandler(GameArea gameArea)
	{
		this.gameArea = gameArea;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e)
	{	
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			gameArea.rotateBlock();
		}

		if (code == KeyEvent.VK_DOWN) {
			gameArea.dropBlock();
		}

		if (code == KeyEvent.VK_LEFT) {
			gameArea.moveBlockLeft();
		}

		if (code == KeyEvent.VK_RIGHT) {
			gameArea.moveBlockRight();
		}	
	}
}
