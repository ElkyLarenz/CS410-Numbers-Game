
/**
 * LobbyPlayerPanel
 * 
 * A panel for displaying information about the other players
 * in the game while in the lobby screen.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import numbersgame.Player;

@SuppressWarnings("serial")
public class LobbyPlayerPanel extends JPanel
{
	private Player player;
	private int position = 0;
	private JLabel playerLabel;
	private JLabel nameLabel;
	private JLabel connectedLabel;
	private Font nameFont;
	
	public LobbyPlayerPanel( Player player, int position )
	{
		this.player = player;
		this.position = position;
		
		nameFont = new Font( "Helvetical", Font.PLAIN, 18 );
		
		this.setLayout( new BorderLayout() );
		
		playerLabel = new JLabel( position + ". Player " + player.getID() );
		
		if ( player.getID() == 1 )
			playerLabel.setText( playerLabel.getText() + " (host)" );
		
		nameLabel = new JLabel( player.getPlayerName(), SwingConstants.CENTER );
		connectedLabel = new JLabel( "Not connected" );
		connectedLabel.setForeground( Color.red );
		
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		playerLabel.setFont( LobbyFrame.titleFont );
		nameLabel.setFont( nameFont );
		
		this.add( playerLabel, BorderLayout.NORTH );
		this.add( nameLabel, BorderLayout.CENTER );
		this.add( connectedLabel, BorderLayout.SOUTH );
		
		updatePlayerInformation();
	}
	
	public void updatePlayerInformation()
	{
		playerLabel.setText( position + ". Player " + player.getID() );
		
		if ( player.getID() == 1 )
			playerLabel.setText( playerLabel.getText() + " (host)" );
		
		nameLabel.setText( player.getPlayerName() );
		
		if ( player.getConnection() )
		{
			connectedLabel.setText( "Connected" );
			connectedLabel.setForeground( Color.green );
		}
		
		refresh();
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public int getPosition()
	{
		return position;
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
}
