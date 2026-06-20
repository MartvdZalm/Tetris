package tetris.shapes;

import java.awt.Color;

public class IShape extends Shape
{
	public IShape()
	{
		super(new int[][] {{1, 1, 1, 1}}, 0);
		color = new Color(0, 191, 255);
	}

	@Override
	public Shape createCopy()
	{
		return new IShape();
	}
}
