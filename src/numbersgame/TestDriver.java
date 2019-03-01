package numbersgame;

import java.io.IOException;

import numbersgame.gui.GUI;

public class TestDriver
{
	public static void main( String[] args ) throws IOException
	{
		Game game = new Game();
		GUI gui = new GUI( game );
		game.setGUI( gui );
		game.setName( "C" );
		game.addPlayer( new String[] { "A", "B", "C", "D" } );
		gui.playerConnected();
		gui.startGame();
		gui.updateGameBoard();
	}
}
