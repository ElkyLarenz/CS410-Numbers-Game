
/**
 * GameFrame
 * 
 * The window the game is played on. Contains the board
 * and information about the players in the game.
 */

package numbersgame.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import numbersgame.Player;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	private GameButtonListener gameButtonListener;
	private Container content;
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel inputPanel;
//	private JLabel playerLabel;
	private GameButton inputButton;
	private BoardPanel boardPanel;
	private Dimension defaultDimension = GUI.defaultWindowSize;
	private GUI gui;
	private GamePlayerPanel[] playerPanels;
	
	public static Font titleFont = new Font( "Helvetica", Font.BOLD, 24 );
	
	public GameFrame( GUI gui )
	{
		this.setTitle( "Numbers Game" );
		
		this.content = this.getContentPane();
		this.gui = gui;
		
		gameButtonListener = new GameButtonListener( this );
		
		mainPanel = new JPanel();
		topPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		inputPanel = new JPanel();
		
		mainPanel.setLayout( new BorderLayout() );
		inputPanel.setLayout( new BorderLayout() );
		
		Player localPlayer = gui.getGame().getLocalPlayerObject();
		
//		playerLabel = new JLabel( "Player " + localPlayer.getID() + ": " + gui.getGame().localPlayerName(), SwingConstants.CENTER );
		
//		playerLabel.setFont( titleFont );
		
		this.setTitle( this.getTitle() + " | Player " + localPlayer.getID() + ": " + localPlayer.getPlayerName() );
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setSize( defaultDimension );
		
//		mainPanel.add( playerLabel, BorderLayout.NORTH );
		
		boardPanel = createBoard();
		
		inputButton = new GameButton( "Choose number" );
		inputButton.addActionListener( gameButtonListener );
		inputButton.setEnabled( false );
		
		inputPanel.add( inputButton );
		
		leftPanel.setLayout( new GridLayout( 4, 1 ) );
		
		playerPanels = new GamePlayerPanel[ 4 ];
		
		for ( int i = 0; i < 4; i++ )
		{
			playerPanels[ i ] = new GamePlayerPanel( gui.getGame().getPlayerArray()[ i ] );
			leftPanel.add( playerPanels[ i ] );
		}
		
		mainPanel.add( topPanel, BorderLayout.NORTH );
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
	
	public void displayNumberInput()
	{
		int chosenNumber = Integer.parseInt( JOptionPane.showInputDialog( "Choose a number" ) );
		
		System.out.println( "The number " + chosenNumber + " was chosen." );
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
