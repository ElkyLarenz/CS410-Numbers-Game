
package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LobbyFrame extends JFrame
{
	private JPanel mainPanel;
	private Dimension defaultDimension = GUI.defaultWindowSize;
	
	private Font titleFont = new Font( "Helvetica", Font.BOLD, 24 );
	
	public LobbyFrame()
	{
		defaultDimension = new Dimension( 640, 480 );
		this.setTitle( "Game Lobby" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( defaultDimension );
		showStartScreen();
	}
	
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
	
	public void showLobbyScreen()
	{
		Container content = this.getContentPane();
		content.removeAll();
		
		mainPanel = new JPanel();
	}
}
