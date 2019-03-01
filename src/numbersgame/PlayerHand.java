package numbersgame;
import java.util.LinkedList;

public class PlayerHand
{
// The player's hand should be sorted
    int[]  hand  = new int[20];


    PlayerHand()
    {
        //default constructor
        //this is here just to set the numbers in hand to to be in the range
        //from 1 to 20
        int[]  hand  = new int[20];
        for(int i = 0; i < hand.length; i++)
        {
            hand[i] = Integer.parseInt(null);
        }

    }
    //create an array index of the numbers in player hand, say how many are field
    public int[] sendHand()
    {
        getHand();
        int count = 0;

        for(int i = 0; i <= hand.length; i++)
        {
            if((Object)hand[i] != null)
            {
                count++;
            }
        }
        int[]  exactNums  = new int[count];
        for(int i = 0; i <= exactNums.length; i++)
        {
            exactNums[i] = hand[i];
        }

        return exactNums;
    }



    public void setHand(int[] hand) {
        this.hand = hand;
    }

    public int[] getHand() {
        return hand;
    }

    /// <summary>
    /// The deck in the hand
    /// </summary>



}
