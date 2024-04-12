package tetris.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.gui.screens.ScreenTypes;
import tetris.gui.GameForm;

public class KeyHandler implements KeyListener
{
	private GameForm gameForm;
	
	public KeyHandler(GameForm gameForm)
	{
		this.gameForm = gameForm;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e)
	{	
		int code = e.getKeyCode();
		
		if (this.gameForm.getCurrentScreenType() == ScreenTypes.TITLE) {
			titleScreen(code);
		}

		if (this.gameForm.getCurrentScreenType() == ScreenTypes.GAME) {
			gameScreen(code);
		}
	}

	private void titleScreen(int code)
	{	
		if (code == KeyEvent.VK_ENTER) {
			this.gameForm.startGame();
		}
	}

	private void gameScreen(int code)
	{
		if (code == KeyEvent.VK_UP) {
			this.gameForm.getGameScreen().rotateBlock();
		}

		if (code == KeyEvent.VK_DOWN) {
			this.gameForm.getGameScreen().dropBlock();
		}

		if (code == KeyEvent.VK_LEFT) {
			this.gameForm.getGameScreen().moveBlockLeft();
		}

		if (code == KeyEvent.VK_RIGHT) {
			this.gameForm.getGameScreen().moveBlockRight();
		}
	} 
}
