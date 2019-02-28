package numbersgame;
import org.junit.Test;

import java.io.IOException;


public class networkTests {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        Server server = new Server();

        client.connectSocket(server.getIpAddress());

        System.out.println(server.getIpAddress());
        System.out.println(client.getIpAddress());
    }

    private static boolean assertEquals(String one, String two){
        return one.equals(two);
    }
}
