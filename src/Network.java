import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Network {
    static String findIPaddress() throws SocketException, UnknownHostException {
        final DatagramSocket ds = new DatagramSocket();
        ds.connect(InetAddress.getByName("8.8.8.8"), 10002);
        return ds.getLocalAddress().getHostAddress();
    }

}
