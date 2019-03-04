
/**
 * GamePlayerPanel
 * 
 * Shows the name of the player on the game window.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import numbersgame.Player;

@SuppressWarnings("serial")
public class GamePlayerPanel extends JPanel
{
	private Player player;
	private JLabel nameLabel;
	
	public GamePlayerPanel( Player player )
	{
		this.player = player;
		
		this.setLayout( new BorderLayout() );
		
		nameLabel = new JLabel( "Player " + player.getID() + ": " + player.getPlayerName(), SwingConstants.CENTER );
		nameLabel.setFont( GameFrame.titleFont );
		
		this.add( nameLabel );
	}
	
	public void updateScore()
	{
		nameLabel.setText( "Player " + player.getID() + ": " + player.getPlayerName() + "[" + player.getScore() + "]" );
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
}
