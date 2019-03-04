
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
	private GameHandPanel[] handPanels;
	
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
		toggleButton( false );
		
		inputPanel.add( inputButton );
		
		leftPanel.setLayout( new GridLayout( 4, 1 ) );
		rightPanel.setLayout( new GridLayout( 4, 1 ) );
		
		playerPanels = new GamePlayerPanel[ 4 ];
		handPanels = new GameHandPanel[ 4 ];
		
		for ( int i = 0; i < 4; i++ )
		{
			boolean isLocalPlayer = false;
			Player player = gui.getGame().getPlayerArray()[ i ];
			
			if ( player == gui.getGame().getLocalPlayerObject() )
				isLocalPlayer = true;
			
			playerPanels[ i ] = new GamePlayerPanel( gui.getGame().getPlayerArray()[ i ] );
			handPanels[ i ] = new GameHandPanel( gui.getGame().getPlayerArray()[ i ], isLocalPlayer );
			
			rightPanel.add( handPanels[ i ] );
			leftPanel.add( playerPanels[ i ] );
		}
		
		mainPanel.add( topPanel, BorderLayout.NORTH );
		mainPanel.add( boardPanel, BorderLayout.CENTER );
		mainPanel.add( leftPanel, BorderLayout.WEST );
		mainPanel.add( rightPanel, BorderLayout.EAST );
		mainPanel.add( inputPanel, BorderLayout.SOUTH );
		
		content.add( mainPanel );
	}
	
	public void toggleButton( boolean enabled )
	{
		inputButton.setEnabled( enabled );
	}
	
	public BoardPanel createBoard()
	{
		return new BoardPanel( this );
	}
	
	public void displayNumberInput()
	{
		String input = JOptionPane.showInputDialog( "Choose a number" );
		
		if ( !input.equals( "" ) )
		{
			int chosenNumber = Integer.parseInt( input );
			
			System.out.println( "The number " + chosenNumber + " was chosen." );
			
			Player localPlayer = gui.getGame().getLocalPlayerObject();
			boolean hasNumber = false;
			
			for ( int i = 0; i < localPlayer.getHandNumbers().length; i++ )
			{
				if ( chosenNumber == localPlayer.getHandNumbers()[ i ] )
					hasNumber = true;
			}
			
			if ( !hasNumber )
			{
				gui.getGame().addNumbertoLocal( chosenNumber );
				toggleButton( false );
			} else
				displayNumberInput();
		}
	}
	
	public void updateBoardData()
	{
		boardPanel.updateCells();
		
		for ( int i = 0; i < handPanels.length; i++ )
		{
			handPanels[ i ].updateHand();
			playerPanels[ i ].updateScore();
		}
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
