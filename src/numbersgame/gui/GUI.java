
/**
 * GUI
 * 
 * Class for managing the GUI windows and communicating with the Game.
 */

package numbersgame.gui;

import java.awt.Dimension;

import numbersgame.Game;

public class GUI
{
	private LobbyFrame lobbyWindow;
	public static Dimension defaultWindowSize = new Dimension( 1024, 768 );
	private Game game;
	private boolean isHost;
	
	public GUI()
	{
		// Want to show this screen when the program first starts
		isHost = false;
		lobbyWindow = new LobbyFrame( this );
		lobbyWindow.setVisible( true );
	}
	
	public void setGame( Game game )
	{
		this.game = game;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void setPlayerName( String name )
	{
		//game.setName( name );
	}
	
	public void playerConnected()
	{
		
	}
	
	public void makeHost()
	{
		isHost = true;
	}
	
	public boolean isHost()
	{
		return isHost;
	}
}
