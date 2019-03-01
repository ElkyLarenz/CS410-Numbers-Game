
/**
 * ServerBrowserButtonListener
 * 
 * Listener for the buttons on the server browser screen.
 */

package numbersgame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerBrowserButtonListener implements ActionListener
{
	LobbyFrame lobbyWindow;
	ServerBrowserPanel serverBrowserPanel;
	
	public ServerBrowserButtonListener( LobbyFrame lobbyWindow, ServerBrowserPanel serverBrowserPanel )
	{
		this.lobbyWindow = lobbyWindow;
		this.serverBrowserPanel = serverBrowserPanel;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() instanceof LobbyButton )
		{
			LobbyButton button = (LobbyButton) e.getSource();
			
			if ( button.getText().equals( "Refresh List" ) )
			{
				serverBrowserPanel.getServerBrowser().refreshServerList();
				serverBrowserPanel.updateTable();
			} else if ( button.getText().equals( "Join Server" ) )
			{
				if ( !serverBrowserPanel.getSelectedIP().equals( "" ) )
				{
					System.out.println( "Joining: " + serverBrowserPanel.getSelectedIP() );
					lobbyWindow.showLobbyScreen();
				}
			}
		}
	}
}
