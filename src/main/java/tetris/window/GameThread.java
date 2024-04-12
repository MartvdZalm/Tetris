package tetris.window;

public class GameThread extends Thread
{	
	private GameArea gameArea;
	private GameForm gameForm;
	
	public GameThread(GameArea gameArea, GameForm gameForm)
	{
		this.gameArea = gameArea;
		this.gameForm = gameForm;
	}
	
	@Override
	public void run()
	{	
		while (true) {
			gameArea.spawnBlock();
			
			while (gameArea.moveBlockDown() == true) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if (gameArea.isBlockOutOfBounds()) {
				break;
			}
			
			gameArea.moveBlockToBackground();
			gameArea.clearLines();
		}	
	}
}
