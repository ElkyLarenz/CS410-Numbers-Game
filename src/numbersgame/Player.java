package numbersgame;

public class Player
{
    private String PlayerName;
    public int PlayerScore;
    public int Won; // how many rounds they won
    private boolean connected = false;
    private int id;
    public int[] hand = new int[20];
    public boolean wonRound = false; //helps tell if they won the round
    public int handLoc = 0;
  

    Player()
    {
        //default constructor
    	PlayerScore = 0;

    }
    @Override
    public String toString()
    {
        return PlayerName;
    }


    
    public Player(String name, int ID)
    {
        PlayerName = name;
        id = ID;
    }

    public void setPlayerName(String name)
    {
        this.PlayerName = name;
        //This method sets the name of the player
    }

    public String getPlayerName()
    {
        return PlayerName;
        //returns the name as a string
    }

    
    public void setConnection(boolean in)
    {
    	connected = in;
    }
    
    public boolean getConnection()
    {
    	return connected;
    }

    public int getID()
    {
    	return id;
    }
    //------------------------------------------------------------------
    public void addNumber(int num)
    {
        //adds number from gui into player hand

      hand[handLoc] = num;
      handLoc++;


    }
    
    //----------------------------------------------------------------------------
    public void randomNum()
    {
        //this is to generate a random number
        int rand = (int)(Math.random() * 20 + 1);


    }

    public boolean setCheck()
    {
        return false;
    }

    public int getScore()
    {
        return this.PlayerScore;
    }
    public int GetWon()
    {
        return Won;
    }
    public int AddtoWon() {
        return Won++;
    }
    public void addScore(int in)
    {
        //the score added is the number of cards in hand


        PlayerScore = in + PlayerScore;

    }

    public int[] getHandNumbers()
    {
        int[] arr = hand;
        return arr;
    }
    
    public void wonRound()
    {
    	wonRound = true;
    }
	public void updateWholeHand(int[] in) 
	{
		// TODO Auto-generated method stub
		hand = in;
		
	}
	public void resetHand() {
		// TODO Auto-generated method stub
		System.out.println("reset Hand");
		hand = new int[20];
		handLoc = 0;
		
	}
	
	
}

