
/**
 * LobbyPlayerPanel
 * 
 * A panel for displaying information about the other players
 * in the game while in the lobby screen.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import numbersgame.Player;

@SuppressWarnings("serial")
public class LobbyPlayerPanel extends JPanel
{
	private Player player;
	private int position = 0;
	
	public LobbyPlayerPanel( Player player, int position )
	{
		this.player = player;
		this.position = position;
		
		this.setLayout( new BorderLayout() );
		
		JLabel playerLabel = new JLabel( position + ". Player " + player.getID() );
		if ( player.getID() == 1 )
			playerLabel.setText( playerLabel.getText() + " (host)" );
		JLabel nameLabel = new JLabel( player.getPlayerName() );
		JLabel connectedLabel = new JLabel( "Not connected" );
		connectedLabel.setForeground( Color.red );
		
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		if ( player.getConnection() )
		{
			connectedLabel.setText( "Connected" );
			connectedLabel.setForeground( Color.green );
		}
		
		playerLabel.setFont( LobbyFrame.titleFont );
		
		this.add( playerLabel, BorderLayout.NORTH );
		this.add( nameLabel, BorderLayout.CENTER );
		this.add( connectedLabel, BorderLayout.SOUTH );
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
