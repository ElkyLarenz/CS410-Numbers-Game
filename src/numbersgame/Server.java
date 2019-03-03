package numbersgame;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

class Server {
    ServerSocket ss;
    private Lobby lobby;
    private String ipAddress;
    private DatagramSocket broadcaster;
    private String hostName;
    private Game game;
    private List<String> playerNames;
    private List<String> hand;
    private List<DataOutputStream> dosList;

    Server(Player host, Game game) throws IOException {
        this.ss = new ServerSocket(3333);
        this.ipAddress = Network.findIPaddress();
        this.lobby = new Lobby();
        this.hostName = host.getPlayerName();
        this.broadcaster = new DatagramSocket();
        this.game = game;

        new broadcastIP().start();
        new startServer().start();
    }

    String getIpAddress() {
        return ipAddress;
    }

    // broadcasts IP of server by broadcasting datagram packet
    // address 224.0.0.0, port 4446
    private class broadcastIP extends Thread {
        public void run() {
            System.out.println("Broadcasting server IP");
            while (lobby.getState()) {
                byte[] buf;
                InetAddress group = null;
                try {
                    group = InetAddress.getByName("230.0.0.0");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                DatagramPacket packet;

                buf = hostName.getBytes();
                packet = new DatagramPacket(buf, buf.length, group, 4445);

                try {
                    broadcaster.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            broadcaster.close();
        }
    }

    // start server, connect clients to client handler
    class startServer extends Thread {
        @Override
        public void run(){
            while (true) {
                Socket s = null;

                try {
                    s = ss.accept();
                    System.out.println("A new client has connected : " + s);

                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dosList.add(dos);

                    System.out.println("create new thread for client");

                    // create new thread
                    Thread t = new ClientHandler(s, dis, dos);

                    t.start();

                } catch (Exception e) {
                    assert s != null;
                    try {
                        s.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    class ClientHandler extends Thread {
        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;

        ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
        }

        @Override
        public void run() {
            String received;
            String toreturn;
            while (true) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                    System.out.println("waiting for client input");
                    while ((received = in.readLine()) != null) {
                        System.out.println(received);
                        readClientMessage(received);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readClientMessage(String inputString) throws IOException {
        ListIterator<String> in;
        List<String> inputList = Arrays.asList(inputString.split(","));
        in = inputList.listIterator();

        while (in.hasNext()) {
            switch (in.next()) {
                case "NAME":
                    updatePlayerNames(in.next());
                    sendPlayerNames();
                    break;
                case "HAND":
                    updatePlayerHand(inputList);
                    sendPlayerHand();
                    break;
            }
        }
    }

    // adds player name to the next open position in the array
    private void updatePlayerNames(String name){
        playerNames.add(name);
    }

    // sends playerNames[] to all clients
    private void sendPlayerNames() throws IOException {
        StringBuilder output = new StringBuilder();

        output.append("NAMES,");
        for (String playerName : playerNames) {
                output.append(playerName).append(",");
        }
        
        sendToAllConnected(output.toString());
    }

    // update player hand (server-side)
    private void updatePlayerHand(List<String> inputList){
        inputList.remove(0);
        hand = inputList;
    }

    // sends hand[] to all clients
    private void sendPlayerHand() throws IOException {
        StringBuilder output = new StringBuilder();

        output.append("HAND,");
        for (String hVal : hand) {
                output.append(hVal).append(",");
        }

        sendToAllConnected(output.toString());
    }

    private void sendToAllConnected(String output) throws IOException {
        for (DataOutputStream dataOutputStream : dosList) {
            dataOutputStream.writeUTF(output);
        }
    }

}
