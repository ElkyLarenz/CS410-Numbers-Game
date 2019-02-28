
package numbersgame.gui;

public class GUI
{
	private LobbyFrame lobbyWindow;
	public GUI()
	{
		// Want to show this screen when the program first starts
		lobbyWindow = new LobbyFrame();
		lobbyWindow.setVisible( true );
	}
}
