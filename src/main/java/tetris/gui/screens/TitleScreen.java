package tetris.gui.screens;

import java.awt.Color;
import java.awt.Graphics;

public class TitleScreen extends Screen
{
	public final int tileSize = screenWidth / 30;

	public TitleScreen()
	{
		this.setBackground(new Color(24, 24, 24));

		repaint();
	}

	private void draw(Graphics g)
	{
		int x = 4;
		int y = 10;
		Color color = Color.magenta;

		// Letter T
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = new Color(0, 191, 255);

		// Letter E
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 4) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = Color.red;

		// Letter T
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = Color.blue;

		// Letter R
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 4) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = Color.yellow;

		// Letter I
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 4) * tileSize);

		x += 2;
		color = Color.green;

		// Letter S
		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);

		drawGridSquare(g, color, x * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);

		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 2) * tileSize);

		drawGridSquare(g, color, (x + 2) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 4) * tileSize);

		drawGridSquare(g, color, (x + 1)* tileSize, (y + 4) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 4) * tileSize);
	}

	private void drawGridSquare(Graphics g, Color color, int x, int y)
	{
		g.setColor(color);
		g.fillRect(x, y, tileSize, tileSize);
		g.setColor(Color.black);
		g.drawRect(x, y, tileSize, tileSize);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		draw(g);
	}
}