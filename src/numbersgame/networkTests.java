package numbersgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class networkTests {
    public static void main(String[] args) throws IOException {
        int[][] hands = new int[4][3];

        for(int i = 0 ; i < hands.length ; i++){
            for(int j = 0 ; j < hands[i].length ; j++){
                hands[i][j] = j;
            }
        }

        StringBuilder output = new StringBuilder();
        output.append("SETUP,");
        for(int i = 0 ; i < hands.length ; i++){
            for(int j = 0 ; j < hands[i].length ; j++){
                output.append(hands[i][j]).append(",");
            }
        }

        List<String> inputList = new ArrayList<>(Arrays.asList(output.toString().split(",")));
        ListIterator<String> in = inputList.listIterator();

        in.next();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                hands[i][j] = Integer.parseInt(in.next());
            }
        }

        System.out.println(Arrays.deepToString(hands));
    }
}
