package tetris.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import tetris.control.KeyHandler;
import tetris.gui.screens.*;

public class GameForm extends JFrame
{
	private Screen screen;
	private ScreenTypes screenType;
	private KeyHandler keyHandler;

	public GameForm()
	{
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		keyHandler = new KeyHandler(this);
		switchScreen(ScreenTypes.TITLE);

		pack();
		setLocationRelativeTo(null);
		addKeyListener(keyHandler);
		setFocusable(true);
	}

	public void switchScreen(ScreenTypes screenType)
	{
		if (this.screen instanceof GameScreen) {
			((GameScreen) this.screen).dispose();
		}

		getContentPane().removeAll();

		switch (screenType) {
			case TITLE:
				this.screen = new TitleScreen();
				break;
			case GAME:
				this.screen = new GameScreen();
				break;
			default:
				this.screen = new TitleScreen();
				break;
		}

		getContentPane().add(this.screen);
		this.screenType = screenType;
		revalidate();
		repaint();
		requestFocus();
	}

	public ScreenTypes getScreenType()
	{
		return screenType;
	}

	public TitleScreen getTitleScreen()
	{
		if (screen instanceof TitleScreen) {
			return (TitleScreen) screen;
		}
		return null;
	}

	public GameScreen getGameScreen()
	{
		if (screen instanceof GameScreen) {
			return (GameScreen) screen;
		}
		return null;
	}
}
