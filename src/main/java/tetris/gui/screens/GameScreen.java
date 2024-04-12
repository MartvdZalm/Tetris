package tetris.gui.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import tetris.shapes.*;

public class GameScreen extends Screen
{
	public final int screenRows = 18;
	public final int screenCols = 10;
	public final int tileSize = screenWidth / screenCols;

	private Color[][] background;
	private Shape block;
	private Shape[] blocks;
	
	public GameScreen()
	{	
		this.setBackground(new Color(24, 24, 24));
		
		background = new Color[screenRows][screenCols];
		blocks = new Shape[] {
			new IShape(),
			new JShape(),
			new LShape(),
			new OShape(),
			new SShape(),
			new TShape(),
			new ZShape()
		};
	}
	
	public void spawnBlock()
	{ 	
		Random r = new Random();
		
		block = blocks[r.nextInt(blocks.length)];
		block.spawn(screenCols);
	}
	
	public boolean isBlockOutOfBounds()
	{
		if (block.getY() < 0) {
			block = null; 
			return true;
		} 
		
		return false;
	}
	
	public boolean moveBlockDown()
	{	
		if (checkBottom() == false) {	
			return false;
		}

		block.moveDown();
		repaint();
		
		return true;
	}
	
	public void moveBlockRight()
	{
		if (block == null || !checkRight()) {
			return;
		}

		block.moveRight();
		repaint();
	}
	
	public void moveBlockLeft()
	{
		if (block == null || !checkLeft()) {
			return;
		}
		
		block.moveLeft();
		repaint();
	}
	
	public void dropBlock()
	{
		if (block == null) {
			return;
		}

		while (checkBottom() ) {
			block.moveDown();
		}

		repaint();
	}
	
	public void rotateBlock()
	{
		if (block == null) {
			return;
		}

		block.rotate();
		
		if (block.getLeftEdge() < 0) {
			block.setX(0);
		}

		if (block.getRightEdge() > screenCols) {
			block.setX(screenCols - block.getWidth());
		}

		if (block.getBottomEdge() >= screenRows) {
			block.setY(screenRows - block.getHeight());
		}

		repaint();
	}
	
	private boolean checkBottom()
	{
		if (block.getBottomEdge() == screenRows) {
			return false;
		}
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for (int col = 0; col < w; col++) {
			for (int row = h - 1; row >= 0; row--) {
				if (shape[row][col] != 0) {
					int x = col + block.getX();
					int y = row + block.getY() + 1;

					if (y < 0) {
						break;
					}

					if (background[y][x] != null) {
						return false;
					}

					break;
				}
			}
		}
		
		return true;
	}  
	
	private boolean checkLeft()
	{
		if (block.getLeftEdge() == 0) {
			return false;
		}
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				if (shape[row][col] != 0) {
					int x = col + block.getX() - 1;
					int y = row + block.getY();

					if(y < 0) {
						break;
					}

					if(background[y][x] != null) {
						return false;
					}

					break;
				}
			}
		}
		
		return true;
	}
	
	private boolean checkRight()
	{
		if (block.getRightEdge() == screenCols) {
			return false;
		}
		
		int[][] shape = block.getShape();
		int w = block.getWidth();
		int h = block.getHeight();
		
		for (int row = 0; row < h; row++) {
			for (int col = w - 1; col >= 0; col--) {
				if (shape[row][col] != 0) {
					int x = col + block.getX() + 1;
					int y = row + block.getY();

					if (y < 0) {
						break;
					}

					if (background[y][x] != null) {
						return false;
					}

					break;
				}
			}
		}
		
		return true;
	}
	
	public void clearLines()
	{
		boolean lineFilled;
		
		for (int r = screenRows - 1; r >= 0; r--) {
			lineFilled = true;

			for (int c = 0; c < screenCols; c++) {
				if (background[r][c] == null) {
					lineFilled = false;
					break;
				}
			}
			
			if (lineFilled) {
				clearLine(r);
				shiftDown(r);
				clearLine(0);
				r++;
				repaint();
			}
		}
	}
	
	private void clearLine(int r)
	{
		for (int i = 0; i < screenCols; i++) {
			background[r][i] = null;
		}
	}
	
	private void shiftDown(int r)
	{	
		for (int row = r; row > 0; row--) {
			for (int col = 0; col < screenCols; col++) {
				background[row][col] = background[row - 1][col];
			}
		}
	}
	
	public void moveBlockToBackground()
	{	
		int[][] shape = block.getShape();
		int h = block.getHeight();
		int w = block.getWidth();
		
		int xPos = block.getX();
		int yPos = block.getY();
		
		Color color = block.getColor();
		
		for (int r = 0; r < h; r++) {
			for (int c = 0; c < w; c++) {
				if (shape[r][c] == 1) {
					background[r + yPos][c + xPos] = color;
				}
			}
		}
	}
	
	private void drawBlock(Graphics g)
	{
		int h = block.getHeight();
		int w = block.getWidth();
		Color c = block.getColor();
		int[][] shape = block.getShape();
		
		for (int row = 0; row < h; row++) {
			for (int col = 0; col < w; col++) {
				if (shape[row][col] == 1) {
					int x = (block.getX() + col) * tileSize;
					int y = (block.getY() + row) * tileSize;
					
					drawGridSquare(g, c, x, y);
				}
			}
		}
	}
	
	private void drawBackground(Graphics g)
	{
		Color color;
		
		for (int r = 0; r < screenRows; r++) {
			for (int c = 0; c < screenCols; c++) {
				color = background[r][c];
				
				if (color != null) {
					int x = c * tileSize;
					int y = r * tileSize;
				
					drawGridSquare(g, color, x, y);
				}
			}
		}
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
		
		drawBackground(g);
		drawBlock(g);
	}
}
