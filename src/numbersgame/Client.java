package numbersgame;

import java.io.*;
import java.net.*;
import java.util.*;

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

    // connect client to ip address
    // at port 3333
    public void connectSocket(String ip) {
        try {
            socket = new Socket(ip, 3333);
            System.out.println("client connected at IP: " + ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new connection().start();
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

    // threaded class for client connection
    private class connection extends Thread {
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("connection with server initiated");
                String inLine;
                while ((inLine = in.readLine()) != null) {
                    System.out.println("CLIENT RECEIVED: " + inLine);
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
        PrintWriter out = new PrintWriter(outstream, true);
        System.out.println("Sending name: " + name);
        out.println("NAME," + name);
    }

    // send player hand to server
    public void sendHand(int[] hand) throws IOException {
        OutputStream outstream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outstream, true);
        StringBuilder output = new StringBuilder();

        output.append("HAND,");
        for (int i = 0; i < hand.length; i++) {
            output.append(hand[i]);
            if (i != hand.length - 1)
                output.append(",");
        }

        System.out.println("Sending hand: " + output);
        out.println(output.toString());
    }

    // read server input messages
    private void readServerMessage(String inputString) {
        ListIterator<String> in;
        List<String> inputList = new ArrayList<>(Arrays.asList(inputString.split(",")));
        in = inputList.listIterator();

        switch (in.next()) {
            case "NAMES": // case for updating player turn
                updatePlayerNames(inputList);
                break;
            case "HAND":
                updatePlayerHand(inputList);
                break;

        }
    }

    // update the list of player names (client-side)
    private void updatePlayerNames(List<String> inputList) {
        inputList.remove(0);
        String[] names = new String[4];
        names = inputList.toArray(names);
        System.out.println("adding to game: " + Arrays.toString(names));
        game.addPlayer(names);
    }

    // update players hands (client-side)
    private void updatePlayerHand(List<String> inputList) {
        inputList.remove(0);

        int[] hand = new int[inputList.size()];
        Iterator<String> itr = inputList.iterator();
        for (int i = 0; i < hand.length; i++) {
            hand[i] = Integer.parseInt(itr.next());
        }

        game.receiveHand(hand);
    }

    /*
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
*/
}
