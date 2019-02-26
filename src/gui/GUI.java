
package gui;

public class GUI
{
	LobbyFrame lobbyWindow;
	public GUI()
	{
		// Want to show this screen when the program first starts
		lobbyWindow = new LobbyFrame();
		lobbyWindow.setVisible( true );
	}
}
