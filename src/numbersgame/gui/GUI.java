
/**
 * GUI
 * 
 * Class for managing the GUI windows and communicating with the Game.
 */

package numbersgame.gui;

import java.awt.Dimension;
import java.io.IOException;

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
		try {
			game.setName( name );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playerConnected()
	{
		
	}
	
	public void start( boolean isHost )
	{
		try {
			game.createClient( isHost );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isHost()
	{
		return isHost;
	}
}
