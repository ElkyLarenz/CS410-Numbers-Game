
/**
 * BoardPanel
 * 
 * The board that is displayed on the game window.
 */

package numbersgame.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel
{
	private GameFrame gameWindow;
	private BoardCellPanel[][] cells;
	
	public BoardPanel( GameFrame gameWindow )
	{
		this.gameWindow = gameWindow;
		this.setLayout( new GridLayout( 4, 4 ) );
		
		cells = new BoardCellPanel[ 4 ][ 4 ];
		
		for ( int i = 0; i < 4; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				BoardCellPanel newCell = new BoardCellPanel( this );
				cells[ i ][ j ] = newCell;
				this.add( newCell );
				
				if ( i == 0 )
					newCell.setLabel( j + 1 );
				
				if ( j == 0 && i != 0 )
					newCell.setLabel( i + 1 );
			}
		}
		
		updateCells();
	}
	
	public void updateCells()
	{
		// Check the superset array to see if we need to change the color
		// of any of the cells.
		boolean[][] superSetArray = gameWindow.getGUI().getGame().getSupersetArray();
		
		for ( int i = 0; i < 4; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				if ( superSetArray[ i ][ j ] )
				{
					System.out.println( "True" );
					cells[ i ][ j ].activate();
				} else
					System.out.println( "False" );
			}
		}
	}
	
	public GameFrame getGameWindow()
	{
		return gameWindow;
	}
	
	public BoardCellPanel getCell( int rowIndex, int colIndex )
	{
		return cells[ rowIndex ][ colIndex ];
	}
}
