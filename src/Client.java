import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Client {
    private Boolean isHost;
    private String ipAddress;
    private String[] serverIPs = new String[4];
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public Client() throws IOException {
        this.isHost = false;
        this.ipAddress = Network.findIPaddress();
        findServers();
    }

    public boolean connectSocket(String ip) throws IOException {
        try {
            socket = new Socket(ip, 4445);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        connection();
        return true;
    }

    public void becomeHost(){
        this.isHost = true;
    }

    public boolean getHost(){
        return isHost;
    }

    public String[] getServerIPs(){
        return serverIPs;
    }

    String getIpAddress(){
        return ipAddress;
    }

    private void findServers() throws IOException {
        MulticastSocket listener = new MulticastSocket(4446);
        InetAddress group = InetAddress.getByName("203.0.113.0");
        listener.joinGroup(group);

        DatagramPacket packet;
        for(int i = 0 ; i < 5 ; i++) {
            byte[] buf = new byte[1];
            packet = new DatagramPacket(buf, buf.length);
            listener.receive(packet);

            serverIPs[i] = packet.getAddress().toString();
        }

        listener.leaveGroup(group);
        socket.close();
    }

    private void connection() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inLine;
        while ((inLine = in.readLine()) != null) {
            System.out.println(inLine);
            readServerMessage(inLine);
        }

    }

    private void readServerMessage(String inputString){
        ListIterator<String> in;
        List<String> inputList = Arrays.asList(inputString.split(","));
        in = inputList.listIterator();

        while (in.hasNext()) {
            switch (in.next()) {
                case "condition":
            }

        }
    }
}
