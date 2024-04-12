package tetris.shapes;

import java.awt.Color;

public class JShape extends Shape
{
	public JShape()
	{
		super(new int[][] {{0, 1}, {0, 1}, {1, 1}});
		color = Color.blue;
	}
}
