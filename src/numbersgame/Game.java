package numbersgame;

import java.io.IOException;
import java.util.Random;

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
    boolean host = false;

    /*
     * This constructer sets the round to 1
     * and creates the client
     */
    public Game() throws IOException {

        round = 1;

        for (int i = 0; i < 4; i++) {
            String empty = "";
            Player temp = new Player(empty, (i + 1));
            players[i] = temp;
        }
    }

    public void setupSuperSet() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i)
                    superSets[i][j] = true;
                else
                    superSets[i][j] = false;
            }
        }
    }

    public Player getLocalPlayerObject() {
        return players[localPlayPos];
    }

    public void createClient(boolean in) throws IOException {
        if (in == true) {

            players[0].setPlayerName(localPlayerName);
            players[0].setConnection(true);
            hostServer = new Server();
            gameClient = new Client(true, this);
            gameClient.createPlayer(localPlayerName);

        } else {
            gameClient = new Client(false, this);
        }

    }

    public String localPlayerName() {
        return localPlayerName;
    }

    //This will add score from the main player("its the one on this device")
 
    /*
     * This will get the score from the player
     * to display in GUI, it returns an int
     */
    public int getScore() {
        return players[localPlayPos].getScore();

    }


    /*
     * Gui uses this to get superset 2D array
     *
     */
    public boolean[][] getSupersetArray() {
        
        return superSets;
    }

    public void send2DArraytoGUI() {
        gameGUI.updateGameBoard();
        ;
    }

    /*
     * Changes the round
     */
    public void changeRound() {
        round++;
    }

    /*
     * GUI can use this method to pull what round it is
     * so it can display it
     */
    public int checkRound() {
        return round;
    }


    public void sendHand() {
        //here is going to be the code to send the hand
    }


    //Method that interact with GUI
    //---------------------------------------------------------
    public void setGUI(GUI in) {
        gameGUI = in;
    }

    /*
     * GUI will use this method to input the name and send it to the client
     */
    public void setName(String in) {

        localPlayerName = in;
    }

    /*
     * Server side uses this method to add players as they are connected
     */
    public void addPlayer(String[] name) {
        for (int i = 0; i < 4; i++) {
            if (name[i] != null) {
                players[i].setPlayerName(name[i]);
                players[i].setConnection(true);
                if (name[i].equals(localPlayerName)) {
                    localPlayPos = i;
                    if (localPlayPos == 0)
                        host = true;
                    System.out.println("Your location within the array is " + i);
                }

            }
        }
		/*
		if(localPlayerName.equals(name)) //this tells us what spot the local player is at
		{
			localPlayPos = playerIndex;
		}
		players[playerIndex].setPlayerName(name);
		*/
		gameGUI.playerConnected();
	
			
		playerIndex++;
		if(playerIndex == 5)
			this.startRound();
	}
	
	
    public void addNumberToPlayer(int pos, int num) {
        players[pos].addNumber(num);
    }

    /*
     * GUI can use this method to get
     * the array of players
     */
    public Player[] getPlayerArray() {
        return players;
    }

    /*
     * GUI uses it to get the client
     */
    public Client getClient() {
        return gameClient;
    }

    //game methods
    //----------------------------------------------------------------------------------------------
    //this starts the Round by checking if its players turn
    public void startRound() {
        //enable gui of the player whos turn it is
    	this.setupSuperSet();
        playerTurn = 0;
        System.out.println(host);
        
        if (host == true)
            this.setUpHand();
        this.startTurn();
    }

    public void setUpHand()
	{
		System.out.println("host is in Hand setup");
		boolean go = false;
		Random rnd = new Random();
		int[][] tempHand = new int[4][3];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(j == 0)
					tempHand[i][j] = rnd.nextInt(20)+1;
				if(j == 1)
				{
					int temp = rnd.nextInt(20)+1;
					boolean br = false;
					do
					{	if(temp == 0)
							temp = rnd.nextInt(20)+1;
						if(temp == tempHand[i][0])
							temp = rnd.nextInt(20)+1;
						else
						{
							tempHand[i][j] = temp;
							br = true;
						}
							
					}while(br== false);
				}
				if(j == 2)
				{
					int temp = rnd.nextInt(20)+1;
					boolean br = false;
					do
					{
						if(temp == 0)
							temp = rnd.nextInt(20)+1;
						if(temp == tempHand[i][0])
							temp = rnd.nextInt(20)+1;
						if(temp == tempHand[i][1])
							temp = rnd.nextInt(20)+1;
						else {
					
						
							br = true;
							tempHand[i][j] = temp;
						}
							
					}while(br== false);
				}
				
			}	

					
			
    }
		 try {
             gameClient.sendInitialHands(tempHand);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
    public void receiveSetupHand(int[][] in) {
        System.out.println("Your Hand ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (localPlayPos == i)
                    System.out.print(in[i][j] + " ");

                players[i].addNumber(in[i][j]);
            }

        }
        gameGUI.updateGameBoard();

    }

    public void startTurn() {
        if (this.checkIfLocalTurn() == true) {
            System.out.printf("your turn");
            gameGUI.startTurn(true);
        } else
            gameGUI.startTurn(false);
    }

    /*
     * This checks if its localPlayers turn
     */
    public boolean checkIfLocalTurn() {
        if (playerTurn == localPlayPos) {

            return true;
        } else {
            return false;
        }
    }

    /*
     * The GUI will use this method to add the number the local player
     * added to their hand
     */
    public void addNumbertoLocal(int in) {
        players[localPlayPos].addNumber(in);
        try {
            gameClient.sendHand(players[localPlayPos].getHandNumbers());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void receiveHand(int[] in) {
        System.out.println("updated hand for player " + players[playerTurn].getPlayerName());
        players[playerTurn].updateWholeHand(in);
        this.SuperSetCheck();
       if(this.checkIfLocalHasAllSuperSet() == true)
       {
       		this.endRound();
       }
        gameGUI.updateGameBoard();

        if (playerTurn == 3)
            playerTurn = 0;
        else
            playerTurn++;
        this.startTurn();

    }


    /*
     * This will check if the main player, this user
     */

    public void SuperSetCheck() {
        // players[localPlayPos].setCheck();
        int[] localHand = players[playerTurn].getHandNumbers();
        boolean overallSuperSet = false; // this will tell me if overall there was at least one superset
        for (int i = 0; i < 4; i++) {
            if (i != playerTurn) {
                int[] checkingHand = players[i].getHandNumbers();
                boolean allSame = subsetCheck(localHand, checkingHand);

                if (allSame) {
                    superSets[playerTurn][i] = true;
                    overallSuperSet = true;
                }
            }
        }

        if (overallSuperSet) {
            this.send2DArraytoGUI();
        }
    }

    // return true if a2 is a subset of a1
    public boolean subsetCheck(int[] arr1, int[] arr2)
    {
        int i = 0;
        int j = 0;
        int m = arr1.length;
        int n = arr2.length;
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < m; j++)
                if(arr2[i] == arr1[j])
                    break;

            /* If the above inner loop
            was not broken at all then
            arr2[i] is not present in
            arr1[] */
            if (j == m)
                return false;
        }

        /* If we reach here then all
        elements of arr2[] are present
        in arr1[] */
        return true;
    }
    //------------------------------------------------------------------------------------------------------


    //---------------------------------------------------------------------------------


    public boolean checkIfLocalHasAllSuperSet() {
        for (int i = 0; i < 4; i++) {
            if (superSets[playerTurn][i] == false)
                return false;
        }
        System.out.println("Winner is player: " + players[playerTurn].getPlayerName());
        return true;
    }

    public void tellGuiItsLocalTurn() {

    }

    //Server uses to tell whos turn it is
    public void setPlayerTurn(int in) {
        playerTurn = in;
    }

    public void shiftPlayers() {
        Player temp;
        temp = players[3];
        for (int i = 0; i < 3; i++) {
            players[i + 1] = players[i];
        }
        players[0] = temp;

        if (localPlayPos == 3)
            localPlayPos = 0;
        else
            localPlayPos++;

    }
    
    public void endRound()
    {
    	System.out.println("END ROUND PHASE");
    	for(int i = 0; i < 4; i++)
    	{
    		if(i == playerTurn)
    		{
    			int counter = players[i].totalNum();
    			players[i].addScore(counter);
    		}
    		else
    			players[i].addScore(10);
    	}
    	
    	gameGUI.updateGameBoard();
    	
    	players[localPlayPos].resetHand();
    	this.shiftPlayers();
    	this.changeRound();
    	this.setupSuperSet();
    	gameGUI.beginNewRound();
    	this.startRound();
    }
}
