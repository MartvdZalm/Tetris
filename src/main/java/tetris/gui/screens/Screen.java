package tetris.gui.screens;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public abstract class Screen extends JPanel
{
	protected final int screenWidth = 400;
	protected final int screenHeight = 720;

	public Screen()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
}