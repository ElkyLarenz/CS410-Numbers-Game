
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

public class LobbyPlayerPanel extends JPanel
{
	private Player player;
	private int position = 0;
	
	public LobbyPlayerPanel( Player player, int position )
	{
		this.player = player;
		this.position = position;
		
		JLabel playerLabel = new JLabel( "Player " + position );
		JLabel nameLabel = new JLabel( player.getPlayerName() );
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		playerLabel.setFont( LobbyFrame.titleFont );
		
		this.add( playerLabel, BorderLayout.NORTH );
		this.add( nameLabel, BorderLayout.NORTH );
	}
}
