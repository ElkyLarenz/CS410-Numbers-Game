package numbersgame;
public class Player
{
    private String PlayerName;
    public int PlayerScore;
    public int Won;


    Player()
    {
        Object PlayerHand = new PlayerHand();

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
    public void playerScore()
    {
        int Score = 0;

    }
    public void addScore(int in, int cards)
    {
        //the score added is the number of cards in hand


        in = in + cards;

    }

    public int getScore()
    {
        return PlayerScore;

    }
    public int GetWon()
    {
        return Won;
    }
    public int AddtoWon()
    {
        return Won++;
    }

    public void setCheck()
    {


    }




}

