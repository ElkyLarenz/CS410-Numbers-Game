package numbersgame;

import java.io.IOException;
import java.util.Arrays;


public class networkTests {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.setName("Tim");
        game.createClient(true);

        int[] numbers = new int[] {1, 2, 3, 4};

        game.gameClient.sendHand(numbers);

        System.out.println(Arrays.toString(game.getPlayerArray()));
    }
}
