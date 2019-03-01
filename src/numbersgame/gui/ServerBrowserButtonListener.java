
/**
 * ServerBrowserButtonListener
 * 
 * Listener for the buttons on the server browser screen.
 */

package numbersgame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
					try {
						lobbyWindow.getGUI().getClient().connectSocket( serverBrowserPanel.getSelectedIP() );
						lobbyWindow.getGUI().getClient().createPlayer( lobbyWindow.getGUI().getGame().localPlayerName() );
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lobbyWindow.showLobbyScreen();
				}
			}
		}
	}
}
