import org.junit.Test;

import java.io.IOException;


public class networkTests {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        Server server = new Server();

        String ip = "192.168.0.14";

        System.out.println(testIPaddress(ip, server));
    }

    private static boolean testIPaddress(String ip, Server server) throws IOException {
        return assertEquals(ip, server.getIpAddress());
    }

    private static boolean assertEquals(String one, String two){
        return one.equals(two);
    }
}
