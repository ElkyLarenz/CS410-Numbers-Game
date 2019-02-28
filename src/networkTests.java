import org.junit.Test;

import java.io.IOException;


public class networkTests {
    public static void main(String[] args) throws IOException {
        String ip = "192.168.0.14";

        System.out.println(testIPaddress(ip));
    }

    private static boolean testIPaddress(String ip) throws IOException {
        Server server = new Server();
        return assertEquals(ip, server.getIpAddress());
    }

    private static boolean assertEquals(String one, String two){
        return one.equals(two);
    }
}
