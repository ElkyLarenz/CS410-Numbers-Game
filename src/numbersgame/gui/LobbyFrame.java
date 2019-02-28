
/**
 * LobbyFrame
 * 
 * What's displayed when the application is first started.
 * Lets the player to decide to host a game or join a game, set their name,
 * and displays information.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import numbersgame.Player;

@SuppressWarnings("serial")
public class LobbyFrame extends JFrame
{
	private JPanel mainPanel;
	private Dimension defaultDimension = GUI.defaultWindowSize;
	private GUI gui;
	
	public static Font titleFont = new Font( "Helvetica", Font.BOLD, 24 );
	
	public LobbyFrame( GUI gui )
	{
		this.gui = gui;
		this.setTitle( "Game Lobby" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( defaultDimension );
		showStartScreen();
	}
	
	// The start screen gives the player the option of joining
	// or hosting a game.
	public void showStartScreen()
	{
		Container content = this.getContentPane();
		content.removeAll();
		
		StartButtonListener startButtonListener = new StartButtonListener( this );
		mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout() );
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new GridLayout( 1, 2 ) );
		LobbyButton joinButton = new LobbyButton( "Join Game" );
		joinButton.addActionListener( startButtonListener );
		LobbyButton hostButton = new LobbyButton( "Host Game" );
		hostButton.addActionListener( startButtonListener );
		JLabel text = new JLabel( "Join or Host game?", SwingConstants.CENTER );
		
		text.setFont( titleFont );

		mainPanel.add( text, BorderLayout.NORTH );
		buttonPanel.add( joinButton );
		buttonPanel.add( hostButton );
		mainPanel.add( buttonPanel );
		
		content.add( mainPanel );
	}
	
	// The lobby screen is used before the game and between rounds.
	// It displays information about the other players.
	public void showLobbyScreen()
	{
		Container content = this.getContentPane();
		content.removeAll();
		
		mainPanel = new JPanel();
		
		mainPanel.setLayout( new GridLayout( 2, 2 ) );
		// temporary players for testing purposes.
		mainPanel.add( new LobbyPlayerPanel( new Player( "Albert" ), 1 ) );
		mainPanel.add( new LobbyPlayerPanel( new Player( "Barry" ), 2 ) );
		mainPanel.add( new LobbyPlayerPanel( new Player( "Chris" ), 3 ) );
		mainPanel.add( new LobbyPlayerPanel( new Player( "Derek" ), 4 ) );
		
		content.add( mainPanel );

		refresh();
	}
	
	public void showServerBrowser()
	{
		Container content = this.getContentPane();
		content.removeAll();
		
		mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout() );
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new GridLayout( 1, 2 ) );
		JLabel label = new JLabel( "Select a server to join" );
		ServerBrowser browser = new ServerBrowser();
		ServerBrowserPanel serverBrowserPanel = new ServerBrowserPanel( browser );
		LobbyButton refreshButton = new LobbyButton( "Refresh List" );
		LobbyButton joinButton = new LobbyButton( "Join Server" );
		ServerBrowserButtonListener serverBrowserButtonListener = new ServerBrowserButtonListener( this, serverBrowserPanel );
		
		refreshButton.addActionListener( serverBrowserButtonListener );
		joinButton.addActionListener( serverBrowserButtonListener );
		
		buttonPanel.add( refreshButton );
		buttonPanel.add( joinButton );
		
		mainPanel.add( label, BorderLayout.NORTH );
		mainPanel.add( serverBrowserPanel, BorderLayout.CENTER );
		mainPanel.add( buttonPanel, BorderLayout.SOUTH );
		
		content.add( mainPanel );
		
		refresh();
	}
	
	public void displayNameInput()
	{
		String playerName = JOptionPane.showInputDialog( "Enter your name" );
		
		gui.setPlayerName( playerName );
	}
	
	public GUI getGUI()
	{
		return gui;
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
}
