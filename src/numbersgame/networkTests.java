package numbersgame;

import java.io.IOException;
import java.util.*;

public class networkTests {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.setName("Tim");
        game.createClient(true);

        int[] numbers = new int[] {1, 2, 3, 4};

        game.gameClient.sendHand(numbers);
        game.gameClient.createPlayer("Bob");

        game.gameClient.createPlayer("Charlie");

        System.out.println(Arrays.toString(game.getPlayerArray()));
    }
}
