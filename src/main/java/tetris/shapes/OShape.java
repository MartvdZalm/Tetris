package tetris.shapes;

import java.awt.Color;

public class OShape extends Shape
{
	public OShape()
	{
		super(new int[][] {{1, 1}, {1, 1}}, 3);
		color = Color.yellow;
	}

	@Override
	public Shape createCopy()
	{
		return new OShape();
	}
}
