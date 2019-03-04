package numbersgame;

import java.io.IOException;
import java.util.Arrays;


public class networkTests {
    public static void main(String[] args) throws IOException {
        // Create a 2D array
        int[][] mat = new int[2][2];
        mat[0][0] = 99;
        mat[0][1] = 151;
        mat[1][0] = 30;
        mat[1][1] = 5;

        StringBuilder output = new StringBuilder();
        output.append("SETUP,");
        for(int i = 0 ; i < mat.length ; i++){
            for(int j = 0 ; j < mat[i].length ; j++){
                output.append(mat[i][j]).append(",");
            }
        }

        System.out.println(output);
    }
}
