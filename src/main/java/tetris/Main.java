package tetris;

import javax.swing.SwingUtilities;
import tetris.gui.GameForm;

public class Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{					
				new GameForm().setVisible(true);	
			}
		});
	}
}