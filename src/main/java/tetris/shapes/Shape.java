package tetris.shapes;

import java.awt.Color;
import java.util.Random;

public abstract class Shape
{
	private int[][] shape;
	protected Color color;
	private int x;
	private int y;
	private int[][][] shapes;
	private int currentRotation;
	private final int typeIndex;

	protected Shape(int[][] shape, int typeIndex)
	{
		this.typeIndex = typeIndex;
		this.shape = shape;
		initShapes();
	}

	private void initShapes()
	{
		shapes = new int[4][][];

		for (int i = 0; i < 4; i++) {
			int r = shape[0].length;
			int c = shape.length;

			shapes[i] = new int[r][c];

			for (int y = 0; y < r; y++) {
				for (int x = 0; x < c; x++) {
					shapes[i][y][x] = shape[c - x - 1][y];
				}
			}

			shape = shapes[i];
		}
	}

	public abstract Shape createCopy();

	public void spawn(int gridWidth)
	{
		currentRotation = 0;
		shape = shapes[currentRotation];
		y = -getHeight();
		x = (gridWidth - getWidth()) / 2;
	}

	public int[][] getShape()
	{
		return shape;
	}

	public int[][] getShapeAtRotation(int rotation)
	{
		return shapes[rotation];
	}

	public Color getColor()
	{
		return color;
	}

	public int getHeight()
	{
		return shape.length;
	}

	public int getWidth()
	{
		return shape[0].length;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getCurrentRotation()
	{
		return currentRotation;
	}

	public void setRotation(int rotation)
	{
		currentRotation = rotation;
		shape = shapes[currentRotation];
	}

	public int getTypeIndex()
	{
		return typeIndex;
	}

	public void moveDown()
	{
		y++;
	}

	public void moveLeft()
	{
		x--;
	}

	public void moveRight()
	{
		x++;
	}

	public int getBottomEdge()
	{
		return y + getHeight();
	}

	public int getLeftEdge()
	{
		return x;
	}

	public int getRightEdge()
	{
		return x + getWidth();
	}
}
