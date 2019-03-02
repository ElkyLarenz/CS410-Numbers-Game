
/**
 * GameButtonListener
 * 
 * Listener for buttons on the game window.
 */

package numbersgame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButtonListener implements ActionListener
{
	private GameFrame gameWindow;
	
	public GameButtonListener( GameFrame gameWindow )
	{
		this.gameWindow = gameWindow;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() instanceof GameButton )
		{
			GameButton button = (GameButton) e.getSource();
			
			if ( button.getText().equals( "Choose number" ) )
			{
				gameWindow.displayNumberInput();
			}
		}
	}

}
