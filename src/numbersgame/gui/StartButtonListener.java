
/**
 * StartButtonListener
 * 
 * An ActionListener for LobbyButton buttons.
 */

package numbersgame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonListener implements ActionListener
{
	private LobbyFrame lobbyWindow;
	
	public StartButtonListener( LobbyFrame lobbyFrame )
	{
		this.lobbyWindow = lobbyFrame;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() instanceof LobbyButton )
		{
			LobbyButton button = (LobbyButton) e.getSource();
			
			lobbyWindow.displayNameInput();
			
			if ( button.getText().equals( "Host Game" ) )
			{
				lobbyWindow.getGUI().makeHost();
				lobbyWindow.showLobbyScreen();
			} else if ( button.getText().equals( "Join Game" ) )
			{
				lobbyWindow.showServerBrowser();
			}
		}
	}
}
