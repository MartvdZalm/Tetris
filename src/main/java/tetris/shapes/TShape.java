package tetris.shapes;

import java.awt.Color;

public class TShape extends Shape
{
	public TShape()
	{
		super(new int[][] {{1, 1, 1}, {0, 1, 0}}, 5);
		color = Color.magenta;
	}

	@Override
	public Shape createCopy()
	{
		return new TShape();
	}
}
