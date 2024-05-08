package tetris.gui;

import tetris.gui.screens.*;

public class GameThread extends Thread
{	
	private GameForm gameForm;
	
	public GameThread(GameForm gameForm)
	{
		this.gameForm = gameForm;
	}

	@Override
	public void run()
	{	
		while (this.gameForm.getScreenType() == ScreenTypes.TITLE) {
			TitleScreen titleScreen = this.gameForm.getTitleScreen();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.gameForm.switchScreen(ScreenTypes.GAME);
		}

		while (this.gameForm.getScreenType() == ScreenTypes.GAME) {
			GameScreen gameScreen = this.gameForm.getGameScreen();
			
			gameScreen.spawnBlock();
			gameScreen.repaint();

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
