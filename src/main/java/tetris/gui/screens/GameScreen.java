package tetris.gui.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import tetris.game.GameEngine;
import tetris.game.GameState;
import tetris.gui.GameLoop;
import tetris.shapes.Shape;

public class GameScreen extends Screen
{
	public static final int BOARD_COLS = GameEngine.COLS;
	public static final int BOARD_ROWS = GameEngine.ROWS;
	public static final int TILE_SIZE = 32;
	public static final int BOARD_WIDTH = BOARD_COLS * TILE_SIZE;
	public static final int BOARD_HEIGHT = BOARD_ROWS * TILE_SIZE;
	public static final int SIDEBAR_WIDTH = 180;
	public static final int BOARD_OFFSET_X = 16;
	public static final int BOARD_OFFSET_Y = 16;

	private final GameEngine engine;
	private final GameLoop gameLoop;

	public GameScreen()
	{
		this.setBackground(new Color(18, 18, 24));
		engine = new GameEngine();
		gameLoop = new GameLoop(engine, this);
		gameLoop.start();
	}

	public GameEngine getEngine()
	{
		return engine;
	}

	public GameLoop getGameLoop()
	{
		return gameLoop;
	}

	public void restart()
	{
		gameLoop.restart();
	}

	public void dispose()
	{
		gameLoop.stop();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawBoard(g2);
		drawGhost(g2);
		drawCurrent(g2);
		drawSidebar(g2);
		drawOverlay(g2);
	}

	private void drawBoard(Graphics2D g)
	{
		int x = BOARD_OFFSET_X;
		int y = BOARD_OFFSET_Y;

		g.setColor(new Color(32, 32, 40));
		g.fillRect(x - 2, y - 2, BOARD_WIDTH + 4, BOARD_HEIGHT + 4);

		Color[][] board = engine.getBoard();
		for (int row = 0; row < BOARD_ROWS; row++) {
			for (int col = 0; col < BOARD_COLS; col++) {
				drawCell(g, board[row][col], x + col * TILE_SIZE, y + row * TILE_SIZE, false);
			}
		}

		g.setColor(new Color(48, 48, 58));
		for (int col = 0; col <= BOARD_COLS; col++) {
			g.drawLine(x + col * TILE_SIZE, y, x + col * TILE_SIZE, y + BOARD_HEIGHT);
		}
		for (int row = 0; row <= BOARD_ROWS; row++) {
			g.drawLine(x, y + row * TILE_SIZE, x + BOARD_WIDTH, y + row * TILE_SIZE);
		}
	}

	private void drawGhost(Graphics2D g)
	{
		Shape block = engine.getCurrent();
		if (block == null || engine.getState() != GameState.PLAYING) {
			return;
		}

		int ghostY = engine.getGhostY();
		if (ghostY == block.getY()) {
			return;
		}

		drawShape(g, block, block.getX(), ghostY, true);
	}

	private void drawCurrent(Graphics2D g)
	{
		Shape block = engine.getCurrent();
		if (block == null) {
			return;
		}

		drawShape(g, block, block.getX(), block.getY(), false);
	}

