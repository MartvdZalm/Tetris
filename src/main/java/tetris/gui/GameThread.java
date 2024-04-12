package tetris.gui;

import tetris.gui.screens.GameScreen;

public class GameThread extends Thread
{	
	private GameScreen gameScreen;
	private GameForm gameForm;
	
	public GameThread(GameScreen gameScreen, GameForm gameForm)
	{
		this.gameScreen = gameScreen;
		this.gameForm = gameForm;
	}
	
	@Override
	public void run()
	{	
		while (true) {
			gameScreen.spawnBlock();
			
			while (gameScreen.moveBlockDown() == true) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (gameScreen.isBlockOutOfBounds()) {
				break;
			}
			
			gameScreen.moveBlockToBackground();
			gameScreen.clearLines();
		}	
	}
}
