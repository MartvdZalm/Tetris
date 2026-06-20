package tetris.shapes;

import java.awt.Color;

public class ZShape extends Shape
{
	public ZShape()
	{
		super(new int[][] {{1, 1, 0}, {0, 1, 1}}, 6);
		color = Color.red;
	}

	@Override
	public Shape createCopy()
	{
		return new ZShape();
	}
}
