package tetris.shapes;

import java.awt.Color;

public class IShape extends Shape
{
	public IShape()
	{
		super(new int[][] {{1, 1, 1, 1}});
		color = new Color(0,191,255);
	}
	
	@Override
	public void rotate()
	{
		super.rotate();
		
		if (this.getWidth() == 1) {
			this.setX(this.getX() + 1);
			this.setY(this.getY() - 1);
		} else {
			this.setX(this.getX() - 1);
			this.setY(this.getY() + 1);
		}
	}
}
