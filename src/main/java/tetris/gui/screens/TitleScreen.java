package tetris.gui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TitleScreen extends Screen
{
	public final int tileSize = screenWidth / 30;

	public TitleScreen()
	{
		setBackground(new Color(18, 18, 24));
	}

	private void drawLogo(Graphics g)
	{
		int x = 4;
		int y = 10;
		Color color = Color.magenta;

		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = new Color(0, 191, 255);

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

		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);

		x += 4;
		color = Color.blue;

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

		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 4) * tileSize);

		x += 2;
		color = Color.green;

		drawGridSquare(g, color, x * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, y * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, y * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 1) * tileSize);
		drawGridSquare(g, color, x * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 2) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 3) * tileSize);
		drawGridSquare(g, color, (x + 2) * tileSize, (y + 4) * tileSize);
		drawGridSquare(g, color, (x + 1) * tileSize, (y + 4) * tileSize);
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

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawLogo(g);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 18));
		String prompt = "Press ENTER to start";
		int textWidth = g.getFontMetrics().stringWidth(prompt);
		g.drawString(prompt, (screenWidth - textWidth) / 2, screenHeight - 80);

		g.setColor(new Color(140, 140, 150));
		g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String hint = "Arrow keys · Space · C to hold · P to pause";
		textWidth = g.getFontMetrics().stringWidth(hint);
		g.drawString(hint, (screenWidth - textWidth) / 2, screenHeight - 50);
	}
}
