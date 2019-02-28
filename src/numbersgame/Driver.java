package numbersgame;
/*
    Numbers Game Program
    CS-410 Software Engineering: Iyengar
    Due: 3/3/2019
    Team:
        Elky Ratliff
        Tim Jensen
        Justin Quella
        Jose Tolentino
 */

/*
Assumptions:
·         There are 4 players. Players are named P1, P2, P3, and P4. The players are on three different machines.
·         Each player is given set of 3 numbers chosen randomly – in the range [1 – 20].
·         Each player knows their numbers – but must guess other players numbers.
·         Players take turn in a cycle. During the turn the player adds a number to his/her set.
·         There is a 4 by 4 grid of dots. These dots are red or green.
·         The goal of a player is to get all the dots green.

·         A dot is green when the player represented in the row holds numbers that is a superset
          of number held by player represented by the column. Otherwise, the dot is red.

·         Ex: If P1 has {3, 5, 6, 9} and P2 has {5, 6, 9} then dot in P1’s row under P2 column
          would be green – and the dot in P2’s row under P1 column would be red.

Control:
·         A new game starts with numbers are randomly created and assigned players.
·         The board is updated after a player has played.
·         A game ends with a winner when a player has all the dots green on their row.
·         The winner is assigned a point score – which is the numbers added during the game
·         The other players are assigned 10 points.
·         A new game is started.
·         A match is a series of 10 games. The winner of the match is the player with the lowest number of points.
·         If there is a tie in the number of points, then the player who wins the greatest number of games wins the match.
·         If there is still a tie – then there is no winner.
Output:
·         Neat, and decipherable, display of the board and dots.
·         Neat, and decipherable, display of the board.
·         Neat graphic prompts and results display.

          All code must be clearly documented, to ensure the reader to follow the intent of the code.
          All labels used must be descriptive of intended use.
          Populated Excel workbook
*/





import java.util.*;

import numbersgame.gui.GUI;

public class Driver
{

    public static void main(String[] args)
    {
        //All code so far is done as if there is only 4 players
        new GUI();
        new Numbers();
        new Player();


        /*for(int i = 0; i <= 3; i++)
        {
            //Creates each player
            new Player().setPlayerName("Player " + i);
            //Reminder: give the player the option to customize their name later
            System.out.println();
        }*/
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();

        for(int i = 0; i <= 3; i++)
        {
            //Creates each player's hand
            LinkedList<PlayerHand> hand = new LinkedList<PlayerHand>();
        }

        //random number generator
        Random rand = new Random();

        int n = rand.nextInt(20) + 1;
        //20 is the maximum and the 1 is our minimum.



    }

}
