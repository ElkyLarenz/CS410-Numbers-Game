package numbersgame;

import java.io.IOException;

import numbersgame.gui.GUI;

public class Game {
	Player[] players = new Player[4]; //this is the array of players
	
	int localPlayPos; //this is the position of the local player within the array
	int round; // this is the round the game is at
	GUI gameGUI; //this connects the Game class to the gui so they can interact
	int playerIndex = 0; //this helps add the player in different spots within the array
	Client gameClient = null; //this creates the client for the player
	int playerTurn; //this will keep track of whos turn it is
	String localPlayerName; //this holds the name of the local player
	Server hostServer; //its the host object for the host
	boolean[][] superSets = new boolean[4][4]; //keeps track of all supersets
	/*
	 * This constructer sets the round to 1
	 * and creates the client
	 */
	public Game() throws IOException
	{
		round = 1;
		
		for(int i = 0; i < 4; i++)
		{
			String empty = "";
			Player temp = new Player(empty, (i+1));
			players[i] = temp; 
		}
	}

	public void setupSuperSet()
	{
		for(int i= 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(j == i)
					superSets[i][j] = true;
				else
					superSets[i][j] = false;
			}
		}
	}
	
	public void createClient(boolean in) throws IOException
	{
		if(in == true)
		{

			players[0].setPlayerName(localPlayerName);
			players[0].setConnection(true);
			hostServer = new Server(players[0], this);
			gameClient = new Client(true, this);
			gameClient.createPlayer(localPlayerName);
			
		}
		else
		{
			gameClient = new Client(false, this);
		}
		
	}
	
	public String localPlayerName()
	{
		return localPlayerName;
	}
	
	//This will add score from the main player("its the one on this device")
	public void addScore(int in, int cards)
	{

		players[localPlayPos].addScore(in, cards);

	}
	
	/*
	 * This will get the score from the player
	 * to display in GUI, it returns an int
	 */
	public int getScore()

	{
		return players[localPlayPos].getScore();

	}
	
	/*
	 * This will check if the main player, this user 
	 */
	
	public void SuperSetCheck()
	{
	// players[localPlayPos].setCheck();
		int[] localHand = players[localPlayPos].getHandNumbers();
		
		for(int i = 0; i < 4; i++)
		{
			if(i != localPlayPos)
			{
				int[] checkingHand = players[i].getHandNumbers();
				boolean allSame = true;
				for(int j = 0; j < localHand.length; j++)
				{
					for(int k= 0; k < checkingHand.length; k++)
					{
						if(localHand[j] != checkingHand[k])
						{
							allSame = false;
						}
							
					}
					
				}
				if(allSame == true)
				{
					superSets[localPlayPos][i] = true;
				}
				
			}
		}
	}
	
	
	
	
	/*
	 * Changes the round
	 */
	public void changeRound()
	{
		round++;
	}
	
	/*
	 * GUI can use this method to pull what round it is
	 * so it can display it
	 */
	public int checkRound()
	{
		return round;
	}
	
	
	public void setGUI(GUI in)
	{
		gameGUI = in;
	}
	/*
	 * GUI will use this method to input the name and send it to the client
	 */
	public void setName(String in) throws IOException
	{
		
		localPlayerName = in;
	}
	
	/*
	 * Server side uses this method to add players as they are connected
	 */
	public void addPlayer(String name)
	{	
		if(localPlayerName.equals(name)) //this tells us what spot the local player is at
		{
			localPlayPos = playerIndex;
		}
		players[playerIndex].setPlayerName(name);
		
		gameGUI.playerConnected();
	
			
		playerIndex++;
		if(playerIndex == 5)
			this.startRound();
	}
	
	/*
	 * The GUI will use this method to add the number the local player 
	 * added to their hand
	 */
	public void addNumbertoLocal(int[]hand, int in)
	{
		players[playerIndex].addNumber(hand, in);
	}
	
	/*
	 * GUI can use this method to get 
	 * the array of players
	 */
	public Player[] getPlayerArray()
	{
		return players;
	}
	
	/*
	 * GUI uses it to get the client
	 */
	public Client getClient()
	{
		return gameClient;
	}
	
	public void startRound()
	{
		playerTurn = 0;
		//enable gui of the player whos turn it is
		
		
	}
	
	//Server uses to tell whos turn it is
	public void setPlayerTurn(int in)
	{
		playerTurn = in;
	}
	
	public void shiftPlayers()
	{
		Player temp;
		temp = players[3];
		for(int i = 2; i < 0; i--)
		{
			players[i+1] = players[i];
		}
		players[0] = temp;
	}
}
