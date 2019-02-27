import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    Boolean isHost;
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Client(String ip) {
        this.isHost = false;
        try {
            this.socket = new Socket(ip, 4444);
            System.out.println("Connected");

            in = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void becomeHost(){
        this.isHost = true;
    }

}
