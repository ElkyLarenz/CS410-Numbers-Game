
package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	Container content;
	private JPanel mainPanel;
	private BoardPanel boardPanel;
	private Dimension defaultDimension = GUI.defaultWindowSize;
	private GUI gui;
	
	public static Font titleFont = new Font( "Helvetica", Font.BOLD, 24 );
	
	public GameFrame( GUI gui )
	{
		this.content = this.getContentPane();
		this.gui = gui;
		
		mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout() );
		
		this.setTitle( "Numbers Game" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( defaultDimension );
		
		initializeBoard();
		
		content.add( mainPanel );
	}
	
	public void initializeBoard()
	{
		boardPanel = new BoardPanel( this );
		mainPanel.add( boardPanel );
	}
	
	public void updateBoardData()
	{
		boardPanel.updateCells();
	}
	
	public GUI getGUI()
	{
		return gui;
	}
	
	public void refresh()
	{
		this.validate();
		this.repaint();
	}
}
