package tetris.shapes;

import java.awt.Color;

public class LShape extends Shape
{
	public LShape()
	{
		super(new int[][] {{1, 0}, {1, 0}, {1, 1}});
		color = Color.orange;
	}
}

