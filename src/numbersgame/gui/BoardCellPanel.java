
/**
 * BoardCellPanel
 * 
 * Contains the information for a single cell on the board.
 * Changes from red to green when told to activate.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BoardCellPanel extends JPanel
{
	private BoardPanel board;
	private boolean activated;
	private JLabel label;
	
	public BoardCellPanel( BoardPanel board )
	{
		this.board = board;
		
		this.setLayout( new BorderLayout() );
		this.setBackground( Color.red );
		this.setBorder( BorderFactory.createLineBorder( Color.black ) );
	}
	
	public void activate()
	{
		this.activated = true;
		this.setBackground( Color.green );
		
		refresh();
	}
	
	public void setLabel( int number )
	{
		label = new JLabel( Integer.toString( number ) );
		label.setFont( new Font( "Helvetica", Font.PLAIN, 16 ) );
		
		this.add( label, BorderLayout.NORTH );
	}
	
	public boolean isActivated()
	{
		return activated;
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
	
	public BoardPanel getBoard()
	{
		return board;
	}
}
