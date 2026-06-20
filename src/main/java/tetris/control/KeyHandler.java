package tetris.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.game.GameState;
import tetris.gui.screens.GameScreen;
import tetris.gui.screens.ScreenTypes;
import tetris.gui.GameForm;

public class KeyHandler implements KeyListener
{
	private final GameForm gameForm;
	private long lastRotateMs;

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

		if (gameForm.getScreenType() == ScreenTypes.TITLE) {
			titleScreen(code);
		}

		if (gameForm.getScreenType() == ScreenTypes.GAME) {
			gameScreen(code);
		}
	}

	private void titleScreen(int code)
	{
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			gameForm.switchScreen(ScreenTypes.GAME);
		}
	}

	private void gameScreen(int code)
	{
		GameScreen screen = gameForm.getGameScreen();
		if (screen == null) {
			return;
		}

		if (code == KeyEvent.VK_R) {
			screen.restart();
			return;
		}

		if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
			screen.getEngine().togglePause();
			screen.repaint();
			return;
		}

		if (screen.getEngine().getState() != GameState.PLAYING) {
			return;
		}

		switch (code) {
			case KeyEvent.VK_LEFT:
				screen.getEngine().moveLeft();
				screen.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				screen.getEngine().moveRight();
				screen.repaint();
				break;
			case KeyEvent.VK_DOWN:
				screen.getEngine().softDrop();
				screen.repaint();
				break;
			case KeyEvent.VK_UP:
				rotateWithCooldown(screen, true);
				break;
			case KeyEvent.VK_Z:
				rotateWithCooldown(screen, false);
				break;
			case KeyEvent.VK_SPACE:
				screen.getEngine().hardDrop();
				screen.repaint();
				break;
			case KeyEvent.VK_C:
			case KeyEvent.VK_SHIFT:
				screen.getEngine().hold();
				screen.repaint();
				break;
			default:
				break;
		}
	}

	private void rotateWithCooldown(GameScreen screen, boolean clockwise)
	{
		long now = System.currentTimeMillis();
		if (now - lastRotateMs < 50) {
			return;
		}
		lastRotateMs = now;

		if (clockwise) {
			screen.getEngine().rotateClockwise();
		} else {
			screen.getEngine().rotateCounterClockwise();
		}
		screen.repaint();
	}
}