	private void drawShape(Graphics2D g, Shape block, int xPos, int yPos, boolean ghost)
	{
		int[][] shape = block.getShape();
		Color color = block.getColor();
		int h = block.getHeight();
		int w = block.getWidth();

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				if (shape[row][col] == 1) {
					int x = BOARD_OFFSET_X + (xPos + col) * TILE_SIZE;
					int y = BOARD_OFFSET_Y + (yPos + row) * TILE_SIZE;

					if (yPos + row < 0) {
						continue;
					}

					if (ghost) {
						g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 60));
						g.fillRect(x + 2, y + 2, TILE_SIZE - 4, TILE_SIZE - 4);
						g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 140));
						g.drawRect(x + 2, y + 2, TILE_SIZE - 5, TILE_SIZE - 5);
					} else {
						drawCell(g, color, x, y, true);
					}
				}
			}
		}
	}

	private void drawCell(Graphics2D g, Color color, int x, int y, boolean highlight)
	{
		drawCell(g, color, x, y, highlight, TILE_SIZE);
	}

	private void drawCell(Graphics2D g, Color color, int x, int y, boolean highlight, int size)
	{
		if (color == null) {
			return;
		}

		if (highlight) {
			g.setColor(color.brighter());
			g.fillRect(x + 1, y + 1, size - 2, Math.max(2, size / 8));
			g.fillRect(x + 1, y + 1, Math.max(2, size / 8), size - 2);
		}

		g.setColor(color);
		g.fillRect(x + 1, y + 1, size - 2, size - 2);

		if (highlight) {
			g.setColor(color.darker());
			g.fillRect(x + size - 5, y + 1, 4, size - 2);
			g.fillRect(x + 1, y + size - 5, size - 2, 4);
		}

		g.setColor(new Color(0, 0, 0, 80));
		g.drawRect(x, y, size - 1, size - 1);
	}

	private void drawSidebar(Graphics2D g)
	{
		int x = BOARD_OFFSET_X + BOARD_WIDTH + 24;
		int y = BOARD_OFFSET_Y;

		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 14));

		drawLabel(g, "SCORE", x, y);
		drawValue(g, String.valueOf(engine.getScore()), x, y + 20);

		drawLabel(g, "LINES", x, y + 60);
		drawValue(g, String.valueOf(engine.getLines()), x, y + 80);

		drawLabel(g, "LEVEL", x, y + 120);
		drawValue(g, String.valueOf(engine.getLevel()), x, y + 140);

		drawLabel(g, "NEXT", x, y + 190);
		drawPreview(g, engine.getNextType(), x, y + 210);

		drawLabel(g, "HOLD", x, y + 310);
		Integer held = engine.getHeldType();
		if (held != null) {
			drawPreview(g, held, x, y + 330);
		} else {
			g.setColor(new Color(48, 48, 58));
			g.drawRect(x, y + 330, 120, 80);
		}

		g.setColor(new Color(140, 140, 150));
		g.setFont(new Font("Monospaced", Font.PLAIN, 11));
		int helpY = y + 450;
		String[] help = {
			"← → Move",
			"↑ Rotate CW",
			"Z Rotate CCW",
			"↓ Soft drop",
			"Space Hard drop",
			"C Hold",
			"P Pause",
			"R Restart"
		};
		for (String line : help) {
			g.drawString(line, x, helpY);
			helpY += 16;
		}
	}

	private void drawPreview(Graphics2D g, int type, int x, int y)
	{
		Shape preview = engine.getPrototype(type);
		int[][] shape = preview.getShapeAtRotation(0);
		int h = shape.length;
		int w = shape[0].length;
		Color color = preview.getColor();
		int cell = 24;
		int offsetX = x + (120 - w * cell) / 2;
		int offsetY = y + (80 - h * cell) / 2;

		g.setColor(new Color(32, 32, 40));
		g.fillRect(x, y, 120, 80);

		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				if (shape[row][col] == 1) {
					drawCell(g, color, offsetX + col * cell, offsetY + row * cell, true, cell);
				}
			}
		}
	}

	private void drawLabel(Graphics2D g, String text, int x, int y)
	{
		g.setColor(new Color(140, 140, 150));
		g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		g.drawString(text, x, y);
	}

	private void drawValue(Graphics2D g, String text, int x, int y)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 18));
		g.drawString(text, x, y);
	}

	private void drawOverlay(Graphics2D g)
	{
		if (engine.getState() == GameState.PAUSED) {
			drawCenteredMessage(g, "PAUSED", "Press P to continue");
		} else if (engine.getState() == GameState.GAME_OVER) {
			drawCenteredMessage(g, "GAME OVER", "Score: " + engine.getScore() + "  —  Press R");
		}
	}

	private void drawCenteredMessage(Graphics2D g, String title, String subtitle)
	{
		g.setColor(new Color(0, 0, 0, 160));
		g.fillRect(BOARD_OFFSET_X, BOARD_OFFSET_Y, BOARD_WIDTH, BOARD_HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospaced", Font.BOLD, 28));
		int titleWidth = g.getFontMetrics().stringWidth(title);
		int centerX = BOARD_OFFSET_X + (BOARD_WIDTH - titleWidth) / 2;
		int centerY = BOARD_OFFSET_Y + BOARD_HEIGHT / 2;
		g.drawString(title, centerX, centerY);

		g.setFont(new Font("Monospaced", Font.PLAIN, 14));
		int subWidth = g.getFontMetrics().stringWidth(subtitle);
		g.drawString(subtitle, BOARD_OFFSET_X + (BOARD_WIDTH - subWidth) / 2, centerY + 30);
	}
}
