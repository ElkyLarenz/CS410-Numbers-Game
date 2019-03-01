
package numbersgame.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel
{
	private GameFrame gameWindow;
	BoardCellPanel[][] cells;
	
	public BoardPanel( GameFrame gameWindow )
	{
		this.gameWindow = gameWindow;
		this.setLayout( new GridLayout( 4, 4 ) );
		
		cells = new BoardCellPanel[ 4 ][ 4 ];
		
		for ( int i = 0; i < 4; i++ )
		{
			for ( int j = 0; j < 4; j++ )
			{
				cells[ i ][ j ] = new BoardCellPanel( this );
				this.add( cells[ i ][ j ] );
			}
		}
	}
	
	public void updateCells()
	{
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
