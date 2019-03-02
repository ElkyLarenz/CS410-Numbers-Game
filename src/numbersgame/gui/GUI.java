
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
	
	public GUI( Game game )
	{
		this.game = game;
		// Want to show this screen when the program first starts
		isHost = false;
		gameInProgress = false;
		lobbyWindow = new LobbyFrame( this );
//		lobbyWindow.showLobbyScreen();
		lobbyWindow.setVisible( true );
//		lobbyWindow.displayNameInput(); // Bypass the lobby screen for now.
		
	}
	
	public void startGame()
	{
		gameInProgress = true;
		gameWindow = new GameFrame( this );
		lobbyWindow.setVisible( false );
		gameWindow.setVisible( true );
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
	
	public void connectToIP( String ipAddress )
	{
		try {
			System.out.println( "Connecting to " + ipAddress );
			client.connectSocket( ipAddress );
			client.createPlayer( game.localPlayerName() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playerConnected()
	{
		lobbyWindow.updateLobby();
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
