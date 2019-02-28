package numbersgame;

import java.io.IOException;

import numbersgame.gui.GUI;

public class Game {
	Player[] players = new Player[4];
	int localPlayPos;
	int round;
	GUI gameGUI;
	int playerIndex = 0;
	Client gameClient = null;
	
	
	public Game() throws IOException
	{
		round = 1;
		gameClient = new Client();
	}
	
	//This will add score from the main player("its the one on this device")
	public void addScore(int in)
	{
		players[localPlayPos].addScore(in);
	}
	/*
	 * This will get the score from the player
	 * to display in GUI, it returns an int
	 */
	public int getScore()
	{
		return players[localPlayPos].getScore;
	}
	/*
	 * This will check if the main player, this user 
	 */
	public boolean SuperSetCheck()
	{
		return players[localPlayPos].setCheck();
	}
	/*
	 * sets the players from lobby
	 */
	public void setPlayers(Player[] in)
	{
		players = in;
	}
	
	public void changeRound()
	{
		round++;
	}
	
	public int checkRound()
	{
		return round;
	}
	
	public void setGUI(GUI in)
	{
		gameGUI = in;
	}
	
	public void setName(String in)
	{
		Client.createPlayer(in);
	}
	
	public void addPlayer(String name)
	{
		players[playerIndex].setPlayerName(name);
		playerIndex++;
		gameGUI.playerConnected();
	}
}
