package numbersgame;
public class Player
{
    private String PlayerName;
    private boolean connected = false;
    private int id;
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
    public int addNumber(int[]hand, int num)
    {
        //adds number from gui into player hand

        int AddedNum = 0;
        for (int i=0; i < hand.length; i++)
        {
            hand[i] = AddedNum;
            AddedNum += num;
        }
        return AddedNum;

    }


}

