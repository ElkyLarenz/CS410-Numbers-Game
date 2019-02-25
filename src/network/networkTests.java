package network;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class networkTests {
    @Test
    public void testIPaddress() throws IOException {
        String ip = "192.168.0.14";
        Server server = new Server();
        assertEquals(ip, server.getIpAddress());
    }
}
