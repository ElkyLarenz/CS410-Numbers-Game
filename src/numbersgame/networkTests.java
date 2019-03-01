package numbersgame;

import java.io.IOException;


public class networkTests {
    public static void main(String[] args) throws IOException {
        Player player = new Player("Tim", 0);
        Game game = new Game();
        Client client = new Client(true);
        Server server = new Server(player, game);

        client.connectSocket(server.getIpAddress());

        System.out.println(server.getIpAddress());
        System.out.println(client.getIpAddress());
    }

    private static boolean assertEquals(String one, String two){
        return one.equals(two);
    }
}
