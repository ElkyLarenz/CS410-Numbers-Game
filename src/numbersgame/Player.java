package numbersgame;

public class Player
{
    private String PlayerName;
    public int PlayerScore;
    public int Won;
    private boolean connected = false;
    private int id;
    public int[] hand;

    Player()
    {
        //default constructor

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
    public void addNumber(int num)
    {
        //adds number from gui into player hand

        int AddedNum = 0;
        for (int i=0; i < hand.length; i++)
        {
            hand[i] = AddedNum;
            AddedNum += num;
        }


    }
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
    public void addScore(int in, int num)
    {
        //the score added is the number of cards in hand


        in = in + num;

    }

    public int[] getHandNumbers(int[] arr)
    {
        return arr;
    }
}

