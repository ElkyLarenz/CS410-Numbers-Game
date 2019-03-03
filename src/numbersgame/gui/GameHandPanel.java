package numbersgame.gui;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import numbersgame.Player;

public class GameHandPanel extends JPanel
{
	private Player player;
	private JLabel handLabel;
	private boolean isLocalPlayer;
	
	public GameHandPanel( Player player, boolean isLocalPlayer )
	{
		this.player = player;
		
		this.setLayout( new BorderLayout() );
		
		handLabel = new JLabel();
		handLabel.setFont( GameFrame.titleFont );
		
		this.add( handLabel );
		
		updateHand();
	}
	
	public void updateHand()
	{
		int[] numbers = new int[ 20 ];
		for ( int i = 0; i < 5; i++ )
		{
			numbers[ i ] = i + 1;
		}
		
		handLabel.setText( convertArray( numbers ) );
		
		refresh();
	}
	
	private String convertArray( int[] hand )
	{
		StringBuilder handString = new StringBuilder();
		
		handString.append( "<html>" );
		
		if ( hand != null )
		{
			for ( int i = 0; i < hand.length; i++ )
			{
				if ( hand[ i ] != 0 )
				{
					if ( i > 0 )
						handString.append( ", " );
					if ( i == 10 )
						handString.append( "<br>" );
					if ( i < 3 )
						handString.append( "?" );
					else
						handString.append( hand[ i ] );
				}
				else
					break;
			}
		}
		
		handString.append( "</html>" );
		
		return handString.toString();
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
}
