package numbersgame;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

class Server {
    private ServerSocket ss;
    //private DatagramSocket broadcaster;
    //private String hostName;
    private List<String> playerNames = new ArrayList<>();
    private List<String> hand = new ArrayList<>();
    private List<DataOutputStream> dosList = new ArrayList<>();

    Server() throws IOException {
        this.ss = new ServerSocket(3333);
        //this.hostName = host.getPlayerName();
        //this.broadcaster = new DatagramSocket();

        //new broadcastIP().start();
        new startServer().start();
    }

    // start server, connect clients to client handler
    class startServer extends Thread {
        @Override
        public void run() {
            while (this.isAlive()) {
                Socket s = null;

                try {
                    s = ss.accept();
                    System.out.println("A new client has connected : " + s);

                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dosList.add(dos); // maintain a list of all connected output streams

                    System.out.println("create new thread for client: " + dosList.toString());

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

    // clienthandler - handles connections to clients
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

            while (this.isAlive()) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                    System.out.println("waiting for client input");
                    for (received = in.readLine(); received != null; received = in.readLine()) {
                        System.out.println("SERVER RECEIVED: " + received);
                        readClientMessage(received);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // method that determine what to do based on input string
        private void readClientMessage(String inputString) throws IOException {
            ListIterator<String> in;
            List<String> inputList = new ArrayList<>(Arrays.asList(inputString.split(",")));
            in = inputList.listIterator();

            switch (in.next()) {
                case "NAMES":
                    updatePlayerNames(in.next());
                    sendPlayerNames();
                    break;
                case "HAND":
                    updatePlayerHand(inputList);
                    sendPlayerHand();
                    break;
                case "SETUP":
                    sendInitialHands(in.next());
                    break;
            }
        }
    }

    // sends 2d int array to players for hand setup
    private void sendInitialHands(String in){
        String output = "SETUP," +
                in;
        sendToAllConnected(output);
    }
    // adds player name to the next open position in the array
    private void updatePlayerNames(String name) {
        playerNames.add(name);
    }

    // sends playerNames[] to all clients
    private void sendPlayerNames() throws IOException {
        StringBuilder output = new StringBuilder();

        output.append("NAMES,");
        sendToAllConnected(buildStandardDataList(output, playerNames));
    }

    // update player hand (server-side)
    private void updatePlayerHand(List<String> inputList) {
        inputList.remove(0);
        hand = inputList;
    }

    // sends hand[] to all clients
    private void sendPlayerHand() throws IOException {
        StringBuilder output = new StringBuilder();

        output.append("HAND,");
        sendToAllConnected(buildStandardDataList(output, hand));
    }

    // sends string to all created dataoutputstreams
    private void sendToAllConnected(String output) {
        for (DataOutputStream dataOutputStream : dosList) {
            PrintWriter out = new PrintWriter(dataOutputStream, true);
            out.println(output);
        }
    }

    private String buildStandardDataList(StringBuilder output, List<String> data) {
        for (int i = 0; i < data.size(); i++) {
            output.append(data.get(i));
            if (i != data.size() - 1)
                output.append(",");
        }

        return output.toString();
    }

    // broadcasts IP of server by broadcasting datagram packet
    // address 224.0.0.0, port 4446
/*
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
*/
}
