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
        for(int i = 0; i < hand.length; i++){
            hand[i] = i + 1; // +1 since we want 1-20
        }


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
