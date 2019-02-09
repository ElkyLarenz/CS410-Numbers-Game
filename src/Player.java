public class Player
{
    private String PlayerName;

    Player()
    {
        //default constructor

    }
    @Override
    public String toString()
    {
        return PlayerName;
    }


    public Player(String name)
    {
        PlayerName = name;
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



}

