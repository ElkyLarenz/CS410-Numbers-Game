
package numbersgame.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardCellPanel extends JPanel
{
	private BoardPanel board;
	private boolean activated;
	
	public BoardCellPanel( BoardPanel board )
	{
		this.board = board;
		
		this.setBackground( Color.red );
		this.setBorder( BorderFactory.createLineBorder( Color.black ) );
	}
	
	public void activate()
	{
		this.activated = true;
		this.setBackground( Color.green );
		
		refresh();
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
