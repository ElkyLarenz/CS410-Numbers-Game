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
		game.setName( "Chris" );
		game.addPlayer( new String[] { "Albert", "Barry", "Chris", "Derek" } );
		gui.playerConnected();
		gui.startGame();
		gui.updateGameBoard();
	}
}
