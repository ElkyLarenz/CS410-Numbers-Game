
package numbersgame.gui;

import java.awt.Dimension;

import numbersgame.Game;

public class GUI
{
	private LobbyFrame lobbyWindow;
	public static Dimension defaultWindowSize = new Dimension( 1024, 768 );
	private Game game;
	
	public GUI()
	{
		// Want to show this screen when the program first starts
		lobbyWindow = new LobbyFrame();
		lobbyWindow.setVisible( true );
	}
	
	public void setGame( Game game )
	{
		this.game = game;
	}
	
	public void playerConnected()
	{
		
	}
}
