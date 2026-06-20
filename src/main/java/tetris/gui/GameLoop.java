package tetris.gui;

import javax.swing.Timer;

import tetris.game.GameEngine;
import tetris.game.GameState;
import tetris.gui.screens.GameScreen;

public class GameLoop
{
	private final GameEngine engine;
	private final GameScreen screen;
	private Timer gravityTimer;

	public GameLoop(GameEngine engine, GameScreen screen)
	{
		this.engine = engine;
		this.screen = screen;
	}

	public void start()
	{
		stop();
		engine.start();
		scheduleGravity();
		screen.repaint();
	}

	public void stop()
	{
		if (gravityTimer != null) {
			gravityTimer.stop();
			gravityTimer = null;
		}
	}

	public void restart()
	{
		stop();
		engine.restart();
		scheduleGravity();
		screen.repaint();
	}

	private void scheduleGravity()
	{
		gravityTimer = new Timer(engine.getGravityIntervalMs(), e -> {
			if (engine.getState() == GameState.PLAYING) {
				engine.tick();
				screen.repaint();
			}
			updateGravitySpeed();
		});
		gravityTimer.start();
	}

	private void updateGravitySpeed()
	{
		if (gravityTimer != null) {
			gravityTimer.setDelay(engine.getGravityIntervalMs());
		}
	}
}
