package numbersgame;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Client {
    private Boolean isHost;
    private String ipAddress;
    private String[] serverIPs = new String[4];
    private String[] serverNames = new String[4];
    private Socket socket;
    private Game game;

    // client
    Client(Boolean isHost, Game game) throws IOException {
        this.isHost = isHost;
        this.ipAddress = Network.findIPaddress();
        this.game = game;

        if (isHost) {
            connectSocket(Network.findIPaddress());
        }
    }

    public boolean connectSocket(String ip) throws IOException {
        try {
            socket = new Socket(ip, 3333);
            System.out.println("client connected at IP: " + ip);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        new connection().start();
        return true;
    }

    public void becomeHost() {
        this.isHost = true;
    }

    public boolean getHost() {
        return isHost;
    }

    public String[] getServerIPs() {
        return serverIPs;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String[] getServerNames() {
        return serverNames;
    }

    public void findServers() {
        MulticastSocket listener = null;
        try {
            listener = new MulticastSocket(4445);
            listener.setSoTimeout(1000);
            InetAddress group = InetAddress.getByName("230.0.0.0");

            listener.joinGroup(group);
            System.out.println("looking for available servers");

            DatagramPacket packet;
            for (int i = 0; i < 4; i++) {
                byte[] buf = new byte[8];
                packet = new DatagramPacket(buf, buf.length);

                System.out.println("looking for server response " + i);
                try {
                    listener.receive(packet);
                    serverIPs[i] = packet.getAddress().toString();
                    serverNames[i] = new String(buf, 0, packet.getLength());
                } catch (IOException e){
                    System.out.println("server search timeout");
                }

            }

            listener.leaveGroup(group);
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class connection extends Thread {
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("connection with server initiated");
                String inLine;
                while ((inLine = in.readLine()) != null) {
                    System.out.println(inLine);
                    readServerMessage(inLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // send player name to server to create player
    public void createPlayer(String name) throws IOException {
        OutputStream outstream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outstream);

        out.print("NAME");
        out.print(name);
    }

    private void readServerMessage(String inputString) {
        ListIterator<String> in;
        List<String> inputList = Arrays.asList(inputString.split(","));
        in = inputList.listIterator();

        while (in.hasNext()) {
            switch (in.next()) {
                case "NAME":
                    game.addPlayer(in.next());
            }
        }
    }
}
