package tetris.gui.screens;

import java.awt.Dimension;
import javax.swing.JPanel;

public abstract class Screen extends JPanel
{
	protected final int screenWidth = GameScreen.BOARD_OFFSET_X * 2 + GameScreen.BOARD_WIDTH + GameScreen.SIDEBAR_WIDTH + 8;
	protected final int screenHeight = GameScreen.BOARD_OFFSET_Y * 2 + GameScreen.BOARD_HEIGHT;

	public Screen()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
}
