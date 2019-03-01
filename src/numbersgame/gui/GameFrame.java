
package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import numbersgame.Player;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	private Container content;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel inputPanel;
	private JLabel playerLabel;
	private BoardPanel boardPanel;
	private Dimension defaultDimension = GUI.defaultWindowSize;
	private GUI gui;
	private GamePlayerPanel[] playerPanels;
	
	public static Font titleFont = new Font( "Helvetica", Font.BOLD, 24 );
	
	public GameFrame( GUI gui )
	{
		this.content = this.getContentPane();
		this.gui = gui;
		
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		inputPanel = new JPanel();
		
		mainPanel.setLayout( new BorderLayout() );
		
		Player localPlayer = gui.getGame().getLocalPlayerObject();
		
		playerLabel = new JLabel( "Player " + localPlayer.getID() + ": " + gui.getGame().localPlayerName(), SwingConstants.CENTER );
		playerLabel.setFont( titleFont );
		
		this.setTitle( "Numbers Game" );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( defaultDimension );
		
		mainPanel.add( playerLabel, BorderLayout.NORTH );
		
		boardPanel = createBoard();
		
		leftPanel.setLayout( new GridLayout( 4, 1 ) );
		
		playerPanels = new GamePlayerPanel[ 4 ];
		
		for ( int i = 0; i < 4; i++ )
		{
			playerPanels[ i ] = new GamePlayerPanel( gui.getGame().getPlayerArray()[ i ] );
			leftPanel.add( playerPanels[ i ] );
		}
		
		mainPanel.add( boardPanel, BorderLayout.CENTER );
		mainPanel.add( leftPanel, BorderLayout.WEST );
		mainPanel.add( rightPanel, BorderLayout.EAST );
		mainPanel.add( inputPanel, BorderLayout.SOUTH );
		
		content.add( mainPanel );
	}
	
	public BoardPanel createBoard()
	{
		return new BoardPanel( this );
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
