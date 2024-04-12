package tetris.shapes;

import java.awt.Color;

public class SShape extends Shape
{
	public SShape()
	{
		super(new int[][] {{0, 1, 1}, {1, 1, 0}});
		color = Color.green;
	}
}
