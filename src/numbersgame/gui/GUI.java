
/**
 * GUI
 * 
 * Class for managing the GUI windows and communicating with the Game.
 */

package numbersgame.gui;

import java.awt.Dimension;
import java.io.IOException;

import numbersgame.Game;
import numbersgame.Client;

public class GUI
{
	private LobbyFrame lobbyWindow;
	private GameFrame gameWindow;
	public static Dimension defaultWindowSize = new Dimension( 1024, 768 );
	private Game game;
	private Client client;
	private boolean isHost;
	private boolean gameInProgress;
	
	public GUI()
	{
		// Want to show this screen when the program first starts
		isHost = false;
		gameInProgress = true;
//		lobbyWindow = new LobbyFrame( this );
//		lobbyWindow.setVisible( true );
		
		gameWindow = new GameFrame( this );
		gameWindow.setVisible( true );
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
	
	public void updateGameBoard()
	{
		if ( gameInProgress )
		{
			gameWindow.updateBoardData();
		}
	}
	
	public void start( boolean isHost )
	{
		try {
			game.createClient( isHost );
			client = game.getClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isHost()
	{
		return isHost;
	}
	
	public Client getClient()
	{
		return client;
	}
}
