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
			
		}
	}
}
